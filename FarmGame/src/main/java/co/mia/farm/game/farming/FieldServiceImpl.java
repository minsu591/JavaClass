package co.mia.farm.game.farming;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.mia.farm.LoginMenu;
import co.mia.farm.dao.DataSource;
import co.mia.farm.game.print.ConsolePrintService;

public class FieldServiceImpl implements FieldService{
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	
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
	public List<InFieldVO> fieldSelect() { //필드 찾아오기
		List<InFieldVO> fields = new ArrayList<InFieldVO>();
		InFieldVO field;
		String sql = "SELECT * FROM IN_FIELD WHERE ACC_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, LoginMenu.loginAccount.getAccId());
			rs = psmt.executeQuery();
			while(rs.next()) {
				field = new InFieldVO();
				field.setFieldX(rs.getInt("field_x"));
				field.setFieldY(rs.getInt("field_y"));
				field.setItemId(rs.getInt("item_id"));
				fields.add(field);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return fields;
	}

	@Override
	public int fieldUpdate(InFieldVO myField, int newId) {
		int n = 0;
		String sql = "UPDATE IN_FIELD SET ITEM_ID = ? WHERE ACC_ID = ? AND FIELD_X = ? AND FIELD_Y =?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, newId);
			psmt.setString(2, LoginMenu.loginAccount.getAccId());
			psmt.setInt(3, myField.getFieldX());
			psmt.setInt(4, myField.getFieldY());
			n = psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}
	
	@Override
	public int fieldUpdateZero(InFieldVO myField) {
		int n = 0;
		String sql = "UPDATE IN_FIELD SET ITEM_ID = NULL WHERE ACC_ID = ? AND FIELD_X = ? AND FIELD_Y =?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, LoginMenu.loginAccount.getAccId());
			psmt.setInt(2, myField.getFieldX());
			psmt.setInt(3, myField.getFieldY());
			
			n = psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}


	@Override
	public int fieldAddHere() {
		int n = 0;
		String sql = "INSERT INTO IN_FIELD VALUES(?,?,?,NULL)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, LoginMenu.loginAccount.getAccId());
			psmt.setInt(2, ConsolePrintService.userX);
			psmt.setInt(3, ConsolePrintService.userY);
			n = psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return n;
	}


	@Override
	public int fieldDrop(InFieldVO myField) { //필드 메우기
		int n = 0;
		String sql = "DELETE IN_FIELD WHERE ACC_ID = ? AND FIELD_X = ? AND FIELD_Y = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, LoginMenu.loginAccount.getAccId());
			psmt.setInt(2, myField.getFieldX());
			psmt.setInt(3, myField.getFieldY());
			n = psmt.executeUpdate();					
		}catch(Exception e) {
			e.printStackTrace();
		}
		return n;
	}


	@Override
	public int fieldInfoSelect(InFieldVO myField) {
		int itemId = 0;
		String sql = "SELECT ITEM_ID FROM IN_FIELD WHERE ACC_ID = ? AND FIELD_X = ? AND FIELD_Y =?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, LoginMenu.loginAccount.getAccId());
			psmt.setInt(2, myField.getFieldX());
			psmt.setInt(3, myField.getFieldY());
			rs = psmt.executeQuery();
			if(rs.next()) {
				itemId = rs.getInt("item_id");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return itemId;
	}

}
