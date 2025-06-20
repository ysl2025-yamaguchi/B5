<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>フレーズ編集画面</title>
 <link rel="stylesheet" type="text/css" href="css/cheese_edit_phrase.css">
 <script src="<c:url value='/js/cheese_edit_phrase.js'/>"></script>

</head>
<body>
    

 <jsp:include page="cheese_header.jsp" />
 <c:if test="${not empty result}">
    <p style="color: green;">${result}</p>
</c:if>
 <form method="POST" action="<c:url value='/CheesePhraseEditServlet' />">

     <div class="phrase">
 
        <input type="hidden" name="phraseId" value="${phrase.id}" />
        <input type="text" name="phraseName" placeholder="名前" value="${phrase.name}"><br>
        <input type="text" name="phraseRemarks" placeholder="メモ" value="${phrase.remarks}"><br>
         
     </div>

     <div>
       <span>タグの追加</span> <br>
         <input type = "radio" id = "registed" name = "tag_registed" value = "registed" checked />
         <label for = "registed">登録済み</label>
            <input type = "radio" id = "new" name = "tag_registed" value = "new">
         <label for = "new">新規</label>  <br>
            <div id ="tag_input_box">
               <select id = "select_tag">
                 
                  <c:forEach var="tag" items="${tagList}">
                     <option value = "${tag.id}"><c:out value = "${tag.name} "/></option>
                  </c:forEach>
               </select>
               <input type = "text" name = "tag_name" id = "input_tag" hidden autocomplete="off">
               <button type = "button" id = "add_tag_button">追加</button>
            </div>
            <div id = "added_tag_list">
            	<c:forEach var="tag" items="${phraseTagList}">
            		<div class = "tag_item">
            			<span><c:out value = "${tag.name}"/></span>
            			<button class = "unassign_button">✕</button>
            		</div>
                </c:forEach>
            </div>
        </div>
     <div>
       <input type ="submit" id="regist_button" name="regist" value = "登録">
     </div>
  </form>
</body>
</html>