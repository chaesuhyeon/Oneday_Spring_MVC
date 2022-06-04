<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
 <head>
  <title>책 상세</title>
 </head>
 <body>
  <h1>책 상세</h1>
  <p>제목 : ${ data.title } </p>
  <p>카테고리 : ${ data.category }</p>
  <p>가격 : <fmt:formatNumber type="number" maxFractionDigits="3" value="${data.price}" /></p> 
  <p>입력일 : <fmt:formatDate value="${data.insert_date}" pattern="yyyy.MM.dd HH:mm:ss" /></p>

  <p>
   <a href="/update?bookId=${bookId}">수정</a>
<%-- bookId의 경우  컨트롤러의 mav에서 data에 담아서 보낸 것이 아니라 따로 키와 값을 설정했으므로 ${bookId} 형식으로 가져온다. --%>
  </p>
  <form method="POST" action="/delete">
   <input type="hidden" name="bookId" value="${bookId}" />
<%--  /delete URL에 bookId 파라미터를 함께 보냄으로써 데이터를 삭제함. type=hidden은 사용자에게는 보이지 않지만, 서버로 전달하거나 숨겨놓고 값을 사용해야 할 때 쓰인다 --%>
   <input type="submit" value="삭제" />
  </form>
  <p>
   <a href="/list">목록으로</a>
  </p>
 </body>
</html>