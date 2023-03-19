package edu.kh.jdbc.run;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class Run {
	public static void main(String[] args) {
		TestService service = new TestService();
		
		// TB_TEST 테이블에 INSERT 수행
		TestVO vo1 = new TestVO(6,"제목55555","내옹55555");
		
		// TB_TEST 테이블에 insert를 수행하는 서비스 메서드를 호출 후
		// 결과 반환 받기
		try {
			int result = service.insert(vo1); // 1/0
			 
			if(result > 0) {
				System.out.println("성공");
				
			} else {
				System.out.println("실패");
			}
		} catch(Exception e) {
			System.out.println("SQL 수행 중 오류 발생");
			e.printStackTrace();
		}
		
	 
		
		
	}
	
	
	
}


