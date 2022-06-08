package co.mia.study.student.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.mia.study.comm.Command;
import co.mia.study.student.service.StudentService;
import co.mia.study.student.serviceImpl.StudentServiceImpl;
import co.mia.study.student.vo.StudentVO;

public class Login implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		StudentService dao = new StudentServiceImpl();
		HttpSession session = request.getSession(); //세션객체 활용을 위해
		
		StudentVO vo = new StudentVO();
		vo.setStudentId(request.getParameter("studentId"));
		vo.setPassword(request.getParameter("password"));
		vo = dao.studentSelect(vo);
		if(vo != null) { //vo가 null이 아니면, 로그인이 성공했으면
			session.setAttribute("studentId", vo.getStudentId()); //아이디를 세션에 담음
			session.setAttribute("name", vo.getName()); //이름을 세션에 담음
			session.setAttribute("author", vo.getAuthor()); //권한을 세션에 담음
			request.setAttribute("message", vo.getName()+"님 환영합니다.");
		}else {
			request.setAttribute("message", "아이디 또는 패스워드가 일치하지 않습니다.");
		}
		return "student/login";
	}

}
