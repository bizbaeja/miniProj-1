<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/signup.css"/>'>
    <title>회원가입</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/read.css'/>">
</head>
<body>
    <div class="wrap">
        <!-- 생략된 네비게이션 및 기타 코드 -->
        <div id="nav">
            <div class="header-nav">
                <a href="mainPage.jsp"><img src="logo.jpeg" alt="로고"></a>
                <h1>
                    <a href="mainPage.jsp">bizbaeja</a>
                </h1>
                <a href="about.jsp">소개</a> <a href="signup.jsp">회원가입</a> <a href="login.jsp">로그인</a>
            </div>
            <div class="header-nav">
                <c:if test="${sessionScope.userid != null}">
                    <a href="#" id="logoutLink">로그아웃</a>
                    <a href="updateForm.jsp">회원정보수정</a>
                </c:if>
            </div>
        </div>

        <div class="user-detail">
            <h2>회원 가입</h2>
            <form id="viewForm" action="user.do" method="post">
            <input type="hidden" id="action" name="action" value="signup">
                <div>
                    <label for="userid">아이디:</label>
                    <input type="text" id="userid" name="userid" required>
                </div>
                <div>
                    <label for="name">이름:</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div>
                    <label for="email">이메일:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div>
                    <label for="password">비밀번호:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div>
                    <label for="passwordConfirm">비밀번호 확인:</label>
                    <input type="password" id="passwordConfirm" name="passwordConfirm" required>
                </div>
                <div>
                    <input type="radio" id="gender1" name="gender" value="male"><label for="gender1">남성</label>
                    <input type="radio" id="gender2" name="gender" value="female"><label for="gender2">여성</label>
                    <input type="radio" id="gender3" name="gender" value="other"><label for="gender3">기타</label>
                </div>
                <h3>취미 선택</h3>
                <c:forEach var="entry" items="${hobbiesList}">
                    <div>
                        <input type="checkbox" id="hobby_<c:out value='${entry.key}'/>" name="hobbies" value="<c:out value='${entry.key}'/>">
                        <label for="hobby_<c:out value='${entry.key}'/>"><c:out value='${entry.value}'/></label>
                    </div>
                </c:forEach>
                <div>
                    <input type="submit" value="가입">
                </div>
            </form>
        </div>
    </div>
    <script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/update.js'/>"></script>
    <script type="text/javascript">
        const viewForm = document.getElementById("viewForm");
        const userid = document.getElementById("userid");
        const password = document.getElementById("password");
        const passwordConfirm = document.getElementById("passwordConfirm");
        const name = document.getElementById("name");
        const email = document.getElementById("email");
        const maleRadio = document.getElementById("gender1");
        const femaleRadio = document.getElementById("gender2");
        const otherRadio = document.getElementById("gender3");
        let validMemberID = "";

        viewForm.addEventListener("submit", function(e) {
            e.preventDefault();

           /*  if (validMemberID === "" || userid.value !== validMemberID) {
                alert("아이디 중복확인 해주세요");
                return;
            } */

            let selectedGender = "";
            if (maleRadio.checked) {
                selectedGender = maleRadio.value;
            } else if (femaleRadio.checked) {
                selectedGender = femaleRadio.value;
            } else if (otherRadio.checked) {
                selectedGender = otherRadio.value;
            } else {
                alert("성별을 선택해주세요.");
                return;
            }

            if (password.value !== passwordConfirm.value) {
                alert("비밀번호가 일치하지 않습니다.");
                passwordConfirm.focus();
                return;
            }

            const selectedHobbies = Array.from(document.querySelectorAll('input[name="hobbies"]:checked')).map(cb => cb.value);

            const param = {
                action: 'signup',
                userid: userid.value,
                password: password.value,
                name: name.value,
                email: email.value,
                gender: selectedGender,
                hobbies: selectedHobbies
            };

            fetch("user.do", {
                method: "POST",
                body: JSON.stringify(param),
                headers: { "Content-type": "application/json; charset=utf-8" }
            })
            .then(res => res.json())
            .then(json => {
                if (json.status == 0) {
                    alert("회원 가입이 완료되었습니다.");
                    location = "user.do?action=mainPage";
                } else {
                    alert(json.statusMessage);
                }
            }).catch(error => {
                console.error('Error:', error);
                alert("회원가입 처리 중 오류가 발생했습니다.");
            });
        });
    </script>
</body>
</html>