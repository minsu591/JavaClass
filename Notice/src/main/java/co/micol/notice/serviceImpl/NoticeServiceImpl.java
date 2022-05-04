package co.micol.notice.serviceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.micol.notice.dao.DataSource;
import co.micol.notice.service.NoticeService;
import co.micol.notice.service.NoticeVO;

public class NoticeServiceImpl implements NoticeService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn; // = dao.getConnection();
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<NoticeVO> noticeSelectList() { // 전체 조회
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		NoticeVO vo;
		String sql = "SELECT * FROM NOTICE";
		try {
			conn = dao.getConnection(); // connection객체 열기
			psmt = conn.prepareStatement(sql); // conn을 통해서 psmt 열기
			rs = psmt.executeQuery(); // select구문이기때문에 rs로 psmt로 executeQuery
			while (rs.next()) { // 한 세트씩 추가
				vo = new NoticeVO();
				vo.setId(rs.getInt("id"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setWdate(rs.getDate("wdate"));
				vo.setHit(rs.getInt("hit"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // Exception 발생 후에도 close하고 나오게
			close(); // 연결된 커넥션 끊어주기
		}
		return list;
	}

	@Override
	public NoticeVO noticeSelect(NoticeVO vo) {// 한 개의 글 검색
		String sql = "SELECT * FROM NOTICE WHERE ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getId()); // 첫 번째 물음표에 값 담아주기
			rs = psmt.executeQuery();
			if (rs.next()) { // 한 글 보기니까 if문에 담기, subject포함
				vo = new NoticeVO();
				vo.setId(rs.getInt("id"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setSubject(rs.getString("subject"));
				vo.setWdate(rs.getDate("wdate"));
				vo.setHit(rs.getInt("hit"));
				hitUpdate(vo.getId()); // 조회수 상승
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	@Override
	public int noticeInsert(NoticeVO vo) {
		int n = 0;
		String sql = "INSERT INTO NOTICE VALUES(B_ID.NEXTVAL,?,?,?,SYSDATE,0)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getWriter());
			psmt.setString(2, vo.getTitle());
			psmt.setString(3, vo.getSubject());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public int noticeUpdate(NoticeVO vo) {
		int n = 0;
		String sql = "UPDATE NOTICE SET TITLE = ?, SUBJECT = ? WHERE ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getSubject());
			psmt.setInt(3, vo.getId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public int noticeDelete(NoticeVO vo) {
		int n = 0;
		String sql = "DELETE FROM NOTICE WHERE ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return n;
	}

	private void close() { // open한 순서의 반대로
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void hitUpdate(int id) { // 출력 없이 내부에서 하는거니까 psmt만 사용
		String sql = "UPDATE NOTICE SET HIT = HIT+1 WHERE ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
