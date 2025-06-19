let selectObj = document.getElementById('select_thema');
let tmpObj = document.getElementById('tmp');

document.getElementById('themeImage').addEventListener('click', function() {
	if (selectObj.style.display === 'none' || selectObj.style.display === '') {
		selectObj.style.display = 'block';
	}
	else {
		selectObj.style.display = 'none';
	}
});

document.getElementById('select_thema').addEventListener('change', function() {
	document.getElementById('change_thema').submit();
}); 
 
 /*
 const img = document.getElementById("themeImage");
    const selector = document.getElementById("themeSelector");
    const body = document.body;
    // 画像クリックでプルダウン表示
    img.addEventListener("click", () => {
      selector.style.display = selector.style.display === "none" ? "inline-block" : "none";
    });
    // プルダウン選択でテーマ変更
    selector.addEventListener("change", () => {
      body.className = ""; // 既存テーマクラス全消し
      body.classList.add("theme-" + selector.value); // 選んだテーマを追加
    });
    */

/*    
const themeImage = document.getElementById("themeImage");
const dropdown = document.getElementById("themeDropdown");
const body = document.body;
// クリックでメニュー開閉
themeImage.addEventListener("click", () => {
  dropdown.style.display = (dropdown.style.display === "block") ? "none" : "block";
});
// メニュー項目クリックでテーマ変更
dropdown.addEventListener("click", (e) => {
  if (e.target.tagName === "LI") {
    const theme = e.target.getAttribute("data-theme");
    body.classList.remove("theme-light", "theme-dark", "theme-ocean", "theme-sunset");
    body.classList.add("theme-" + theme);
    dropdown.style.display = "none"; // 閉じる
  }
});
// ページの他の場所をクリックしたら閉じる
document.addEventListener("click", (e) => {
  if (!themeImage.contains(e.target) && !dropdown.contains(e.target)) {
    dropdown.style.display = "none";
  }
});
*/