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
	<form method="GET" action="LibrarianHome">
		<button type="submit" class="form-button">トップページに戻る</button>
	</form>


	<h1>追加完了</h1>
	<h6>以下の資料を追加しました。続けて追加する場合はこちら</h6>
		<form method="GET" action="AddBook">
		<button type="submit" class="form-button">続けて資料を追加する</button>
	</form>
	
	<table border="1">
		<tr bgcolor="#cccccc">
			<th>GIGA LIBRARY BOOK ID</th>
			<th>所蔵機関</th>
			<th>請求記号</th>
			<th>書籍名</th>
			<th>出版社名</th>
			<th>書誌ID（NDLBibID）</th>
			<th>ISBN</th>
			<th>ISSN</th>
			<th>バージョン</th>
			<th>シリーズ</th>
			<th>サイズ</th>
			<th>国会図書館書誌ID</th>
			<th>国会図書館書誌URL</th>
		</tr>

		<%
		BookDetailBean bookBean = (BookDetailBean)session.getAttribute("add_book");
		%>

		<tr>
			<td>(提出時点では未実装)</td>
			<td><%=bookBean.getBelong_high_school_name()%></td>
			<td><%=bookBean.getCall_number()%></td>
			<td><%=bookBean.getTitle()%></td>
			<td><%=bookBean.getPublisher()%></td>
			<td><%=bookBean.getNb_no()%></td>
			<td><%=bookBean.getIsbn()%></td>
			<td><%=bookBean.getIssn()%></td>
			<td><%=bookBean.getVer()%></td>
			<td><%=bookBean.getSeries()%></td>
			<td><%=bookBean.getSize()%></td>
			<td><%=bookBean.getBook_no()%></td>
			<td><%=bookBean.getBook_uri()%></td>
		</tr>
	</table>
</div></div>

</body>
</html>