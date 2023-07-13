<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.container{
		width:1200px;
		margin: 50px auto;	
		font-size: 0px;
	}
	form{
		text-align: center;
	}
	input, select, button{
		width: 200px;
		height : 50px;
		font-size: 1.5rem;
		box-sizing: border-box;
	}
	button{
		display : inline-block;
		box-sizing: border-box;
		font-size: 1.5rem;	
		height : 50px;	
	}
</style>
</head>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>
	<div class="container">
		<form action="/member/update" method="post">
			<input type="text" name="memberId" value="${member.memberId }" readonly><br>
			<input type="password" name="passwd" placeholder="새 암호 입력" required><br>
			<input type="text" name="name" placeholder="이름 입력" value="${member.name }"><br>
			<input type="text" name="age" placeholder="나이 입력" value="${member.age }"><br>
			<select name="gender">
				<option <c:if test='${member.gender == "M".charAt(0) }'>selected</c:if>>M</option>
				<option <c:if test='${member.gender == "F".charAt(0) }'>selected</c:if>>F</option>
			</select><br>
			<button>수정</button>
		</form>
	</div>
</body>
</html>




