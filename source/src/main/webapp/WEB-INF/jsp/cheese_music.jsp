<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<c:if test="${not empty sessionScope.result}">
	<p style="color: green;">${sessionScope.result}</p>
	<c:remove var="result" scope="session"/>
</c:if>

	<button id="addMusic">+</button>
	<label>曲を追加</label>
	<div id="addMusicSection">
	<form method="POST" id="regist_music" action="<c:url value='/CheeseRegistMusicServlet' />">
		<input type="text" name="name" placeholder="曲名" id="music_name">
		<button type="submit" value="登録">追加</button>
		<p id="output" style="color: red;"></p>
	</form>
	</div>
<!-- 曲検索 -->
<section>
<label><img src="<c:url value='/img/blackcheese.png' />" width="25" height="25">曲検索</label>
<form method="POST" action="<c:url value='/CheeseMusicListServlet' />">
	<input type="text" name="name" placeholder="キーワード">
	<button type="submit">
		<img src="<c:url value='/img/search.png' />" width="15" height="15">
	</button>
	<div>
		<label><input type="radio" name="sort" value="new" checked>新しい順</label>
		<label><input type="radio" name="sort" value="old">古い順</label>
		<label><input type="radio" name="sort" value="update">更新順</label>
	</div>
</form>
</section>
<!-- 曲リスト -->
<c:if test="${not empty cardList}">
<label>曲一覧</label>
<c:forEach var="music" items="${cardList}">
<div class="music-box">	
 	<button class="toggle-btn" data-target="music_${music.id}" >▶ ${music.name}</button>

      <div id="music_${music.id}" class="toggle-section">
      
       <a href="<c:url value='/CheeseEditMusicServlet?id=${music.id}' />">
       <button type="submit">編集</button>
       </a>
       
       <form action="CheeseDeleteMusicServlet" method="POST" style="display:inline;" onsubmit="return confirm('本当に削除しますか？');">
        <input type="hidden" name="id" value="${music.id}">
        <button type="submit">削除</button>
       </form>
      
      <!-- フレーズ一覧 -->
      <div class="phrase-list">
      <div>${phrase.name}</div>
        <!--  /c:forEach-->
       </div>
     </div>
    </div>
  </c:forEach>
</c:if>
    
<c:if test="${empty cardList}">
  <p>検索結果がありません。</p>
</c:if>
 </main>
<script src="<c:url value='/js/cheese_music.js' />"></script>
</body>
</html>