<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Custom Logout Page</h1>
	
	<form method='post' action="/customLogout">
		<button class="btn btn-success">로그아웃</button>
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />		
	</form>
	<script>
		$(".btn-success").on("click", function(e){
			e.preventDefault();
			alert("로그아웃 하였습니다.");
			$("form").submit();
		});
	</script>
	
	<!-- 
	<c:if test="${param.logout != null}">
		<script>
			$(document).ready(function(){
				alert("로그아웃 하였습니다.");
			});
		</script>
	</c:if>
	 -->
</body>
</html>