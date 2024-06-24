package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCDelete {

	public static void main(String[] args) {

		// 아이디, 비밀번호, 나이, email을 받아서, 해당 아이디를 업데이트

		String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 접속주소
		String uid = "HR"; // 계정명
		String upw = "HR"; // 비밀번호
		

		Scanner scan = new Scanner(System.in);
		System.out.print("삭제할 아이디 > ");
		String id = scan.next();

		String sql = "DELETE FROM MEMBER WHERE ID = ?";
		

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName("oracle.jdbc.OracleDriver"); // 드라이버 클래스 호출

			conn = DriverManager.getConnection(url, uid, upw); // conn객체 생성

			pstmt = conn.prepareStatement(sql); // pstmt객체 생성
			pstmt.setString(1, id);

			int result = pstmt.executeUpdate(); // insert, update, delete

			if (result == 1) {
				System.out.println("DELETE 성공");
			} else {
				System.out.println("DELETE 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (Exception e2) {
			}
		}
	}
}
