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
	<h1>書籍返却</h1><br>
	<form method="GET" action="ReturnConfirm">
	<div class="row g-3"><div class="col-sm-7">
		<c:if test="${error == 'no_exist'}">存在しないBOOK IDが指定されています。</c:if>
		<c:if test="${error == 'no_rental'}">貸出されていないBOOK IDが指定されています。</c:if>		
		<input type="text" class="form-control"  id="book_id1" name="book_id1"  placeholder="GIGA LIBRARY BOOK IDを入力" size="50" maxlength="255">
		<input type="text" class="form-control"  id="book_id2" name="book_id2"  placeholder="GIGA LIBRARY BOOK IDを入力" size="50" maxlength="255">
		<input type="text" class="form-control"  id="book_id3" name="book_id3"  placeholder="GIGA LIBRARY BOOK IDを入力" size="50" maxlength="255">
		<input type="text" class="form-control"  id="book_id4" name="book_id4"  placeholder="GIGA LIBRARY BOOK IDを入力" size="50" maxlength="255">
		<input type="text" class="form-control"  id="book_id5" name="book_id5"  placeholder="GIGA LIBRARY BOOK IDを入力" size="50" maxlength="255">
		<input type="text" class="form-control"  id="book_id6" name="book_id6"  placeholder="GIGA LIBRARY BOOK IDを入力" size="50" maxlength="255">
		<input type="text" class="form-control"  id="book_id7" name="book_id7"  placeholder="GIGA LIBRARY BOOK IDを入力" size="50" maxlength="255">
		<input type="text" class="form-control"  id="book_id8" name="book_id8"  placeholder="GIGA LIBRARY BOOK IDを入力" size="50" maxlength="255">
		<input type="text" class="form-control"  id="book_id9" name="book_id9"  placeholder="GIGA LIBRARY BOOK IDを入力" size="50" maxlength="255">	
		<input type="text" class="form-control"  id="book_id10" name="book_id10"  placeholder="GIGA LIBRARY BOOK IDを入力" size="50" maxlength="255">																	
		<br>
			<button type="submit" class="btn btn-primary">返却</button>
		
	</div></div>
	</form>

</div></div>

</body>
</html>