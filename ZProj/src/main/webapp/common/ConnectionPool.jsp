<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// [톰캣 서버가 ConnectionPool에 미리 생성해놓은 Connection 객체를 사용]
	//1] InitialContext 객체 생성(Context > InitialContext)
	Context ctx = new InitialContext();
	
	//2] InitialContext 객체로 JNDI(Java Naming and Directory Interface)서비스 구조의 초기 ROOT 디렉토리 얻기
	ctx = (Context)ctx.lookup("java:comp/env"); //java:comp/env는 자원을 참조할 때 사용되는 표준적인 경로
	
	//3] 톰캣의 context.xml에 등록한 네이밍으로 DataSource를 얻는다
	DataSource source = (DataSource)ctx.lookup("ictuser");
	
	//4]DataSource의 getConnection()메소드로 톰캣이 pool에 미리 생성해 놓은  Connection객체를 가져다 쓴다.
	Connection conn = source.getConnection();
	String connString;
	if(conn != null){
		connString = "<h3>커넥션 객체 가져오기 성공</h3>";
		conn.close();
	}else{
		connString="<h3>커넥션 객체 가져오기 실패</h3>";
	}
%>
<jsp:include page="/template/Top.jsp" />
<div class="container" style="margin-top: 50px">
	<div class="jumbotron bg-info">
		<h1>Connection Pool</h1>
	</div>
	<!--jumbotron-->
	<fieldset class="form-group border p-3">
		<legend class="w-auto px-3">커넥션 풀</legend>
		<%=connString %>
	</fieldset>
</div>
<!--container-->
<jsp:include page="/template/Footer.jsp" />