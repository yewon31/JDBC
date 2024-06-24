package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JDBCSelect2 {

	public static void main(String[] args) {
		
		/*
		 * 직원 테이블과, 부서 테이블을 left조인하고
		 * 
		 * 직원아이디, 직무아이디, 부서명, 이름 만 출력합니다.
		 * 
		 * 조건은 직원아이디를 입력받아서, 이 아이디에 해당하는 데이터만 출력.
		 *  
		 *  
		 */
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //접속주소
		String uid = "HR"; //계정명
		String upw = "HR"; //비밀번호
		
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("조회할 직원아이디 > ");
		int p_employee_id = scan.nextInt();
		
		
		String sql = "SELECT E.EMPLOYEE_ID, E.JOB_ID, D.DEPARTMENT_NAME, E.LAST_NAME || ' ' || E.FIRST_NAME AS NAME\r\n"
						+ "FROM EMPLOYEES E\r\n"
						+ "LEFT JOIN DEPARTMENTS D\r\n"
						+ "ON E.DEPARTMENT_ID = D.DEPARTMENT_ID\r\n"
						+ "WHERE E.EMPLOYEE_ID = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //select는 결과를 조회해서 처리할 ResultSet객체가 필요함
		
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			conn = DriverManager.getConnection(url, uid, upw); //conn객체
			
			pstmt = conn.prepareStatement(sql); //pstmt객체
			pstmt.setInt(1, p_employee_id); //?값 채움
			
			rs = pstmt.executeQuery(); //select는 executeQuery() 구문으로 실행함
			
			//rs에 저장된 데이터를 1행씩 처리하는 구문
			
			while(rs.next()) { //다음행이 있는지 확인해서, 다음이 있으면 전진 true반환
				
				//1행에 대한 프로그램 처리
				//getString(), getInt(), getDate(), getTimestamp() 등을 이용해서 데이터를 읽어들임
				int employee_id = rs.getInt("EMPLOYEE_ID");
				String job_id = rs.getString("JOB_ID");
				String department_name = rs.getString("DEPARTMENT_NAME"); 
				String name = rs.getString("NAME"); 
				
				System.out.println("직원 아이디 : " + employee_id);
				System.out.println("직무 아이디 : " + job_id);
				System.out.println("부서명 : " + department_name);
				System.out.println("이름 : " + name);
				System.out.println("=============================================");
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
