<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MUSIC CHEESE</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cheese_common_thema1.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cheese_common_thema2.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cheese_common_thema3.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cheese_common.css">
	
	
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
  <script src="<c:url value='/js/cheese_common.js' />" defer></script>
	
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