<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>home</title>
   <link rel="stylesheet" type="text/css" href="css/cheese_home.css">
   <script src = "js/cheese_home.js"></script>
</head>

<body>
   <!-- ヘッダー -->
   <jsp:include page="cheese_header.jsp" />

   <!-- フレーズ登録 -->
    <div>
      <span id = "error_message"></span> <br>
      <form method = "POST" id = "regist_phrase_form" action = "B5/CheeseRegistPhraseServlet">
      <input type = "file" value = "uplode file" accept = ".mp3, .m4a, .wav"> <br>
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
   <form method = "post" action = "B5/CheesePhraseListServlet" autocomplete = "off">
      <span>フレーズを検索</span> <br>
      <dvi class = "search_box">
         <input type = "text" name = "search_word_line" placeholder = "「キーワード」「#タグ」">
         <input type = "submit" name = "search" value = "" class> <br>
         <select name = "order">
            <option value = "created_desc">登録が新しい順</option>
            <option value = "created_asc">登録が古い順</option>
            <option value = "updated_desc">更新が新しい順</option>
            <option value = "updated_asc">更新が古い順</option>
         </select>
      </dvi> 
   </form>

   <dvi class = "phrase_list">
      <c:forEach var="phrase" items="${phraseList}">
         <details open>
            <summary class="test">
               フレーズA
               <c:out value = "${phras.name}" />
            </summary>
            <div>
               <audio controls src="phrase_audio/sample.m4a"></audio><br>
               <table>
                  <tr>
                     <td>
                        メモ：
                     </td>
                     <td>
                        <span>ここにメモ内容を表示</span>
                        <c:out value = "${phrase.reamarks} " /></option>
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
                        タグA タグB タグC
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
   </dvi>

</body>

</html>