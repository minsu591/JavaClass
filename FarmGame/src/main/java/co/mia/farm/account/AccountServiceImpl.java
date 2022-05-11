package co.mia.farm.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import co.mia.farm.LoginMenu;
import co.mia.farm.dao.DataSource;

public class AccountServiceImpl implements AccountService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public int accountInsert(AccountVO vo) { // 회원가입
		int n = 0;
		AccountVO checkVO = accountCheck(vo);
		if (StringUtils.isEmpty(checkVO.getAccId())) {
			// 회원 가입 진행
			String sql = "INSERT INTO ACCOUNTS VALUES(?, ?, ?)";
			try {
				conn = dao.getConnection(); // 연결
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, vo.getAccId());
				psmt.setString(2, vo.getAccPw());
				psmt.setString(3, vo.getAccPhone());
				n = psmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return n;
	}

	@Override
	public AccountVO accountLogin(AccountVO vo) { // 로그인
		AccountVO checkAcc = accountCheck(vo);
		if (StringUtils.isEmpty(checkAcc.getAccId())) {
			System.out.println("존재하지 않는 아이디입니다.");
			vo = new AccountVO();
		} else {
			if (checkAcc.getAccPw().equals(vo.getAccPw())) {
				// 로그인 성공!
				System.out.println("로그인에 성공했습니다.");
			} else {
				System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
				checkAcc = new AccountVO();
			}
		}
		return checkAcc;

	}

	private AccountVO accountCheck(AccountVO vo) { // db에 아이디가 있으면 db의 data를 가져옴
		AccountVO checkVO = new AccountVO();
		String sql = "SELECT * FROM ACCOUNTS WHERE ACC_ID = ?";
		try {
			conn = dao.getConnection(); // connection 열기
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getAccId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				checkVO.setAccId(rs.getString("acc_id"));
				checkVO.setAccPw(rs.getString("acc_pw"));
				checkVO.setAccPhone(rs.getString("acc_phone"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return checkVO;
	}

	public int characterInsert(CharacterVO chVO) {
		int n = 0;
		String sql = "INSERT INTO CHARACTERS VALUES(?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, chVO.getAccId());
			psmt.setString(2, chVO.getFarmName());
			psmt.setString(3, chVO.getUserNickname());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return n;

	}

	private void close() { // rs, psmt, conn 열었던거 닫기
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public boolean characterCheck(String id) {
		boolean makeCha = true;
		String sql = "SELECT acc_id FROM CHARACTERS WHERE ACC_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				System.out.println("기존 캐릭터로 입장합니다...");
				makeCha = false;
			}else {
				System.out.println("새 캐릭터를 생성합니다...");
				makeCha = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return makeCha;
	}

	@Override
	public CharacterVO characterSelect(String id) {//캐릭터 정보 가져오기
		CharacterVO chVO = new CharacterVO();
		String sql = "SELECT * FROM CHARACTERS WHERE ACC_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				chVO.setAccId(id);
				chVO.setFarmName(rs.getString("farm_name"));
				chVO.setUserNickname(rs.getString("user_nickname"));
				chVO.setUserMoney(rs.getInt("user_money"));
				chVO.setUserExp(rs.getInt("user_exp"));
				chVO.setUserHp(rs.getInt("user_hp"));
				chVO.setUserLevel(rs.getInt("user_level"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return chVO;
	}

	@Override
	public int characterModify() {
		CharacterVO ch =LoginMenu.loginCharacter;
		int n = 0;
		String sql = "UPDATE CHARACTERS SET USER_HP =?, USER_LEVEL =?,USER_EXP =?,USER_MONEY =? WHERE ACC_ID =?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, ch.getUserHp());
			psmt.setInt(2, ch.getUserLevel());
			psmt.setInt(3, ch.getUserExp());
			psmt.setInt(4, ch.getUserMoney());
			psmt.setString(5, ch.getAccId());
			n = psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

}
