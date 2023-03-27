<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업로드 하는 곳</title>
</head>
<body>

	<!-- form enctype="multipart/form-data"를 적어줘야 한다. -->
	<form method="post" action="uploadFormAction" enctype="multipart/form-data">
		<!-- input type="file" 이라고 꼭 적어줘야 함 -->
		<input type='file' name='uploadFile' multiple>
		<button>Submit</button>
	</form>
	
</body>
</html>