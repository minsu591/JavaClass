package co.mia.farm.game.status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import co.mia.farm.LoginMenu;
import co.mia.farm.dao.DataSource;

public class LevelServiceImpl implements LevelService{
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	@Override
	public LevelVO levelOneSelect(String accId) { //유저 아이디에 해당하는 레벨 정보 불러오기
		LevelVO level = new LevelVO();
		String sql = "SELECT * FROM LEVELS WHERE USER_LEVEL = (SELECT USER_LEVEL FROM CHARACTERS WHERE ACC_ID = ?)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, LoginMenu.loginCharacter.getAccId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				level.setUserLevel(rs.getInt("user_level"));
				level.setLevelName(rs.getString("level_name"));
				level.setMaxExp(rs.getInt("max_exp"));
				level.setMaxHp(rs.getInt("max_hp"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return level;
	}
	
	private void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(psmt != null) {
				psmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
