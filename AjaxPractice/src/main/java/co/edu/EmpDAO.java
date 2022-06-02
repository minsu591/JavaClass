package co.edu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO extends DAO{
	
	//전체 리스트 가져오기
	public List<Employee> selectEmpList(){
		connect();
		List<Employee> empList = new ArrayList<Employee>();
		String sql = "SELECT * FROM EMP_TABLE ORDER BY 1";
		Employee emp;
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				emp=new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setEmail(rs.getString("email"));
				empList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		System.out.println("Select 완료!");
		return empList;
	}
	
	//Insert
	public Employee insertEmp(Employee emp) {
		connect();
		String sql = "INSERT INTO EMP_TABLE(employee_id,first_name,last_name,email,hire_date,job_id) VALUES(?,?,?,?,?,?)";
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
	//Modify
	public Employee modifyEmp(Employee emp) {
		connect();
		String sql = "update emp_table set first_name=?, last_name=?, email=?, hire_date=? where employee_id=?";
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
	//Delete
	public String deleteEmp(Employee emp) {
		connect();
		String sql = "delete emp_table where employee_id=?";
		try {
			psmt = conn.prepareStatement(sql);	
			psmt.setInt(1, emp.getEmployeeId());
			psmt.executeUpdate();
			System.out.println("delete 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return null;
	}
	
	//1건 조회

}
