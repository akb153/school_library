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
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="header.jsp" flush="true" />

<title>GIGA LIBRARY</title>
<style>

.container {
  /* 子要素を水平方向の中央に配置する */
  display: flex;
  justify-content: center;
}

th, td {
	border: 1px solid black; /* セルの境界線のスタイルを設定 */
	padding: 10px;
	margin: 10px;
}

h1, table, button, h6 {
	margin-left: 20px;
}

</style>
</head>
<body>

	<h1>書籍検索結果</h1>
	
	
	<%int list_size = (int)session.getAttribute("list_size");%>
	<h6>検索結果：${list_size}件</h6>
	
	<table border="1">
		<tr bgcolor="#cccccc">
			<th>書籍名</th>
			<th>出版社、出版年</th>
			<th>所蔵機関</th>
		</tr>

		<%
		ArrayList<BookSearchBean> searchingBookList = (ArrayList<BookSearchBean>)session.getAttribute("bookSearchList");
		%>

		

		<%
		for (BookSearchBean bookBean : searchingBookList) {
		%>
		<tr>
			<td><a href="BookReserve?book_id=<%=bookBean.getBook_id()%>"><%=bookBean.getTitle()%></a></td>
			<td><%=bookBean.getPublisher()%></td>
			<td><%=bookBean.getBelong_high_school_name()%></td>

		</tr>
		<%
}
%>

	</table>

</body>
</html>