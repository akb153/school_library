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
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link rel="icon" sizes="16x16" href="${pageContext.request.contextPath}/pic//favicon.ico" />

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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<div class="container"><div class="child">
	<h1>貸出内容確認</h1>
	
	
	<%int rentaling_size = (int)session.getAttribute("rentaling_size");%>
	<h6>貸出書籍：${rentaling_size}件</h6>
	
	
		<button type="submit" class="btn btn-primary" id="btn">以下の書籍を貸出する</button>
	
	<script>
		var btn = document.getElementById('btn');
		btn.addEventListener('click', function() {
	    var result = window.confirm('以下の書籍を貸出しますか？');
   			if( result ){	
   				window.location.href = 'RentalComplete';
   	   		}
		})
	</script>
	
	<table border="1">
		<tr bgcolor="#cccccc">
			<th>書籍名</th>
			<th>出版社、出版年</th>
			<th>所蔵機関</th>
		</tr>

		<%
		ArrayList<BookDetailBean> rentalingBookList = (ArrayList<BookDetailBean>)session.getAttribute("rentalingBookList");
		%>

		

		<%
		for (BookDetailBean bookBean : rentalingBookList) {
		%>
		<tr>
			<td><%=bookBean.getTitle()%></a></td>
			<td><%=bookBean.getPublisher()%></td>
			<td><%=bookBean.getBelong_high_school_id()%></td>

		</tr>
		<%
}
%>

	</table>
</div></div>
</body>
</html>