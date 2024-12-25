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
	<a href="Admission" style="margin: 50px 50px 50px 50px;"><button type="button" class="form-button"><img src="${pageContext.request.contextPath}/pic/Admission.png" alt="入学" width="150" height="150" ></button></a>	
	<a href="Graduation" style="margin: 50px 50px 50px 50px;"><button type="button" class="form-button"><img src="${pageContext.request.contextPath}/pic/Graduation.png" alt="卒業" width="150" height="150" ></button></a>
	<a href="#" style="margin: 50px 50px 50px 50px;"><button type="button" class="form-button"><img src="${pageContext.request.contextPath}/pic/CSV.png" alt="CSVデータ取込" width="150" height="150" ></button></a>

	</div></div>
</body>
</html>