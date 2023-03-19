package edu.kh.jdbc.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import static edu.kh.jdbc.common.JDBCTemplate.*;

import edu.kh.jdbc.model.vo.TestVO;

// DAO(Data Access Object) : 데이터가 저장된 DB에 접근하는 객체
//						-> SQL 수행, 결과 반환 받는 기능을 수행
public class TestDAO {
	// JDBC 객체를 참조하기 위한 참조변수 선언
	 private Statement stmt;
	 private PreparedStatement pstmt;
	 private ResultSet rs;
	 
	// XML로 SQL 다룰 예정 -> Properties 객체 사용
	 private Properties prop;
	 
	 public TestDAO() {
		 try {
			 prop = new Properties();
			 prop.loadFromXML(new FileInputStream("test-query.xml"));
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	
	 public int insert(Connection conn, TestVO vo1) throws SQLException {
		 int result = 0;
		 
		 try {
			 // SQL 작성(test-query.xml에 작성된 SQL 얻어오기(양식 있자나!!)
			 
			 String sql = prop.getProperty("insert");
			 
			 // pstmt 객체 생성
			 pstmt = conn.prepareStatement(sql);
			 
			 // private인 TestVO에 접근하기 위해 그 뭐냐 getter setter 애들
			 pstmt.setInt(1, vo1.getTestNo());
			 pstmt.setString(2, vo1.getTestTitle());
			 pstmt.setString(3, vo1.getTestContent());
			 
			 result = pstmt.executeUpdate();
			 
		 } finally { 
			 close(pstmt); // 이건 참고로 JDBCTemplate에 있는 close(stmt) 메서드임~
		 }
		return result;
	 }
	
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
