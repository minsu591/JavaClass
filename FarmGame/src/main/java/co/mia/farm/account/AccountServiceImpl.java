package co.mia.farm.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import co.mia.farm.dao.DataSource;

public class AccountServiceImpl implements AccountService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	private boolean makeCharacter;
	private Scanner scn = new Scanner(System.in);

	@Override
	public int accountInsert(AccountVO vo) { // 회원가입
		int n = 0;
		AccountVO checkVO = accountCheck(vo);
		if (StringUtils.isEmpty(checkVO.getAccId())) {
			// 회원 가입 진행
			String sql = "INSERT INTO ACCOUNT_VO VALUES(ACCOUNT_VO_ID_SEQ.NEXTVAL, ?, ?, ?)";
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
				vo = checkAcc;
			} else {
				System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
				vo = new AccountVO();
			}
		}
		return vo;

	}

	private AccountVO accountCheck(AccountVO vo) { // db에 아이디가 있으면 db의 data를 가져옴
		AccountVO checkVO = new AccountVO();
		String sql = "SELECT * FROM ACCOUNT_VO WHERE ACC_ID = ?";
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

	public int characterInsert(CharacterVO chVO) { // accountVO 넣으면 캐릭터 생성
		int n = 0;
		CharacterVO cvo = new CharacterVO();
		String sql = "INSERT INTO ADMIN.CH_VO VALUES(?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, chVO.getAcc_id());
			psmt.setString(2, chVO.getFarm_name());
			psmt.setString(3, chVO.getUser_nickname());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return n;

	}

	public boolean characterCheck(AccountVO vo) { // 현재 로그인한 아이디에 캐릭터가 있으면 false, 없으면 true;
		String sql = "SELECT * FROM CH_VO WHERE ACC_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getAccId());
			System.out.println(vo.getAccId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				if (StringUtils.isEmpty(rs.getString("acc_id"))) {
					System.out.println(rs.getString("acc_id"));
					System.out.println("이미 캐릭터가 있다.");
					makeCharacter = false;
				} else {
					System.out.println("캐릭터를 생성해라");
					makeCharacter = true;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return makeCharacter;
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

}
