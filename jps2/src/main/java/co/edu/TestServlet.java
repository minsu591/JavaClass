package co.edu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//annotation을 사용해서
@WebServlet("/testServ") //이렇게 사용하면 web.xml에 따로 url 정보를 입력하지 않아도 됨
public class TestServlet extends HttpServlet{
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/xml;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		//json 형태의 데이터
		//[{"name":"홍길동","age":10},{"name":"박민수","age":14}]
//		out.print("[{\"name\":\"홍길동\",\"age\":10},{\"name\":\"박민수\",\"age\":14}]");
		
		//xml형태의 데이터
		//<data><name>홍길동</name><age>10</age><name>박민수</name><age>17</age></data>
		out.print("<data><name>홍길동</name><age>10</age><name>박민수</name><age>17</age></data>");
	}
}
