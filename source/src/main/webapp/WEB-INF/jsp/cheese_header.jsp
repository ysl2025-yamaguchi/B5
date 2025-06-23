<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MUSIC CHEESE</title>
	<link rel="stylesheet" href="<c:url value='/css/cheese_common_thema1.css' />">
	<script src="<c:url value='/js/cheese_common.js' />"></script>
</head>
<body>
<input type="hidden" name="selected_theme" id="selected_theme">
<header class="header">
<div class="cheese-menu">
	<form id = "change_thema" method = "POST" action = "/CheeseChengeThemaServlet">
	  <img src="<c:url value='/img/blackcheese.png' />" id="themeImage" alt="テーマ切替" width="30" height="30" style="margin-right: 8px;">
	  <select class = "select_thema" id = "select_thema" name="selected_theme">
		<option value = "light">ライト</option>
		<option value = "dark">ダーク</option>
		<option value = "ocean">オーシャン</option>
		<option value = "sunset">サンセット</option>
	  </select>
  </form>
</div>

<!-- 
<div class="cheese-icon">
		<img src="<c:url value='/img/blackcheese.png' />" width="30" height="30" alt="CHEESE" id="themaImage">
	</div>
	 -->
	
	
	<div class="logo">
		<a href="<c:url value='/CheesePhraseListServlet' />">
			<img src="<c:url value='/img/logo.png' />" width="300" height="100" alt="MUSIC CHEESE">
		</a>
	</div>
	<nav class="nav">
		<ul>
			<li><a href="<c:url value='/CheesePhraseListServlet' />"><strong>Home</strong></a><span class="line-right"></span></li>
			<li><a href="<c:url value='/CheeseMusicListServlet' />"><strong>Music</strong></a><span class="line-right"></span></li>
			<li><a href="<c:url value='/CheeseLogoutServlet' />"><strong>Logout</strong></a><span class="line-left"></span></li>
		</ul>
	</nav>
</header>
</body>
</html>