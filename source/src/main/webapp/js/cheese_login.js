document.addEventListener("DOMContentLoaded", function () {
	
  // intro終了後にログイン画面を表示
  setTimeout(function () {
    const intro = document.getElementById("introScreen");
    if (intro) {
      intro.style.display = "none";
    }
    document.querySelector(".noheader").style.display = "flex";
  }, 3000); // 3秒後に切り替え

  // 初期状態でログイン画面を隠しておく
  document.querySelector(".noheader").style.display = "none";

  // 要素取得
  const loginForm = document.getElementById("loginForm");
  const registerForm = document.getElementById("registerForm");
  const loginBtn = document.getElementById("loginBtn");
  const registerBtn = document.getElementById("registerBtn");


  // ログイン画面表示切替
  loginBtn.addEventListener("click", showLogin);
  registerBtn.addEventListener("click", showRegister);

  // フォーム送信時バリデーション
  loginForm.addEventListener("submit", function(event) {
    if (!validateLogin()) {
      event.preventDefault();
    }
  });
	
  registerForm.addEventListener("submit", function(event) {
    if (!validateRegister()) {
      event.preventDefault();
    }
  });

  // 表示切替関数
  function showLogin() {
    loginForm.classList.add("active");
    registerForm.classList.remove("active");
    loginBtn.classList.add("active");
    registerBtn.classList.remove("active");
  }

  function showRegister() {
    registerForm.classList.add("active");
    loginForm.classList.remove("active");
    registerBtn.classList.add("active");
    loginBtn.classList.remove("active");
  }

  // ログイン
  function validateLogin() {
    const user = document.getElementById("loginUser").value.trim();
    const pass = document.getElementById("loginPass").value.trim();
    const error = document.getElementById("loginError");
    if (!user || !pass) {
      if (!user && !pass) {
        error.textContent = "ユーザー名とパスワードを入力してください";
      } else if (!user) {
        error.textContent = "ユーザー名を入力してください";
      } else {
        error.textContent = "パスワードを入力してください";
      }
      blinkErrorElement("loginError");
      return false;
    }
    error.textContent = "";
    return true;
  }

  // 新規登録
  function validateRegister() {
    const user = document.getElementById("regUser").value.trim();
    const pass = document.getElementById("regPass").value.trim();
    const confirm = document.getElementById("regPassConfirm").value.trim();
    const error = document.getElementById("registerError");
    if (!user || !pass || !confirm || pass !== confirm) {
      if (!user || !pass || !confirm) {
        error.textContent = "全ての項目を入力してください";
      } else if (pass !== confirm){
        error.textContent = "パスワードが一致しません！";
      } else {
		error.textContent ="";
	  }
      blinkErrorElement("registerError");
      return false;
    }
    error.textContent = "";
    return true;
  }

  // 点滅アニメーション
  function blinkErrorElement(id) {
    const el = document.getElementById(id);
    if (el) {
      el.classList.remove("blink-error");
      void el.offsetWidth; // トリガー再発火用
      el.classList.add("blink-error");
    }
  }


});



document.addEventListener("DOMContentLoaded", function () {
  const feature = document.querySelector(".feature-section");

  window.addEventListener("scroll", function () {
    const trigger = window.innerHeight * 0.8;
    const featureTop = feature.getBoundingClientRect().top;

    if (featureTop < trigger) {
      feature.classList.add("visible");
    }
  });
});




