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

<link rel="icon" sizes="16x16" href="${pageContext.request.contextPath}/pic//favicon.ico" />
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

<title>GIGA LIBRARY</title>
<style>


.flex {
  display: flex;/* 横並び*/  
  align-items: center;
}

.f_item:last-child{
    margin-left : auto;
    margin-right: 3%;    
}


</style>
</head>
<body>


<%
	LoginBean lb = new LoginBean();
	lb = (LoginBean)session.getAttribute("loginBean");
	session.setAttribute("admin_flg",lb.getAdmin_flg());
	session.setAttribute("librarian_flg",lb.getLibrarian_flg());
	session.setAttribute("library_user_flg",lb.getLibrary_user_flg());
	session.setAttribute("library_committee_flg",lb.getLibrary_committee_flg());
	session.setAttribute("transporter_flg",lb.getTransporter_flg());
	session.setAttribute("rental_flg",lb.getRental_flg());
	session.setAttribute("return_flg",lb.getReturn_flg());
%>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<div class="flex">
	<img class="f_item" src="${pageContext.request.contextPath}/pic/logo.png" alt="GIGA LIBRARYロゴ画像" width="100" height="100" >
	<p class="f_item">ログイン中ユーザー：<%=lb.getUser_name()%> さん </p>
	<div  class="f_item btn-group">
	<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
		画面切替
		<span class="caret"></span>
	</button>
	<ul class="dropdown-menu" role="menu">
		<c:if test="${admin_flg == '1'}"><li role="presentation"><a role="menuitem" tabindex="-1" href="Admin_home">管理者画面</a></li></c:if>
		<c:if test="${librarian_flg == '1'}"><li role="presentation"><a role="menuitem" tabindex="-1" href="LibrarianHome">司書画面</a></li></c:if>
		<c:if test="${library_user_flg == '1'}"><li role="presentation"><a role="menuitem" tabindex="-1" href="Library_user_home">マイページ</a></li></c:if>
		<c:if test="${library_user_flg == '1'}"><li role="presentation"><a role="menuitem" tabindex="-1" href="SelfRental">セルフ貸出</a></li></c:if>
<%-- 		<c:if test="${transporter_flg == '1'}"><li role="presentation"><a role="menuitem" tabindex="-1" href="#">運搬業者画面</a></li></c:if> --%>
		<c:if test="${rental_flg == '1'}"><li role="presentation"><a role="menuitem" tabindex="-1" href="Rental">貸出用ＰＣ画面</a></li></c:if>
		<c:if test="${return_flg == '1'}"><li role="presentation"><a role="menuitem" tabindex="-1" href="Return">返却用ＰＣ画面</a></li></c:if>
		<li role="presentation"><a role="menuitem" tabindex="-1" href="PwChange">パスワード変更</a></li>
		<li role="presentation"><a role="menuitem" tabindex="-1" href="Logout">ログアウト</a></li>
	</ul>
	</div>
</div>
</body>
</html>