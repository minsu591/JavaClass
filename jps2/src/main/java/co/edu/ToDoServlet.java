package co.edu;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/todo")
public class ToDoServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//목록 조회
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");
		
		ToDoDAO dao = new ToDoDAO();
		List<ToDoVO> list = dao.selectToDoList();
		Gson gson = new GsonBuilder().create();
		resp.getWriter().print(gson.toJson(list));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		
		String cmd = req.getParameter("cmd");
		ToDoDAO dao = new ToDoDAO();
		ToDoVO vo = new ToDoVO();
		Gson gson = new GsonBuilder().create();
		
		//목록 추가
		if(cmd.equals("insert")) {
			String title =req.getParameter("title");
			vo.setTitle(title);
			ToDoVO reToDo = dao.insertToDoList(vo);
			resp.getWriter().print(gson.toJson(reToDo));
		}
		//목록 삭제
		if(cmd.equals("delete")) {
			int no = Integer.parseInt(req.getParameter("no"));
			vo.setNo(no);
			dao.deleteToDoList(vo);
		}
		//체크 변화
		if(cmd.equals("check")) {
			int no = Integer.parseInt(req.getParameter("no"));
			vo.setNo(no);
			dao.updateToDoList(vo);
		}
	}
}
