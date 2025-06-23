"use strict";

window.addEventListener('DOMContentLoaded', function() {
   // ラジオボタンによるプルダウンとテキストボックスの表示切替
   document.querySelectorAll('input[name="tag_registed"]').forEach(function(radio) {
      radio.addEventListener('change', function() {
         const mode = this.id;
         if (mode === 'registed') {
            document.getElementById('select_tag').style.display = 'block';
            document.getElementById('input_tag').style.display = 'none';
         }
         else {
            document.getElementById('select_tag').style.display = 'none';
            document.getElementById('input_tag').style.display = 'block';
         }
      });
   });

   // 追加ボタンによるタグの追加表示
   document.getElementById('add_tag_button').addEventListener('click', function () {
      const isRegisted = document.getElementById('registed').checked;
      let value = '';
      let id = 0;

      if (isRegisted) {
         const select = document.getElementById('select_tag');
         value = select.options[select.selectedIndex].text.trim();
         id = select.value;
      } else {
         value = document.getElementById('input_tag').value.trim();
         
         const options = document.getElementById("select_tag");
         for (const option of options) {
				console.log()
				if (value === option.textContent.trim()) {
					id = option.value;
				}
			}
      }

      if (value === '') {
         return;
      }

      // 重複チェック
      const existingTags = Array.from(document.querySelectorAll('#added_tag_list .tag_item span')).map(span => span.textContent);
      if (existingTags.includes(value)) {
         return;
      }

      // タグ追加
      const tagListDiv = document.getElementById('added_tag_list');
      const tagItem = document.createElement('div');
      tagListDiv.appendChild(tagItem);
      tagItem.classList.add('tag_item');

      const tagText = document.createElement('span');
      tagText.textContent = value;
      
      const inputTagId = document.createElement('input');
      inputTagId.type = 'hidden';
      inputTagId.value = id;
      inputTagId.name = 'registed_tag_id';
      
      const inputTagName = document.createElement('input');
      inputTagName.type = 'hidden';
      inputTagName.value = value;
      inputTagName.name = 'registed_tag_name';

      const unassignButton = document.createElement('button');
      unassignButton.textContent = '✕';
      unassignButton.classList.add('unassign_button');
      unassignButton.addEventListener('click', function() {
         tagListDiv.removeChild(tagItem);
      });

      tagItem.appendChild(tagText);
      tagItem.appendChild(unassignButton);
      tagItem.appendChild(inputTagId);
      tagItem.appendChild(inputTagName);
      
      tagListDiv.appendChild(tagItem);

      value = document.getElementById('input_tag').value = '';
   });

   // 登録ボタン押下時，送信する
   document.getElementById('regist_button').addEventListener('click', function() {
      const phraseName = document.getElementById('regist_phrase_form');
      const errorMessageObj = document.getElementById('error_message');
      if (!phraseName.name.value) {
         errorMessageObj.textContent = 'フレーズ名を入力してください';
         return false;
      }
      else{
         this.form.submit();
      }
      errorMessageObj.textContent = null;
   });
});



document.addEventListener('DOMContentLoaded',pageLoad)
// テキストボックス内でエンターキーを押したら追加ボタンを起動
function pageLoad(){
   var textbox = document.getElementById('input_tag');
   textbox.addEventListener('keydown', enterKeyPress);
}

function enterKeyPress(event){
   if (event.key === 'Enter') {
      event.preventDefault(); // フォーム送信などのデフォルト動作を防止
      document.getElementById('add_tag_button').click();
   }
}
