<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Cheese Edit Music</title>
</head>
<body>

<jsp:include page="cheese_header.jsp" />

<!-- ▼ フレーズ一覧（JavaScript用） -->
<script>
  const phraseList = [
    <c:forEach var="p" items="${phraseList}">
      { id: ${p.id}, name: "${p.name}", path: "${p.path}" },
    </c:forEach>
  ];
</script>

<!-- 曲名表示 -->
<h1>曲名：${music.name}</h1>
<input type="hidden" id="songId" name="songId" value="${music.id}" />

<!-- ボタン群 -->
<div class="actionButtons">
  <label>重複数チェック: <input type="number" id="duplicateCount" value="3" min="2" max="5"></label>
  <button id="checkDuplicates" type="button">重複チェック</button>
  <button id="saveBtn" form="editForm" type="submit">保存</button>
</div>

<!-- メッセージ -->
<div id="dupMessageArea" class="message red"></div>
<div id="dupSongNames" class="message gray"></div>
<div id="messageArea" class="message red">
  <c:if test="${not empty errorMsg}">
    ${errorMsg}
  </c:if>
</div>

<!-- ＋ボタン -->
<button id="addPhraseBtn" type="button">＋</button>

<!-- ▼ フォーム -->
<form id="editForm" method="post" action="CheeseEditMusicServlet">

<input type="hidden" name="music_id" value="${musicId}" />

  <!-- ▼ フレーズ入力欄リスト -->
  <div id="phraseContainer">


    <c:forEach var = "i" items = "${assignedPhraseList}">
      <div class="phraseBox" data-index="${i}">
        <input type = "hidden" name = "phrase_id" value = "${assignedPhraseList[i].id}">
        <h3>${i + 1}. </h3>

		
		<span id ="phraseName"><c:out value = "${assignedPhraseList[i].name}"/></span> <br>

        <input type="text" name="title" value="${assignedMusicPhraseList[i].title}" placeholder="タイトル">
        <input type="text" name="remarks" value="${assignedMusicPhraseList[i].remarks}"placeholder="メモ">

        <!-- ▼ 音声フレーズ選択欄 -->
        <div class="phrase-audio-section">
          <label>フレーズ選択：
            <select>
            <option value="">-- フレーズを選択 --</option>
              <c:forEach var="p" items="${phraseList}">
                <option value="${p.id}">${p.name}</option>
              </c:forEach>
              <!-- JSでpopulatePhraseOptions()が追加 -->
            </select>
          </label>
          <button type="button" class="playBtn">▶</button>
        </div>

        <!-- ▼ 操作ボタン -->
        <div class="controls">
          <button class="moveUp" type="button">↑</button>
          <button class="moveDown" type="button">↓</button>
          <button class="deleteBtn" type="button">×</button>
        </div>
      </div>
    </c:forEach>

    <!-- 初期データがないとき、空の1つを用意 -->
    <c:if test="${empty assignedPhraseList}">
      <div class="phraseBox" data-index="0">
        <h3>1.</h3>

        <input type="text" name="title" placeholder="タイトル">
        <textarea name="remarks"></textarea>

        <div class="phrase-audio-section">
          <label>フレーズ選択：
            <select class="phrase-select" name="phraseSelect">
              <option value="">-- フレーズを選択 --</option>
            </select>
          </label>
          <button type="button" class="playBtn">▶</button>
        </div>

        <div class="controls">
          <button class="moveUp" type="button">↑</button>
          <button class="moveDown" type="button">↓</button>
          <button class="deleteBtn" type="button">×</button>
        </div>
      </div>
    </c:if>

  </div><!-- phraseContainer -->

</form>

<!-- JavaScript読み込み -->
<script src="<c:url value='/js/cheese_edit_music.js' />"></script>

</body>
</html>


    <!--  
      <h3>${status.index + 1}.</h3>

      <input type="hidden" name="musicId" value="${song.id}" />
      
      <input type="text" name="title" value="${phrase.title}" placeholder="タイトル"> 
      <textarea name="memo">${phrase.memo}</textarea>
   		
      <div class="controls">
        <button class="moveUp" type="button">↑</button>
        <button class="moveDown" type="button">↓</button>
        <button class="deleteBtn" type="button">×</button>
      </div>
    </div>
    -->
