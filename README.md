# Oneday_Spring_MVC

---

# 스프링 MVC 하루만에 배우기

[](https://wikidocs.net/book/5792)[스프링 MVC 하루만에 배우기 - WikiDocs](https://wikidocs.net/book/5792)

---

- ### Eclipse, MariaDB, JAVA 8 , Maven , MyBatis

---

- pom.xml : 메이븐 설정 파일

- context.xml : 외부 연동 관련 정보 설정 (db 연결 )

---

#### SqlSessionTemplate

- 마이바티스 스프링 연동모듈의 핵심
  
  - 마이바티스를 이용하여 DAO를 구현하려면 SqlSession 객체가 필요함
  
  - SqlSession이란 논리적인 연결상태를 말함

- DB에 개별적으로 쿼리를 실행시키는 객체
  
  ```
  <bean id="sqlSessionTemplate"
    class="org.mybatis.spring.SqlSessionTemplate">
    <constructor-arg index="0" ref="sqlSessionFactory" />
   </bean>  
  ```
  
  - sqlSessionTemplate 빈은 sqlSessionFactory 객체를 생성자로 받음
  
  - 따라서 sqlSessionTemplate 객체는 sqlSession 객체가 가지고 있는 DB 접속 정보와 매퍼 파일의 위치를 알 수 있게 됨

- SqlSessionTemplate은 SqlSession을 구현하고 코드에서 SqlSession를 대체하는 역할을 한다.

- SqlSessionTemplate 은 쓰레드에 안전하고 여러개의 DAO나 매퍼에서 공유할수 있다.

- SqlSessionTemplate 객체 사용
  
  - new 키워드를 통해 직접 생성하지 않음 .  대신 의존성 주입을 통해 주입 받음 
  
  - SqlSessionTemplate를 bean 으로 등록
  
  - 클래스에서 SqlSessionTemplate 필드(Field)방식으로 주입하여 사용
  
  - SqlSession (SqlSessionTemplate)은 프록시 사용 및 스레드 안전 SqlSession 메서드를 호출

---

### Mapper

- `namespace`:  xml 파일은 보통 여러개 생성되기 때문에 이를 구별하는 용도로 사용

```
<mapper namespace="book"> </mapper>
```

```
    <insert id="insert" parameterType="hashMap" useGeneratedKeys="true" keyProperty="book_id">  
        <![CDATA[
        insert into book (title, category, price) values (#{title}, #{category}, #{price})
        ]]>
    </insert>
```

- 데이터 입력 SQL 쿼리

`INSERT INTO 테이블이름 (키1, 키2) VALUES (키1값, 키2값)`

- `parameterType` : 쿼리에 적용할 파라미터 타입

- `useGeneratedKeys` & `keyProperty` : 둘이 한쌍.  useGeneratedKeys가 true로 설정되면 마이바티스는 Insert 쿼리 실행 후 생성된 PK를 파라미터 객체의 keyProperty 속성에 넣어줌 

```
쿼리 실행 전 
{
    "title" : "제목" , "category" : "IT", "price" : 10000
}


```
쿼리 실행 후 
{
    "title" : "제목" , "category" : "IT", "price" : 10000 , "book_id" : 1
}
```
```

- `#{title}` : 파라미터로 입력된 키를 값으로 치환 



---

### Service

- service class 는 controller와 DAO를 연결하는 역할 