package co.mia.study.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.mia.study.comm.Command;
import co.mia.study.home.HomeCommand;
import co.mia.study.notice.command.NoticeSelectList;
import co.mia.study.notice.command.NoticeSelectOne;
import co.mia.study.student.command.Login;
import co.mia.study.student.command.LoginForm;
import co.mia.study.student.command.Logout;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();
       
    public FrontController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		map.put("/home.do",new HomeCommand());
		map.put("/noticeSelectList.do", new NoticeSelectList()); //공지사항
		map.put("/noticeSelectOne.do", new NoticeSelectOne()); //세부내역보기
		map.put("/loginForm.do", new LoginForm()); //로그인 페이지
		map.put("/login.do", new Login()); //로그인 완료 후 페이지
		map.put("/logout.do", new Logout()); //로그아웃 처리
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI(); //도메인 제외하고 전부
		String contextPath = request.getContextPath(); //첫번째 디렉토리
		String page = uri.substring(contextPath.length()); //실제 요청한거
		
		Command command = map.get(page);
		String viewPage = command.exec(request, response);
		
		if(!viewPage.endsWith(".do") && !viewPage.isEmpty()) { //viewResolve
//			viewPage = "/WEB-INF/views/"+viewPage+".jsp";
			viewPage = viewPage + ".tiles";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
