package co.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/message")
public class MessageServlet extends HttpServlet{
	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");
		MessageDAO dao = new MessageDAO();
		
		Gson gson = new GsonBuilder().create();
		PrintWriter out = resp.getWriter();
		
		if(req.getMethod().equals("GET")) {//get방식이면 select
			List<MessageVO> list = dao.messageList();
			out.print(gson.toJson(list));
			
		}else if(req.getMethod().equals("POST")) { //post방식이면 insert
			MessageVO msg = new MessageVO();
			System.out.println(msg);
			msg.setContent(req.getParameter("content"));
			msg.setWriter(req.getParameter("writer"));
			dao.insertMessage(msg);
		}
		out.close();
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
	
	
}
