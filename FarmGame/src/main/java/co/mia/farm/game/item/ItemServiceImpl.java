package co.mia.farm.game.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.mia.farm.LoginMenu;
import co.mia.farm.StaticMenu;
import co.mia.farm.dao.DataSource;

public class ItemServiceImpl implements ItemService {
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
	
	public void itemsPrint(List<ItemVO> userItems) { // 아이템 창 출력
		if (userItems.size() == 0) {
			System.out.println("아이템 창이 비었습니다.");
			StaticMenu.waitTime(1000);
		} else {
			System.out.printf("======= %s의 아이템 List =======\n", LoginMenu.loginCharacter.getUserNickname());
			for (int i = 0; i < userItems.size(); i++) {
				AllProductVO sysItem = itemGetproduct(userItems.get(i).getItemID());
				System.out.printf("%s번, [%s : %d개]\n", i + 1, sysItem.getItemName(), userItems.get(i).getItemCnt());
			}
			System.out.println("=================================");
		}
	}
	
	@Override
	public int itemInsert(int itemId, int itemCnt) {
		int n = 0;
		String sql = "INSERT INTO ITEMS VALUES(?,?,?)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, LoginMenu.loginAccount.getAccId());
			psmt.setInt(2, itemId);
			psmt.setInt(3, itemCnt);
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public AllProductVO itemGetproduct(int itemId) { //상세정보
		AllProductVO product = new AllProductVO();
		String sql = "SELECT * FROM ALL_PRODUCTS WHERE ITEM_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, itemId);
			rs = psmt.executeQuery();
			if(rs.next()) {
				product.setItemId(rs.getInt("item_id"));
				product.setItemName(rs.getString("item_name"));
				product.setASell(rs.getInt("a_sell"));
				product.setAPur(rs.getInt("a_pur"));
				product.setALevel(rs.getInt("a_level"));
				product.setAExplain(rs.getString("a_explain"));
				product.setCExp(rs.getInt("c_exp"));
				product.setCTime(rs.getInt("c_time"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return product;
	}



	@Override
	public List<ItemVO> itemAllSelect() {
		List<ItemVO> haveItems = new ArrayList<ItemVO>();
		ItemVO vo;
		String sql = "SELECT * FROM ITEMS WHERE ACC_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, LoginMenu.loginAccount.getAccId());
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new ItemVO();
				vo.setItemID(rs.getInt("item_id"));
				vo.setItemCnt(rs.getInt("item_cnt"));
				haveItems.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return haveItems;
	}



	@Override
	public ItemVO itemOneSelect(int itemId) { //한 개 조회
		ItemVO item = new ItemVO(-1,-1);
		String sql = "SELECT * FROM ITEMS WHERE ACC_ID = ? AND ITEM_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, LoginMenu.loginAccount.getAccId());
			psmt.setInt(2, itemId);
			rs = psmt.executeQuery();
			if(rs.next()) {
				item.setItemID(rs.getInt("item_id"));
				item.setItemCnt(rs.getInt("item_cnt"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return item;
	}

	

	@Override
	public int itemUpdateCnt(ItemVO item, int newCnt) {
		int n = 0;
		String sql = "UPDATE ITEMS SET ITEM_CNT = ? WHERE ACC_ID = ? AND ITEM_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, newCnt);
			psmt.setString(2,LoginMenu.loginAccount.getAccId());
			psmt.setInt(3, item.getItemID());
			n = psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public int itemDelete(ItemVO item) { //아이템 0개 확인
		int n = 0;
		String sql = "DELETE ITEMS WHERE ITEM_ID = ? AND ACC_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, item.getItemID());
			psmt.setString(2, LoginMenu.loginAccount.getAccId());
			n = psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return n;
	}

	@Override
	public List<ItemVO> itemSeedSelect() {
		List<ItemVO> haveItems = new ArrayList<ItemVO>();
		ItemVO vo = new ItemVO();
		String sql = "SELECT * FROM ITEMS WHERE ACC_ID = ? AND ITEM_ID = (SELECT ITEM_ID FROM ALL_PRODUCTS WHERE ITEM_NAME LIKE '%씨앗')";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, LoginMenu.loginAccount.getAccId());
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new ItemVO();
				vo.setItemID(rs.getInt("item_id"));
				vo.setItemCnt(rs.getInt("item_cnt"));
				haveItems.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return haveItems;
	}


}
