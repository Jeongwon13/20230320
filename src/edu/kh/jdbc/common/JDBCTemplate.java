package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		try {
			if(conn == null || conn.isClosed()) {
				Properties prop = new Properties();
				// Map<String, String> 형태의 객체, XML 입출력 특화
				
				// driver.xml 파일 읽어오기
				prop.loadFromXML(new FileInputStream("driver.xml"));
				// -> XML 파일에 작성된 내용이 Properties 객체에 모두 저장됨.
			
				// XML 에서 읽어온 값을 모두 String 변수에 저장
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String password = prop.getProperty("password");
				
				// 커넥션 생성
				Class.forName(driver); // Oracle JDBC Driver 객체 메모리 로드
				conn = DriverManager.getConnection(url, user, password);
				
				// 개발자가 직접 트랜잭션 제어할 수 있게 자동 커밋 비활성화
				conn.setAutoCommit(false);	
			}
		} catch(Exception e) {
			System.out.println("[Connection 생성 중 예외 발생]");
			e.printStackTrace();
		}
		return conn;
	}
	
	
	
	
	
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed())  stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/** ResultSet 객체 자원 반환 메서드
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed())  rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/** 트랜잭션 Commit 메서드
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			// 연결되어 Connection 객체일 경우에만 Commit 진행
			if(conn != null && !conn.isClosed())  conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/** 트랜잭션 Rollback 메서드
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			// 연결되어 Connection 객체일 경우에만 rollback 진행
			if(conn != null && !conn.isClosed())  conn.rollback();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
	
	
	
	
	
	
	
	
	
	
	
 