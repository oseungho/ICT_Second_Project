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
        <h1>Find Password</h1>
		<form action="<c:url value="/find/find.do"/>" method="POST">
	        <div class="int-area">
	            <input type="text" name="id" id="id" autocomplete="off" required>
	            <label for="id">USERNAME</label>
	        </div>
	        <div class="int-area">
	            <input type="text" name="name" id="name" autocomplete="off" required>
	            <label for="name">NAME</label>
	        </div>
	        <div class="int-area">
	            <input type="text" name="email" id="email" autocomplete="off" required>
	            <label for="email">EMAIL</label>
	        </div>
	        <div class="btn-area">
	        	<button id="btn" type="submit">Find</button>
	    	</div>
	    </form>
		</section>
</body>
</html>
<script>
	var msg = "${MSG}";
	if(msg != ""){
		alert(msg);
	}
</script>