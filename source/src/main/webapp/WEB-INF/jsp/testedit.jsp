<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>Phrase Update Test</title></head>
<body>
    <h2>フレーズ更新テストフォーム</h2>
    <form action="CheesePhraseEditServlet" method="post">
        <p>phraseTagId: <input type="text" name="phraseTagId" value="1" /></p>
        <p>phraseId: <input type="text" name="phraseId" value="1" /></p>
        <p>tagId: <input type="text" name="tagId" value="2" /></p>
        <p>tagName: <input type="text" name="tagName" value="テストタグ" /></p>
        <input type="submit" name="submit" value="更新" />
    </form>
</body>
</html>