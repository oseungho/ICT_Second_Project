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
	 <script src="../js/mainform.js"></script>
	 <link rel="stylesheet" href="../css/Mainform.css">
 </head>
  <body>
		<!-- 네비게이션 바  -->
		<!--상단 고정-->
	 <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">   
	   <!--Brand / Logo 표시-->    
	   <a class="navbar-brand" href="#"><i class="fa-solid fa-house-chimney"></i></a>
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
					<a class="nav-link" href='<c:url value="/loginout/loginform.jsp"/>'>Logout <i class='fas fa-sign-out-alt'></i></a>
				</li>
			</c:if>
			
            <!--Navbar With Dropdown-->
            <!--하단고정일때는 dropdown을 dropup으로-->					   
		   <li class="nav-item dropdown">
			 <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Menu</a>
			 <div class="dropdown-menu">
			   <a class="dropdown-item" href="<c:url value="/signup/mypage.do"/>">My Info <i class="bi bi-info-circle"></i></a>
			   <!-- <a class="dropdown-item" href="MyPageform.jsp">ë´ ì ë³´ <i class="bi bi-info-circle"></i></a>-->
			   <a class="dropdown-item" href="#">자료실 <i class='far fa-folder-open'></i></a>
			   <a class="dropdown-item" href="<c:url value='/list/list.do'/>">게시판 <i class='fas fa-edit'></i></a>
			 </div>
		   </li>
		   <li class="nav-item">
			 <form class="form-inline" action="#">
			   <input class="form-control mr-sm-2" type="text" placeholder="Input Search?">
			   <button class="btn btn-success" type="submit">Search</button>
			 </form>
		   </li>
		 </ul>
	   </div>
	 </nav>

	<div class="main-form" style="margin-top: 50px">
		<section class="section_first">
			<h1 class="h1_first">Main</h1>			
			<h1 class="h1_second">Page</h1>
		</section>
	</div>
	<div class="content-form" style="margin-top: 50px">
		<section class="section_second">
			<div class="content-left">
				<h3>Recent uploads</h3>
				<table class="table table-dark table-hover text-center">
					<thead>
						<tr>
							<th class="col-2">No</th>
							<th>Title</th>
							<th class="col-2">Writer</th>
							<th>Date</th>
						</tr>
					</thead>
					<tbody class="table-sm">
						<c:choose>
							<c:when test="${empty items}">
								<tr>
									<td colspan="5">No posts have been registered.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="item" items="${items}">
									<tr>
										<th class="col-1">${item.no}</th>
										<th><a href="<c:url value="/view/click.do?no=${item.no }"/>"> ${item.title}</a></th>
										<th class="col-2">${item.name}</th>
										<th>${item.postDate}</th>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			<div class="content-right">
				<h3>Popular Post</h3>
				<table class="table table-dark table-hover text-center">
					<thead>
						<tr>
							<th class="col-2">No</th>
							<th>Title</th>
							<th class="col-2">Writer</th>
							<th>Date</th>
						</tr>
					</thead>
					<tbody class="table-sm">
						<c:choose>
							<c:when test="${empty items}">
								<tr>
									<td colspan="5">No posts have been registered.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="item" items="${items}">
									<tr>
										<th class="col-1">${item.no}</th>
										<th><a href="<c:url value="/view/click.do?no=${item.no }"/>"> ${item.title}</a></th>
										<th class="col-2">${item.name}</th>
										<th>${item.postDate}</th>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</section>
	</div>
<!--container-->
