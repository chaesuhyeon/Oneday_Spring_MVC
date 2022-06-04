package sample.spring.yse;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
 @Autowired // DB접근을 위해 BookDao 인스턴스를 주입 받음 
 BookDao bookDao;
 
 
 @Override
 public String create(Map<String, Object> map) {
     int affectRowCount = this.bookDao.insert(map);
     if (affectRowCount ==  1) {
         return map.get("book_id").toString(); // 입력에 성공한 경우  map 인스턴스에 book 테이블의 PK인 book_id가 담겨 있을 것. 
     }
     return null; // 실패했으면 null을 return 하여 실패했음을 호출한 곳에 알림 

 }
 
 @Override
 public Map<String, Object> detail(Map<String, Object> map){
     return this.bookDao.selectDetail(map);
 }

 public boolean edit(Map<String, Object> map) {
     int affectRowCount = this.bookDao.update(map);
     return affectRowCount == 1 ; // 1개의 행만 영향 받았는지만 검사하면 됨
 }

 public boolean remove(Map<String, Object> map){
     int affectRowCount = this.bookDao.delete(map);
     return affectRowCount == 1; // 1개의 행만 영향 받았는지만 검사하면 됨
 }
 public List<Map<String, Object>> list(Map<String, Object> map) {
     return this.bookDao.selectList(map);
 }
}