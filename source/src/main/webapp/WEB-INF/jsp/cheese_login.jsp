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
	<div>
		<span id="loginError_message" style="color: red;"></span>
		<button onclick="loginError()">エラーを表示</button>

		<label for="users_name">ユーザー名</label>
		<inpit type="" id="" name="" required><br>

		<label for="password">パスワード</label>
		<inpit type="password" id="password" name="password" required><br>

		<button type="">ログイン</button>
	</div>
	<div>
		<span id="registError_message" style="color: red;"></span>
		<button onclick="registError()">エラーを表示</button>

		<label for="users_name">ユーザー名</label>
		<inpit type="" id="" name="" required><br>

		<label for="password">パスワード</label>
		<inpit type="password" id="password" name="password" required><br>

		<button type="">新規登録</button>
	</div>

</form>
</body>
</html>