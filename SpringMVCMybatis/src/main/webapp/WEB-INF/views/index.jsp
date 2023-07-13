<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		관리자 아이디, 비밀번호를 입력해서
		login 경로 전송
		
		1. LoginServlet을 만들어서 url 경로는 login으로 설정
		2. 임의로 관리자 아이디, 비번을 member 테이블에 저장한 후 
		   사용자가 보낸 아이디 비밀번호가 관리자에 해당하면 main.jsp로 이동
		   세션에 로그인 성공했다는 데이터를 하나 저장
		3. 만약에 아이디 비번이 틀리면 index.jsp로 이동하게끔 처리	
	 -->
	<style>
	.container {
		width: 1200px;
		margin: 100px auto;
	}
	
	h2 {
		text-align: center;
	}
	
	input {
		width: 300px;
		height: 40px;
		font-size: 18px;
		outline: none;
		border-radius: 5px;
		box-sizing: border-box;
		margin-bottom: 5px;
		border: 1px solid gray;
	}
	
	button {
		width: 300px;
		height: 40px;
		font-size: 18px;
		background-color: rgb(52, 152, 219);
		outline: none;
		box-sizing: border-box;
		border-radius: 5px;
		border: 1px solid rgb(52, 120, 255);
		color: white;
		font-weight: bold;
	}
	
	form {
		width: 300px;
		display: flex;
		margin: 0 auto;
		flex-direction: column;
	}
	</style>

	<div class="container">
		<h2>회원 관리 시스템</h2>
		<form action="login" method="post">
			<input type="text" name="id" placeholder="아이디를 입력하세요"> 
			<input type="password" name="passwd" placeholder="암호를 입력하세요">
			<button>로그인</button>
		</form>
	</div>
</body>
</html>







