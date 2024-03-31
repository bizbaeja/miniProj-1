<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/signup.css"/>'>
    <title>Board Post</title>
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
            </div>
            <div class="header-nav">
                <c:if test="${sessionScope.userid != null}">
                    <a href="#" id="logoutLink">로그아웃</a>
                    <a href="updateForm.jsp">회원정보수정</a>
                </c:if>
            </div>
        </div>

        <div id="content">
            <div class="vertical-container">
                <c:choose>
                    <c:when test="${not empty board}">
                        <div class="post-view">
                            <h2>게시글 제목: ${board.title}</h2>
                            <p>작성자: ${board.userid}</p>
                            <p>작성일: ${board.postdate}</p>
                            <p>내용: ${board.content}</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <form class="board-form" action="board.do" method="post">
                            <input type="hidden" name="action" value="board"/>
                            <input type="hidden" name="userid" value="${sessionScope.userid}"/>
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
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/boardUpdate.js'/>"></script>
</body>
</html>
