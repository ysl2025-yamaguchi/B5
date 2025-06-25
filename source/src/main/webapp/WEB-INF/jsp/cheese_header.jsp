<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MUSIC CHEESE</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cheese_common_thema1.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cheese_common_thema2.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cheese_common_thema3.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cheese_common_thema4.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cheese_common_thema5.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cheese_common.css">
	
	
</head>
<body>
<input type="hidden" name="selected_theme" id="selected_theme">
<header class="header">
	
	<div class="cheese-menu">
	<img src="<c:url value='/img/blackcheese.png' />" alt="テーマアイコン" id="themeIcon" width="40" height="40">
	</div>

  <form method = "POST" action = "CheeseChangeThemaServlet" name = "change_thema">
	  <div id="themeModal">
	    <input type = "hidden" value = "${loginUser.thema}" name = "thema" id = "thema_number">
	    <div class="modal-content">
	      <p>テーマを選択してください</p>
	      <div class="theme-option" data-theme="light">ライト</div>
	      <div class="theme-option" data-theme="dark">ダーク</div>
	      <div class="theme-option" data-theme="ocean">オーシャン</div>
	      <div class="theme-option" data-theme="pink">ピンク</div>
	      <div class="theme-option" data-theme="green">グリーン</div>
	    </div>
	  </div>
  </form>


  </script>
  <script src="<c:url value='/js/cheese_common.js' />" defer></script>
	
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