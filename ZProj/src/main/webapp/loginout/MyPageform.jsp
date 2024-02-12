<%@page import="model.MemberDTO"%>
<%@page import="model.bbs.BBSDao"%>
<%@page import="com.oracle.wls.shaded.org.apache.xalan.lib.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
	<link rel="stylesheet" href="../css/MyPageform.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"   
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="   
	crossorigin="anonymous">
    </script>
<%--
	MemberDTO mto = new MemberDTO();
	BBSDao dao = new BBSDao(application);
	String id = (String)session.getAttribute("USER-ID");
	mto = dao.mydata(id);
--%>
</head>
	<section class="sign-up-form">
		<div class="back">
			<a id="back" class="" type="button" href="<c:url value="/loginout/${referer}"/>">back</a>
		</div>
		<h1>My Page</h1>
		<form action="<c:url value="/signup/mypageupdate.do"/>" class="main-form" method="post">
			<div class="int-area">
				<label for="id">UserName</label>
				<input class="text" type="text" name="id" placeholder="아이디를 입력하세요" value='${USER_ID }' readonly>
			</div>
			<div class="int-area">
				<label for="pwd">PassWord</label>
				<input class="text" type="password" name="pwd" placeholder="변경할 비밀번호를 입력하세요">
			</div>
			<div class="int-area">
				<label for="pwd2">PassWord Chk</label>
				<input class="text" type="password" name="pwd2" placeholder="변경할 비밀번호를 한번 더 입력하세요">
			</div>
			<div class="int-area">
				<label for="name">NAME</label>
				<input class="text" type="text" name="name" placeholder="이름을 입력하세요" value='${USER_NAME}'>
			</div>
			<div class="int-area">
				<label for="email">EMAIL</label>
				<input class="text" type="text" name="email" placeholder="메일을 입력하세요" value='${USER_EMAIL}'>
			</div>
			<div class="int-area">
				<label for="gender">Gender</label>
				<div class="chk-div">
					<c:choose>
						<c:when test='${USER_GENDER == "m"}'>
							<label class="chk-la" for="man">남자</label>
							<input class="chk-in" type="checkbox" name="gender" value="m" id="man" checked>
														<label class="chk-la" for="woman">여자</label>
							<input class="chk-in" type="checkbox" name="gender" value="w" id="woman">
						</c:when>
						<c:otherwise>
							<label class="chk-la" for="man">남자</label>
							<input class="chk-in" type="checkbox" name="gender" value="m" id="man">
							<label class="chk-la" for="woman">여자</label>
							<input class="chk-in" type="checkbox" name="gender" value="w" id="woman" checked>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="int-area">
				<label for="inter">Inter</label>
				<div class="chk-div">
						<label class="chk-la" for="POL">정치</label>
						<input class="chk-in" type="checkbox" name="inter" value="POL" id="POL" <c:if test="${fn:contains(USER_INTERS, 'POL')}">checked</c:if>>
						<label class="chk-la" for="ECO">경제</label>
						<input class="chk-in" type="checkbox" name="inter" value="ECO" id="ECO" <c:if test="${fn:contains(USER_INTERS, 'ECO')}">checked</c:if>>
						<label class="chk-la" for="ENT">연예</label>
						<input class="chk-in" type="checkbox" name="inter" value="ENT" id="ENT" <c:if test="${fn:contains(USER_INTERS, 'ENT')}">checked</c:if>>
						<label class="chk-la" for="SPO">스포츠</label>
						<input class="chk-in" type="checkbox" name="inter" value="SPO" id="SPO" <c:if test="${fn:contains(USER_INTERS, 'SPO')}">checked</c:if>>
				</div>
			</div>	
			<div class="int-area">
				<label class="chk-la" for="grade">Grade</label>
				<select class="chk-select" name="grade">
					<option value="">학력을 선택하세요</option>
					<option value="ELE" ${USER_EDU == 'ELE' ? "selected":''}>초등학교</option>
					<option value="MID" ${USER_EDU == 'MID' ? "selected":''}>중학교</option>
					<option value="HIG" ${USER_EDU == 'HIG' ? "selected":''}>고등학교</option>
					<option value="UNI" ${USER_EDU == 'UNI' ? "selected":''}>대학교</option>
				</select>
			</div>
			<div class="int-area">
				<label class="chk-la" for="self">Introduce</label>
				<textarea class="text text-area" rows="5" name="self">${USER_INFO }</textarea>
			</div>
			<div class="btn-area">
				<button id="btn" type="submit">Update</button>
			</div>		
		</form>
	</section>
<!--container-->
<script>

</script>


