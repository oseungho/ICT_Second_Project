<%@page import="com.oracle.wls.shaded.org.apache.xalan.lib.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
	<link rel="stylesheet" href="../css/SignUpform.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"   
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="   
	crossorigin="anonymous">
    </script>
</head>
<!-- 
<c:if test="${ERROR_MSG != null}">
    <script>
    	alert(${ERROR_MSG});
    </script>
</c:if>
 -->

	<section class="sign-up-form">
		<div class="back">
			<a id="back" type="button" href="loginform.jsp">back
			</a>
		</div>
		<h1>Sign Up</h1>
		<form action="<c:url value="/signup/signup.do"/>" class="main-form" method="post">
			<div class="int-area">
				<label for="id">UserName</label>
				<input class="text" type="text" name="id" placeholder="아이디를 입력하세요" autocomplete="off" required>
			</div>
			<div class="int-area">
				<label for="pwd">PassWord</label>
				<input class="text" type="password" name="pwd" placeholder="비밀번호를 입력하세요" autocomplete="off" required>
			</div>
			<div class="int-area">
				<label for="pwd2">PassWord Chk</label>
				<input class="text" type="password" name="pwd2" placeholder="한번 더 비밀번호를 입력하세요" autocomplete="off" required>
			</div>
			<div class="int-area">
				<label for="name">NAME</label>
				<input class="text" type="text" name="name" placeholder="이름을 입력하세요" required>
			</div>
			<div class="int-area">
				<label for="email">EMAIL</label>
				<input class="text" type="text" name="email" placeholder="메일을 입력하세요" value='${USER_EMAIL}'>
			</div>
			<div class="int-area">
				<label for="gender">Gender</label>
				<div class="chk-div">
					<label class="chk-la" for="man">남자</label>
					<input class="chk-in" type="checkbox" name="gender" value="m" id="man">
					<label class="chk-la" for="woman">여자</label>
					<input class="chk-in" type="checkbox" name="gender" value="w" id="woman">
				</div>
			</div>
			<div class="int-area">
				<label for="inter">Inter</label>
				<div class="chk-div">
					<label class="chk-la" for="POL">정치</label>
					<input class="chk-in" type="checkbox" name="inter" value="POL" id="POL">
					<label class="chk-la" for="ECO">경제</label>
					<input class="chk-in" type="checkbox" name="inter" value="ECO" id="ECO">
					<label class="chk-la" for="ENT">연예</label>
					<input class="chk-in" type="checkbox" name="inter" value="ENT" id="ENT">
					<label class="chk-la" for="SPO">스포츠</label>
					<input class="chk-in" type="checkbox" name="inter" value="SPO" id="SPO">
				</div>
			</div>	
			<div class="int-area">
				<label class="chk-la" for="grade">Grade</label>
				<select class="chk-select" name="grade">
					<option value="">학력을 선택하세요</option>
					<option value="ELE">초등학교</option>
					<option value="MID">중학교</option>
					<option value="HIG">고등학교</option>
					<option value="UNI">대학교</option>
				</select>
			</div>
			<div class="int-area">
				<label class="chk-la" for="self">Introduce</label>
				<textarea class="text text-area" rows="5" name="self"></textarea>
			</div>
			<div class="btn-area">
				<button id="btn" type="submit">Submit</button>
			</div>		
		</form>
	</section>
<!--container-->

