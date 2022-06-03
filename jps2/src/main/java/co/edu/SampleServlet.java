package co.edu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SampleServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		System.out.println("init 호출...");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//응답정보의 content-type을 설정해주어야함
		//한글이 포함되어있으면 charset=utf-8 포함
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		//요청 정보에도 한글이 포함되어있다면
		req.setCharacterEncoding("utf-8");

		if (req.getMethod().equals("GET")) { // get방식의 요청이 들어오면 어떻게 하겠다
			System.out.println("GET 요청입니다.");
		} else if (req.getMethod().equals("POST")) { // get방식의 요청이 들어오면 어떻게 하겠다
			System.out.println("POST 요청입니다.");
		}

		String name = req.getParameter("name");
		String age = req.getParameter("age");

		PrintWriter out = resp.getWriter();
		out.print("<h3>요청 파라미터 (name) : " + name + "</h3>");
		out.print("<h3>요청 파라미터 (age) : " + age + "</h3>");
		
		out.close(); //사용 다하고 나면 close해주기
	}

//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doGet(req, resp);
//	}
//	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doPost(req, resp);
//	}

	@Override
	public void destroy() {
		System.out.println("destroy 호출...");
	}
}
