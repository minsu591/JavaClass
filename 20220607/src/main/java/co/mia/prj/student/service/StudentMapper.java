package co.mia.prj.student.service;

import java.util.List;

import co.mia.prj.student.vo.StudentVO;

public interface StudentMapper {
	List<StudentVO> studentSelectList(); //전체리스트
	StudentVO studentSelect(StudentVO vo); //한 명 조회
	int studentInsert(StudentVO vo); //삽입
	int studentUpdate(StudentVO vo); //변경
	int studentDelete(StudentVO vo); //삭제
}
