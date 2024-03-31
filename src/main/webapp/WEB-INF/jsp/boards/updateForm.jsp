<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>게시물 수정</title>
    <style>
        label {
            display: inline-block;
            width: 120px;
        }
        input, textarea {
            margin-bottom: 10px; 
        }
    </style>
</head>
<body>
    <h1>게시물 수정</h1>
    <form id="updateForm" method="post" action="board.do" >
        <input type="hidden" name="action" value="update">
        <label>게시물 번호: </label>
        <input type="text" id="boardid" name="boardid" value="${board.boardid}" readonly="readonly"> <br/>
        <label>제목: </label>
        <input type="text" id="title" name="title" value="${board.title}"><br/>
        <label>내용: </label>
         <input type="hidden" id="userid" name="userid" value="${board.userid}">
        <textarea id="content" name="content" rows="4">${board.content}</textarea><br/>
        <div>
            <input type="submit" value="수정">
            <a href="board.do?action=view&boardid=${board.boardid}">취소</a>
        </div>
    </form>
<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/boardUpdate.js'/>"></script>
 
</body>
</html>
