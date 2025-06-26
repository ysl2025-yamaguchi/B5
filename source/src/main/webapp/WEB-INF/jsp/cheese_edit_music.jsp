<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cheese Edit Music</title>
<link rel="stylesheet" href="<c:url value='/css/cheese_edit_music.css' />">
</head>
<body>

	<jsp:include page="cheese_header.jsp" />

	<!-- 曲名表示 -->
	<h1>曲名：${music.name}</h1>
	<input type="hidden" id="songId" name="songId" value="${music.id}" />

	<!-- ボタン群 -->
	<div class="actionButtons">
		<button id="addPhraseBtn" type="button">＋</button>
		<button id="saveBtn" form="editForm" type="submit">保存</button>
	</div>

	<!-- メッセージ -->
	<div id="dupMessageArea" class="message red"></div>
	<div id="dupSongNames" class="message gray"></div>
	<div id="messageArea" class="message red">
		<c:if test="${not empty result}">
    	<span id="error_message"><c:out value="${result}" /></span>
	<br>
  </c:if>
	</div>

	<!-- ＋ボタン -->
	

	<!--  テンプレート -->
	<div class = "phraseBox" data-index = "0">
		<input type="hidden" name="phrase_id"
			value="">
		<h3></h3>
		<button class="deleteBtn" type="button">×</button>
		<span>フレーズ名:</span> <select name="phraseSelect">
			<option value="">-- フレーズを選択 --</option>
			<c:forEach var="p" items="${phraseList}">
				<option value="${p.id}"
					data-path="<c:url value='/upload/${p.path}'/>"
					<c:if test = "${p.name == assignedPhraseList[i].name}">selected</c:if>>
					${p.name}</option>
			</c:forEach>
			<!-- JSでpopulatePhraseOptions()が追加 -->
		</select>

		<audio controls
			src="null"></audio>
		<br> <span>タイトル:</span> <input type="text" name="title"
			value="" placeholder="タイトル">
		<span>メモ:</span> <input type="text" name="remarks"
			value="" placeholder="メモ">

		<!-- ▼ 操作ボタン -->
		<div class="controls">
			<button class="moveUp" type="button">↑</button>
			<button class="moveDown" type="button">↓</button>
		</div>
	</div>

	<!-- ▼ フォーム -->
	<form id="editForm" method="post" action="<c:url value='/CheeseEditMusicServlet' />">

		<input type="hidden" name="music_id" value="${music.id}" />

		<!-- ▼ フレーズ入力欄リスト -->
		<div id="phraseContainer">
			
			<c:if test="${assignedPhraseList != null && assignedPhraseList.size() > 0}">
				<c:forEach var="i" begin="0" end="${assignedPhraseList .size()- 1}">

					<div class="phraseBox" data-index="${i}">
						<input type="hidden" name="phrase_id"
							value="${assignedPhraseList[i].id}">
						<h3>${i + 1}.</h3>
						<button class="deleteBtn" type="button">×</button>
						<span>フレーズ名:</span> <select name="phraseSelect">
							<option value="">-- フレーズを選択 --</option>
							<c:forEach var="p" items="${phraseList}">
								<option value="${p.id}"
									data-path="<c:url value='/upload/${p.path}'/>"
									<c:if test = "${p.name == assignedPhraseList[i].name}">selected</c:if>>
									${p.name}</option>
							</c:forEach>
							<!-- JSでpopulatePhraseOptions()が追加 -->
						</select>
						
						<audio controls
							src="<c:url value='/upload/${assignedPhraseList[i].path}'/>"></audio>
						<br> <span>タイトル:</span> <input type="text" name="title"
							value="${assignedMusicPhraseList[i].title}" placeholder="タイトル">
						<span>メモ:</span> <input type="text" name="remarks"
							value="${assignedMusicPhraseList[i].remarks}" placeholder="メモ">

						<!-- ▼ 操作ボタン -->
						<div class="controls">
							<button class="moveUp" type="button">↑</button>
							<button class="moveDown" type="button">↓</button>
							
						</div>
					</div>
				</c:forEach>
			</c:if>

			<!-- 初期データがないとき、空の1つを用意 -->
			<!--
			<c:if test="${empty assignedPhraseList}">
				<div class="phraseBox" data-index="0">
					<h3>1.</h3>

					<input type="text" name="title" placeholder="タイトル">
					<textarea name="remarks"></textarea>

					<div class="phrase-audio-section">
						<label>フレーズ選択： <select class="phrase-select"
							name="phraseSelect">
								<option value="0">-- フレーズを選択 --</option>
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
			</c:if>-->
		</div> 
		<!-- phraseContainer -->

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
