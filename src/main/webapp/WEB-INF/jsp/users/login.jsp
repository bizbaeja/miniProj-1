<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">     
<link rel="stylesheet" type="text/css" href='<c:url value="/resoures/css/signup.css"/>'>
<title>Insert title here</title>
</head>
<body>
 <div id="wrap">
          <div id="nav">
		        <div class="header-nav">
			    <a href="mainPage.jsp"><img src="logo.jpeg" alt="로고"></a>
			    <h1><a href="mainPage.jsp">bizbaeja</a></h1>
			    <a href="about.jsp">소개</a>
			    <a href="signup.jsp">회원가입</a>
		<!-- 	    <a href="login.jsp">로그인</a> -->
</div>  	<div class="header-nav">
				  <c:if test="${sessionScope.userid != null}">
				   <a href="#" id="logoutLink">로그아웃</a>
				   <a href="updateForm.jsp">회원정보수정</a>
	   			</c:if>
			</div>
          
	<nav>
        </div>
    
        <div id="content">
         <div class="signup-form">
        <h2>로그인</h2>
        <form action="your-server-endpoint" method="post">
            <div class="form-control">
                <label for="name">아이디:</label>
                <input type="text" id="id" name="id" required>
            
            <div class="form-control">
                <label for="password">비밀번호:</label>
                <input type="password" id="password" name="password" required>
            </div>
           

            
            <div class="form-control">
                <button type="submit">로그인</button>
            </div>
        </form>
        
    </div>
        </div>

    </div>
</body>
</html>