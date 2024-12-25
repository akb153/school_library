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
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
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





</style>
</head>
<body>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<%
	session.setAttribute("error",request.getParameter("error"));
%>
	<div class="container"><div class="child">
	<h1>書籍貸出</h1><br>
	<form method="GET" action="RentalConfirm">
	<div class="row g-3"><div class="col-sm-7">
		<c:if test="${error == 'duplicate'}">同じ書籍が複数回指定されています。</c:if>
		<c:if test="${error == 'mistake'}">ユーザーIDかパスワードが違います。</c:if>
		<c:if test="${error == 'no_exist'}">存在しないBOOK IDが指定されています。</c:if>
		<c:if test="${error == 'overdue'}">このアカウントは書籍の返却が延滞しています。先に書籍を返却してください。</c:if>
		<c:if test="${error == 'reserved'}">以下の書籍はほかのユーザーが予約中のため貸出できません。貸出するためにはGIGA LIBRARYのマイページから予約をし、順番待ちしてください。</c:if><br>
		<%
		ArrayList<BookDetailBean> reservedBookList = (ArrayList<BookDetailBean>)session.getAttribute("reservation_check_result");
	%>
		<c:if test="${error == 'reserved'}">
				<%
		for (BookDetailBean bookBean : reservedBookList) {
		%>
		<tr>
			<td><%=bookBean.getTitle()%></td>
		</tr>
		<%
}
%>
		</c:if>
		<c:if test="${error == 'borrowed'}">以下の書籍はほかのユーザーに貸出中のため貸出できません。貸出するためにはGIGA LIBRARYのマイページから予約をし、順番待ちしてください。</c:if>
				<%
		ArrayList<BookDetailBean> borrowedBookList = (ArrayList<BookDetailBean>)session.getAttribute("borrowing_check_result");
	%>
		<c:if test="${error == 'borrowed'}">
				<%
		for (BookDetailBean bookBean : borrowedBookList) {
		%>
		<tr>
			<td><%=bookBean.getTitle()%></td>
		</tr>
		<%
}
%>　　　</c:if>
		<c:if test="${error == 'limit_over'}">
		<%
		int borrowing_num = (int)session.getAttribute("borrowing_num");
	%>
		あなたはすでに${borrowing_num}冊の貸出書籍があります。貸出上限数（10冊）を超えた貸出はできません。今回の貸出数を減らすか、貸出中の書籍を返却してから改めてお試しください。
		</c:if>



		<input type="text" class="form-control"  id="user_id" name="user_id"  placeholder="ユーザーIDを入力" size="50" required>
		<input type="password" class="form-control"  id="password" name="password"  placeholder="パスワードを入力" size="50" required>
		<br>
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
			<button type="submit" class="btn btn-primary">貸出</button>
		
	</div></div>
	</form>

</div></div>

</body>
</html>