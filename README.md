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



- **데이터 입력 SQL 쿼리**

`INSERT INTO 테이블이름 (키1, 키2) VALUES (키1값, 키2값)`

- `parameterType` : 쿼리에 적용할 파라미터 타입

- `useGeneratedKeys` & `keyProperty` : 둘이 한쌍.  useGeneratedKeys가 true로 설정되면 마이바티스는 Insert 쿼리 실행 후 생성된 PK를 파라미터 객체의 keyProperty 속성에 넣어줌 

```
쿼리 실행 전 
{
    "title" : "제목" , "category" : "IT", "price" : 10000
}
```

```
쿼리 실행 후 
{
 "title" : "제목" , "category" : "IT", "price" : 10000 , "book_id" : 1
}
```

- `#{title}` : 파라미터로 입력된 키를 값으로 치환 



- **책 수정 기능 **

`UPDATE 테이블명 SET 컬럼들 WHERE 조건`



- **책 삭제 기능**

`DELETE FROM 테이블명 WHERE 조건`



- **책 리스트 & 검색 기능**
  
  - `where 1 = 1` : 1 = 1은 늘 참이기 때문에 검색 조건을 무조건 and로 연결하기 위해 사용
  
  - `<if>`문은 마이바티스에서 조건을 나타냄
  
  - `test`는 조건 규칙을 나타내는 항목
  
  - `<if test="keyword != null and keyword != ''">` : 만약 키워드가 있으면  `<if>` ~ `</if>` 안의 쿼리문이 DB 쿼리에 포함됨 
  
  - 이처럼 쿼리의 내용이 파라미터가 아니라 마이바티스 규칙에 의해서 변경되는 것을 `동적쿼리`라고 부름 

```
    <select id="select_list" parameterType="hashMap" resultType="hashMap">
        <![CDATA[
        select book_id, title, category, price, insert_date from book where 1=1
        ]]>
        <if test="keyword != null and keyword != ''">
        and (title like CONCAT('%',#{keyword},'%') or category like CONCAT('%',#{keyword},'%'))
        </if>
        order by insert_date desc
    </select>
```

* SQL 쿼리 조건에서 포함을 나타내는 구문은 `like`
  
  * `title like '검색어%'` 일 경우는 검색어로 시작한다는 뜻
  
  * `title like '%검색어'` 일 경우는 검색어로 끝난다는 뜻
  
  * `title like '%검색어%'`일 경우는 검색어를 포함한다는 뜻
  
  * 마이바티스에서는 쿼리 파라미터에 `''`을 붙이지 않기 때문에 `title like '%#{keyword}%'` 형식으로 표기하기는 어려움 --> MariaDB의 CONCAT 함수를 이용해 문자열을 이어 붙임
    
    * `CONCAT('%',#{keyword},'%')` --> `'%키워드%'`
  
  * `and (title like CONCAT('%',#{keyword},'%') or category like CONCAT('%',#{keyword},'%'))`  : 제목이나 분류 안에 키워드가 있을 경우를 조건으로 함  --> `and title like '%키워드%' or category like '%키워드%'`

---

### Service

- service class 는 controller와 DAO를 연결하는 역할 