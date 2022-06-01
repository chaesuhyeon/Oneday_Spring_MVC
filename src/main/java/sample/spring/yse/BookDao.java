package sample.spring.yse;


import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // 데이터에 접근하는 클래스임을 명시
public class BookDao {
	@Autowired // 어노테이션으로 의존성 주입 (Field Injection)
	SqlSessionTemplate sqlSessionTemplate; // 매퍼 XML을 실행시키기 위해 SqlSessionTemplate 객체를 멤버변수로 선언
	// 스프링은 미리 만들어 놓은 sqlSessionTemplate 타입 객체를 BookDao 객체에 주입 
	
	public int insert(Map<String, Object> map) {
		  return this.sqlSessionTemplate.insert("book.insert", map); // insert(실행될 mapper에 선언된 네임스페이스.아이디 , 쿼리에 전달할 데이터)
	// 반환값은 쿼리의 영향을 받은 행의 수 . insert 쿼리의 경우 성공하면 1개의 행이 생기므로 1, 실패하면 0이 됨 	
	}

}
