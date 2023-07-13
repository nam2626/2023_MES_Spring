<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등급 관리 페이지</title>
<style>
	h2{
		width:1200px;
		margin:0 auto;
		text-align: center;;
	}
	.container{
		width:1200px;
		margin : 0 auto;
		display:flex;
	}
	.manage_container, .register_container{
		padding:20px;
	}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
	function grade_update(){
		let data = '';
		$(this).siblings('input').each(function(i,obj){
			data += `\${$(obj).attr('class')}=\${$(obj).val()}&`;
		});
		
		$.ajax({
			url : 'grade/update',
			data : data,
			type : 'post',
			dataType : 'json',
			success : function(r){
				if(r.result == 1){
					alert('데이터 수정 완료');
				}else{
					alert('데이터 수정 실패');
				}
			}
		});
	}
	function grade_delete(){
		let data = 'grade_no='+$(this).siblings('.grade_no').val();
				
		$.ajax({
			url : 'grade/delete',
			data : data,
			type : 'post',
			dataType : 'json',
			success : (r)=>{
				if(r.result == 1){
					alert('데이터 삭제 완료');
					$(this).parent().remove();
				}else{
					alert('데이터 삭제 실패');
				}
			}
		});
	}
	$(function(){
		$('.btn_update').click(grade_update);
		$('.btn_delete').click(grade_delete);
		$("#register_grade").submit(function (e) {
			e.preventDefault();
			
			let data = $(this).serialize();
			
			$.ajax({
				url : 'grade/append',
				data : data,
				type : 'post',
				dataType : 'json',
				success : function(r){
					console.log(r);
					//결과를 태그에 변경
					if(r.result == 0)
						alert('등급 등록 실패, 데이터를 확인하세요');
					else{
						let tag = '';
						for(let i=0;i<r.list.length;i++){
							tag += `<p>`;
							tag += `<input type='text' class='grade_no' value='\${r.list[i].gradeNo }' readonly>`;
							tag += `<input type='text' class='grade_name' value='\${r.list[i].gradeName }'>`;
							tag += `<button class='btn_update'>수정</button>`;
							tag += `<button class='btn_delete'>삭제</button>`;
							tag += `</p>`;
						}
						$('.content').html(tag);
						$('.btn_update').click(grade_update);
					}
				}
			});
		});
		$('#search').keyup(function() {
			let data = 'grade_name='+$('#search').val();
			
			$.ajax({
				url : 'grade/search',
				data : data,
				type : 'post',
				dataType : 'json',
				success : function(r){
					let tag = '';
					for(let i=0;i<r.list.length;i++){
						tag += `<p>`;
						tag += `<input type='text' class='grade_no' value='\${r.list[i].gradeNo }' readonly>`;
						tag += `<input type='text' class='grade_name' value='\${r.list[i].gradeName }'>`;
						tag += `<button class='btn_update'>수정</button>`;
						tag += `<button class='btn_delete'>삭제</button>`;
						tag += `</p>`;
					}
					$('.content').html(tag);
					$('.btn_update').click(grade_update);	
					$('.btn_delete').click(grade_delete);	
				}
			});
		});
	});
</script>
</head>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>
	<h2>회원 등급 관리 페이지</h2>
	<div class="container">
		<div class="manage_container">
			<h3>등급 관리</h3>
			<hr>
			<p>
				<input type="text" id="search" placeholder="검색할 등급명 일부">
			</p>
			<div class="content">
				<!-- 회원 등급 목록 -->
				<c:forEach var="g" items="${list }">
					<p>
						<input type="text" class='grade_no' value='${g.gradeNo }' readonly>
						<input type="text" class='grade_name' value='${g.gradeName }'>
						<button class='btn_update'>수정</button>
						<button class='btn_delete'>삭제</button>
					</p>
				</c:forEach>
			</div>
		</div>
		<div class="register_container">	
			<h3>등급 추가 폼</h3>
			<hr>
			<form id="register_grade">
				<input type="text" name="grade_no" placeholder="등급번호">
				<input type="text" name="grade_name" placeholder="등급명">
				<button>등급 추가</button>
			</form>
		</div>
	</div>	
</body>
</html>












