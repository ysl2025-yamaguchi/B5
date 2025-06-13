<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン/新規登録</title>
<jsp:include page="cheese_header.jsp"/>
</head>
<body>

    <h1>
		<img src="/webapp/img/blackcheese.png" width="50" height="50" alt="CHEESE">
	</h1>
	<div class="logo">
		<a href="/webapp/MenuServlet"><img src="/webapp/img/logo.png" width="600" height="200" alt="MUSIC CHEESE"></a>
	</div>

	<div class="container">
        <!-- 切り替えボタン -->
        <button onclick="showLogin()">ログイン画面</button>
        <button onclick="showRegister()">新規登録画面</button>

 <form method="POST" action="webapp/CheeseLoginServlet">


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
    </div>

 </form>

<script src="/webapp/js/cheese_login.js"></script>
</body>
</html>