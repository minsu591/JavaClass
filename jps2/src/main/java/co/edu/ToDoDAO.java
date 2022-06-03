package co.edu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToDoDAO extends DAO{
	//전체 조회
	public List<ToDoVO> selectToDoList(){
		List<ToDoVO> list = new ArrayList<ToDoVO>();
		connect();
		String sql = "SELECT * FROM TODOLIST";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ToDoVO todo = new ToDoVO();
				todo.setNo(rs.getInt("no"));
				todo.setTitle(rs.getString("title"));
				todo.setChecked(rs.getInt("checked"));
				list.add(todo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
	}
	
	//하나 추가
	public ToDoVO insertToDoList(ToDoVO todo){
		connect();
		String sql2 = "select todo_seq.nextval as no from dual";
		int no = -1;
		try {
			psmt = conn.prepareStatement(sql2);
			rs = psmt.executeQuery();
			if(rs.next()) {
				no = rs.getInt("no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		todo.setNo(no);

		String sql = "INSERT INTO TODOLIST VALUES(?,?,0)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, no);
			psmt.setString(2, todo.getTitle());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return todo;
	}
	//하나 삭제
	public void deleteToDoList(ToDoVO todo){
		connect();
		String sql = "DELETE TODOLIST WHERE NO = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, todo.getNo());
			psmt.executeUpdate();
			System.out.println("1건 삭제완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	//check변화
	public void updateToDoList(ToDoVO todo) {
		connect();
		String sql = "update todolist set checked=? where no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			if(todo.getChecked()==0) {
				psmt.setInt(1, 1);
			}else {
				psmt.setInt(1, 0);
			}
			psmt.setInt(2, todo.getNo());
			psmt.executeUpdate();
			System.out.println("1건 삭제완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
}
