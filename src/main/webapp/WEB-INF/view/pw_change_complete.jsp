<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.*"%>
<%@page import="javax.servlet.jsp.jstl.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" sizes="16x16" href="${pageContext.request.contextPath}/pic//favicon.ico" />
<jsp:include page="header.jsp" flush="true" />
<title>GIGA LIBRARY</title>
<style>
table {
	border-collapse: collapse; /* セルの境界線を重ねて単線にする */
	width: auto;	
}

th, td {
	border: 1px solid black; /* セルの境界線のスタイルを設定 */
	padding: 10px;
	width: auto;
}


.container {
  /* 子要素を水平方向の中央に配置する */
  display: flex;
  justify-content: center;
}

</style>
</head>
<body>

<%
	session.setAttribute("error",request.getParameter("error"));
%>
	<div class="container"><div class="child">
	<h1>パスワードを変更しました</h1><br>

	</div></div>
</body>
</html>