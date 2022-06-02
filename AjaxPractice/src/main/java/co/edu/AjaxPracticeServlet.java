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
 * Servlet implementation class AjaxPracticeServlet
 */
@WebServlet({"/AjaxPracticeServlet", "/ajaxPractice.do"})
public class AjaxPracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxPracticeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String job = request.getParameter("job"); //job 정보 받아오기
		PrintWriter out = response.getWriter(); //응답 스트림에 텍스트를 기록하기 위함
		
		if(job.equals("html")) {//form을 통해 들어온 job의 값이 html이라면 (링크를 통해 들어오면 html)
			out.print("<h3>job이 html인 Ajax Page입니다.</h3>");
			out.print("<a href='index.html'>첫 페이지</a>");
		}else if(job.equals("json")) {//job이 json이라면 (form에서는 이게 기본)
//			out.print("<h3>job이 json인 Ajax Page입니다.</h3>");
			EmpDAO dao = new EmpDAO();
			List<Employee> empList = dao.selectEmpList();
			Gson gson = new GsonBuilder().create();
			out.print(gson.toJson(empList));
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
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
			dao.insertEmp(emp);
		}else if(cmd.equals("update")) {
			emp.setEmployeeId(Integer.parseInt(empId));
			EmpDAO dao = new EmpDAO();
			if(dao.modifyEmp(emp)==null) {
				System.out.println("error");
			}else {
				dao.modifyEmp(emp);
			}
		}else if(cmd.equals("delete")) {
			emp.setEmployeeId(Integer.parseInt(empId));
			EmpDAO dao = new EmpDAO();
			dao.deleteEmp(emp);
		}
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(emp));
	}

}
