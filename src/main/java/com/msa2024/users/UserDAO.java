package com.msa2024.users;

/**
 * Servlet implementation class UserDAO
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO {

	private static Connection conn = null;
	private static PreparedStatement userListPstmt = null;
	private static PreparedStatement userInsertPstmt = null;
	private static PreparedStatement userDeletePstmt = null;
	private static PreparedStatement userDetailPstmt = null;

	private static PreparedStatement userUpdatePstmt = null;

	private static PreparedStatement hobbyListPstmt = null;
	private static PreparedStatement userHoddyUpdatePstmt = null;

	private static PreparedStatement userValidationIdPstmt = null;
	private static PreparedStatement userValidationPasswordPstmt = null;
	private static PreparedStatement userDeletePstmt1;

	static {

		try {
			// 1. JDBC 드라이버 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			// 2. DB 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/miniProj-db", "bituser", "1004");

			// 3. SQL 실행 객체 준비
			// 4. SQL 실행
			System.out.println("연결 성공");
			conn.setAutoCommit(false);

			userListPstmt = conn.prepareStatement("select * from tb_users");
//	            userListPstmt2 = conn.prepareStatement("select * from users where username like ?");
			userInsertPstmt = conn.prepareStatement(
					"insert into tb_users (userid, name, email, password , birth, gender, register) values (?, ?, ?, ?, ?, ?, ?)");
			userDetailPstmt = conn.prepareStatement("select * from tb_users where userid=?");
			userValidationIdPstmt = conn.prepareStatement("select userid from tb_users where userid=?  ");
			userValidationPasswordPstmt = conn.prepareStatement("select password from tb_users whrere password=? ");
			// delete 가 되지 않았던 이유: ? 개수에 맞춰서 setString() 을 해주어야 한다.
			userDeletePstmt = conn.prepareStatement("delete from tb_users where userid=?");
			userDeletePstmt1 = conn.prepareStatement("delete from tb_board where userid=?");

			hobbyListPstmt = conn.prepareStatement("SELECT * FROM tb_hobbies;");
			userHoddyUpdatePstmt = conn.prepareStatement("INSERT INTO tb_userhobbies (userid, hobbyid) VALUES (?, ?)");
			userUpdatePstmt = conn.prepareStatement(
					"update tb_users set name=?, email=?, password=?, birth=?, gender=?, hobbyid=? where userid=?");
			// 6. 연결 해제
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public Map<Integer, String> getHobbies() {
		Map<Integer, String> hobbies = new HashMap<>();
		// 취미 정보를 조회하는 SQL 쿼리를 실행하는 코드
		// 결과를 받아 Map<Integer, String>에 저장하여 반환
		
		try(ResultSet rs = hobbyListPstmt.executeQuery()) {
			while(rs.next()) {
				var hobbyVO = new HobbyVO(
						rs.getInt("hobbyid"),
						rs.getString("hobbyname")
						);

				hobbies.put(hobbyVO.getHobbyid(), hobbyVO.getHobbyname());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hobbies;
	}

	public List<UserVO> list(UserVO user) {
		List<UserVO> list1 = new ArrayList<>();
		Map<Integer, String> hobbyMap = new HashMap<>();

		try {

			ResultSet rs = userListPstmt.executeQuery();

			while (rs.next()) {
				UserVO users = new UserVO(rs.getString("userid"), rs.getString("name"), rs.getString("email"),
						rs.getString("password"), rs.getString("birth"), rs.getString("gender"),
						rs.getString("register"));

				list1.add(users);
			}
			rs.close();

			ResultSet hs = hobbyListPstmt.executeQuery();
			while (hs.next()) {
				int hobbyId = hs.getInt("hobbyid"); // 취미 ID를 Integer 형태로 가져옵니다.
				String hobbyName = hs.getString("hobbyname"); // 취미 이름을 가져옵니다.
				hobbyMap.put(hobbyId, hobbyName);

			}
			hs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list1;
	}

	public UserVO read(String userId) {
		String sql = "SELECT * FROM tb_users WHERE userid = ?";

		try (PreparedStatement userDetailPstmt = conn.prepareStatement(sql)) {
			userDetailPstmt.setString(1, userId);

			try (ResultSet rs = userDetailPstmt.executeQuery()) {
				if (rs.next()) {

					var UserVO = new UserVO(rs.getString("userid"), rs.getString("name"), rs.getString("email"),
							rs.getString("password"), rs.getString("birth"), rs.getString("gender"),
							rs.getString("register"), rs.getInt("hobbyid"));
//					System.out.println(UserVO.getName());
					return UserVO;

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("UserDAO retrun null");
		return null;

	}

	public int delete(UserVO user) {
		int updated = 0;

		try {
			// tb_board에서 사용자 게시글 삭제
			userDeletePstmt.setString(1, user.getUserid());
			updated = userDeletePstmt.executeUpdate();

			// 모든 삭제 작업 후 커밋
			conn.commit();
		} catch (Exception e) {
			try {
				// 롤백 실행
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return updated;
	}

	public int update(UserVO user) {

		System.out.println("cjcj11111cjcj " + user);

		int updated = 0;
		try {
			userUpdatePstmt.setString(1, user.getName());
			userUpdatePstmt.setString(2, user.getEmail());
			userUpdatePstmt.setString(3, user.getPassword());
			userUpdatePstmt.setString(4, user.getBirth());
			userUpdatePstmt.setString(5, user.getGender());
			userUpdatePstmt.setInt(6, user.getHobbyid());
			userUpdatePstmt.setString(7, user.getUserid()); // WHERE 절에서 사용될 userid
			

			updated = userUpdatePstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updated;
	}

	public void insertUserWithHobbies(HobbyVO user, List<Integer> hobbyIds) throws SQLException {
		// 사용자 정보 삽입 쿼리
		String sqlUser = "INSERT INTO tb_users (userid, name, email, password, birth, gender, register) VALUES (?, ?, ?, ?, ?, ?, ?)";
		// 취미 정보 삽입 쿼리
		String sqlHobby = "INSERT INTO tb_userhobbies (userid, hobbyid) VALUES (?, ?)";

		PreparedStatement pstmtUser = null;
		PreparedStatement pstmtHobby = null;

		try {
			conn.setAutoCommit(false);

			// 사용자 정보 삽입
			pstmtUser = conn.prepareStatement(sqlUser);
			pstmtUser.setInt(1, user.getHobbyid());
			pstmtUser.setString(2, user.getHobbyname());

			pstmtUser.executeUpdate();

			// 취미 정보 삽입
			pstmtHobby = conn.prepareStatement(sqlHobby);
			for (Integer hobbyId : hobbyIds) {
				pstmtHobby.setInt(1, hobbyId);
				pstmtHobby.setInt(2, user.getHobbyid());

				pstmtHobby.executeUpdate();
			}

			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				conn.rollback();
			}
			throw e;
		} finally {
			if (pstmtUser != null)
				pstmtUser.close();
			if (pstmtHobby != null)
				pstmtHobby.close();
			conn.setAutoCommit(true);
		}
	}
}
