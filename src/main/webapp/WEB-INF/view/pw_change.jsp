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
	<h1>パスワード変更</h1><br>
	<form method="POST" action="PwChangeComplete">
		<table class="form-table">
			<c:if test="${error == 'mistake'}">パスワードが違います。</c:if>
			<c:if test="${error == 'session'}">セッションが途切れました。再度ログインしてください。</c:if>
			<c:if test="${error == 'not_match'}">新パスワードと新パスワード（確認用）が一致しません。</c:if>
			<tr>
				<td><label for="password">現在のパスワード</label></td>
				<td><input type="password" id="password" name="password" class="form-input"
					required></td>
			</tr>
			<tr>
				<td><label for="newpassword">新パスワード:</label></td>
				<td><input type="password" id="newpassword" name="newpassword" class="form-input"
					required></td>
			</tr>
			<tr>
				<td><label for="newpassword">新パスワード（確認用）:</label></td>
				<td><input type="password" id="newpassword2" name="newpassword2" class="form-input"
					required></td>
			</tr>
		</table>
		<button type="submit" class="form-button">パスワード変更</button>
	</form>
	</div></div>
</body>
</html>