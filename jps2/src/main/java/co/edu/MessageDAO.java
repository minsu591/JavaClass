package co.edu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO extends DAO{
	
	//FormServlet에 사용할 메소드
	public MessageVO getMessage(String user, String pass) {
		MessageVO msg = new MessageVO();
		msg.setMsgId(100);
		msg.setContent("하잉");
		msg.setWriter("admin");
		msg.setCreateDate("2022-06-01");
		
		return msg;
	}
	
	
	//메세지 전체조회
	public List<MessageVO> messageList(){
		List<MessageVO> msgList = new ArrayList<MessageVO>();
		connect();
		//5분 전의 데이터만 가져오겠다
		String sql = "select * from messages where create_date >= sysdate-(1/24/60)*30 order by 1";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MessageVO msg = new MessageVO();
				msg.setMsgId(rs.getInt("msg_id"));
				msg.setWriter(rs.getString("writer"));
				msg.setCreateDate(rs.getString("create_date"));
				msg.setContent(rs.getString("content"));
				msgList.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return msgList;
	}
	
	public void insertMessage(MessageVO msg) { //content, writer값만 필요
		List<MessageVO> msgList = new ArrayList<MessageVO>();
		connect();
		String sql = "insert into messages values(mess_seq.nextval,?,?,sysdate)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, msg.getContent());
			psmt.setString(2, msg.getWriter());
			int n = psmt.executeUpdate();
			if(n!=-1) {
				System.out.println("메세지 전송 완료");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
