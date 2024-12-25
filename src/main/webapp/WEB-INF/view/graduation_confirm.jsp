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
	<h1>ユーザー削除内容確認</h1>
	
	
	
		<button type="submit" class="btn btn-primary" id="btn">以下のユーザーを削除する</button>
	
	<script>
		var btn = document.getElementById('btn');
		btn.addEventListener('click', function() {
	    var result = window.confirm('以下のユーザーを削除しますか？');
   			if( result ){	
   				window.location.href = 'GraduationComplete';
   	   		}
		})
	</script>
	
	<table border="1">
		<tr bgcolor="#cccccc">
			<th>ユーザーID</th>
			<th>氏名</th>
			<th>所属高校</th>
		</tr>

		<%
		LoginBean deleteUser = (LoginBean)session.getAttribute("delete_user");
		%>

		<tr>
			<td><%=deleteUser.getUser_id()%></td>
			<td><%=deleteUser.getUser_name()%></td>
			<td><%=deleteUser.getBelong_high_school_id()%></td>
		</tr>

	</table>


</div></div>
</body>
</html>