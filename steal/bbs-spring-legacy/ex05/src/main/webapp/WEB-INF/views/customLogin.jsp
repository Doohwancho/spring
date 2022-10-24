<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Custom Login Page</title>

	<style>
        /* @import "compass/css3"; */

        *,
        *:before,
        *:after {
            box-sizing: border-box;
        }

        html {
            overflow-y: scroll;
        }

        body {
            background: #c1bdba;
            font-family: 'Titillium Web', sans-serif;
        }

        a {
            text-decoration: none;
            color: #1ab188;
            transition: 0.5s ease;
        }

        a:hover {
            color: #179b77;
        }

        .form {
            background: rgba(19, 35, 47, .9);
            padding: 40px;
            max-width: 600px;
            margin: 40px auto;
            border-radius: 4px;
            box-shadow: 0 4px 10px 4px rgba(19, 35, 47, .3);
        }

        .tab-group {
            list-style: none;
            padding: 0;
            margin: 0 0 40px 0;
        }

        .tab-group:after {
            content: "";
            display: table;
            clear: both;
        }

        .tab-group li a {
            display: block;
            text-decoration: none;
            padding: 15px;
            background: rgba(160, 179, 176, .25);
            color: #a0b3b0;
            font-size: 20px;
            float: left;
            width: 50%;
            text-align: center;
            cursor: pointer;
            transition: 0.5s ease;
        }

        .tab-group li a:hover {
            background: #179b77;
            color: #fff;
        }

        .tab-group .active a {
            background: #1ab188;
            color: #fff;
        }

        .tab-content>div:last-child {
            display: none;
        }

        h1 {
            text-align: center;
            color: #fff;
            font-weight: 300;
            margin: 0 0 40px;
        }

        label {
            position: absolute;
            transform: translateY(6px);
            left: 13px;
            color: rgba(255, 255, 255, .5);
            transition: all 0.25s ease;
            pointer-events: none;
            font-size: 22px;
        }

        label .req {
            margin: 2px;
            color: #1ab188;
        }

        label.active {
            transform: translateY(50px);
            left: 2px;
            font-size: 14px;
        }

        label.active .req {
            opacity: 0;
        }

        label.highlight {
            color: #fff;
        }

        input:not(.chkbox),
        textarea {
            font-size: 22px;
            
            
            display: block; 
            width: 100%;
            height: 100%;
            
            
            padding: 5px 10px;
            background: none;
            background-image: none;
            border: 1px solid #a0b3b0;
            color: #fff;
            border-radius: 0;
            transition: border-color 0.25s ease, box-shadow 0.25s ease;
        }
         
        input[id="cb1"]{
        	position:relative;
        	display: inline-block;
        	margin-left:170px;
			width: 18px;
			height: 38px; 
			border: 20px solid #bcbcbc;
			cursor: pointer;
            /* transition: border-color 0.25s ease, box-shadow 0.25s ease;*/
        }

        input:focus,
        textarea:focus {
            outline: 0;
            border-color: #1ab188;
        }

        textarea {
            border: 2px solid #a0b3b0;
            resize: vertical;
        }

        .field-wrap {
            position: relative;
            margin-bottom: 40px;
        }

        .top-row:after {
            content: "";
            display: table;
            clear: both;
        }

        .top-row>div {
            float: left;
            width: 48%;
            margin-right: 4%;
        }

        .top-row>div:last-child {
            margin: 0;
        }

        .button {
            border: 0;
            outline: none;
            border-radius: 0;
            padding: 15px 0;
            font-size: 2rem;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 0.1em;
            background: #1ab188;
            color: #fff;
            transition: all 0.5s ease;
            
        }

        .button:hover,
        .button:focus {
            background: #179b77;
        }

        .button-block {
            display: block;
            width: 100%;
        }

        .forgot {
            margin-top: -20px;
            text-align: right;
        }
    </style>
</head>

<!-- 
	* adders
	<h2><c:out value="${error }"/></h2>
	<h2><c:out value="${logout }"/></h2>
 -->

<body>
    
	<div class="form">

        <div id="login">
            <h1>Welcome Back!</h1>
            
            <form action="/login" method="post">

                <div class="field-wrap">
                    <label>
                        User ID<span class="req">*</span>
                    </label>
                    <input type="text" name='username' id="userId" value="" autofocus/>
                </div>

                <div class="field-wrap">
                    <label>
                        Password<span class="req">*</span>
                    </label>
                    <input type="password" name='password' id="userPassword" value="" /> 
                </div>
                <div class="field-wrap"> 
                    <label>Remember Me</label><input type="checkbox" class="chkbox" id="cb1" name='remember-me'>
                </div>
                <!-- <p class="forgot"><a href="#">Forgot Password?</a></p>  -->
                <button class="button button-block btnSubmit">Log In</button>
                
                <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />

            </form>
        </div>

    </div> <!--/form -->

    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>

    <script>
  
    	
        $(".form").find('input, textarea').on('keyup blur focus', function (e) {

            var $this = $(this),
                label = $this.prev('label');

            if (e.type === 'keyup') {
                if ($this.val() === '') {
                    label.removeClass('active highlight');
                } else {
                    label.addClass('active highlight');
                }
            } else if (e.type === 'blur') {
                if ($this.val() === '') {
                    label.removeClass('active highlight');
                } else {
                    label.removeClass('highlight');
                }
            } else if (e.type === 'focus') {

                if ($this.val() === '') {
                    label.removeClass('highlight');
                }
                else if ($this.val() !== '') {
                    label.addClass('highlight');
                }
            }
        });

        
        
		$(".btnSubmit").on("click", function(e){
			e.preventDefault();
			
        	if($("#userId").val()==""){
				alert("아이디를 입력해주세요.");
				$("#userid").focus();
				return false;
			}
			if($("#userPassword").val()==""){
				alert("비밀번호를 입력해주세요.");
				$("#userpw").focus();
				return false;
			}
			
			$("form").submit();
		});
        
        
    </script>
</body>

</html>