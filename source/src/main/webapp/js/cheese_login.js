document.addEventListener("DOMContentLoaded", function () {
	//読み込みタイミングを DOMContentLoaded に遅らせ
	
	
  const loginForm = document.getElementById("loginForm");
  const registerForm = document.getElementById("registerForm");
  const loginBtn = document.getElementById("loginBtn");
  const registerBtn = document.getElementById("registerBtn");
  const loginSubmitBtn = document.querySelector("#loginForm .submit-button");
  const registerSubmitBtn = document.querySelector("#registerForm .submit-button");

  loginBtn.addEventListener("click", showLogin);
  registerBtn.addEventListener("click", showRegister);
  loginSubmitBtn.addEventListener("click", validateLogin);
  registerSubmitBtn.addEventListener("click", validateRegister);

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

// ログインバリデーション
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
  document.querySelector("form").submit(); // ログイン成功で送信
  return true;
}

// 新規登録バリデーション
function validateRegister() {
  const user = document.getElementById("regUser").value.trim();
  const pass = document.getElementById("regPass").value.trim();
  const confirm = document.getElementById("regPassConfirm").value.trim();
  const error = document.getElementById("registerError");

  if (!user || !pass || !confirm || pass !== confirm) {
    if (!user || !pass || !confirm) {
      error.textContent = "全ての項目を入力してください";
    } else {
      error.textContent = "パスワードが一致しません！";
    }
    blinkErrorElement("registerError");
    return false;
  }

  error.textContent = "";
  document.querySelector("form").submit(); // 登録成功で送信
  return true;
}

// 点滅アニメーション
function blinkErrorElement(id) {
  const el = document.getElementById(id);
  if (el) {
    el.classList.remove("blink-error");
    void el.offsetWidth;
    el.classList.add("blink-error");
  }
}

});