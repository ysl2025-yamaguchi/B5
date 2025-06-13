<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Music</title>
</head>
<body>

<header class="header">
	<h1><img src="/webapp/img/logo.png" width="50" height="50" alt="CHEESE"></h1>
	<div class="logo"><a href="/webapp/MenuServlet"><img src="/webapp/img/logo.png" width="500" height="200" alt="MUSIC CHEESE"></a></div>
	<nav class="nav">
		<ul>
			<li><a href="/webapp/CheesePhraseListServlet"><strong>Home</strong></a></li>
			<li><a href="/webapp/CheeseMusicListServlet"><strong>Music</strong></a></li>
			<li><a href="/webapp/CheeseLogoutServlet"><strong>Logout</strong></a></li>
		</ul>
	</nav>
</header>

<!-- 曲名入力フォーム -->
<h1 for="songName">曲名：</h1>
<input type="text" id="songName" name="songName" value="${song.name}" />

<!-- ボタン群 -->
<div class="actionButtons">
<label>重複数チェック: <input type="number" id="duplicateCount" value="3" min="2" max="5"></label>
<button id="checkDuplicates">重複チェック</button>
<button id="saveBtn" >保存</button>
</div>

<!-- 重複メッセージ -->
<div id="dupMessageArea" class="message red"></div>
<div id="dupSongNames" class="message gray"></div>

<div id="messageArea" class="message red">
<c:if test="${not empty errorMsg}">
${errorMsg}
</c:if>
</div>


<!-- 追加ボタン -->
<button id="addPhraseBtn">＋</button>

<!-- フレーズリスト -->
<div id="phraseContainer">
<c:forEach var="phrase" items="${phraseList}" varStatus="status">
<div class="phraseBox" data-index="${status.index}">
<h3>${status.index + 1}.</h3>
<input type="text" name="title" value="${phrase.title}" placeholder="タイトル">
<input type="text" name="author" value="${phrase.author}" placeholder="名前">
<textarea name="memo">${phrase.memo}</textarea>

<!-- コントロールボタン -->
<div class="controls">
<button class="moveUp">↑</button>
<button class="moveDown">↓</button>
<button class="deleteBtn">×</button>
</div>
</div>
</c:forEach>
</div>

<script src="scripts/cheese_edit_music.js"></script>

</body>
</html>