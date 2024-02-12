<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    
    <script src="https://kit.fontawesome.com/0b4621b427.js" crossorigin="anonymous"></script>
    <style>
        .fa-house-chimney:hover, i:hover{
        	color : red;
        }
        .menu-bar{
			height: 50px;
			display: flex;
		}
		.menu-bar>li{
			display: flex;
		    align-items: center; /* 세로 중앙 정렬 */
		    margin-right: 50px;
		}
		.menu-bar > li img{
			padding-right: 0;
		    height: 20px; /* 이미지 높이 조절 */
		    /* 추가적인 스타일링을 위한 속성들 */
		}
        
    </style>
</head>
<body>
	<!-- 네비게이션 바  -->
	<!--상단 고정-->
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">   
      <!--Brand / Logo 표시-->      
      <a class="navbar-brand" href="<c:url value="/main/mainlist.do"/>"><i class="fa-solid fa-house-chimney"></i></a>
      <!-- Navbar text-->
      <span class="navbar-text">
      	<c:if test="${USER_ID != null}">
      		${USER_ID}
      	</c:if>
      </span>
      
      <!-- Toggler/collapsibe Button -->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">   
        <!-- Links -->
        <ul class="navbar-nav mr-3"><!--mr-3은 Navbar text와의 여백용-->
			<div class="menu-bar">
				<li class="nav-item">
					<img src="../images/pythonimg.png" alt="Python Image"><a class="nav-link" href="<c:url value="/list/python.do?list=python"/>" id="navmenu" name="python">PYTHON</a>
				</li>
				<li class="nav-item">
					<img src="../images/htmlimg.png" alt="Html Image"><a class="nav-link" href="<c:url value="/list/html.do?list=html"/>" id="navmenu" name="html">HTML</a>
				</li>
				<li class="nav-item">
					<img src="../images/cssimg.png" alt="Css Image"><a class="nav-link" href="<c:url value="/list/css.do?list=css"/>" id="navmenu" name="css">CSS</a>
				</li>
				<li class="nav-item">
					<img src="../images/javaimg.png" alt="Java Image"><a class="nav-link" href="<c:url value="/list/java.do?list=java"/>" id="navmenu" name="java">JAVA</a>
				</li>
			</div>
			<c:if test="${USER_ID != null}">
				<li class="nav-item">
	            	<a class="nav-link" href='<c:url value="/loginout/loginform.jsp"/>'>로그아웃<i class='fas fa-sign-out-alt'></i></a>
		        </li>
	        </c:if>
          <!--Navbar With Dropdown-->
          <!--하단고정일때는 dropdown을 dropup으로-->
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">메뉴</a>
            <div class="dropdown-menu">
              <a class="dropdown-item" href="<c:url value="/signup/mypage.do"/>">내 정보 <i class="bi bi-info-circle"></i></a>
              <!-- <a class="dropdown-item" href="MyPageform.jsp">내 정보 <i class="bi bi-info-circle"></i></a>-->
              <a class="dropdown-item" href="<c:url value='/menu/List2.ict'/>">자료실 <i class='far fa-folder-open'></i></a>
              <a class="dropdown-item" href="<c:url value='/list/list.do'/>"> 게시판 <i class='fas fa-edit'></i></a>
            </div>
          </li>
          <li class="nav-item">
            <form class="form-inline" action="#">
              <input class="form-control mr-sm-2" type="text" placeholder="검색어 입력">
              <button class="btn btn-success" type="submit">검색</button>
            </form>
          </li>
        </ul>
      </div>
    </nav>