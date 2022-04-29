package co.micol.student.service;

import java.util.List;

import co.micol.student.dto.StudentVO;

public interface StudentService {
	List<StudentVO> selectListStudent(); //학생정보를 리스트에 받아
	StudentVO selectStudent(StudentVO student); //학생 한 명 목록
	int insertStudent(StudentVO student); //한 명 추가
	int updateStudent(StudentVO student); //한 명 수정
	int deleteStudent(StudentVO student); //한 명 삭제
	

}
