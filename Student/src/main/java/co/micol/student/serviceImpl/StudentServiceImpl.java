package co.micol.student.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.micol.student.dao.DataSource;
import co.micol.student.dto.StudentVO;
import co.micol.student.service.StudentService;

public class StudentServiceImpl implements StudentService {
	private DataSource dataSource = DataSource.getInstance();
	private Connection conn = dataSource.getConnection(); // 커넥션 연결
	private PreparedStatement psmt; // 명령 구문 실행, sql 명령 실행
	private ResultSet rs; // select 결과 담음

	@Override
	public List<StudentVO> selectListStudent() {
		// 전체 학생 리스트 가져오기
		List<StudentVO> students = new ArrayList<StudentVO>();
		StudentVO student;
		String sql = "SELECT * FROM STUDENT";
		try { //DB가 꺼져있을 수도 있으니까 try-catch 구문에 담기
			psmt = conn.prepareStatement(sql); //커넥션 객체를 통해 sql문을 보내고 명령 실행
			rs= psmt.executeQuery(); //sql을 실행하고 결과 받아오기
			while(rs.next()) { //ResultSet의 문장을 읽을 때 .next() -> 값이 없으면 EOF -> false
				student = new StudentVO(); //있는 곳 값에 다시 담지 않게 초기화
				student.setStudentId(rs.getString("student_id"));
				student.setName(rs.getString("name"));
				student.setBirthday(rs.getDate("birthday"));
				student.setMajor(rs.getString("major"));
				student.setAddress(rs.getString("address"));
				student.setTel(rs.getString("tel"));
				students.add(student);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return students;
	}

	@Override
	public StudentVO selectStudent(StudentVO student) { //한 명 조회
		StudentVO vo = new StudentVO();
		//물음표 쓰는거 주의 -> 맞아 그거야
		String sql = "SELECT * FROM STUDENT WHERE STUDENT_ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			//첫 번째 물음표에 student.getStudentId()를 넣겠다.
			psmt.setString(1, student.getStudentId());
			rs = psmt.executeQuery(); //select는 query,
			if(rs.next()) {
				vo.setStudentId(rs.getString("student_Id"));
				vo.setName(rs.getString("name"));
				vo.setBirthday(rs.getDate("birthday"));
				vo.setMajor(rs.getString("major"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int insertStudent(StudentVO student) { //한 명의 데이터 인서트
		int n = 0; //성공적으로 들어가면 1, 안들어가면 0을 출력
		String sql = "INSERT INTO STUDENT VALUES(?,?,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, student.getStudentId());
			psmt.setString(2, student.getName());
			psmt.setDate(3, student.getBirthday());
			psmt.setString(4, student.getMajor());
			psmt.setString(5, student.getAddress());
			psmt.setString(6, student.getTel());
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int updateStudent(StudentVO student) { //major, address, tel 중에 null값이 있으면 오류 발생
		int n = 0;
		String sql = "UPDATE STUDENT SET MAJOR = ?, ADDRESS = ?,"
				+ " TEL = ? WHERE STUDENT_ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, student.getMajor());
			psmt.setString(2, student.getAddress());
			psmt.setString(3, student.getTel());
			psmt.setString(4, student.getStudentId());
			n= psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int deleteStudent(StudentVO student) {
		int n = 0;
		String sql = "DELETE FROM STUDENT WHERE STUDENT_ID= ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, student.getStudentId());
			n = psmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return n;
	}

}
