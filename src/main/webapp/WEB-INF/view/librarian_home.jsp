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


</style>
</head>
<body>




	<div class="container"><div class="child">
	<a href="AddBook" style="margin: 50px 50px 50px 50px;"><button type="button" class="form-button"><img src="${pageContext.request.contextPath}/pic/AddBook.png" alt="書籍の追加" width="150" height="150" ></button></a>	
	<a href="DeleteBook" style="margin: 50px 50px 50px 50px;"><button type="button" class="form-button"><img src="${pageContext.request.contextPath}/pic/DeleteBook.png" alt="書籍の削除" width="150" height="150" ></button></a>

	</div></div>
</body>
</html>