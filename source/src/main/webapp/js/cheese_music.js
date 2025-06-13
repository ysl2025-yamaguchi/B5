
//エラーメッセージの表示
document.getElementById('regist_music').onsubmit = function(event) {
    
    const music_name = document.getElementById('music_name').value;
    if (music_name === '') {
        document.getElementById('output'). textContent ='曲名を入力してください。';
        event.preventDefault();
    }
};

//曲追加ボタンの操作
document.getElementById('addMusic').addEventListener('click', function(e){
  e.preventDefault();
  document.getElementById('addMusicSection').style.display = 'block';
});

document.getElementById('addMusic').addEventListener('dblclick', function(e){
  e.preventDefault();
  document.getElementById('addMusicSection').style.display = 'none';
});

//曲表示ボタンの操作
document.getElementById('openMusic').addEventListener('click', function(e){
  e.preventDefault();
  document.getElementById('openMusicSection').style.display = 'block';
});

document.getElementById('openMusic').addEventListener('dblclick', function(e){
  e.preventDefault();
  document.getElementById('openMusicSection').style.display = 'none';
});
