package co.mia.prj.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 모든 요청을 받아서 처리하는 곳
 */
@WebServlet("*.do") //모든 요청은 얘가 다 받음
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();
       
    public FrontController() {
        super();
    }

	/**
	 * 요청과 처리 명령을 연결하는 부분
	 */
	public void init(ServletConfig config) throws ServletException {
		map.put("/test.do",new TestCommand()); //test.do로 들어오면 TestCommand로 돌려줌
		map.put("/memberList.do", new MemberListCommand());
	}

	/**
	 * 여기서 들어온 요청을 분석하고 명령을 실행해서 결과를 돌려보내주는 곳
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //한글 깨짐 방지
		
		String uri = request.getRequestURI(); //요청 URI 구함
		String contextPath = request.getContextPath(); //루트 디렉토리 정보
		String page = uri.substring(contextPath.length());
		
		Command command = map.get(page); //(key값 넣으면 value값 리턴) 실행할 명령객체를 찾음
		String viewPage = command.exec(request, response); //명령을 실행하고 결과를 돌려받음 (보여줄 페이지가 viewPage에 담김)
		
		if(!viewPage.endsWith(".do") && !viewPage.equals(null)) { //돌려받은 결과값에 .do가 포함되어있지 않고 페이지가 비어있지 않으면
			//돌려줄 페이지를 이 과정을 통해 서버에서 접근할 수 있도록 도와줌
			//viewResolve
			viewPage = "/WEB-INF/jsp/" + viewPage + ".jsp";
		}
		
		//결과 페이지를 돌려줌
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage); //돌려줄 페이지 실어주면됨
		dispatcher.forward(request, response); //내가 request 객체를 주면, request 객체를 요청할 때 같이 실어서 보내줌.
		
	}

}
