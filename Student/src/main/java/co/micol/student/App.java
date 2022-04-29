package co.micol.student;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import co.micol.student.dto.StudentVO;
import co.micol.student.service.StudentService;
import co.micol.student.serviceImpl.StudentServiceImpl;

public class App 
{
    public static void main( String[] args )
    {
    	StudentService dao = new StudentServiceImpl();
    	List<StudentVO> list = new ArrayList<StudentVO>();
    	//전체 리스트 조회
    	list= dao.selectListStudent();
    	for(StudentVO vo : list) {
    		vo.toString();
    	}
//    	System.out.println("===================");
//    	//한 명 조회
//    	StudentVO student = new StudentVO();
//    	student.setStudentId("park@abc.com");
//    	student = dao.selectStudent(student);
//    	student.toString();
//    	System.out.println("===================");

    	//한 명 삽입 -> 여기서 하는건 자동 commit
//    	StudentVO vo = new StudentVO();
//    	vo.setStudentId("leeto@abc.com");
//    	vo.setName("이승주");
//    	vo.setBirthday(Date.valueOf("1999-07-15")); //자바 sql 데이트에서는 문자열을 데이트 타입으로 변환해주는게 Date.valueOf
//    	vo.setMajor("경제학과");
//    	vo.setAddress("대구광역시 중구 중앙대로 21");
//    	vo.setTel("010-2222-3333");
//    	int n = dao.updateStudent(vo);
//    	if(n!=0) {
//    		System.out.println("정상적으로 추가되었습니다.");
//    	}else {
//    		System.out.println("추가에 실패했습니다.");
//    	}
    	
    	
    	
        
    }
}
