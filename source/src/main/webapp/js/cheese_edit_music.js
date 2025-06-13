document.addEventListener('DOMContentLoaded', function () {
const saveBtn = document.getElementById('saveBtn');
const messageArea = document.getElementById('messageArea');

saveBtn.addEventListener('click', function () {
const songName = document.getElementById('songName').value;

if (!songName || songName.trim() === '') {
messageArea.textContent = '曲タイトルを入力してください';
return;
}

// 正常な入力時の処理
messageArea.style.color = 'green';
messageArea.textContent = '保存しました！（仮処理）';
});
});