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


	<div class="container"><div class="child">
	<h1>アカウント削除</h1><br>
	<form method="GET" action="GraduationConfirm">
	<div class="row g-3"><div class="col-sm-7">

	<table border="1">
		<tr bgcolor="#cccccc">
			<th>ユーザーID</th>
			<th>氏名</th>
			<th>所属高校</th>
		</tr>

		<%
		ArrayList<LoginBean> userList = (ArrayList<LoginBean>)session.getAttribute("user_list");
		%>

		

		<%
		for (LoginBean userBean : userList) {
		%>
		<tr>
			<td><a href="GraduationConfirm?user_id=<%=userBean.getUser_id()%>"><%=userBean.getUser_id()%></a></td>
			<td><%=userBean.getUser_name()%></td>
			<td><%=userBean.getBelong_high_school_id()%></td>
		</tr>
		<%
}
%>

	</table>


	</div></div>
	</form>

</div></div>

</body>
</html>