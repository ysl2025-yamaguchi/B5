 const modal = document.getElementById("themeModal");
    const icon = document.getElementById("themeIcon");
    icon.addEventListener("click", () => {
      modal.style.display = "flex";
    });

    document.querySelectorAll(".theme-option").forEach(btn => {
      btn.addEventListener("click", () => {
        const theme = btn.getAttribute("data-theme");
        document.body.className = theme;
        modal.style.display = "none";
        // TODO: fetchでサーブレットにPOSTしてDB保存も可能
      });
    });

    // モーダル背景をクリックしたら閉じる
    modal.addEventListener("click", (e) => {
      if (e.target === modal) modal.style.display = "none";
    });
