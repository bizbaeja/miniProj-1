<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/signup.css"/>' >
<title>Insert title here</title>
</head>
<body>
 <div id="wrap">
          <div id="nav">
		        <div class="header-nav">
			    <a href="mainPage.jsp"><img src="logo.jpeg" alt="로고"></a>
			    <h1><a href="mainPage.jsp">bizbaeja</a></h1>
			    <a href="about.jsp">소개</a>
	<!-- 		    <a href="signup.jsp">회원가입</a> -->
			    <a href="login.jsp">로그인</a>
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
        <h2>회원가입</h2>
        <form action="your-server-endpoint" method="post">
            <div class="form-control">
                <label for="name">아이디:</label>
                <input type="text" id="id" name="id" required>
            </div>
               <div class="form-control">
                <label for="name">이름:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-control">
                <label for="email">이메일:</label>
                <input type="email" id="email" name="email" required>
            </div>
            
            <div class="form-control">
                <label for="password">비밀번호:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-control">
                <label for="passwordConfirm">비밀번호 확인:</label>
                <input type="password" id="passwordConfirm" name="passwordConfirm" required>
            </div>
            <div class="form-control">
                <label for="birth">생년월일:</label>
                <input type="date" id="birth" name="birth">
            </div>
            <div class="form-control">
                <label for="gender">성별:</label>
                <select id="gender" name="gender">
                    <option value="Male">남성</option>
                    <option value="Female">여성</option>
                    <option value="Other">기타</option>
                </select>
            </div>
             <div class="form-control">
                <label for="hobby">성별:</label>
               	<input type="checkbox" value="태권도">태권도
               	<input type="checkbox" value="영화보기">영화보기
               	<input type="checkbox" value="음악감상">음악감상
            </div>
            
            <div class="form-control">
                <button type="submit">가입하기</button>
            </div>
        </form>
        
    </div>
        </div>

    </div>
</body>
</html>