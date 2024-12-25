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



<div class="container"><div class="child">

	<!-- 「トップページ」ボタン -->
	<form method="GET" action="Return">
		<button type="submit" class="form-button">トップページに戻る</button>
	</form>


	<h1>返却完了</h1>
		<%int returning_size = (int)session.getAttribute("returning_size");%>
	<h6>返却書籍：${returning_size}件</h6>
	<h6>以下の書籍を返却しました。返却された書籍は返却棚に配置してください。次の予約がある場合は専用棚に配置してください。</h6>
	
	<table border="1">
		<tr bgcolor="#cccccc">
			<th>書籍名</th>
			<th>出版社、出版年</th>
			<th>所蔵機関</th>
			<th>補足</th>
		</tr>

		<%
		ArrayList<BookDetailBean> returningBookList = (ArrayList<BookDetailBean>)session.getAttribute("returningBookList");
		%>

		

		<%
		for (BookDetailBean bookBean : returningBookList) {
		%>
		<tr>
			<td><%=bookBean.getTitle()%></a></td>
			<td><%=bookBean.getPublisher()%></td>
			<td><%=bookBean.getBelong_high_school_id()%></td>
			<td>（次の予約がある場合はここに表示されます）</td>
		</tr>
		<%
}
%>

	</table>
</div></div>

</body>
</html>