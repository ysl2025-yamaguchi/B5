<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン/新規登録</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cheese_common_thema1.css">
<script src="<c:url value='/js/cheese_common.js' />"></script>

</head>
<body>
<!--  
<div class="cheese-icon">
<img src="<c:url value='/img/blackcheese.png' />" width="30" height="30" alt="CHEESE" id="themeImage">

<select id="themeSelector">
      <option value="light">ライト</option>
      <option value="dark">ダーク</option>
      <option value="ocean">オーシャン</option>
      <option value="sunset">サンセット</option>
</select>

	
	</div>
-->

<input type="hidden" name="selected_theme" id="selected_theme">
<header class="header">
<div class="cheese-menu">
	<form id = "change_thema" method = "POST" action = "/CheeseChengeThemaServlet">
	  <img src="<c:url value='/img/blackcheese.png' />" id="themeImage" alt="テーマ切替" width="30" height="30"> <br>
	  <select class = "select_thema" id = "select_thema">
		<option value = "light">ライト</option>
		<option value = "dark">ダーク</option>
		<option value = "ocean">オーシャン</option>
		<option value = "sunset">サンセット</option>
	  </select>
  </form>
</div>

	<div class="logo">
		<a href="<c:url value='/CheeseLoginServlet' />">
			<img src="<c:url value='/img/logo.png' />" width="300" height="100" alt="MUSIC CHEESE">
		</a>
	</div>
</header>
	<div class="container">
        <!-- 切り替えボタン -->
        <button onclick="showLogin()">ログイン画面</button>
        <button onclick="showRegister()">新規登録画面</button>
    </div>

 <form method="POST" action="CheeseLoginServlet">
 	
	<!-- ログインフォーム -->
	<div id="loginForm" class="form-container">
	    <h2>ログイン</h2>
	    <span id="loginError_message" class="error-message"></span>
	    <input type="text" id="login_username" name="login_username" placeholder="ユーザー名"><br>
	    <input type="password" id="login_password" name="login_password" placeholder="パスワード"><br>
	    <button onclick="validateLogin()">ログイン</button>
	</div>
	
	<!-- 新規登録フォーム -->
	<div id="registerForm" class="form-container">
	    <h2>新規登録</h2>
	    <span id="registError_message" class="error-message"></span>
	    <input type="text" id="register_username" name="register_username" placeholder="ユーザー名"><br>
	    <input type="password" id="register_password" name="register_password" placeholder="パスワード"><br>
	    <input type="password" id="register_rePassword" name="register_rePassword" placeholder="パスワード（確認用）"><br>
	    <button onclick="validateRegister()">新規登録</button>
	</div>
 
 </form>
<script src="<c:url value='/js/cheese_login.js' />"></script>
<script src="<c:url value='/js/cheese_common.js' />"></script>

</body>
</html>

