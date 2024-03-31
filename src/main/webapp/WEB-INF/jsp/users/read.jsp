<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>User insert form</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/read.css'/>">
</head>
<body>
	<div class="wrap">
		<!-- 생략된 네비게이션 및 기타 코드 -->
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

			<nav>
		</div>

		<div class="user-detail">
			<h1>View User Detail</h1>

			<form id="viewForm" method="post" action="user.do">
				<!--name의 value값은 post로 보낼시에 key값이 된다. -->
				<input type="hidden" id="action" name="action" value="update">
                <input type="hidden" id="userid" name="userid" value="${user.userid}">
									${user.userid}

				<div class="form-row">
					<label for="name">비밀번호 변경: </label> <input type="text" id="password"
						name="password" value="${user.password}">
				</div>

				<div class="form-row">
					<label for="name">이름: </label> <input type="text" id="name"
						name="name" value="${user.name}">
				</div>

				<div class="form-row">
					<label for="email">이메일: </label> <input type="text" id="email"
						name="email" value="${user.email}">
				</div>

				<div class="form-row">
			<input type="date" name="birth" value="${user.birth}"><label for="birth">생일: </label> 
				</div>
				
				<input type="radio" id="gender1" name="gender" value="male" ${"male".equals(user.gender) ? "checked ='checked'" : ""}><label for="gender1">male</label>
				
				<input type="radio" id="gender2" name="gender" value="female"  ${"female".equals(user.gender) ? "checked ='checked'" : ""}><label for="gender2">female</label>
				
				<input type="radio" id="gender3" name="gender" value="other" ${"other".equals(user.gender) ? "checked ='checked'" : ""}><label for="gender3">other</label>
			
				<div class="form-row">
					<label for="register">등록일: </label> <input type="text"
						id="register" name="register" value="${user.register}" readonly>
				</div>
 				<h3>취미 선택</h3>
        				<c:forEach var="hobby" items="${user.hobbyList}">
				    <div>
				        <input type="checkbox" id="hobby${hobby.hobbyid}" name="hobbies" value="${hobby.hobbyid}" ${hobby.checked}/>
				        <label for="hobby${hobby.hobbyid}">${hobby.hobbyname}</label>
				    </div>
				</c:forEach>
                <div>
				
			<input type="submit" value="수정" >
			</form>
		</div>
	</div>


	<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
	<script>
function jsDelete() {
	if (confirm("정말로 삭제하시겠습니까?")) {
		/*                 
		//서버의 URL을 설정한다 
		action.value = "delete";
	
		//서버의 URL로 전송한다 
		viewForm.submit();
		*/
		action.value = "delete";
		//myFetch 는 
		myFetch("user.do", "read", json => {
			if(json.status == 0) {
				//성공
				alert("회원정보를 삭제 하였습니다");
				location = "user.do?action=list";
			} else {
				alert(json.statusMessage);
			}
		});
	}
}

const viewForm = document.getElementById("viewForm");
viewForm.addEventListener("submit", e =>  {
	e.preventDefault();
    var userid = document.getElementById("userid").value;
    var form = document.getElementById("viewForm");
    form.action.value = "update";
    var formData = new FormData(form);

    // Check if the user confirms the update before making the call
    if (confirm("수정하시겠습니까?")) {
        // myFetch call to save the data
        myFetch("user.do", "viewForm", json => {
        	
            if (json.status === 0) {
                // Data save successful, alert and redirect
                alert("정상적으로 수정되었습니다.");
                window.location.href = "user.do?action=read&userid=" + userid;
            } else {
                // If there was an error in saving data, alert the error
                alert(json.statusMessage);
            }
        });
    } else {
        // If the user cancels, do nothing
        console.log("User cancelled the update.");
    }
})
</script>
	<!-- 두개의 폼을 하나로 합치는 방법 , js를 사용하여 처리  -->


</body>
</html>