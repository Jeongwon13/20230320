package edu.kh.jdbc.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;

import edu.kh.jdbc.model.dao.TestDAO;
import edu.kh.jdbc.model.vo.TestVO;

public class TestService {
	
	TestDAO dao = new TestDAO();
	
	// 1행 삽입 서비스
	public int insert(TestVO vo1) throws SQLException {
		Connection conn = getConnection();
		
		int result = dao.insert(conn, vo1);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
}
