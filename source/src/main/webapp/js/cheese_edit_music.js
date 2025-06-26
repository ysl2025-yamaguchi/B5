/*
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

	 const form = document.getElementById('editForm');
	 const phraseBoxes = document.querySelectorAll('.phraseBox');

	 form.querySelectorAll('input[name^="phrases["]').forEach(el => el.remove());

	 phraseBoxes.forEach((box, index) => {
		const title = box.querySelector('input[name="title"]').value; 
		const memo = box.querySelector('textarea[name="memo"]').value;
		const phraseId = box.querySelector('select[name="phraseSelect"]').value || "";

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

		const inputPhraseId = document.createElement('input');
		inputPhraseId.type = 'hidden';
		inputPhraseId.name = `phrases[${index}].phraseId`;
		inputPhraseId.value = phraseId;
		form.appendChild(inputPhraseId);
	 });

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

		  <!-- ▼ 音声フレーズ選択欄（ここを修正） -->
  <div class="phrase-audio-section">
	 <label>フレーズ選択：
		<select class="phrase-select" name="phraseSelect">
		  <option value="">-- フレーズを選択 --</option>
		  <!-- JSでpopulatePhraseOptions()が追加してくれる -->
		</select>
	 </label>
	 <button type="button" class="playBtn">▶</button>
  </div>

		<div class="controls">
		  <button class="moveUp" type="button">↑</button>
		  <button class="moveDown" type="button">↓</button>
		  <button class="deleteBtn" type="button">×</button>
		</div>
	 `;

	 phraseContainer.appendChild(phraseBox);
	 attachControlEvents(phraseBox);
	 populatePhraseOptions(phraseBox); // ← 音声一覧取得処理
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

	 const playBtn = box.querySelector('.playBtn');
	 const select = box.querySelector('.phrase-select');
	 playBtn.addEventListener('click', function () {
		const path = select.selectedOptions[0]?.dataset.path;
		if (path) {
		  const audio = new Audio(path);
		  audio.play();
		} else {
		  alert("再生できる音声が選択されていません。");
		}
	 });
  }

  // 初期表示分にもイベント付与
  document.querySelectorAll('.phraseBox').forEach(box => {
	 attachControlEvents(box);
	 populatePhraseOptions(box); // 初期のセレクトにも反映
  });

  // 🔽 音声選択肢をサーバーから取得して <select> に追加
  function populatePhraseOptions(box) {
	 const select = box.querySelector('.phrase-select');
	 fetch("CheesePhraseListServlet") // ← 必要なサーブレットを作っておく
		.then(res => res.json())
		.then(data => {
		  data.forEach(phrase => {
			 const opt = document.createElement("option");
			 opt.value = phrase.id;
			 opt.textContent = phrase.name;
			 opt.dataset.path = phrase.path;
			 select.appendChild(opt);
		  });
		})
		.catch(() => {
		  const opt = document.createElement("option");
		  opt.textContent = "取得失敗";
		  select.appendChild(opt);
		});
  }

});

// 🔽 重複チェック処理（あなたの元のコードそのまま）
document.getElementById("checkDuplicates").addEventListener("click", function () {
  const inputs = document.querySelectorAll(".phrase-name");
  const params = [];
  inputs.forEach((el, i) => {
	 if (el.value.trim() !== "") {
		params.push("phrase" + i + "=" + encodeURIComponent(el.value.trim()));
	 }
  });
  fetch("CheeseCheckMusicServlet", {
	 method: "POST",
	 headers: { "Content-Type": "application/x-www-form-urlencoded" },
	 body: params.join("&")
  })
  .then(res => res.text())
  .then(msg => alert(msg));
});
*/

/*
let phraseBox = document.getElementById("phraseBox");
// 複製
let clone_element = phraseBox.cloneNode(true);
// 複製した要素の属性を編集
clone_element.id = "phraseBox2";
// 複製した要素の子孫要素を編集
let h2_element = clone_element.querySelector("h2");
h2_element.textContent = '複製した要素';
let h2_element = clone_element.querySelector("p");
h2_element.textContent = '複製した要素のテキスト';
// 複製したHTML要素をページに挿入
phraseBox.after(clone_element);
*/

document.addEventListener("DOMContentLoaded", function() {
	const phraseContainer = document.getElementById("phraseContainer");
	const addBtn = document.getElementById("addPhraseBtn");

	// 最初の1個目をテンプレートとして使う
	const templateBox = document.querySelector(".phraseBox");
	templateBox.style.display = 'none';

	// 枠の番号をふりなおす
	function renumberBoxes() {
		const boxes = phraseContainer.querySelectorAll(".phraseBox");
		boxes.forEach((box, index) => {
			box.setAttribute("data-index", index);
			const h3 = box.querySelector("h3");
			if (h3) h3.textContent = `${index + 1}.`;
		});
	}

	// 削除・並び替えボタンのイベントをセット
	function setControls(box) {
		const deleteBtn = box.querySelector(".deleteBtn");
		const moveUpBtn = box.querySelector(".moveUp");
		const moveDownBtn = box.querySelector(".moveDown");

		if (deleteBtn) {
			deleteBtn.addEventListener("click", () => {
				box.remove();
				renumberBoxes();
			});
		}

		if (moveUpBtn) {
			moveUpBtn.addEventListener("click", () => {
				const prev = box.previousElementSibling;
				if (prev && prev.classList.contains("phraseBox")) {
					phraseContainer.insertBefore(box, prev);
					renumberBoxes();
				}
			});
		}

		if (moveDownBtn) {
			moveDownBtn.addEventListener("click", () => {
				const next = box.nextElementSibling;
				if (next && next.classList.contains("phraseBox")) {
					phraseContainer.insertBefore(next, box);
					renumberBoxes();
				}
			});
		}

		// ▼ select変更時にspanのテキストを更新する処理を追加
		const select = box.querySelector('select[name="phraseSelect"]');
		const phrase_id = box.querySelector('input[name="phrase_id"]');
		const audio = box.querySelector('audio');

		if (select) {
			select.addEventListener("change", function() {
				const selectedOption = this.options[this.selectedIndex];
				const path = selectedOption.getAttribute('data-path');
				phrase_id.value = selectedOption.value;

				if (path !== null && path !== "") {
					audio.src = path;
					audio.load();
				}
				if (path == null) {
					audio.style.display = 'none';
				}
				else {
					audio.style.display = 'inline-block';
				}
			});
		}

		if (audio.getAttribute("src") == "" || audio.getAttribute("src") == 'null') {
			audio.style.display = 'none';
		}
		else {
			audio.style.display = 'inline-block';
		}
	}

	// セレクトボックスにフレーズを追加
	function populatePhraseOptions(selectEl) {
		// JSP側で渡された phraseList を使う
		if (typeof phraseList === "undefined") return;

		phraseList.forEach(p => {
			const option = document.createElement("option");
			option.value = p.id;
			option.textContent = p.name;
			option.setAttribute("data-path", p.path);
			selectEl.appendChild(option);
		});
	}


	// ＋ボタンが押された時の処理
	addBtn.addEventListener("click", () => {
		const clone = templateBox.cloneNode(true);
		
		clone.style.display = 'block';

		// 入力初期化
		const phrase_id = clone.querySelector('input[name="phrase_id"]');
		if (phrase_id) phrase_id.value = "";

		const title = clone.querySelector('input[name="title"]');
		if (title) title.value = "";

		const remarks = clone.querySelector('input[name="remarks"]');
		if (remarks) remarks.value = "";

		const audio = clone.querySelector('audio');
		audio.src = "";

		const select = clone.querySelector('select[name="phraseSelect"]');
		const options = select.options;

		options[0].selected = true;

		// ボタンイベント再セット
		setControls(clone);

		// 最後に追加
		phraseContainer.appendChild(clone);
		renumberBoxes();
	});

	// 初期表示の1個目にもイベントつける
	// setControls(templateBox)
	// 全ての要素にイベントをつける
	document.querySelectorAll(".phraseBox").forEach(setControls);

	const form = document.getElementById("editForm");

	form.addEventListener("submit", function(e) {
		const phraseIds = form.querySelectorAll('input[name="phrase_id"]');
		let hasZero = false;

		phraseIds.forEach(input => {
			if (input.value === "0" || input.value === "") {
				hasZero = true;
			}
		});

		if (hasZero) {
			e.preventDefault(); // フォーム送信を止める
			alert("未設定のフレーズがあります。");
		}
	});


});



