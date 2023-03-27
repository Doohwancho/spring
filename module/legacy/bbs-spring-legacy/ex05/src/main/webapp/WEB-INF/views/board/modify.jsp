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



<title>Hello, BattleGround!</title>
</head>

<body>
   	<!-- thumbnail picture -->
   	<div class='bigPictureWrapper'>
   		<div class='bigPicture'>
   			
   		</div>
   	</div>
   	
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="list">Autochess</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link" href="list">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Register</a>
				</li>
			</ul>
		</div>
	</nav>

	<div class="c1" style="padding-left: 1rem;">
		<h1>Board Register</h1>
		<br>

		<div class="card w-75 border-secondary mb-3">
			<h5 class="card-header">Register</h5>
			<div class="card-body">
			
				<form role="form" action="/board/modify" method="post">
				
					<div class="form-group">
						<label for="exampleInputPassword1">Bno</label> <input
							type="text" class="form-control" id="exampleInputPassword1"
							name="bno" placeholder="Bno" value='<c:out value="${board.bno }"/>' readonly>
					</div>
					
					<div class="form-group form-group col-md-20">
						<label for="exampleInputPassword1">Title</label> 
						<input type="text" class="form-control" id="exampleInputPassword1"
							name="title" placeholder="write title" value='<c:out value="${board.title }"/>'>
					</div>
					<div class="form-group"><label for="exampleFormControlTextarea1">Text Area</label><textarea class="form-control" rows="5" name="content" placeholder="write content"><c:out value="${board.content }"/></textarea></div>
	
					<div class="form-group">
						<label for="exampleInputPassword1">Writer</label> <input
							type="text" class="form-control" id="exampleInputPassword1"
							name="writer" placeholder="write writer" value='<c:out value="${board.writer }"/>' readonly>
					</div>
					
					<div class="form-group">
						<label for="exampleInputPassword1">regdate</label> <input
							type="text" class="form-control" id="exampleInputPassword1"
							name="regdate" placeholder="write writer" value='<fmt:formatDate pattern = "yyyy/MM/dd" value="${board.regdate }"/>' readonly>
					</div>
					
					<div class="form-group">
						<label for="exampleInputPassword1">updateDate</label> <input
							type="text" class="form-control" id="exampleInputPassword1"
							name="updateDate" placeholder="write writer" value='<fmt:formatDate pattern = "yyyy/MM/dd" value="${board.updateDate }"/>' readonly>
					</div>
					
					<sec:authentication property="principal" var="pinfo"/>
					<sec:authorize access="isAuthenticated()">
						<c:if test="${pinfo.username eq board.writer}">
							<button type="submit" data-oper='modify' class="btn btn-primary">Modify</button>
							<button type="submit" data-oper='remove' class="btn btn-primary">Remove</button>
						</c:if>
					</sec:authorize>
					
					
					<button type="submit" data-oper='list' class="btn btn-primary">List</button>
					
					<!-- type='hidden' -->
					<input type='hidden' name='bno' value='<c:out value="${board.bno }"/>'>
					<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }"/>'>
					<input type='hidden' name='amount' value='<c:out value="${cri.amount }"/>'>
					<input type='hidden' name='type' value='<c:out value="${cri.type }"/>'/>
					<input type='hidden' name='keyword' value='<c:out value="${cri.keyword }"/>'/>
					<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
				</form>
			</div>
			<div class="card w-75 border-secondary mb-3" style="width: 18rem;"> 
			  <div class="card-header"> 
			    	Files
			  </div> 
			  
			  <ul class="list-group list-group-flush" id = "uploadFiles">
			  
			  	<div class='list-group-item uploadDiv'>
					<input type='file' name='uploadFile' multiple>
				</div>
				
			    <li class="list-group-item">
			    	<div class='uploadResult'> 
			    		<ul>
			    		
			    		</ul>
			    	</div>
			    </li>
			  </ul>
			</div>
		</div>
	</div>

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
		
	<script type="text/javascript">
	
		$(document).ready(function(){
			//Spring-security 적용 이 후, 게시물 등록 위해 ajax에 X-CSRF-TOKEN 처리
			var csrfHeaderName="${_csrf.headerName}";
			var csrfTokenValue="${_csrf.token}";
			
			var formObj = $("form");
			
			$('button').on("click", function(e){
				/*
					글을 작성할때나, 회원가입을 할때 버튼 한번 클릭할때마다 페이지가 쭉 위로 올라가는 경험을 해보신분들은 그 짜증스러움을 익히 아시리라 생각합니다. ㅠㅠ
					이 브라우저 행동을 막기위해서 사용하는게 preventDefault 입니다.
					preventDefault 는 a 태그 처럼 클릭 이벤트 외에 별도의 브라우저 행동을 막기 위해 사용됩니다.
				*/
				e.preventDefault(); 
				
				var operation = $(this).data("oper");
				
				console.log(operation);
				
				if(operation === 'remove'){
					formObj.attr("action", "/board/remove");
				} else if(operation === 'list'){
					//move to list
					formObj.attr("action", "/board/list").attr("method","get");
					
					var pageNumTag = $("input[name='pageNum']").clone();
					var amountTag = $("input[name='amount']").clone();
					var keywordTag = $("input[name='keyword']").clone();
					var typeTag = $("input[name='type']").clone();
					
					formObj.empty();
					formObj.append(pageNumTag);
					formObj.append(amountTag);
					formObj.append(keywordTag);
					formObj.append(typeTag);
				} else if(operation == 'modify'){
					console.log("submit clicked!");
					
					var str = "";
					
					$(".uploadResult ul li").each(function(i, obj){
						
						var jobj = $(obj);
						
						console.dir(obj);
						
						str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
						str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
						str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
						str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data("type")+"'>";
						  
					});   
					formObj.append(str);
				}
				formObj.submit();
			});
			
			
			var bno = '<c:out value="${board.bno}"/>';
			$.getJSON("/board/getAttachList", {bno:bno}, function(arr){
				console.log(arr);
				var str="";
				
				$(arr).each(function(i, attach){
					//image type
					if(attach.fileType){
						var fileCallPath = encodeURIComponent( attach.uploadPath+ "/s_" + attach.uuid+"_"+attach.fileName);
						str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'><div>";
						str += "<span> "+attach.fileName+"</span>";
						str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image' class='close' aria-label='Close'><span aria-hidden='true'>&times;</span></button><br>";
						str += "<img src='/display?fileName="+fileCallPath+"'>";
						str += "</div>";
						str += "</li>";
					} else {
						str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'><div>";
						str += "<span> "+attach.fileName+"</span><br/>";
						str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' class='close' aria-label='Close'><span aria-hidden='true'>&times;</span></button><br>";
						str += "<img src='/resources/img/attach.png'>";
						str += "</div>";
						str += "</li>";
					}
				});
				$(".uploadResult ul").html(str);
				
			});
			
			$(".uploadResult").on("click", "button", function(e){
				console.log("delete file");
				
				if(confirm("Remove this file?")){
					var targetLi = $(this).closest("li");
					targetLi.remove();
				}
				
			});
			
			
			//첨부파일 추가
			var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
			var maxSize = 5242880;
			
			function checkExtension(fileName, fileSize){
				if(fileSize >= maxSize){
					alert("파일 사이즈 초과!");
					return false;
				}
				
				if(regex.test(fileName)){
					alert("해당 종류의 파일은 업로드 할 수 없습니다.");
					return false;
				}
				return true;
			}
			
			$("input[type='file']").change(function(e){
				
				var formData = new FormData();
				var inputFile = $("input[name='uploadFile']");
				var files = inputFile[0].files;
				
				for(var i = 0; i < files.length; i++){
					if(!checkExtension(files[i].name, files[i].size)){
						return false;
					} 
					formData.append("uploadFile", files[i]);
				}
				
				
				//주의! - processData: false, contentType: false 안하면 전송이 안된다!! 
				$.ajax({
					url: '/uploadAjaxAction',
					processData: false,
					contentType: false,
					data: formData,
					type: 'POST',
					beforeSend: function(xhr){
						xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
					},
					dataType:'json', 
					success: function(result){
						//alert("Uploaded");
						console.log("result received!");
						console.log(result);
						
						showUploadResult(result);
						
						//$(".uploadDiv").html(cloneObj.html());
					} 
				}); //$.ajax
			});
			
			function showUploadResult(uploadResultArr){
				if(!uploadResultArr || uploadResultArr.length == 0) return;
				
				var uploadUL = $(".uploadResult ul");
				
				var str = "";
				
				$(uploadResultArr).each(function(i, obj){
					if(obj.image){
						var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid+"_"+obj.fileName); //파일이름에 포함된 공백이나 한글 바르게 인코딩 처리
						
						str += "<li data-path='"+ obj.uploadPath +"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'><div>";
						str += "<span> "+obj.fileName+"</span>";
						str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image' class='close' aria-label='Close'><span aria-hidden='true'>&times;</span></button><br>";
						str += "<img src='/display?fileName=" + fileCallPath + "'>";
						str += "</div></li>";
							
					} else {
						var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid+"_"+obj.fileName);
						var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
						
						str += "<li data-path='"+ obj.uploadPath +"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'><div>";
						str += "<span> "+obj.fileName+"</span>";
						str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' class='close' aria-label='Close'><span aria-hidden='true'>&times;</span></button><br>";
						str += "<img src='/img/attach.png'></a>";
						str += "</div></li>";
					}
				});   
				
				uploadUL.append(str);
			};
			
		});
	
	</script>
</body>

</html>