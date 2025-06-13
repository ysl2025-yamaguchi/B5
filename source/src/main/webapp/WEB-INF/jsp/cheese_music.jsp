<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>曲画面 | MUSIC CHEESE</title>
</head>
<body>
<jsp:include page="cheese_header.jsp" />
<main>
<!-- 曲追加 -->

	<button id="addMusic">+</button>
	<label>曲を追加</label>
	<div id="addMusicSection">
	<form method="POST" id="regist_music" action="/webapp/CheeseRegistMusicServlet">
		<input type="text" placeholder="名前" id="music_name" name="music_name">
		<button type="submit">追加</button>
		<p id="output" style="color: red;"></p>
	</form>
	</div>
<!-- 曲検索 -->
<section>
<h2><img src="/webapp/img/blackcheese.png" width="25" height="25">曲検索</h2>
<div>
	<input type="text" placeholder="キーワード">
	<button><img src="/webapp/img/search.png" width="15" height="15"></button>
	<div>
		<label><input type="radio" name="sort" checked>新しい順</label>
		<label><input type="radio" name="sort">古い順</label>
		<label><input type="radio" name="sort">更新順</label>
	</div>
</div>
</section>
<!-- 曲リスト -->
 <section class="song-list">
    <div class="song-header">
    <span><img src="/webapp/img/open.png" width="15" height="15">${music.name}</span>
      <div>
        <button>編集</button>
        <button>削除</button>
      </div>
    </div>
    <div class="phrases">
      <p>${phrase.title}</p>
    </div>
  </section>
 </main>
 <script src="/webapp/js/cheese_music.js"></script>
</body>
</html>