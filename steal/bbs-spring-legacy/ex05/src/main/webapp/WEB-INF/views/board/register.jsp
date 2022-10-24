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



<title>Register!</title>

<style>
	.uploadResult{
		width:100%;
		background-color: gray;
	}
	
	.uploadResult ul{
		display:flex;
		flex-flow:row;
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
	
	.bigPictureWrapper{
		position:absolute;
		display: none;
		justify-content: center;
		align-items: center;
		top:0%;
		width:100%;
		height:100%;
		background-color: gray;
		z-index: 100;
		background:rgba(255, 255, 255, 0.5);
	}
	
	.bigPicture{
		position:relative;
		display:flex;
		justify-content: center;
		align-items: center;
	}
	
	.bigPicture img{
		width:600px;
	}
 
</style>

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
				<li class="nav-item active"><a class="nav-link" href="list">Home
						<span class="sr-only">(current)</span>
				</a></li> 
				<!-- 
				<li class="nav-item"><a class="nav-link" href="#">Register</a></li>
				 -->
			</ul>
		</div>
	</nav>

	<div class="c1" style="padding-left: 1rem;">
		<h1>Board Register</h1>
		<br>

		<div class="card w-75 border-secondary mb-3">
			<h5 class="card-header">Register</h5>
			<div class="card-body">
				<form role="form" action="/board/register" method="post">
					<div class="form-group col-md-20">
						<label for="exampleInputPassword1">Title</label> <input
							type="text" class="form-control" name="title"
							placeholder="write title">
					</div>

					<div class="form-group">
						<label for="exampleFormControlTextarea1">Text Area</label>
						<textarea class="form-control" rows="5" name="content"
							placeholder="write content"></textarea>
					</div>

					<div class="form-group">
						<label for="exampleInputPassword1">Writer</label> 
						<input class="form-control" name="writer"
							value='<sec:authentication property="principal.username" />' readonly />
					</div>

					<button type="submit" class="btn btn-primary">Submit</button>
					<button type="reset" class="btn btn-primary">Reset</button>
					<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
				</form>
			</div>
		</div>
		
		<div class="card w-75 border-secondary mb-3"> 
			<div class="card-header">File Attach</div>
	
			<div class="card-body">
			
				<div class='uploadDiv'>
					<input type='file' name='uploadFile' multiple>
				</div>
				
				<div class='uploadResult'>
					<ul>
							
					</ul>
				</div>
				<!-- 
				<div class='bigPictureWrapper'>
					<div class='bigPicture'></div>
				</div>
	
				<button id='uploadBtn'>Upload</button>
	 			-->	
			</div>
	
		</div>
	</div>


	<script src="https://code.jquery.com/jquery-3.5.1.js"
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
		
	<script>
		$(document).ready(function(e){
			//Spring-security 적용 이 후, 게시물 등록 위해 ajax에 X-CSRF-TOKEN 처리
			var csrfHeaderName="${_csrf.headerName}";
			var csrfTokenValue="${_csrf.token}";
			
			var formObj = $("form[role='form']");
			
			$("button[type='submit']").on("click", function(e){
				e.preventDefault();
				console.log("submit clicked!");
				
				var str = "";
				
				$(".uploadResult ul li").each(function(i, obj){
					var jobj = $(obj);
					
					console.dir(jobj); 
					
					str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
					str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
					str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
					str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data("type")+"'>";
				});
				 
				formObj.append(str).submit();  
			});
			
			//파일의 확장자나 크기의 사전 처리
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
			//"button[type='submit']"
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
					beforeSend: function(xhr){
						xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
					},
					data: formData,
					type: 'POST',
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
			
			$(".uploadResult").on("click", "button", function(e){
				console.log("delete file");
				
				var targetFile = $(this).data("file");
				var type = $(this).data("type");
				
				var targetLi = $(this).closest("li");
				
				console.log(targetFile);
				console.log(type);
				console.log(targetLi);
				
				$.ajax({
					url:'/deleteFile',
					data: {fileName: targetFile, type:type},
					beforeSend: function(xhr){
						xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
					},
					dataType:'text',
					type:'POST',
					success:function(result){ 
						alert(result); 
						targetLi.remove();
					}
					
				});
			});
		});
	</script>
</body>

</html>