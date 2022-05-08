package co.mia.farm.game.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.mia.farm.LoginMenu;
import co.mia.farm.dao.DataSource;

public class ItemServiceImpl implements ItemService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public int itemInsert(String itemId, String itemName, int itemStatus) {
		int n = 0;
		String sql = "INSERT INTO ITEMS VALUES(?,?,?,?,DEFAULT)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, LoginMenu.loginAccount.getAccId());
			psmt.setString(2, itemId);
			psmt.setString(3, itemName);
			psmt.setInt(4, itemStatus);
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}
	
	
	
	@Override
	public List<ItemVO> itemSelect() {
		List<ItemVO> haveItems = new ArrayList<ItemVO>();
		ItemVO vo;
		String sql = "SELECT * FROM ITEMS WHERE ACC_ID = ? and item_cnt = 0";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, LoginMenu.loginAccount.getAccId());
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new ItemVO();
				vo.setItemID(rs.getString("item_id"));
				vo.setItemStatus(rs.getInt("item_status"));
				haveItems.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return haveItems;
	}

	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ItemVO itemCheck(String itemName) {
		ItemVO item = new ItemVO();
		String sql = "SELECT * FROM ITEMS WHERE ACC_ID = 'ADMIN' AND ITEM_NAME = '감자'";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, LoginMenu.loginAccount.getAccId());
			psmt.setString(2, itemName);
			rs = psmt.executeQuery();
			if(rs.next()) {
				item.setItemName(rs.getString("item_name"));
				item.setItemID(rs.getString("item_id"));
				item.setItemStatus(rs.getInt("item_status"));
				item.setItemCnt(rs.getInt("item_cnt"));			
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return item;
	}



	@Override
	public int itemUpdate(String itemName, int itemCnt) { //아이템 정보 수정 => 갯수 변경
		int n =0;
		String sql = "UPDATE ITEMS SET ITEM_CNT = ? WHERE ACC_ID = ? AND ITEM_NAME = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, itemCnt);
			psmt.setString(2, LoginMenu.loginAccount.getAccId());
			psmt.setString(3, itemName);
			n = psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}


}
