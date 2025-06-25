 const modal = document.getElementById("themeModal");
 const icon = document.getElementById("themeIcon");
 const themaNumber = document.getElementById("thema_number");
 
 let themaName;
 
 switch (themaNumber.value) {
	case '1':
	  themaName = 'light';
	  break;
	case '2':
	  themaName = 'dark';
	  break;
	case '3':
	  themaName = 'ocean';
	  break;
	case '4':
	  themaName = 'pink';
	  break;
	case '5':
	  themaName = 'green';
	  break;
	default:
	  themaName = 'light';
	  break;
}
document.body.className = themaName;
console.log(themaName);
 
 
 
 icon.addEventListener("click", () => {
   modal.style.display = "flex";
 });

 document.querySelectorAll(".theme-option").forEach(btn => {
   btn.addEventListener("click", () => {
     const thema = btn.getAttribute("data-theme");
     //document.body.className = thema;
     modal.style.display = "none";
     
     // TODO: fetchでサーブレットにPOSTしてDB保存も可能
     switch (thema) {
	    case 'light':
	      themaNumber.value = 1;
	      break;
	    case 'dark':
	      themaNumber.value = 2;
	      break;
	    case 'ocean':
	      themaNumber.value = 3;
	      break;
	    case 'pink':
	      themaNumber.value = 4;
	      break;
	    case 'green':
	      themaNumber.value = 5;
	      break;
	    default:
	      themaNumber.value = 1;
	      break;
     }
     
     document.change_thema.submit();
   });
 });

 // モーダル背景をクリックしたら閉じる
 modal.addEventListener("click", (e) => {
   if (e.target === modal) modal.style.display = "none";
 });
    
    
