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
	<h1>資料の追加</h1><br>
	<form method="GET" action="AddBookConfirm">
	<div class="row g-3"><div class="col-sm-7">

		<table class="form-table">
			<tr>
				<td><label for=belong_high_school_id>所蔵機関:</label></td>
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
				<td><label for="call_number">請求記号:</label></td>
				<td><input type="text" id="call_number" name="call_number" class="form-input"
					required maxlength="255"></td>
			</tr>
			<tr>
				<td><label for="title">書籍名:</label></td>
				<td><input type="text" id="title" name="title" class="form-input"
					required maxlength="255"></td>
			</tr>
			<tr>
				<td><label for="publisher">出版社名:</label></td>
				<td><input type="text" id="publisher" name="publisher" class="form-input"
					required maxlength="255"></td>
			</tr>

			<tr>
				<td><label for="nb_no">書誌ID（NDLBibID）:</label></td>
				<td><input type="text" id="nb_no" name="nb_no" class="form-input"
					 maxlength="255"></td>
			</tr>
			<tr>
				<td><label for="isbn">ISBN:</label></td>
				<td><input type="text" id="isbn" name="isbn" class="form-input"
					 maxlength="255"></td>
			</tr>
			<tr>
				<td><label for="issn">ISSN:</label></td>
				<td><input type="text" id="issn" name="issn" class="form-input"
					 maxlength="255"></td>
			</tr>
			<tr>
				<td><label for="ver">バージョン:</label></td>
				<td><input type="text" id="ver" name="ver" class="form-input"
					 maxlength="255"></td>
			</tr>
			<tr>
				<td><label for="series">シリーズ:</label></td>
				<td><input type="text" id="series" name="series" class="form-input"
					 maxlength="255"></td>
			</tr>
			<tr>
				<td><label for="size">サイズ:</label></td>
				<td><input type="text" id="size" name="size" class="form-input"
					 maxlength="255"></td>
			</tr>
			<tr>
				<td><label for="book_no">国会図書館書誌ID:</label></td>
				<td><input type="text" id="book_no" name="book_no" class="form-input"
					 maxlength="255"></td>
			</tr>
			<tr>
				<td><label for="book_uri">国会図書館書誌URL:</label></td>
				<td><input type="text" id="book_uri" name="book_uri" class="form-input"
					 maxlength="255"></td>
			</tr>
		</table>
			<button type="submit" class="btn btn-primary">登録</button>
		
	</div></div>
	</form>

</div></div>

</body>
</html>