<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src = "js/cheese_edit_phrase.js"></script>
<link rel="stylesheet" type="text/css" href="css/cheese_edit_phrase.css">
</head>
<body>
  
 <form action="/CheeseEditPhraseServlet" method="post" id="updateTag">
    <div class="phrase">
      <input type="hidden" name="id" value="${phrases}" />
      <input type="text" name="phraseName" placeholder="名前" value="${phrases.id}"><br>
      <input type="text" name="phraseRemarks" placeholder="メモ" value="${phrases.id}"><br>
    </div>

    <div>
      <button type="button" id="addTag">+</button>
      <label>タグを追加</label>
    </div>

    <div class="addContainer">
      <div id="addTagSection">
        <div class="registeredTag">
          <input type="radio" name="tagType" id="registeredTagRadio" value="registered" checked>
          <label for="registeredTagRadio">登録済</label><br>
          <select name="registeredTag" id="registeredTagSelect">
            <c:forEach var="tag" items="${tags}">
                <option value="${tag.id}">${tag.name}</option>
            </c:forEach>
           
          </select>
        </div>

        <div class="newTag">
          <input type="radio" name="tagType" id="newTagRadio" value="new">
          <label for="newTagRadio">新規</label><br>
          <input type="text" name="newTag" id="newTagInput" placeholder="新しいタグ名">
        </div>

        <div id="tagsContainer"></div>

        <div class="addButton">
          <input type="button" value="追加" id="addButton"><br>
        </div>
      </div>
    </div>

    <div>
      <input type="submit" value="変更">
    </div>
  </form>
   
    


</body>
</html>