<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MUSIC CHEESE</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cheese_common_thema1.css">
</head>
<body>
<header class="header">
<div class="cheese-menu">
  <img src="<c:url value='/img/blackcheese.png' />" id="themeImage" alt="テーマ切替" width="30" height="30">
  <ul class="dropdown" id="themeDropdown">
    <li data-theme="light">ライト</li>
    <li data-theme="dark">ダーク</li>
    <li data-theme="ocean">オーシャン</li>
    <li data-theme="sunset">サンセット</li>
  </ul>
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