<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>a
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/signup.css"/>'>
<title>Insert title here</title>
</head>
<body>
 <div id="wrap">
    <nav>
          <div id="nav">
		        <div class="header-nav">
				    <a href="mainPage.jsp"><img src="logo.jpeg" alt="로고"></a>
			    <h1><a href="mainPage.jsp">bizbaeja</a></h1>
			    <a href="user.do?action=about">소개</a>
			    <a href="user.do?action=signupForm">회원가입</a>
			    <a href="user.do?action=loginForm">로그인</a>
			    <a href="user.do?action=list">회원정보</a>
			    <a href="board.do?action=list">게시판</a>
				</div>  	
				<div class="header-nav">
				  <c:if test="${sessionScope.userid != null}">
				   <a href="#" id="logoutLink">로그아웃</a>
				   <a href="updateForm.jsp">회원정보수정</a>
	   			</c:if>
			    </div>
          
	
        </div>
    </nav>

		로그인되었습니다.  
       
<h1>회원목록</h1>
	  
    <form id="searchForm" action="user.do?action=list" method="get" >
    	<label>이름 : </label>
    	<input type="text" id="searchKey" name="searchKey" value="${param.searchKey}">
    	<input type="submit" value="검색">
    </form>
    
    <form id="list" action="user.do" method="post">
    	<input type="hidden" id="action" name="action" value="read">
    	<input type="hidden" id="userid" name="userid" >
    </form>
    
    <table border="1">
        <tr>
            <th>ID</th>
            <th>이름</th>
            <th>이메일</th>
            <th>생일</th>
            <th>성별</th>
            <th>등록일</th>
        </tr>
        <c:forEach var="user" items="${usersList}">
        <tr>
            <td onclick="jsView('${user.userid}')"  style="cursor:pointer;">${user.userid}</td>
            <td><a href="user.do?action=read&userid=${user.userid}">${user.name}</a></td>
            <td>${user.email}</td>
            <td>${user.birth}</td>
            <td>${user.gender}</td>
            <td>${user.register}</td>
        </tr>
        </c:forEach>
    </table>
    
 </div >
</body>
</html>