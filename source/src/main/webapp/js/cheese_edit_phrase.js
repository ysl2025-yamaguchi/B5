document.addEventListener('DOMContentLoaded', function () {

  // ラジオボタン切り替え
  document.querySelectorAll('input[name="tag_registed"]').forEach(function (radio) {
    radio.addEventListener('change', function () {
      const isRegisted = (this.value === 'registed');
      document.getElementById('select_tag').style.display = isRegisted ? 'inline' : 'none';
      document.getElementById('input_tag').style.display = isRegisted ? 'none' : 'inline';
    });
  });

  // タグ追加ボタン処理
  document.getElementById('add_tag_button').addEventListener('click', function () {
    const isRegisted = document.getElementById('registed').checked;
    let tagName = "";
    let tagId = "";

    if (isRegisted) {
      const select = document.getElementById('select_tag');
      const selectedOption = select.options[select.selectedIndex];
      tagName = selectedOption.text.trim();
      tagId = selectedOption.value;
    } else {
      tagName = document.getElementById('input_tag').value.trim();
      if (tagName === "") return;
    }

    if (tagName === "") return;

    // タグ表示ブロック作成
    const tagListDiv = document.getElementById('added_tag_list');
    const tagItem = document.createElement('div');
    tagItem.classList.add('tag_item');

    const tagText = document.createElement('span');
    tagText.textContent = tagName;

    const removeBtn = document.createElement('button');
    removeBtn.textContent = "✕";
    removeBtn.classList.add('unassign_button');
    removeBtn.addEventListener('click', function () {
      tagListDiv.removeChild(tagItem);
    });

    // hidden inputs
    const hiddenTagInput = document.createElement('input');
    hiddenTagInput.type = 'hidden';
    hiddenTagInput.name = 'addedTags';
    hiddenTagInput.value = tagName;

    const hiddenModeInput = document.createElement('input');
    hiddenModeInput.type = 'hidden';
    hiddenModeInput.name = 'tagMode';
    hiddenModeInput.value = isRegisted ? 'registed' : 'new';

    // 要素を追加
    tagItem.appendChild(tagText);
    tagItem.appendChild(removeBtn);
    tagItem.appendChild(hiddenTagInput);
    tagItem.appendChild(hiddenModeInput);
    tagListDiv.appendChild(tagItem);

    // 入力欄リセット
    if (!isRegisted) {
      document.getElementById('input_tag').value = "";
    }

  });

});




