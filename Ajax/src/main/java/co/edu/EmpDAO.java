package co.edu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDAO extends DAO{
	
	//리스트
	public List<Employee> empList() {
		connect();
		String sql = "SELECT * FROM EMP_TEMP ORDER BY 1";
		List<Employee> list = new ArrayList<Employee>();
		Employee emp;
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
	}
	//입력
	public Employee empInsert(Employee emp) {
		connect();
		String sql = "INSERT INTO EMP_TEMP(employee_id,first_name,last_name,email,hire_date,job_id) VALUES(?,?,?,?,?,?)";
		String seqSql = "SELECT EMPLOYEES_SEQ.NEXTVAL FROM DUAL";
		int nextSeq = -1;
		try {
			//select니까 rs로 받아오기
			psmt = conn.prepareStatement(seqSql);
			rs = psmt.executeQuery();
			if(rs.next()) {
				nextSeq = rs.getInt(1);
			}
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, nextSeq);
			psmt.setString(2, emp.getFirstName());
			psmt.setString(3, emp.getLastName());
			psmt.setString(4, emp.getEmail());
			psmt.setString(5, emp.getHireDate());
			psmt.setString(6, emp.getJobId());
			
			int n = psmt.executeUpdate();
			System.out.println(n + "건 입력 완료");
			emp.setEmployeeId(nextSeq);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return emp;
	}
	
	//수정
	public Employee empUpdate(Employee emp) {
		connect();
		String sql = "update emp_temp set first_name=?, last_name=?, email=?, hire_date=? where employee_id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getFirstName());
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getHireDate());
			psmt.setInt(5, emp.getEmployeeId());
			int r = psmt.executeUpdate();
			System.out.println(r+"건 수정");
			if(r>0) {
				return emp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return null;
		
	}
	//삭제
	public void deleteEmp(Employee emp) {
		connect();
		String sql = "delete emp_temp where employee_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, emp.getEmployeeId());
			int n = psmt.executeUpdate();
			if(n!=-1) {
				System.out.println("1건 삭제");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	
	//한 건 조회
	
	//부서별 인원 차트 만들기 : 부서명 = 인원 형식으로 출력
	public Map<String, Integer> getMemberByDept() {
		Map<String, Integer> map = new HashMap<String,Integer>();
		connect();
		String sql = "select d.department_name, count(1) as cnt from employees e, departments d "
				+ "where e.department_id = d.department_id group by department_name order by 1";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				map.put(rs.getString("department_name"), rs.getInt("cnt")); //칼럼 이름
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return map;
	}
	
	//캘린더
	//캘린더 조회
	public List<ScheduleVO> selectSchedule(){
		connect();
		List<ScheduleVO> slist = new ArrayList<ScheduleVO>();
		String sql = "select * from schedules";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ScheduleVO sc = new ScheduleVO();
				sc.setTitle(rs.getString("title"));
				sc.setStart(rs.getString("start_date"));
				sc.setEnd(rs.getString("end_date"));
				slist.add(sc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return slist;
	}
	
	//캘린더 삽입 (스케줄 등록)
	public void insertSchedule(ScheduleVO sched) {
		connect();
		String sql = "insert into schedules values(?,?,?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sched.getTitle());
			psmt.setString(2, sched.getStart());
			psmt.setString(3, sched.getEnd());
			int n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	//캘린더 삭제 (스케줄 삭제)
	public void deleteSchedule(ScheduleVO sched) {
		connect();
		String sql = "delete from schedules where title=? and start_date = ? and end_date = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sched.getTitle());
			psmt.setString(2, sched.getStart());
			psmt.setString(3, sched.getEnd());
			int n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
}
