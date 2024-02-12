<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../css/loginform.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"   
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="   
	crossorigin="anonymous">
    </script>
	<style>
	body{
		background: url("https://mblogthumb-phinf.pstatic.net/MjAxNzA1MThfMTk0/MDAxNDk1MDcyMDU3NzQ1.h1e1PlWtySebc3uX83A2yDwWTC6r9aF69oyWswhNuYIg.P0lx69KkoOZZWG-rXzRzFA8BvX4Bn-9PpnfYKKIcni4g.PNG.seinlove0523/image_5740981981495070704213.png?type=w800") no-repeat center;
	}
	</style>
</head>
<body>
    <section class="login-form">
        <h1>Change Password</h1>

		<form action="<c:url value="/find/set.do"/>" method="POST">
			<div class="int-area">
	            <input type="text" name="code" id="code" autocomplete="off" required>
	            <label for="code">Code Check</label>
	        </div>
	        <div class="int-area">
	            <input type="password" name="cpwd" id="cpwd" autocomplete="off" required>
	            <label for="cpwd">Change PWD</label>
	        </div>
	        <div class="int-area">
	            <input type="password" name="cpwdchk" id="cpwdchk" autocomplete="off" required>
	            <label for="cpwdchk">Change PWD CHK</label>
	        </div>
	        <div class="btn-area">
	        	<button id="btn" type="submit">Change Password</button>
	    	</div>
	    </form>
		</section>
</body>
</html>
<script>
	//서블릿에서 전달된 비밀번호 변경 성공 여부 변수 가져오기
	var pwdChangedSuccessfully = "${pwdChangedSuccessfully}";
	var msg = "${MSG}";
	//비밀번호 변경이 성공했을 때 메시지 창 띄우기
	if (pwdChangedSuccessfully) { // 비밀번호 변경 성공 여부를 확인하는 조건
	    // 메시지 창 띄우기
	    alert("비밀번호가 성공적으로 변경되었습니다.");
	
	    // 확인 클릭 시 팝업 창 닫기
	    window.close();
	}
	if(msg != ""){
		alert(msg);
	}
</script>