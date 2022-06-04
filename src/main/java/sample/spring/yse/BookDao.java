package sample.spring.yse;


import java.util.List;
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
		  return this.sqlSessionTemplate.insert("book.insert", map); 
		  // insert(실행될 mapper에 선언된 네임스페이스.아이디 , 쿼리에 전달할 데이터)
	// 반환값은 쿼리의 영향을 받은 행의 수 . insert 쿼리의 경우 성공하면 1개의 행이 생기므로 1, 실패하면 0이 됨 	
	}
	
	public Map<String, Object> selectDetail(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("book.select_detail", map);
		//selectOne :  데이터를 한 개만 가져올 때 사용
	}

	public int update(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("book.update", map);
	}

	public int delete(Map<String , Object> map) {
		return  this.sqlSessionTemplate.delete("book.delete", map);
	}

	public List<Map<String, Object>> selectList(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("book.select_list", map);
		// 결과를 목록으로 받기 위해서는 sqlSessionTemplate.selectList 사용할 수 있음
		// sqlSessionTemplate.selectList 메소드는 결과 집합 목록을 반환. 따라서 집합 타입인 Map<String, Object> 목록 List 타입으로 읽어들일 수 있음
	}
}
