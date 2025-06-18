<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>home</title>
   <link rel="stylesheet" type="text/css" href="css/cheese_home.css">
   <!-- <script src = "js/cheese_home.js"></script> -->
   <script src="<c:url value='/js/cheese_home.js' />"></script>
</head>

<body>
   <!-- ヘッダー -->
   <jsp:include page="cheese_header.jsp" />

   <!-- フレーズ登録 -->
    <div>
      <span id = "error_message"><c:out value = "${result}"/></span> <br>
      <form method = "POST" id = "regist_phrase_form" action = "CheeseRegistPhraseServlet" enctype="multipart/form-data">
      <input type = "file" value = "uplode file" name = "uploded_file" accept = ".mp3, .m4a, .wav"> <br>
         <input type = "text" name = "name" placeholder = "フレーズ名"> <br>
         <input type = "text" name = "remarks" placeholder = "メモ"> <br>
         <div>
            <span>タグの追加</span> <br>
            <input type = "radio" id = "registed" name = "tag_registed" value = "registed" checked />
            <label for = "registed">登録済み</label>
            <input type = "radio" id = "new" name = "tag_registed" value = "new">
            <label for = "new">新規</label>  <br>
            <div id ="tag_input_box">
               <select id = "select_tag">
                  <option value = "0">タグA</option>
                  <option value = "0">タグB</option>
                  <option value = "0">タグC</option>
                  <c:forEach var="tag" items="${tagList}">
                     <option value = "${tag.id}"><c:out value = "${tag.name} "/></option>
                  </c:forEach>
               </select>
               <input type = "text" name = "tag_name" id = "input_tag" hidden autocomplete="off">
               <button type = "button" id = "add_tag_button">追加</button>
            </div>
            <div id = "added_tag_list">
               
            </div>
         </div>
         <input type = "button" id = "regist_button" name = "regist" value = "登録">
      </form>
   </div>

   <!-- フレーズ検索 -->
   <form method = "post" action = "CheesePhraseListServlet" autocomplete = "off">
      <span>フレーズを検索</span> <br>
      <div class = "search_box">
         <input type = "text" name = "search_str_line" placeholder = "「キーワード」「#タグ」"  value="${searchStrLine}">
         <input type = "submit" name = "search" value = ""> <br>
         <select name = "order">
            <option value = "created_desc" <c:if test = "${order == 'created_desc'}">selected</c:if>>登録が新しい順</option>
            <option value = "created_asc" <c:if test = "${order == 'created_asc'}">selected</c:if>>登録が古い順</option>
            <option value = "updated_desc" <c:if test = "${order == 'updated_desc'}">selected</c:if>>更新が新しい順</option>
            <option value = "updated_asc" <c:if test = "${order == 'updated_asc'}">selected</c:if>>更新が古い順</option>
         </select>
      </div> 
   </form>

   <div class = "phrase_list">
      <c:forEach var="phrase" items="${phraseList}">
         <details>
            <summary class="test">
               <c:out value = "${phrase.name}" />
            </summary>
            <div>
               <audio controls src="phrase_audio/sample.m4a"></audio><br>
               <table>
                  <tr>
                     <td>
                        メモ：
                     </td>
                     <td>
                        <span></span>
                        <c:out value = "${phrase.remarks} " />
                     </td>
                  </tr>
                  <tr>
                     <td>
                        タグ：
                     </td>
                     <td>
                        <c:forEach var="tag" items="${tagList[phrase.id]}">
                           <c:out value = "${tag.name} " />
                        </c:forEach>
                     </td>
                  </tr>
               </table>
               <div>
                  <form method = "POST" action = "B5/CheeseEditPhraseervlet">
                     <input type = "hidden" name = "id" value = "${phrase.id}">
                     <input type = "submit" value = "編集">
                  </form>
                  <form method = "POST" action = "B5/CheeseDeletePhraseervlet">
                     <input type = "hidden" name = "id" value = "${phrase.id}">
                     <input type = "submit" value = "削除">
                  </form>
               </div>
            </div>
         </details>
      </c:forEach>
         <c:forEach var="e" items="${companyList}">
         <option value="${e.id}"><c:out value = "${e.name}" /></option>
      </c:forEach>
   </div>

</body>

</html>