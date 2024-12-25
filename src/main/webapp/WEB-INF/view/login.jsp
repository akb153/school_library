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
	<img src="${pageContext.request.contextPath}/pic/logo.png" alt="GIGA LIBRARYロゴ画像" width="250" height="250" >
	<h1>ログイン</h1><br>
	<!-- 「ログイン」ボタン -->
	<form method="POST" action="Login">
		<table class="form-table">
			<c:if test="${error == 'mistake'}">ユーザーIDかパスワードが違います</c:if>
			<c:if test="${error == 'locked'}">このアカウントはパスワード間違い上限回数を超えたためロックされています。管理者にご連絡ください。</c:if>
			<c:if test="${error == 'session'}">セッションが途切れました。再度ログインしてください。</c:if>
			<c:if test="${error == 'logout'}">ログアウトしました。</c:if>
			<tr>
				<td><label for="user_id">ユーザーID:</label></td>
				<td><input type="text" id="user_id" name="user_id" class="form-input"
					required></td>
			</tr>
			<tr>
				<td><label for="password">パスワード:</label></td>
				<td><input type="password" id="password" name="password" class="form-input"
					required></td>
			</tr>
		</table>
		<button type="submit" class="form-button">ログイン</button>
	</form>
	</div></div>
</body>
</html>