package co.mia.prj.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.mia.prj.student.service.StudentService;
import co.mia.prj.student.serviceImpl.StudentServiceImpl;
import co.mia.prj.student.vo.StudentVO;

public class MemberListCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
//		//DB 처리하는 부분 작업
//		//보여줄 페이지에 값을 싣고
//		request.setAttribute("name", "홍길동");
//		request.setAttribute("id", "micol");
//		request.setAttribute("password", "1234");
//		
//		return "member/member";
		
		StudentService dao = new StudentServiceImpl();
		List<StudentVO> students = new ArrayList<StudentVO>();
		students = dao.studentSelectList();
		
		request.setAttribute("students", students); //실행내역 객체 담고
		return "member/member";
	}

}
