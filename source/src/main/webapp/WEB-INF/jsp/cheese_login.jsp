<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン/新規登録</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cheese_common_thema1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cheese_login.css">

</head>
<body>


<div id="introScreen" class="intro-screen">
  <h1 class="intro-logo">MUSIC CHEESE</h1>
</div>



<input type="hidden" name="selected_theme" id="selected_theme">
<header class="header">
<!--
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
-->
	<div class="logo">
		<a href="<c:url value='/CheeseLoginServlet' />">
			<img src="<c:url value='/img/logo.png' />" width="300" height="100" alt="MUSIC CHEESE">
		</a>
	</div>
</header>
<div class="noheader">
  <div class="card-container">
    <div class="switch-buttons">
      <button type="button" id="loginBtn" class="active">ログイン画面</button>
      <button type="button" id="registerBtn">新規登録画面</button>
    </div>
    
    
<c:if test="${not empty sessionScope.register_success}">
  <div class="success-message">${sessionScope.register_success}</div>
  <c:remove var="register_success" scope="session" />
</c:if>


    <!-- ログインフォーム -->
    <form id="loginForm" class="form-section active" method="POST" action="CheeseLoginServlet">
    <div >
      <span id="loginError" class="error-message"></span>
      <input type="text" id="loginUser" name="loginUser" placeholder="ユーザー名">
      <input type="password" id="loginPass" name="loginPass" placeholder="パスワード">
      <input type="submit" class="submit-button" value="ログイン">
    </div>
    </form>
    
    <!-- 新規登録フォーム -->
    <form id="registerForm" class="form-section"  method="POST" action="CheeseRegistUserServlet">
    <div >
      <span id="registerError" class="error-message"></span>
      <input type="text" id="regUser" name="regUser" placeholder="ユーザー名" >
      <input type="password" id="regPass" name="regPass" placeholder="パスワード" >
      <input type="password" id="regPassConfirm" name="regPassConfirm" placeholder="パスワード確認">
      <input type="submit" class="submit-button" value="登録">
		
    </div>
    </form>
  </div>
 </div>
 <script src="<c:url value='/js/cheese_login.js' />" defer></script>
</body>
</html>

