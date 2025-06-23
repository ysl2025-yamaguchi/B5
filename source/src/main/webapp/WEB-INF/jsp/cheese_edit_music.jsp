<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cheese Edit Music</title>
<style>
  .phraseBox {
    border: 1px solid #ccc;
    padding: 10px;
    margin-bottom: 10px;
  }
  .controls button {
    margin-right: 5px;
  }
  .message {
    margin: 10px 0;
    font-weight: bold;
  }
  .red { color: red; }
  .green { color: green; }
  .gray { color: gray; }
</style>
</head>
<body>

<jsp:include page="cheese_header.jsp" />


  <select class="phraseSelect" name="phrase_id">
      <c:forEach var="p" items="${phraseList}">
        <option value="${p.id}" data-path="${p.path}">${p.name}</option>
      </c:forEach>
    </select>


<!-- 曲名入力フォーム -->
<h1 for="songName">曲名：${music.name}</h1>
<input type="hidden" id="songName" name="songName" value="${music.name}" />


<!-- ボタン群 -->
<div class="actionButtons">
  <label>重複数チェック: <input type="number" id="duplicateCount" value="3" min="2" max="5"></label>
  <button id="checkDuplicates" type="button">重複チェック</button>
  <button id="saveBtn" form="editForm" type="button">保存</button>
</div>

<!-- メッセージエリア -->
<div id="dupMessageArea" class="message red"></div>
<div id="dupSongNames" class="message gray"></div>
<div id="messageArea" class="message red">
  <c:if test="${not empty errorMsg}">
    ${errorMsg}
  </c:if>
</div>

<!-- ＋ボタン -->
<button id="addPhraseBtn" type="button">＋</button>

<!-- ✅ フォーム開始 -->
<form id="editForm" method="post" action="CheeseEditMusicServlet">

<!-- フレーズリスト -->
<div id="phraseContainer">
<c:forEach var="phrase" items="${phraseList2}" varStatus="status"> 
    <div class="phraseBox" data-index="${status.index}">
      <h3>${status.index + 1}.</h3>
      
      
 <!-- フレーズ選択＋再生 -->
 <!--  
<div class="phrase-audio-section">
  <label>フレーズ選択：
    <select class="phraseSelect" name="phrase_id">
      <c:forEach var="p" items="${phraseList2}">
        <option value="${p.id}" data-path="${p.path}">${p.name}</option>
      </c:forEach>
    </select>
  </label>
  <audio class="phrasePlayer" controls style="display:none;"></audio>
</div>
 -->
      
      
      <input type="hidden" name="musicId" value="${song.id}" />
      
      <input type="text" name="title" value="${phrase.title}" placeholder="タイトル">
      <input type="text" name="author" value="${phrase.author}" placeholder="名前">
      <textarea name="memo">${phrase.memo}</textarea>
      
      <!-- タグ入力欄 
<div class="tag-section">
  <input type="text" name="tagInput" class="tagInput" placeholder="タグを入力（例：#ラブソング）">
  <button type="button" class="addTagBtn">タグ追加</button>
  <div class="tagList"></div> 
</div>
     -->
     
      <!-- コントロールボタン -->
      <div class="controls">
        <button class="moveUp" type="button">↑</button>
        <button class="moveDown" type="button">↓</button>
        <button class="deleteBtn" type="button">×</button>
      </div>
    </div>
</c:forEach> 
</div>

</form>
<!-- ✅ フォーム終了 -->

<script src="<c:url value='/js/cheese_edit_music.js' />"></script>

</body>
</html>
