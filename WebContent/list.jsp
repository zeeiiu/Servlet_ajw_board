<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  
   <table width="500" cellpadding="0" cellspacing="0" border="1">
      <tr>
         <td>번호</td>
         <td>이름</td>
         <td>제목</td>
         <td>날짜</td>
         <td>히트</td>
      </tr>
      <c:forEach var = "board" items="${boards}">
      <tr>
         <td>${board.bid}</td>
         <td>${board.bname}</td>
         <td>
            <c:forEach begin="1" end="${board.bindent}">-</c:forEach>
            <a href="content_view.do?bid=${board.bid}">${board.btitle}</a></td>
         <td>${board.bdate}</td>
         <td>${dto.bhit}</td>
      </tr>
      </c:forEach>
      <tr>
         <td colspan="5"> <a href="write_view.do">글작성</a> </td>
      </tr>
   </table>





</body>
</html>