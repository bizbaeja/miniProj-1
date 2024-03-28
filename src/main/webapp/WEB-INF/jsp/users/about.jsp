<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/main.css"/>'>
</head>
<body>
 <div id="wrap">
                    <div id="nav">
		        <div class="header-nav">
			    <a href="mainPage.jsp"><img src="logo.jpeg" alt="로고"></a>
			    <h1><a href="mainPage.jsp">bizbaeja</a></h1>
			    <a href="about.jsp">소개</a>
			    <a href="signup.jsp">회원가입</a>
			    <a href="login.jsp">로그인</a>
</div>  	<div class="header-nav">
				  <c:if test="${sessionScope.userid != null}">
				   <a href="users?action=logout">로그아웃</a>
				   <a href="users?action=updateForm">회원정보수정</a>
	   			</c:if>
			</div>

    
        <div id="content">
         제 회사는 
        </div>
       
        <div id="footer">jhcompany</div>
    </div>
</body>
</html>