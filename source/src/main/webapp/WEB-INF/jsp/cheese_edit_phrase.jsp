<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     
    <small id="errorMessage"></small>
    <!-- 編集したいフレーズデータの入力 -->
    <form action="">
      <div class="phrase">
      <input type="text" name="phraseName" id="" placeholder="名前"><br>
     <input type="text" name="phraseRemarks" id="" placeholder="メモ"><br>
    </div>

    <div class="addTag">
        <div class="">
            <button class="addTag" name="">+</button>
            <label for="">タグを追加</label>
    </div>
      
    <div class="registeredTag">
          <input type="radio" name="" id="">
          <label for="">登録済</label><br>
          <select name="" id="">
            <option value="">登録済タグ</option>
          </select>
    </div>

    <div class="newTag">
          <input type="radio" name="" id="">
          <label for="">新規</label><br>
          <input type="text" name="" id="">
    </div>
        <input type="submit" value="追加"><br>
        <input type="submit" value="登録">
    </div>
    
    </form>
    
</body>
</html>