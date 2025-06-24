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



<div class="feature-scroll-icon">
  <a href="#featureSection">
    <img src="<c:url value='/img/musictoha.png' />" alt="特徴へ移動" width="180" height="30">
  </a>
</div>


<div id="introScreen" class="intro-screen">
  <h1 class="intro-logo">MUSIC CHEESE</h1>
</div>


<input type="hidden" name="selected_theme" id="selected_theme">
<header class="header">


	<div class="logo">
		<a href="<c:url value='/CheeseLoginServlet' />">
			<img src="<c:url value='/img/logo.png' />" width="300" height="100" alt="MUSIC CHEESE">
		</a>
	</div>
</header>


<div class="noheader">
  <div class="card-container">
  <div class="login-header-message">
  <h2>ログイン/新規登録はこちら</h2>
  </div>
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
 
 
<div id="featureSection" class="feature-section">
<div class="feature">
  <div class="feature">
  	<p><font size="16">1🧀</font></p><br>
    <h1><font size="10" class="moji">思いついたフレーズの録音は？</font></h1>
    <h2>ボイスメモで録音した音声を名前付きで登録</h2>
    <h2>歌詞も一緒に添付可能</h2>
  </div>
  <div class="feature">
  	<p><font size="16">2🧀</font></p><br>
    <h1><font size="10" class="moji">フレーズに様々な条件を付与</font></h1>
    <h2>フレーズの曲調やキー、リズムなどもメモへ保存</h2>
    <h2>さらにタグによるフレーズの管理・検索も可能</h2>
  </div>
  <div class="feature">
  	<p><font size="16">3🧀</font></p><br>
    <h1><font size="10" class="moji">作曲ソフトへの架け橋</font></h1>
    <h2>曲ごとにフレーズを組み合わせることで構想案を整理</h2>
    <h2 class="cheese-h2">（曲のイメージを可視化）</h2>
  </div>
</div>
</div>

 <footer>
 <div class="gotop">
            <a href="#top"><img src="<c:url value='/img/gotop.png' />" width="60" height="60" alt="ページトップに戻る"></a>
        </div>
        <p class="copyright">&copy; the cheese</p>
    </footer>
 
 <script src="<c:url value='/js/cheese_login.js' />" defer></script>
</body>
</html>

