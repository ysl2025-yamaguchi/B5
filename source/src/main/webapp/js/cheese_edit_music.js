
/*document.addEventListener('DOMContentLoaded', function () {
  const saveBtn = document.getElementById('saveBtn');
  const messageArea = document.getElementById('messageArea');
  const addPhraseBtn = document.getElementById('addPhraseBtn');
  const phraseContainer = document.getElementById('phraseContainer');

  // 保存ボタンの処理
  saveBtn.addEventListener('click', function () {
    const songName = document.getElementById('songName').value;
    if (!songName || songName.trim() === '') {
      messageArea.textContent = '曲タイトルを入力してください';
      messageArea.style.color = 'red';
      return;
    }

    // 保存処理に必要なデータ収集（ここはfetchで送る処理にあとで拡張可）
    const phraseBoxes = document.querySelectorAll('.phraseBox');
    const phrases = Array.from(phraseBoxes).map((box, index) => {
      return {
        title: box.querySelector('input[name="title"]').value,
        author: box.querySelector('input[name="author"]').value,
        memo: box.querySelector('textarea[name="memo"]').value,
        order: index
      };
    });

    console.log('保存データ:', {
      songName: songName,
      phrases: phrases
    });

    messageArea.textContent = '保存しました！（仮処理）';
    messageArea.style.color = 'green';
  });

  // ＋ボタンの処理：フレーズ欄追加
  addPhraseBtn.addEventListener('click', function () {
    const phraseBoxes = document.querySelectorAll('.phraseBox');
    const newIndex = phraseBoxes.length;

    // 元のテンプレートから複製
    const newBox = document.createElement('div');
    newBox.className = 'phraseBox';
    newBox.dataset.index = newIndex;
    newBox.innerHTML = `
      <h3>${newIndex + 1}.</h3>
      <input type="text" name="title" placeholder="タイトル">
      <input type="text" name="author" placeholder="名前">
      <textarea name="memo"></textarea>
      <div class="controls">
        <button class="moveUp" type="button">↑</button>
        <button class="moveDown" type="button">↓</button>
        <button class="deleteBtn" type="button">×</button>
      </div>
    `;

    phraseContainer.appendChild(newBox);
    attachControlEvents(newBox); // イベント付け直し
  });

  // 全ての既存のphraseBoxにイベント付け
  document.querySelectorAll('.phraseBox').forEach(box => {
    attachControlEvents(box);
  });

  // ↑↓×ボタンのイベントを1つの関数で設定
  function attachControlEvents(box) {
    const deleteBtn = box.querySelector('.deleteBtn');
    const moveUpBtn = box.querySelector('.moveUp');
    const moveDownBtn = box.querySelector('.moveDown');

    deleteBtn.addEventListener('click', function () {
      box.remove();
      updatePhraseIndices();
    });

    moveUpBtn.addEventListener('click', function () {
      const prev = box.previousElementSibling;
      if (prev && prev.classList.contains('phraseBox')) {
        phraseContainer.insertBefore(box, prev);
        updatePhraseIndices();
      }
    });

    moveDownBtn.addEventListener('click', function () {
      const next = box.nextElementSibling;
      if (next && next.classList.contains('phraseBox')) {
        phraseContainer.insertBefore(next, box);
        updatePhraseIndices();
      }
    });
  }

  // 番号をリセット（1. 2. ...）
  function updatePhraseIndices() {
    const boxes = document.querySelectorAll('.phraseBox');
    boxes.forEach((box, i) => {
      box.dataset.index = i;
      const heading = box.querySelector('h3');
      if (heading) heading.textContent = `${i + 1}.`;
    });
  }
});
*/

document.addEventListener('DOMContentLoaded', function () {
  const saveBtn = document.getElementById('saveBtn');
  const messageArea = document.getElementById('messageArea');
  const phraseContainer = document.getElementById('phraseContainer');
  const addPhraseBtn = document.getElementById('addPhraseBtn');

  // 保存ボタン処理
  saveBtn.addEventListener('click', function (e) {
    e.preventDefault();

    const songName = document.getElementById('songName').value;
    if (!songName || songName.trim() === '') {
      messageArea.textContent = '曲タイトルを入力してください';
      messageArea.style.color = 'red';
      return;
    }

    // form に hidden input を追加して submit する
    const form = document.getElementById('editForm');
    const phraseBoxes = document.querySelectorAll('.phraseBox');

    // 古い hidden input を削除
    form.querySelectorAll('input[name^="phrases["]').forEach(el => el.remove());

    phraseBoxes.forEach((box, index) => {
      const title = box.querySelector('input[name="title"]').value;
      const memo = box.querySelector('textarea[name="memo"]').value;

      const inputTitle = document.createElement('input');
      inputTitle.type = 'hidden';
      inputTitle.name = `phrases[${index}].title`;
      inputTitle.value = title;
      form.appendChild(inputTitle);

      const inputMemo = document.createElement('input');
      inputMemo.type = 'hidden';
      inputMemo.name = `phrases[${index}].memo`;
      inputMemo.value = memo;
      form.appendChild(inputMemo);

      // タグ複数選択も hidden input として追加（必要なら）
      const selectedTags = Array.from(box.querySelectorAll('select[name="tags"] option:checked'))
        .map(opt => opt.value);

      selectedTags.forEach((tag, tagIndex) => {
        const inputTag = document.createElement('input');
        inputTag.type = 'hidden';
        inputTag.name = `phrases[${index}].tags[${tagIndex}]`;
        inputTag.value = tag;
        form.appendChild(inputTag);
      });
    });

    // 正常表示
    messageArea.style.color = 'green';
    messageArea.textContent = '保存しました（送信中）';

    form.submit();
  });

  // ＋ボタン：フレーズ追加
  addPhraseBtn.addEventListener('click', function () {
    const newIndex = phraseContainer.children.length;

    const phraseBox = document.createElement('div');
    phraseBox.className = 'phraseBox';
    phraseBox.dataset.index = newIndex;

    phraseBox.innerHTML = `
      <h3>${newIndex + 1}.</h3>
      <input type="text" name="title" placeholder="タイトル">
      <input type="text" name="author" placeholder="名前">
      <textarea name="memo"></textarea>
      <select name="tags" multiple>
        <option value="恋愛">恋愛</option>
        <option value="日常">日常</option>
        <option value="人生">人生</option>
      </select>
      <div class="controls">
        <button class="moveUp">↑</button>
        <button class="moveDown">↓</button>
        <button class="deleteBtn">×</button>
      </div>
    `;

    phraseContainer.appendChild(phraseBox);
    attachControlEvents(phraseBox);
  });

  // ↑ ↓ × イベントを設定
  function attachControlEvents(box) {
    box.querySelector('.moveUp').addEventListener('click', function () {
      const prev = box.previousElementSibling;
      if (prev) phraseContainer.insertBefore(box, prev);
    });

    box.querySelector('.moveDown').addEventListener('click', function () {
      const next = box.nextElementSibling;
      if (next) phraseContainer.insertBefore(next, box);
    });

    box.querySelector('.deleteBtn').addEventListener('click', function () {
      phraseContainer.removeChild(box);
    });
  }

  // 初期表示分にもイベント付与
  document.querySelectorAll('.phraseBox').forEach(attachControlEvents);
  
});
