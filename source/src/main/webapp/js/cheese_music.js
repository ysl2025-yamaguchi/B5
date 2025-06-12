document.getElementById('regist_music').onsubmit = function(event) {
    
    const music_name = document.getElementById('music_name').value;
    if (music_name === '') {
        document.getElementById('output'). textContent ='曲名を入力してください。';
        event.preventDefault();
    }
};