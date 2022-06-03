package co.edu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/formServlet")
public class FormServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet()");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		String user = req.getParameter("user");
		String pass = req.getParameter("passwd");
		String cmd = req.getParameter("cmd"); //search
		
		MessageDAO dao = new MessageDAO();
		System.out.println(cmd);
		if(cmd.equals("search")) {
			MessageVO msg = dao.getMessage(user, pass);
			out.print("<h3>메세지 정보</h3>"); //html => 필요한 데이터만 넣어도 DOM이 페이지를 만들어줌
			out.print("<p>메세지 내용 : "+msg.getContent()+"</p>");
			out.print("<p>작성자 : "+msg.getWriter()+"</p>");			
		}else if(cmd.equals("insert")) {
			out.print("<p>입력한 user정보 : "+user+"</p>");
			out.print("<p>입력한 passWord정보 : "+pass+"</p>");	
		}
		
		//multipart request
		
		
	}

}
