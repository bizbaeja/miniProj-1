<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User insert form</title>
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
			    <a href="user.do?action=list">게시판</a>
</div>  	<div class="header-nav">
				  <c:if test="${sessionScope.userid != null}">
				   <a href="#" id="logoutLink">로그아웃</a>
				   <a href="updateForm.jsp">회원정보수정</a>
	   			</c:if>
	   			<c:if test="${sessionScope.userid == 'bituser'}">
				   <a href="user.do?action=list" id="admin">관리자</a>
				   
	   			</c:if>
			</div>
          
	<nav>
        </div>
    
        <div id="content">
        <img src="octopus.gif" alt="환영합니다" width="1000" height="300">
        </div>
       
        <div id="footer">jhcompany</div>
    </div>
</body>



	
  </div>

<script>
function jsDelete() {
	if (confirm("정말로 삭제하시겠습니까?")) {
		//서버의 URL을 설정한다 
		action.value = "delete";
	
		//서버의 URL로 전송한다 
		viewForm.submit();
	}
}

function jsUpdateForm() {
	if (confirm("정말로 수정하시겠습니까?")) {
		//서버의 URL을 설정한다 
		action.value = "updateForm";
	
		//서버의 URL로 전송한다 
		viewForm.submit();
	}	
}
document.addEventListener('DOMContentLoaded', function() {
    var logoutLink = document.getElementById('logoutLink');

    logoutLink.addEventListener('click', function(e) {
        e.preventDefault(); // 기본 링크 동작 방지

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "users", true); // "users"는 로그아웃 처리를 담당하는 서버의 URL
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        xhr.onreadystatechange = function() {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
        
                window.location.href = "login.jsp";
            }
        };

        xhr.send("action=logout"); // 데이터를 서버에 보냅니다.
    });
});
</script>

</body>
</html>

