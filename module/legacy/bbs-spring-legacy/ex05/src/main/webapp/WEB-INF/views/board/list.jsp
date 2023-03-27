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


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
 
<title>Welcome to battleground!</title>
</head>

<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="list">Autochess</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav"> 
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link" href="list">Home<span class="sr-only">(current)</span></a></li>
				
				
				<sec:authorize access="isAnonymous()">
					<li class="nav-item">
						<form id='registerId' method='get' action="/member/register">
							<a class="nav-link">Register</a>	
						</form>
					</li>
					<li class="nav-item">
						<form id='loginId' method='get' action="/customLogin">
							<a class="nav-link">Login</a>		
						</form>
					</li>
				</sec:authorize>
				 
				
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item">
						<a class="nav-link" href="#" onclick="document.getElementById('member-update-form').submit();">Member-Info</a>
						<form id="member-update-form" action='<c:url value='/member/memberUpdate'/>' method="GET">
						   <!-- <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>  -->
						</form> 
					</li> 
					 
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
	</nav>


	<div class="c1" style="padding: 1rem;">
		<h1>Welcome to BattleGround!</h1>
		<br>
		<table class="table">
			<button type="button" class="btn btn-dark btn-secondary float-right" id="regBtn">write</button>
			<thead class="thead-dark">
				<tr>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">작성일</th>
					<th scope="col">수정일</th>
				</tr>
			</thead>


			<c:forEach items="${list}" var="board">
				<tr>
					<td><c:out value="${board.bno }" /></td>
					<td>
						<!-- 
						<a href='/board/get?bno=<c:out value="${board.bno }"/>'>
							<c:out value="${board.title }" />
						</a>
						 -->
						 <!-- 
						 	$(".move").on("click", function(e){
								e.preventDefault();
								actionForm.append("<input type='hidden' name='bno' value='" + $(this).attr("href")+"'>");
								actionForm.attr("action", "/board/get");
								actionForm.submit();
							});
						  -->
						 <a class='move' href='<c:out value="${board.bno }"/>'><c:out value="${board.title }"/><b> [<c:out value="${board.replyCnt }"/>]</b></a>
					</td>
					<td><c:out value="${board.writer }" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${board.regdate }" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${board.updateDate }" /></td>
				</tr>
			</c:forEach>
		</table>
		
		
		<!-- 검색 -->
		<div class='row'>
			<div class="col-lg-12">
				<form id='searchForm' action="/board/list" method='get'>
			      <select name='type'>
			        <option value="" <c:out value="${pageMaker.cri.type == null?'selected':''}"/>>--</option>
			        <option value="T" <c:out value="${pageMaker.cri.type eq 'T'?'selected':''}"/>>제목</option>
			        <option value="C" <c:out value="${pageMaker.cri.type eq 'C'?'selected':''}"/>>내용</option>
			        <option value="W" <c:out value="${pageMaker.cri.type eq 'W'?'selected':''}"/>>작성자</option>
			        <option value="TC" <c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}"/>>제목 or 내용</option>
			        <option value="TW" <c:out value="${pageMaker.cri.type eq 'TW'?'selected':''}"/>>제목 or 작성자</option>
			        <option value="TWC" <c:out value="${pageMaker.cri.type eq 'TWC'?'selected':''}"/>>제목 or 내용 or 작성자</option>
			      </select> 
			      
					<input type="text" placeholder="Search" name='keyword' value='<c:out value="${pageMaker.cri.keyword }"/>'/> 
					<input type='hidden' name='pageNum' value='<c:out value="${pageMaker.cri.pageNum }"/>'/>
					<input type='hidden' name='amount' value='<c:out value="${pageMaker.cri.amount }"/>'/>
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>	
		</div>
		
		<div class='float-right'>
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-end">
					<c:if test="${pageMaker.prev }">
						<li class="page-item"><a class="page-link" href="${pageMaker.startPage -1 }">Previous</a></li>
					</c:if>
					
					<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
						<li class="page-item ${pageMaker.cri.pageNum == num ? 'active':'' }"><a class="page-link" href="${num }">${num }</a></li>
					</c:forEach>
					
					<c:if test="${pageMaker.next }">
						<li class="page-item"><a class="page-link" href="${pageMaker.endPage+1 }">Next</a></li>
					</c:if>
				</ul>
			</nav>
		</div>
		 
	</div>



	<div class="modal" id= "myModal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Modal title</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>Modal body text goes here.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Save changes</button>
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<form id='actionForm' action="/board/list" method='get'>
		<input type='hidden' name='pageNum' value="${pageMaker.cri.pageNum }"/>
		<input type='hidden' name='amount' value="${pageMaker.cri.amount }"/>
		<input type='hidden' name='type' value="${pageMaker.cri.type }"/>
		<input type='hidden' name='keyword' value="${pageMaker.cri.keyword }"/>
	</form>



	<script
		  src="https://code.jquery.com/jquery-3.5.1.js"
		  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		  crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
		
	<!-- register 시 도배방지 modal추가. 주의사항 : jquery이용하는 것이기 때문에, script jquery이후에 적어야 한다. -->
	<script type="text/javascript">
		
		$(document).ready(function() {
			var result = '<c:out value="${result}"/>';
			
			checkModal(result);
			
			history.replaceState({}, null, null);
			
			function checkModal(result){
				if(result==='' || history.state){
					return;
				}
				if(parseInt(result) > 0){ 
					$(".modal-body").html("게시글 "+parseInt(result)+" 번이 등록되었습니다.")

					$("#myModal").modal("show"); 
				} 
			} 
			$("#regBtn").on("click", function(){
				self.location="/board/register"; 
			}); 
			
			var actionForm = $("#actionForm");
			
			$(".page-item a").on("click", function(e){
				e.preventDefault(); //a태그 클릭해도 페이지 이동 없도록 처리
				
				console.log('click');
				
				actionForm.find("input[name='pageNum']").val($(this).attr("href"));
				actionForm.submit();
			});
			
			$(".move").on("click", function(e){
				e.preventDefault();
				actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr('href')+"'>");
				actionForm.attr("action", "/board/get");
				actionForm.submit();
			});
			
			var searchForm = $("#searchForm");
			
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
			
			
			$("#registerId").on("click", function(){
				history.replaceState({}, null, location.pathname);
				document.getElementById('registerId').submit();
				//self.location="/member/memberInfo"; 
			}); 
			
			
			$("#loginId").on("click", function(){
				history.replaceState({}, null, location.pathname);
				document.getElementById('loginId').submit();
				//self.location="/customLogin";  //절대경로 지정해 주는 방법. 단, get/post로 못넘김
			});
			
			/*
			$("#logoutId").on("click", function(e){
				//e.stopImmediatePropagation(); 
				//e.stopPropagation();
				//e.preventDefault();
				
				document.getElementById('logoutId').submit();
				
				//return false;
			});
			*/
			 
			
		}); 
		
	</script>
</body>
</html>

