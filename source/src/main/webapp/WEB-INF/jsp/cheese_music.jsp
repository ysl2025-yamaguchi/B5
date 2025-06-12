<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>曲画面 | MUSIC CHEESE</title>
</head>
<body>
<section>
	<h1><img src="/webapp/img/add.png" width="25" height="25">曲を追加</h1>
	<div>
	<input type="text" placeholder="名前">
	<button>追加</button>
	</div>
</section>
<section>
<h2><img src="/webapp/img/blackcheese.png" width="25" height="25">曲検索</h2>
<div>
	<input type="text" placeholder="キーワード">
	<button><img src="/webapp/img/search.png" width="15" height="15"></button>
	<div>
		<label><input type="radio" name="sort" checked>新しい順</label>
		<label><input type="radio" name="sort">古い順</label>
		<label><input type="radio" name="sort">更新順</label>
	</div>
</div>
</section>

 <section>
    <div>
      <span><img src="/webapp/img/open.png" width="15" height="15">曲A</span>
      <div>
        <button>編集</button>
        <button>削除</button>
      </div>
    </div>
    <div>
      ・Aフレーズ<br>
      ・Bフレーズ
    </div>
  </section>
</body>
</html>