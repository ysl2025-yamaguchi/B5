
//エラーメッセージの表示
document.getElementById('regist_music').onsubmit = function(event) {
    
    const music_name = document.getElementById('music_name').value;
    if (music_name === '') {
        document.getElementById('output'). textContent ='曲名を入力してください。';
        event.preventDefault();
    }
};

/*document.addEventListener("DOMContentLoaded", function () {
  // 初期状態：曲追加セクションを非表示
  const addSection = document.getElementById('addMusicSection');
  if (addSection) addSection.style.display = 'none';

 // 曲追加ボタンのトグル
 /* const addBtn = document.getElementById('addMusic');
  if (addBtn) {
    addBtn.addEventListener('click', function (e) {
      e.preventDefault();
      addSection.style.display = (addSection.style.display === 'block') ? 'none' : 'block';
    });
  }*/

  // 初期状態：全ての曲詳細セクションを非表示
  /*const toggleSections = document.querySelectorAll(".toggle-section");
  toggleSections.forEach(function(section) {
    section.style.display = "none";
  }); */

//曲追加ボタンの操作



document.getElementById('addMusic').addEventListener('dblclick', function(e){
  e.preventDefault();
  document.getElementById('addMusicSection').style.display = 'none';
});

document.getElementById('addMusic').addEventListener('click', function(e){
  e.preventDefault();
  document.getElementById('addMusicSection').style.display = 'block';
});

//曲表示ボタンの操作

  document.addEventListener("DOMContentLoaded", function () {
    const toggleButtons = document.querySelectorAll(".toggle-btn");

    toggleButtons.forEach(function (btn) {
      btn.addEventListener("click", function () {
        const targetId = btn.getAttribute("data-target");
        const section = document.getElementById(targetId);

        if (section.style.display === "block") {
          section.style.display = "none";
          btn.textContent = btn.textContent.replace("▼", "▶");
        } else {
          section.style.display = "block";
          btn.textContent = btn.textContent.replace("▶", "▼");
        }
      });
    });
  });