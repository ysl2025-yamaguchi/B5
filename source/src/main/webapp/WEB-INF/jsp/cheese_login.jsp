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
<form method="POST" action="webapp/CheeseLoginServlet">
	
	<h1>
		<img src="/webapp/img/blackcheese.png" width="50" height="50" alt="CHEESE">
	</h1>
	<div class="logo">
		<a href="/webapp/MenuServlet"><img src="/webapp/img/logo.png" width="600" height="200" alt="MUSIC CHEESE"></a>
	</div>
	

	<div>
		<span id="loginError_message" style="color: red;"></span>
		<!-- <button onclick="loginError()">エラーを表示</button> -->

		<input type="text" id="username" name="username" required placeholder="ユーザー名"><br>

		<input type="password" id="password" name="password" required placeholder="パスワード"><br>

		<button type="submit">ログイン</button>
	</div>
	<div>
		<span id="registError_message" style="color: red;"></span>
		<!-- <button onclick="registError()">エラーを表示</button> -->

		<input type="text" id="" name="" required placeholder="ユーザー名"><br>

		<input type="password" id="password" name="password" required placeholder="パスワード"><br>

		<input type="password" id="password" name="password" required placeholder="パスワード（確認用）"><br>
		<button type="submit">新規登録</button>
	</div>
</form>
</body>
</html>