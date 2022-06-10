package co.mia.bootstraptest.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.mia.bootstraptest.comm.Command;
import co.mia.bootstraptest.home.Home;
import co.mia.bootstraptest.notice.command.AjaxSearchList;
import co.mia.bootstraptest.notice.command.noticeInput;
import co.mia.bootstraptest.notice.command.noticeInputForm;
import co.mia.bootstraptest.notice.command.noticeList;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String,Command> map = new HashMap<String, Command>();
       

    public FrontController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		map.put("/home.do", new Home());
		map.put("/noticeInputForm.do", new noticeInputForm()); //게시글 입력폼
		map.put("/noticeInput.do", new noticeInput());
		map.put("/noticeList.do", new noticeList());
		map.put("/ajaxSearchList.do", new AjaxSearchList());
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		
		//경로 (.do~)
		Command command = map.get(page);
		String viewPage = command.exec(request, response);
		
		//(실제 return된 경로)
		if(!viewPage.endsWith(".do") && !viewPage.isEmpty()) {
			if(viewPage.startsWith("ajax:")) {
				response.setContentType("text/html; charset=utf-8");
				viewPage = viewPage.substring(5);
				response.getWriter().append(viewPage);
				return;
			}else {
//				viewPage = viewPage+".tiles";
				viewPage = "/WEB-INF/views/"+ viewPage+".jsp";
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
