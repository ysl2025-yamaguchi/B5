<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MUSIC CHEESE</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cheese_common_thema1.css">
</head>
<body>
<header class="header">
	<div class="cheese-icon">
		<img src="${pageContext.request.contextPath}/img/blackcheese.png" width="30" height="30" alt="CHEESE">
	</div>
	<div class="logo">
		<a href="${pageContext.request.contextPath}/CheesePhraseListServlet">
			<img src="${pageContext.request.contextPath}/img/logo.png" width="300" height="100" alt="MUSIC CHEESE">
		</a>
	</div>
	<nav class="nav">
		<ul>
			<li><a href="${pageContext.request.contextPath}/CheesePhraseListServlet"><strong>Home</strong></a><span class="line-right"></span></li>
			<li><a href="${pageContext.request.contextPath}/CheeseMusicListServlet"><strong>Music</strong></a><span class="line-right"></span></li>
			<li><a href="${pageContext.request.contextPath}/CheeseLogoutServlet"><strong>Logout</strong></a><span class="line-left"></span></li>
		</ul>
	</nav>
</header>
</body>
</html>