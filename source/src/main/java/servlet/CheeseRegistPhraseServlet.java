package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.CheesePhraseDao;
import dao.CheesePhraseTagDao;
import dao.CheeseTagDao;
import dto.CheesePhrase;
import dto.CheeseTag;
import dto.CheeseUser;

/**
 * Servlet implementation class CheeseRegistPhraseServlet
 */
@MultipartConfig
@WebServlet("/CheeseRegistPhraseServlet")
public class CheeseRegistPhraseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		CheeseUser user = (CheeseUser)session.getAttribute("loginUser");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/CheeseLoginServlet");
			return;
		}
		int userId = user.getId();
		
		boolean testFlag = true;
		boolean result = false;
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		
		CheesePhraseDao phraseDao = new CheesePhraseDao();
		CheesePhrase phrase = new CheesePhrase();
		CheesePhrase uploadedPhrase = new CheesePhrase();
		
		String phraseName = request.getParameter("name");
		String phraseRemarks = request.getParameter("remarks");

		
		// 音声ファイルを取得
		Part part = request.getPart("uploded_file");
		if (part != null && part.getSize() > 0) {
			// ファイルのファイル名を取得
			String uplodedFileName = part.getSubmittedFileName();
			
			// ファイルの拡張子を取得
			int dotPosition = uplodedFileName.lastIndexOf(".");
			if (dotPosition != -1) {
				// ファイルの拡張子を取得
				String extension = uplodedFileName.substring(dotPosition);
				// 拡張子の指定
				if (extension.equals(".wav") || extension.equals(".mp3") || extension.equals(".m4a")) {
					// 保存時のファイルパスの宣言
					String dirPath;
					
					try {
						// データベースへ登録
						phrase.setName(phraseName);
						phrase.setRemarks(phraseRemarks);
						phrase.setUserId(userId);
						uploadedPhrase = phraseDao.insertWithFile(phrase, extension);
						
						// ファイルの保存処理
						if (testFlag) {
							dirPath  = getServletContext().getRealPath("/upload");
//							String dirPath = "C:/plusdojo2025/B5/uploded";
						}
						else {
							dirPath = request.getContextPath() + "/upload";
						}
						
						File dir = new File(dirPath);
						if (!dir.exists()) {
							dir.mkdir();
						}
						
						String fullPath = dirPath + File.separator + uploadedPhrase.getPath();
				    	part.write(fullPath);
						
						result = true;
					}
					catch (IOException e ) {
						result = false;
					}
				}
			}
		}
		else {
			phrase.setName(phraseName);
			phrase.setRemarks(phraseRemarks);
			phrase.setUserId(userId);
			
			uploadedPhrase = phraseDao.insertWithoutFile(phrase);
			
			if (uploadedPhrase != null) {
				result = true;
			}
			else {
				result = false;
			}
		}
		
		if (result) {
			String[] tagIdArray = request.getParameterValues("registed_tag_id");
			String[] tagNameArray = request.getParameterValues("registed_tag_name");
			
			int tagId;
			String tagName;
			if (tagIdArray != null && tagNameArray != null) {
				for (int i = 0; i < tagIdArray.length; i++) {
					tagId = Integer.parseInt(tagIdArray[i]);
					
					if (tagId == 0) {
						// タグの追加
						CheeseTagDao tagDao = new CheeseTagDao();
						CheeseTag tag = new CheeseTag();
						
						tagName = tagNameArray[i];
						tag.setName(tagName);
						tag.setUserId(userId);
						
						tagId = tagDao.insert(tag);
					}
					
					// フレーズとタグの中間テーブルに追加
					CheesePhraseTagDao phraseTagDao = new CheesePhraseTagDao();
					
					result = phraseTagDao.insert(uploadedPhrase.getId(), tagId);
				}
			}
		}
		
		if (result) {
			request.getSession().setAttribute("result", "登録成功しました！");
		}
		else {
			request.getSession().setAttribute("result", "登録に失敗しました。");
		}
		
		// 同じJSPにフォワードする
		response.sendRedirect(request.getContextPath() + "/CheesePhraseListServlet"); // JSPを表示するサーブレットなど
	    return;
	}

}
