/* ログイン時のエラー文 */
function loginError() {
    var inputName = document.getElementById("username");
    var inputPw = document.getElementById("password");
    
    if(inputName.value === "") {
		alert("ユーザー名を入力してください。");
		return false;
	} else if(inputPw.value === "") {
		alert("パスワードを入力してください。");
		return false;
	} else {
		return true;
	}
}

/* 新規登録時のエラー文 */
function registError() {
    var inputName = document.getElementById("username");
    var inputPw = document.getElementById("password");
    var inputRePw = document.getElementById("rePassword");
    
    if(inputName.value === "") {
		alert("ユーザー名を入力してください。");
		return false;
	} else if(inputPw.value === "") {
		alert("パスワードを入力してください。");
		return false;
	} else if(inputRePw.value === "") {
		alert("パスワード（確認用）を入力してください。");
		return false;
	} else {
		return true;
	}
}