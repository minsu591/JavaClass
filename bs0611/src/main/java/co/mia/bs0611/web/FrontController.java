package co.mia.bs0611.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.mia.bs0611.body.command.About;
import co.mia.bs0611.body.command.Contact;
import co.mia.bs0611.body.command.Portfolio;
import co.mia.bs0611.body.command.Service;
import co.mia.bs0611.body.command.Team;
import co.mia.bs0611.comm.Command;
import co.mia.bs0611.home.Home;
import co.mia.bs0611.notice.command.NoticeForm;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();
       

    public FrontController() {
        super();
    }


	public void init(ServletConfig config) throws ServletException {
		map.put("/home.do", new Home());
		map.put("/service.do", new Service());
		map.put("/portfolio.do", new Portfolio());
		map.put("/about.do", new About());
		map.put("/team.do", new Team());
		map.put("/contact.do", new Contact());
		
		map.put("/noticeForm.do", new NoticeForm()); //form 통해서 들어온 notice 처리
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		
		Command command = map.get(page);
		String viewPage = command.exec(request, response);
		System.out.println(viewPage);
		
		if(!viewPage.endsWith(".do") && !viewPage.isEmpty()) {
			viewPage = viewPage+".tiles";
//			viewPage = "/WEB-INF/views/"+viewPage+".jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}
