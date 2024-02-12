<%@page import="model.PagingUtil"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.bbs.BBSDto"%>
<%@page import="java.util.List"%>
<%@page import="model.bbs.BBSDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<link rel="stylesheet" href="../css/Listform.css">
</head>


<jsp:include page="/template/Top.jsp" />
	<div class="container main-form" style="margin-top: 50px">
		<div class="main-area">
			<h1 class="top-h">
				목록 페이지 <small>(5/24)</small>
			</h1>
		</div>

		<div class="btn-area">
			<button id="btn" onclick="window.location.href='http://localhost:8080/ZProj/loginout/Writeform.jsp'">글등록</button>
			<!--<a href="<c:url value="/loginout/Writeform.jsp"/>">글등록</a>-->
		</div>
		<table class="table table-dark table-hover text-center">
			<thead>
				<tr>
					<th class="col-1">번호</th>
					<th>제목</th>
					<th class="col-2">작성자</th>
					<th class="col-1">조회수</th>
					<th class="col-2">작성일</th>
					<th class="col-1">좋아요</th>
				</tr>
			</thead>
			<tbody class="table-sm">
				<c:choose>
					<c:when test="${empty items}">
						<tr>
							<td colspan="5">등록된 글이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="item" items="${items }">
							<tr id="${item.no}">
								<th class="col-1">${item.no}</th>
								<th><a href="<c:url value="/view/click.do?no=${item.no }"/>"> ${item.title}</a></th>
								<th class="col-2">${item.name}</th>
								<th class="col-1">${item.hitCount}</th>
								<th class="col-2">${item.postDate}</th>
								<th class="col-1">

									<button id="likebtn" onclick="likeButtonClicked(${item.no})">
									<i id="likeicon" class="fa fa-heart" style="font-size:16px;color:white"></i>
											&nbsp;</button>
								<!-- 
								 <button class="w3-button w3-black w3-round" id="rec_update">
									<i class="fa fa-heart" style="font-size:16px;color:red"></i>
									&nbsp;<span class="rec_count"></span>
								</button> 
								 -->
								</th>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<!-- 페이징 출력 -->
		<div class="paging">
			<span>1 2 3 4 5</span>
		</div>
		<!-- 검색 UI -->
		<form class="form-inline justify-content-center" method="post">
			<select class="form-control" name="searchColumn">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="name">작성자</option>
			</select> 
			<input type="text" class="form-control mx-2 my-2"
				placeholder="검색어를 입력하세요" name="searchWord" />
			<button type="submit" class="btn btn-primary">검색</button>
		</form>
	</div>
<!--container-->
<script>
	//document.querySelector('#')	
	function likeButtonClicked(no) {
	    var xhr = new XMLHttpRequest();
	    var url = "<c:url value='/likebtn/oneclick.do'/>"; // 서블릿 URL 설정
	
	    xhr.open("POST", url, true);
	    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
	    xhr.onreadystatechange = function () {
	        if (xhr.readyState === 4) {
	            if (xhr.status === 200) {
	                var response = JSON.parse(xhr.responseText);
	                processResponse(response); // 서버로부터 받은 응답 처리 함수 호출
	            } else {
	                console.error('Request failed. Status:', xhr.status);
	            }
	        }
	    };
	
	    var params = "no=" + encodeURIComponent(no); // 좋아요를 누른 글의 번호를 가져와 전송
	    xhr.send(params);
	}
	// 서버로부터 받은 응답 처리하는 함수
	function processResponse(response) {
	    console.log(response); // 서버로부터 받은 응답 확인
	    
	    // 받은 응답에 따라 클라이언트 측에서 원하는 동작 수행
	    var likeButton = document.getElementById("likeicon"); // 좋아요 버튼의 ID나 클래스 등으로 가져와야 합니다.
	    var chk = (`\${response.number}`);
	    var test = document.getElementById(chk);
		var ttest = test.querySelector("#likeicon");
	    if (response.message === "좋아요") {
	    	console.log(ttest);
	    	//console.log(test.lastChild);
	        ttest.style.backgroundColor = "red"; // 좋아요가 되어 있는 경우 버튼 색을 변경합니다.
	    } else if (response.message === "좋아요 취소") {
	    	ttest.style.backgroundColor = "white"; // 좋아요가 취소된 경우 버튼 색을 변경합니다.
	    } else {
	        // 다른 처리가 필요한 경우에 대한 로직을 작성할 수 있습니다.
	    }
	}

/*
	$(function(){
		// 추천버튼 클릭시(추천 추가 또는 추천 제거)
		$("#rec_update").click(function(){
			$.ajax({
				url: "/likebtn/oneclick.do",
	            type: "POST",
	            data: {
	                no: ${no},
	                id: '${id}'
	            },
	            success: function () {
			        recCount();
	            },
			})
		})
	}
*/
	/*
	function likeButtonClicked(no) {
	    var xhr = new XMLHttpRequest();
	    var url = "<c:url value="/likebtn/oneclick.do"/>"; // 서블릿 URL 설정
	
	    
	    xhr.open("POST", url, true);
	    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
	    xhr.onreadystatechange = function () {
	        if (xhr.readyState === 4 && xhr.status === 200) {
	            var response = JSON.parse(xhr.responseText);
	            console.log(response); // 서버로부터 받은 응답 처리
	        }
	    };
	
	    var params = "no=" + encodeURIComponent(no); // 좋아요를 누른 글의 번호를 가져와 전송
	    xhr.send(params);
	}*/

</script>
