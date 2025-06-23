<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MUSIC CHEESE</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cheese_common_thema1.css">
	<script src="<c:url value='/js/cheese_common.js' />"></script>
	
	
	  <style>
    body.light { background: #ffffff; color: #222; }
    body.dark { background: #111; color: #fff; }
    body.ocean { background: #006e7f; color: #fff; }
    body.sunset { background: #ff6f61; color: #fff; }

    #themeIcon {
      cursor: pointer;
      width: 60px;
      transition: transform 0.3s;
    }
    #themeIcon:hover {
      transform: scale(1.1);
    }

    #themeModal {
      position: fixed;
      top: 0; left: 0;
      width: 100vw; height: 100vh;
      background: rgba(0,0,0,0.6);
      display: none;
      justify-content: center;
      align-items: center;
    }

    #themeModal .modal-content {
      background: #fff;
      color: #000;
      padding: 1em 2em;
      border-radius: 12px;
    }

    .theme-option {
      margin: 8px;
      cursor: pointer;
      padding: 6px 12px;
      border: 1px solid #ccc;
      border-radius: 8px;
      transition: background 0.3s;
    }

    .theme-option:hover {
      background: #f0f0f0;
    }
    
    .cheese-menu {
  position: fixed;           /* ページ右上に固定表示 */
  top: 15px;
  right: 15px;
  z-index: 1000;
}

.cheese-menu img {
  width: 30px;               /* 小さくしてアイコンっぽく */
  height: 45px;
  border-radius: 50%;        /* 円形にしてChrome風 */
  border: 2px solid #ccc;    /* 境界線つけてそれっぽく */
  cursor: pointer;
  box-shadow: 0 2px 5px rgba(0,0,0,0.2);
  transition: transform 0.2s;
}

.cheese-menu img:hover {
  transform: scale(1.1);     /* ホバーでちょい拡大 */
}
  </style>
	
	
</head>
<body>
<input type="hidden" name="selected_theme" id="selected_theme">
<header class="header">
	
	<div class="cheese-menu">
	<img src="<c:url value='/img/blackcheese.png' />" alt="テーマアイコン" id="themeIcon">
	</div>

  <div id="themeModal">
    <div class="modal-content">
      <p>テーマを選択してください:</p>
      <div class="theme-option" data-theme="light">ライト</div>
      <div class="theme-option" data-theme="dark">ダーク</div>
      <div class="theme-option" data-theme="ocean">オーシャン</div>
      <div class="theme-option" data-theme="sunset">サンセット</div>
    </div>
  </div>

  <script>
    const modal = document.getElementById("themeModal");
    const icon = document.getElementById("themeIcon");
    icon.addEventListener("click", () => {
      modal.style.display = "flex";
    });

    document.querySelectorAll(".theme-option").forEach(btn => {
      btn.addEventListener("click", () => {
        const theme = btn.getAttribute("data-theme");
        document.body.className = theme;
        modal.style.display = "none";
        // TODO: fetchでサーブレットにPOSTしてDB保存も可能
      });
    });

    // モーダル背景をクリックしたら閉じる
    modal.addEventListener("click", (e) => {
      if (e.target === modal) modal.style.display = "none";
    });
  </script>
	
	<div class="logo">
		<a href="<c:url value='/CheesePhraseListServlet' />">
			<img src="<c:url value='/img/logo.png' />" width="300" height="100" alt="MUSIC CHEESE">
		</a>
	</div>
	<nav class="nav">
		<ul>
			<li><a href="<c:url value='/CheesePhraseListServlet' />"><strong>Home</strong></a><span class="line-right"></span></li>
			<li><a href="<c:url value='/CheeseMusicListServlet' />"><strong>Music</strong></a><span class="line-right"></span></li>
			<li><a href="<c:url value='/CheeseLogoutServlet' />"><strong>Logout</strong></a><span class="line-left"></span></li>
		</ul>
	</nav>
</header>
</body>
</html>