<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>회원 탈퇴 페이지</title>

<style>
	.uploadResult{
		width:100%;
		background-color:gray;
	}
	
	.uploadResult ul{
		display:flex;
		flex-flow: row;
		justify-content: center;
		align-items: center;
	}
	
	.uploadResult ul li{
		list-style: none;
		padding: 10px;
		align-content: center;
		text-align: center;
	}
	
	.uploadResult ul li img{
		width: 100px;
	}
	
	.uploadResult ul li span{
		color: white;
	}
	
	
</style>

</head>

<body>
   	
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="/">Battle Ground</a> 
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button> 
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link" href="/">Home<span class="sr-only">(current)</span></a></li>
				
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item">
						<a class="nav-link" href="#" onclick="document.getElementById('logout-form').submit();">Sign out</a>
						<form id="logout-form" action='<c:url value='/customLogout'/>' method="POST">
						   <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
						</form> 
					</li> 
					<!-- 
					<li class="nav-item">
					<form id='logoutId' method='post' action="/customLogout">
						<a class="nav-link">Logout</a>
						<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />		
					</form>
					</li>
					 -->
				</sec:authorize>
			</ul>
		</div>
	</nav> <!-- end of nav-bar -->

	<div class="c1" style="padding-left: 1rem;">
		<div class="card w-75 border-secondary mb-3">
			<h5 class="card-header">회원정보</h5>
			<div class="card-body">
				<form role="form">

					<div class="form-group">
						<label for="labelBno">아이디</label> <input type="text"
							class="form-control" name="userid" placeholder="Bno"
							value='<c:out value="${member.userid }"/>' readonly>
					</div>

					<div class="form-group form-group col-md-20">
						<label for="labelTitle">비밀번호</label> <input type="text"
							class="form-control" id="userpw" placeholder="write password"
							value=''>
					</div>


					<!-- textarea에 엔터치거나 스페이스있으면 웹에서도 그렇게 뜸!! 주의!! -->
					<div class="form-group">
						<!-- 
						<label for="labelTextArea">이름</label>
						<textarea class="form-control" rows="5" name="content" placeholder="write content" readonly><c:out value="${member.userName }" /></textarea>
						 -->
						 <label for="labelTitle">이름</label> <input type="text"
							class="form-control" id="userName" placeholder="write new name"
							value='<c:out value="${member.userName }"/>' readonly>
					</div>

					<div class="form-group">
						<label for="labelWriter">register date</label> <input type="text"
							class="form-control" name="writer" placeholder="write writer"
							value='<c:out value="${member.regDate }"/>' readonly>
					</div>
					
					<sec:authentication property="principal" var="pinfo"/>
					
					<sec:authorize access="isAuthenticated()">
						<button type="button" id="withdrawBtn" class="btn btn-danger">회원탈퇴</button>		
					</sec:authorize>
					
					<button type="button" id="listBtn" class="btn btn-primary">List</button>
					<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
							<!-- end of button -->
				</form> <!-- end of a post -->

			</div>
			
		</div> <!-- end of hidden info regarding post -->
	</div> <!-- end of the entire div -->
		 
	
	<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
	
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
	<script type="text/javascript" src="/resources/js/member.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var csrfHeaderName="${_csrf.headerName}";
			var csrfTokenValue="${_csrf.token}";
			
			//Ajax spring security header...
			$(document).ajaxSend(function(e, xhr, options){
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			}); 
			
			var withdrawBtn = $("#withdrawBtn");
			
			var userid = '<c:out value="${member.userid}"/>';
			var userpw = null;
			//var userName = null;
			
			var vo = null;
			
			
			withdrawBtn.on("click", function(e){
				
				userpw= $('#userpw').val();
				//userName = $('#userName').val();
				
				console.log("user password: "+userpw);
				
				vo = {userid : userid, 
					  userpw: userpw,
					  };
				
				if(!userpw){
					alert("정보를 입력하지 않으셨습니다!");
					return;
				}
				
				//console.log("vo: "+vo);
				//console.log(vo.userpw+"   "+vo.userName);
				
				memberService.remove(vo, function(result){
					//alert(result);
					alert("success!");
					
					/*
						location.href 와 location.replace()의 차이
						
						location.href는 url이동시 대부분 사용.
						웹브라우저의 히스토리가 저장되고, 브라우저 뒤로가기시 location.href를 호출한 페이지로 이동함.
						사용방법은 location.href = 'page.html';
					
						location.replace()는 히스토리가 저장안됨. 뒤로가기시 히스토리에 가장 이전페이지로 이동함.
						뒤로가기로 location.replace()를 호출한 페이지로 갈 수 없음.
						따라서 주로 결제한 직후 이걸 씀.\
					*/
					location.replace('/'); 
					location.href= "/";
					//window.opener.location.href = "";
				});
			});
			
			
			$("#searchForm button").on("click", function(e){
				if(!searchForm.find("option:selected").val()){
					alert("검색종류를 선택하세요.");
					return false;
				}
				
				if(!searchForm.find("input[name='keyword']").val()){
					alert("키워드를 입력하세요");
					return false;
				}
				
				searchForm.find("input[name='pageNum']").val("1"); //무조건 1페이지로
				e.preventDefault();
				
				searchForm.submit();
			});
			
			$("#listBtn").on("click", function(){
				self.location="/"; 
			}); 
			
		});
	</script>
	 
</body>

</html>