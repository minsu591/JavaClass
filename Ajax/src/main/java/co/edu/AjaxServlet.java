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

/**
 * Servlet implementation class AjaxServlet
 */
@WebServlet({ "/AjaxServlet", "/ajax.do" })
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath()); //프로젝트 상위의 정보를 텍스트 형식으로 출력

		response.setCharacterEncoding("utf-8"); // 응답 정보에 한글 포함시 처리
		response.setContentType("text/html;charset=utf-8");
	
		String job = request.getParameter("job"); // 요청 정보에서 job이라는 파라메터를 읽어오겠다
		PrintWriter out = response.getWriter(); // 출력스트림

		if (job.equals("html")) {
			out.print("<h3>Ajax page입니다.</h3>");
			out.print("<a href='index.html'>첫 페이지로</a>");
		} else if (job.equals("json")) {
//			out.print("<h3>JSON page입니다.</h3>");
//			out.print("<a href='index.html'>첫 페이지로</a>");

			// [{fname : ?, lname : ?},{},{},{}]
			String json = "[";
			EmpDAO dao = new EmpDAO();
			List<Employee> list = dao.empList();
//			for(int i =0;i<list.size();i++) {
//				json+= "{\"fname\":\""+list.get(i).getFirstName()+"\"}";
//				if(i!=list.size()-1) {
//					json +=",";
//				}
//			}
//			json += "]";
			Gson gson = new GsonBuilder().create();
			out.print(gson.toJson(list));
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //요청할 때 변환
		response.setCharacterEncoding("utf-8"); //요청 받을 때 변환
		response.setContentType("text/html;charset=utf-8");
		
		String cmd = request.getParameter("cmd");
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String hdate = request.getParameter("hdate");
		String job = request.getParameter("job");
		String empId = request.getParameter("empId");
		
		
		Employee emp = new Employee();
		emp.setFirstName(fname);
		emp.setLastName(lname);
		emp.setHireDate(hdate);
		emp.setEmail(email);
		emp.setJobId(job);
		
		if(cmd.equals("insert")) {
			EmpDAO dao = new EmpDAO();
			dao.empInsert(emp);
			
		}else if(cmd.equals("update")) {
			emp.setEmployeeId(Integer.parseInt(empId));
			EmpDAO dao = new EmpDAO();
			
			if(dao.empUpdate(emp) == null) {
				System.out.println("error");
			}else {
				dao.empUpdate(emp);
			}
			
		}else if(cmd.equals("delete")) {
			emp.setEmployeeId(Integer.parseInt(empId));
			EmpDAO dao = new EmpDAO();
			dao.deleteEmp(emp);
			
		}
		//null일 때 
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(emp));
		
	}

}
