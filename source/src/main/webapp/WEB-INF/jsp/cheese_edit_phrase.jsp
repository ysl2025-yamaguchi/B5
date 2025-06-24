<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>フレーズ編集画面</title>

<link rel="stylesheet" href="<c:url value='/css/cheese_edit_phrase.css' />">
</head>
<body>
 <jsp:include page="cheese_header.jsp" />

<span id = "error_message"><c:out value = "${result}"/></span> <br>
 <form method="post" action="<c:url value='/CheesePhraseEditServlet' />" id="updatePhrase">
      <!-- phrase -->
      <div class="phrase">
         <input type = "hidden" name ="id" value = "${phrase.id}">
         <input type="text" id="phraseName" name="phraseName" placeholder="フレーズ名" value="${phrase.name}"><br>
         <input type="text" id="phraseRemarks" name="phraseRemarks" placeholder="メモ" value="${phrase.remarks}"><br>
      </div>
      <div>
      <!-- add tag -->
            <label><img src="<c:url value='/img/blackcheese.png' />" width="25" height="25">タグの追加</label><br>
            <input type = "radio" id = "registed" name = "tag_registed" value = "registed" checked />
            <label for = "registed">登録済み</label>
            <input type = "radio" id = "new" name = "tag_registed" value = "new">
            <label for = "new">新規</label>  <br>
            <div id ="tag_input_box">
              <select id ="select_tag">
               
                     <c:forEach var="tag" items="${phraseTagList[phrase.id]}">
                         <option><c:out value = "${tag.name} "/></option>
                      </c:forEach>
                
              </select>
               <input type = "text" name = "tag_name" id = "input_tag" hidden autocomplete="off">
               <button type = "button" id = "add_tag_button">追加</button>
            </div>
            
            <div id = "added_tag_list">
                 <div class="tag_item"><input type="hidden">
            </div>
            
            
           </div>
                <input type = "submit" id = "regist_button" name = "regist" value = "登録">
           </div>
    
    </form>
 <script src="<c:url value='/js/cheese_edit_phrase.js'/>"></script>

</body>
</html>
