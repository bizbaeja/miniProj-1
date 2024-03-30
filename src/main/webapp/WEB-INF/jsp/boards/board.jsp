<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/signup.css"/>'>
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
        <div class="vertical-container">
           <div class="post-view">
                <h2>게시글 제목</h2>
                <p>작성자: userid</p>
                <p>작성일: postdate</p>
                <p>내용: 게시글 내용</p>
            </div>
            <form class="board-form" action="SubmitPostServlet" method="post">
                <div class="form-control">
                    <label for="title">제목:</label>
                    <input type="text" id="title" name="title" required>
                </div>
                <div class="form-control">
                    <label for="content">내용:</label>
                    <textarea id="content" name="content" rows="5" required></textarea>
                </div>
                <div class="form-control">
                    <button type="submit">게시글 작성</button>
                </div>
                
            </form>
            </div>
        </div>

    

    </div>
</body>
</html>