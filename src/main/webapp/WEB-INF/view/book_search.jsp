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

<jsp:include page="header.jsp" flush="true" />

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


.container {
  /* 子要素を水平方向の中央に配置する */
  display: flex;
  justify-content: center;
  align-items: center;
  
}

.publish-year input{
	display: initial;
	size: initial;
	width: initial;
}

label{
    font-weight: normal !important;
}

</style>
</head>
<body>

<%
	session.setAttribute("error",request.getParameter("error"));
%>
	<div class="container"><div class="child">
	<h1>書籍検索・予約</h1><br>
	<form method="GET" action="BookSearchResult">
	<div class="input-group">
		<input type="text" class="form-control"  id="keyword" name="keyword"  placeholder="検索キーワードを入力" size="50" maxlength="255">
		<span class="input-group-btn">
			<button type="submit" class="btn btn-primary">検索</button>
		</span>
	</div>
			<input type="checkbox"><label>GIGA LIBRARY全体から検索する</label>


    <div id="toggleElement" style="display: none;">
       <h6>図書館<input type="text" class="form-control" size="25"  id="library" name="library" maxlength="255"></h6>
       <h6>タイトル<input type="text" class="form-control" size="25" id="title" name="title"  maxlength="255"></h6>
       <h6>出版者<input type="text" class="form-control" placeholder="出版社名などを入力" size="25"  id="publisher" name="publisher" maxlength="255"></h6>
       <!-- <div class="publish-year"><h6>出版年（西暦）<input type="text" class="form-control" placeholder="YYYY" size="12"  id="start_year" name="start_year" >年～<input type="text" class="form-control" placeholder="YYYY" size="12"  id="end_year" name="end_year" >年</h6></div> -->
       <h6>ISBN / ISSN<input type="text" class="form-control" placeholder="978-4-XXXXX-XXX-X" size="25"  id="ISBN_ISSN" name="ISBN_ISSN" maxlength="255"></h6>
       	<button type="submit" class="btn btn-primary">検索</button>
    </div>
    </form>
    	<br>
    	<button id="toggleButton">詳細検索</button>
	    <script src="${pageContext.request.contextPath}/js/BookSearch.js"></script>


</div></div>

</body>
</html>