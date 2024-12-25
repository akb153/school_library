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


</style>
</head>
<body>

<%
	session.setAttribute("error",request.getParameter("error"));
%>
		<%
		ArrayList<HighSchoolBean> highSchoolList = (ArrayList<HighSchoolBean>)session.getAttribute("high_school_list");
		%>

	<div class="container"><div class="child">
	<h1>アカウント登録</h1><br>
	<form method="GET" action="AdmissionConfirm">
	<div class="row g-3"><div class="col-sm-7">

		<table class="form-table">
			<c:if test="${error == 'user_id'}">そのユーザーIDはすでに使われています。(削除済ユーザーのIDと重複している可能性もあります)</c:if>
			<tr>
				<td><label for="user_id">ユーザーID:</label></td>
				<td><input type="text" id="user_id" name="user_id" class="form-input"
					required maxlength="255"></td>
			</tr>
			<tr>
				<td><label for="user_id">氏名:</label></td>
				<td><input type="text" id="user_name" name="user_name" class="form-input"
					required maxlength="255"></td>
			</tr>
			<tr>
				<td><label for="password">仮パスワード:</label></td>
				<td><input type="text" id="password" name="password" class="form-input"
					required maxlength="255"></td>
			</tr>
			<tr>
				<td><label for=belong_high_school_id>所属高校:</label></td>
				<td><select name="belong_high_school_id">
					<%
					for (HighSchoolBean highSchoolBean : highSchoolList) {
					%>
					<option value=<%=highSchoolBean.getHigh_school_id()%>><%=highSchoolBean.getHigh_school_name()%></option>
					<%
					}
					%>
				</select></td>
			</tr>
			<tr>
				<td><label for="mail_address">メールアドレス:</label></td>
				<td><input type="email" id="mail_address" name="mail_address" class="form-input"
					required maxlength="255"></td>
			</tr>
		</table>
			<button type="submit" class="btn btn-primary">登録</button>
		
	</div></div>
	</form>

</div></div>

</body>
</html>