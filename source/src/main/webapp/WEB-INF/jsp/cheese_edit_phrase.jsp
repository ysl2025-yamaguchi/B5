<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     
   <span id="errorMessage"></span>
    <!-- 編集したいフレーズデータの入力 -->
    <form action="/webapp/CheeseEditPhraseServlet">
      <div class="phrase">
       <input type="hidden" name="phraseId" value="${phrases.id}"/>
        <input type="text" name="phraseName" id="" placeholder="名前" value="${phrases.name}"><br>
        <input type="text" name="phraseRemarks" id="" placeholder="メモ" value="${phrases.remarks}"><br>
      </div>

      <div>
        <button id="addTag" name="">+</button>
        <label for="">タグを追加</label>
      </div>
      <div id="addTagSection">
          <div class="registeredTag">
          <input type="radio" name="" id="" >
          <label for="">登録済</label><br>
          <select name="" id="">
            <option value="">登録済タグ</option>
          </select>
      </div>

      <div class="newTag" >
          <input type="radio" name="" id="">
          <label for="">新規</label><br>
          <input type="text" name="" id="">
      </div>
      <div class="addButton">
        <input type="submit" value="追加"><br>
      </div>

      <div class="addedTag" hidden>
        <input type="text" name="" id="" readonly>
      </div>
      </div>
     
        
      <input type="submit" value="登録">
     
    
    </form>
    <script>
 // タグを追加
  //+ボタンをクリックすると登録済や新規のフォームが表示される
  document.getElementById('addTag').addEventListener('click', function (e) {
        e.preventDefault();
        document.getElementById('addTagSection').style.display = 'block';
      });
  //+ボタンをダブルクリックすると登録済や新規のフォームが消える
  document.getElementById('addTag').addEventListener('dblclick', function (e) {
        e.preventDefault();
        document.getElementById('addTagSection').style.display = 'none';
       
      });
    </script>
   </body>
</html>