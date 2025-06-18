
//エラーメッセージの表示
document.getElementById('regist_music').onsubmit = function(event) {
    
    const music_name = document.getElementById('music_name').value;
    if (music_name === '') {
        document.getElementById('output'). textContent ='曲名を入力してください。';
        event.preventDefault();
    }
};

// 曲追加フォームを初期非表示
  document.addEventListener("DOMContentLoaded", function () {
  const addMusicSection = document.getElementById('addMusicSection');
  addMusicSection.style.display = 'none';

  const addMusicBtn = document.getElementById('addMusic');

  addMusicBtn.addEventListener('click', function(e){
    e.preventDefault();
    addMusicSection.style.display = 'block';
  });

  addMusicBtn.addEventListener('dblclick', function(e){
    e.preventDefault();
    addMusicSection.style.display = 'none';
  });

// 全ての詳細セクションを非表示
  const toggleSections = document.querySelectorAll(".toggle-section");
  toggleSections.forEach(function (section) {
    section.style.display = "none";
  });

// 開閉処理を追加
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