package co.micol.prj.listTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import co.micol.prj.dto.StudentVO;

public class ListTest {
	public void listTest() {
		//배열형의 리스트를 만들겠다
		List<String> list = new ArrayList<String>();
		list.add("홍길동");
		list.add("박기자");
		list.add("이승리");
		
		for(String str : list) {
			System.out.println(str);
		}
	}

	public void studentList() {
		List<StudentVO> students = new ArrayList<StudentVO>();
		//한 명 학생의 데이터
		StudentVO student = new StudentVO();
		student.setStudentId("202204001");
		student.setName("홍길동");
		student.setAge(20);
		student.setBirthDay(Date.valueOf("2002-03-01")); //String 타입을 Date타입으로 변환
		student.setGender("F");
		//리스트에 삽입
		students.add(student);
		
		student = new StudentVO(); //student라는 인스턴스를 초기화
		student.setStudentId("202204002");
		student.setName("박기자");
		student.setAge(20);
		student.setBirthDay(Date.valueOf("2002-04-01")); //String 타입을 Date타입으로 변환
		student.setGender("W");
		students.add(student);
		
		for(StudentVO vo : students) {
			vo.toString();
		}
	}
}
