package sample.spring.yse;


import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // �����Ϳ� �����ϴ� Ŭ�������� ���
public class BookDao {
	@Autowired // ������̼����� ������ ���� (Field Injection)
	SqlSessionTemplate sqlSessionTemplate; // ���� XML�� �����Ű�� ���� SqlSessionTemplate ��ü�� ��������� ����
	// �������� �̸� ����� ���� sqlSessionTemplate Ÿ�� ��ü�� BookDao ��ü�� ���� 
	
	public int insert(Map<String, Object> map) {
		  return this.sqlSessionTemplate.insert("book.insert", map); 
		  // insert(����� mapper�� ����� ���ӽ����̽�.���̵� , ������ ������ ������)
	// ��ȯ���� ������ ������ ���� ���� �� . insert ������ ��� �����ϸ� 1���� ���� ����Ƿ� 1, �����ϸ� 0�� �� 	
	}
	
	public Map<String, Object> selectDetail(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("book.select_detail", map);
		//selectOne :  �����͸� �� ���� ������ �� ���
	}

	public int update(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("book.update", map);
	}

	public int delete(Map<String , Object> map) {
		return  this.sqlSessionTemplate.delete("book.delete", map);
	}

	public List<Map<String, Object>> selectList(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("book.select_list", map);
		// ����� ������� �ޱ� ���ؼ��� sqlSessionTemplate.selectList ����� �� ����
		// sqlSessionTemplate.selectList �޼ҵ�� ��� ���� ����� ��ȯ. ���� ���� Ÿ���� Map<String, Object> ��� List Ÿ������ �о���� �� ����
	}
}
