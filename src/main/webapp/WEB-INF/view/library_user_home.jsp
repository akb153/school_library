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

h1, table, button,h6 {
	margin-left: 20px;
}
.deadline_warning {
	color: red;
  	display: none;
}
.reserving_order_warning {
	color: red;
  	display: none;
}

</style>
</head>
<body>

	<h1>書籍検索・予約</h1>
	<button type="button" class="form-button"><a href="BookSearch">書籍検索・予約</a></button>

	<h1>貸出中書籍一覧</h1>

	<h6 class="deadline_warning" id ="deadline_warning">返却期限を超えた資料があります。速やかに返却してください。該当資料が返却されるまで、資料の新規貸出はできません。</h6>
	<table border="1">
		<tr bgcolor="#cccccc">
			<th>GIGA LIBRARY BOOK ID</th>
			<th>書籍名</th>
			<th>貸出日</th>
			<th>返却予定日</th>
			<th>返却先</th>
		</tr>

		<%
		ArrayList<BorrowingBookBean> borrowingBookList = (ArrayList<BorrowingBookBean>)session.getAttribute("borrowingBookList");
	%>

		<%
		for (BorrowingBookBean bookBean : borrowingBookList) {
		%>
		<tr>
			<td><%=bookBean.getBorrowing_book()%></td>
			<td><%=bookBean.getTitle()%></td>
			<td><%=bookBean.getBorrowing_start_date()%></td>
			<td class="deadline"><%=bookBean.getBorrowing_end_date()%></td>
			<td><%=bookBean.getBook_belonging_school()%></td>
		</tr>
		<%
}
%>
	</table>
		    <script src="${pageContext.request.contextPath}/js/DeadlineWarning.js"></script>
	
		<h1>予約中書籍一覧</h1>
		
	<h6 class="reserving_order_warning" id ="reserving_order_warning">貸出準備ができている資料があります。速やかに受け取ってください。</h6>
	<table border="1">
		<tr bgcolor="#cccccc">
			<th>GIGA LIBRARY BOOK ID</th>
			<th>書籍名</th>
			<th>予約日</th>
<!-- 			<th>予約受取期限</th> -->
			<th>予約順位</th>
			<th>貸出状況</th>
			<th>受取先</th>
			<th>キャンセル</th>
		</tr>

		<%
		ArrayList<ReservingBookBean> reservingBookList = (ArrayList<ReservingBookBean>)session.getAttribute("reservingBookList");
		%>

		<%
		for (ReservingBookBean reservingBean : reservingBookList) {
			session.setAttribute("borrowing_flg",reservingBean.isBorrowing_flg());
		%>
		<tr>

			<td><%=reservingBean.getReserving_book()%></td>
			<td><%=reservingBean.getTitle()%></td>
			<td><%=reservingBean.getReserving_date()%></td>
<%-- 			<td><%=( reservingBean.getDeadline_for_receiving() == null ) ? "" : reservingBean.getDeadline_for_receiving()%></td> --%>
			<td class="reserving_order" id="reserving_order"><%=reservingBean.getRow_num()%>位</td>
			<td><c:if test="${borrowing_flg == true}">貸出中</c:if><c:if test="${borrowing_flg == false}">未貸出</c:if></td>
			<td><%=reservingBean.getBook_belonging_school()%></td>
			<td><a href="#">予約をキャンセル</a></td>
		</tr>
		<%
}
%>

	</table>

</body>
</html>