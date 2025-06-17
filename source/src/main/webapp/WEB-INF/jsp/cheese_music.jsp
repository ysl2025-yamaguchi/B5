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
<c:if test="${not empty message}">
	<p style="color: green;">${result}</p>
</c:if>
	<button id="addMusic">+</button>
	<label>曲を追加</label>
	<div id="addMusicSection">
	<form method="POST" id="regist_music" action="<c:url value='/CheeseRegistMusicServlet' />">
		<input type="text" placeholder="名前" id="music_name" name="music_name">
		<button type="submit">追加</button>
		<p id="output" style="color: red;"></p>
	</form>
	</div>
<!-- 曲検索 -->
<section>
<h2><img src="${pageContext.request.contextPath}/img/blackcheese.png" width="25" height="25">曲検索</h2>
<form method="POST" action="<c:url value='/CheeseMusicListServlet' />">
	<input type="text" name="name" placeholder="キーワード">
	<button type="submit">
		<img src="${pageContext.request.contextPath}/img/search.png" width="15" height="15">
	</button>
	<div>
		<label><input type="radio" name="sort" checked>新しい順</label>
		<label><input type="radio" name="sort">古い順</label>
		<label><input type="radio" name="sort">更新順</label>
	</div>
</form>
</section>
<!-- 曲リスト -->
<c:if test="${not empty cardList}">
<h2>曲一覧</h2>
<c:forEach var="music" items="${cardList}">
 	<button id="openMusic">></button>
    <label>${music.name}</label>
      <div id="openMusicSection">
       <form action="#" method="POST">${phrase.title}<br>
       <button type="submit">編集</button>
       <button type="submit" formaction="CheeseDeleteMusicServlet?id=${music.id} }">削除</button>
       </form>
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