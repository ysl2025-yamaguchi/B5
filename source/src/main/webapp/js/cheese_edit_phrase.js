// タグを追加
//+ボタンをクリックすると登録済や新規のフォームが表示される
document.getElementById('addTag').addEventListener('click', function (e) {
      e.preventDefault();
      document.getElementById('addTagSection').style.display = 'block';
    });
//+ボタンをダブルクリックすると登録済や新規のフォームが消える
document.getElementById('addTag').addEventListener('dblclick', function (e) {
      e.preventDefault();
      document.getElementById('addTagSection').style.display = 'none';
     
    });
let formObj = document.getElementById('updateTag');
let errorMessageObj = document.getElementById('error_message');


formObj.onsubmit = function() {
    errorMessageObj.textContent = '変更が行われました';
 }
//新規追加
const addButton=document.getElementById('addButton');
const newTagInput=document.getElementById('newTagInput');
const tagsContainer=document.getElementById('tagsContainer');

    addButton.addEventListener('click',function () {
      const tagText=newTagInput.value.trim();
      if (tagText === '') return;

      const tagDiv=document.createElement('div');
      tagDiv.classList.add('addedTag');

      const tagInput=document.createElement('input');
      tagInput.type='text';
      tagInput.value=tagText;
      tagInput.readOnly=true;

      const deleteBtn=document.createElement('button');
      deleteBtn.textContent='✖';
      deleteBtn.addEventListener('click',function () {
        tagsContainer.removeChild(tagDiv);
      });

      tagDiv.appendChild(tagInput);
      tagDiv.appendChild(deleteBtn);
      tagsContainer.appendChild(tagDiv);

      newTagInput.value = '';
    });
