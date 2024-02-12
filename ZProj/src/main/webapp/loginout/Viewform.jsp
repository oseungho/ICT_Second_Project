<%@page import="model.PagingUtil"%>
<%@page import="java.util.Map"%>
<%@page import="model.bbs.BBSDto"%>
<%@page import="model.bbs.BBSDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 로그인 여부 판단:필터 사용시 아래 주석처리 -->
<%--@ include file="/common/IsMember.jsp"--%>
<head>
	<link rel="stylesheet" href="../css/Listform.css">
	<style>
		td{
			color : white;
		}
	</style>
</head>

<jsp:include page="/template/Top.jsp" />
<div class="container main-form" style="margin-top: 50px">
	<div class="main-area">
		<h1 class="top-h">상세보기 페이지</h1>
	</div>
	<table class="table table-bordered">
		<tbody class="table-sm">
			<tr>
				<th class="w-25 bg-dark text-white text-center">번호</th>
				<td>${no}</td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">작성자</th>
				<td>${id }</td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">작성일</th>
				<td>${postdate}</td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">조회수</th>
				<td>${hitcount}</td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">제목</th>
				<td>${title }</td>
			</tr>
			<tr>
				<th class="bg-dark text-white text-center" colspan="2">내 용</th>
			</tr>
			<tr>
				<td colspan="2">${content}</td>
			</tr>
		</tbody>
	</table>

	<!-- 수정/삭제/목록 컨트롤 버튼 -->
	<div class="text-center">	
		<!-- 세션기반 -->
		<%-- if(session.getAttribute("USER-ID").equals(dto.getId())){ --%>
		<c:if test="${id == USER_ID }">
			<a href="<c:url value="/edit/edit.do?no=${no }"/>"  class="btn btn-success">수정</a>
			<a href="javascript:isDelete()" class="btn btn-success">삭제</a>	
		</c:if>
		<a href="<c:url value="/list/list.do"/>" class="btn btn-success"> 목록</a>
	</div>
</div>
<!--container-->
<jsp:include page="/template/Footer.jsp" />
<script>
function isDelete() {
    var confirmDelete = confirm("정말로 삭제하시겠습니까?");
    
    if (confirmDelete) {
        var xhr = new XMLHttpRequest();
        var no = "${no}"; // 삭제할 아이템의 ID 혹은 필요한 데이터
        
        xhr.open("DELETE", "<c:url value='/view/delete.do'/>?no=" + no, true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    alert("삭제되었습니다.");
                    location.href = "<c:url value='/list/list.do'/>";
                } else {
                    alert("삭제 실패");
                }
            }
        };
        
        xhr.send();
    } else {
        return false;
    }
}
</script>
