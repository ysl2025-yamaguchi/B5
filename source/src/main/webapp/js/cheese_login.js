document.getElementById("loginForm").style.display = "block";

        function showLogin() {
            document.getElementById("loginForm").style.display = "block";
            document.getElementById("registerForm").style.display = "none";
        }

        function showRegister() {
            document.getElementById("loginForm").style.display = "none";
            document.getElementById("registerForm").style.display = "block";
        }


function validateLogin() {
            let username = document.getElementById("login_username").value;
            let password = document.getElementById("login_password").value;
            let errorMessage = document.getElementById("loginError_message");

            if (!username || !password)
            {
                event.preventDefault()
                if (!username && !password) {
                    errorMessage.textContent = "ユーザー名とパスワードを入力してください";
                } else if(!username && password) {
                    errorMessage.textContent = "ユーザー名を入力してください";
                } else if(username && !password) {
                    errorMessage.textContent = "パスワードを入力してください";
                } else {
                    errorMessage.textContent = ""; // エラー解除
                    alert("ログイン処理を開始！");
                }
            }
        }

        function validateRegister() {
            let username = document.getElementById("register_username").value;
            let password = document.getElementById("register_password").value;
            let rePassword = document.getElementById("register_rePassword").value;
            let errorMessage = document.getElementById("registError_message");

            if (!username || !password || !rePassword)
            {
                event.preventDefault()
                if (!username && !password && !rePassword) {
                    errorMessage.textContent = "全ての項目を入力してください";
                } else if(!username && password) {
                    errorMessage.textContent = "ユーザー名を入力してください";
                } else if(username && !password) {
                    errorMessage.textContent = "パスワードを入力してください";
                } else if (password !== rePassword) {
                    errorMessage.textContent = "パスワードが一致しません！";
                } else {
                    errorMessage.textContent = ""; // エラー解除
                    alert("新規登録処理を開始！");
                }
            }
        }
        