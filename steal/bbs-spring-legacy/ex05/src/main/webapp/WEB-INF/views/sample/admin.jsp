<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>/sample/admin page</h1>
	
	<p>principal : <sec:authentication property="principal" /></p>
	<p>MemberVO : <sec:authentication property="principal.member" /></p>
	<p>������̸� : <sec:authentication property="principal.member.userName" /></p>
	<p>����ھ��̵� : <sec:authentication property="principal.username" /></p>
	<p>����� ���� ����Ʈ : <sec:authentication property="principal.member.authList" /></p>
	
	<a href="/customLogout">logout</a>
	
	<!-- 
	principal : org.zerock.domain.CustomUser@bc1272a6: Username: admin90; Password: [PROTECTED]; Enabled: true; AccountNonExpired: true; credentialsNonExpired: true; AccountNonLocked: true; Granted Authorities: ROLE_ADMIN

	MemberVO : MemberVO(userid=admin90, userpw=$2a$10$9nWBfvzMgcTtbYcLcjwz9eQm2iLV6D5djURruovmugQHBeBcMCzuy, userName=������90, enabled=false, regDate=Wed Jul 08 20:10:28 KST 2020, updateDate=Wed Jul 08 20:10:28 KST 2020, authList=[AuthVO(userid=admin90, auth=ROLE_ADMIN)])
	
	������̸� : ������90
	
	����ھ��̵� : admin90
	
	����� ���� ����Ʈ : [AuthVO(userid=admin90, auth=ROLE_ADMIN)]
	
	 -->
</body>
</html>