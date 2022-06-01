package sample.spring.yse;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
 @Autowired // DB������ ���� BookDao �ν��Ͻ��� ���� ���� 
 BookDao bookDao;
 
 
 @Override
 public String create(Map<String, Object> map) {
     int affectRowCount = this.bookDao.insert(map);
     if (affectRowCount ==  1) {
         return map.get("book_id").toString(); // �Է¿� ������ ���  map �ν��Ͻ��� book ���̺��� PK�� book_id�� ��� ���� ��. 
     }
     return null; // ���������� null�� return �Ͽ� ���������� ȣ���� ���� �˸� 

 }

}