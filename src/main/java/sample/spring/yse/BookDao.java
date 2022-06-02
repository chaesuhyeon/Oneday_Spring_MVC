package sample.spring.yse;


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

}
