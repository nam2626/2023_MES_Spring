<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	border-collapse: collapse;
	margin: 30px auto;
}

td, th {
	padding: 10px;
	font-size: 1.3em;
	border: 1px solid black;
	text-align: center;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
	function delete_member(){
		$.ajax({
			url : '/member/delete/'+$(this).parent().parent().children().first().text(),
			type : 'post',
			dataType : 'json',
			success:(r)=>{
				if(Number(r.count) == 0){
					alert(r.message);
					location.reload();
				}else{
					alert(r.message);
					console.log($(this).parent().parent());
					$(this).parent().parent().remove();
				}				
			}
		});
	}
	$(function(){
		$('.btn_search').click(function(){
			let data = $('form').serialize();
			$.ajax({
				url: '/member/search',
				data : data,
				type : 'post',
				dataType: 'json',
				success:function(r){
					console.log(r);
					let tag = '';
					for(let i=0;i<r.length;i++){
						tag += `<tr>`;
						tag += `<td><a href="/member/detail/\${r[i].memberId}">\${ r[i].memberId}</a></td>`;
						tag += `<td>\${ r[i].name}</td>`;
						tag += `<td>\${ r[i].age}</td>`;
						tag += `<td>\${ r[i].gender}</td>`;
						tag += `<td><button type='button' class='btn_delete'>삭제</button></td>`;
						tag += `</tr>`;
					}
					$('tbody').html(tag);
					//이벤트처리도 추가
					$('.btn_delete').click(delete_member);
				}
					
			});
		});
		$('.btn_delete').click(delete_member);
		
		//검색 결과 초기화
		$('.btn_reset').click(function(){
			location.reload();
		});
	});
</script>
</head>
<body>
	<!-- header.jsp include -->
	<jsp:include page="template/header.jsp"></jsp:include>
	<table>
		<thead>
			<tr>
				<td colspan="5">
					<!-- 
						아이디, 이름, 성별 중 하나로 검색
						아이디, 이름, 성별 중 하나 선택할 목록
						검색어 받을 텍스트 상자,
						검색을 수행할 버튼, 검색 결과를 리셋할 버튼
					 -->
					<form>
						<select name="kind">
							<option value="member_id">아이디</option>
							<option value="member_name">이름</option>
							<option value="member_gender">성별</option>
						</select> <input type="text" name="search" placeholder="검색어 입력하세요">
						<button type="button" class="btn_search">검색</button>
						<button type="button" class="btn_reset">검색 초기화</button>
					</form>
				</td>
			</tr>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<!-- 
				회원 아이디 클릭시 /member/detail?id=아이디값   
				회원정보 수정 페이지로 이동(member_update_view.jsp)
				해당 페이지 폼에 회원 정보를 미리 셋팅
			
			 -->
			<c:forEach items="${list }" var="m">
				<tr>
					<td><a href="/member/detail/${m.memberId}">${ m.memberId}</a></td>
					<td>${ m.name}</td>
					<td>${ m.age}</td>
					<td>${ m.gender}</td>
					<td><button type='button' class='btn_delete'>삭제</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>















