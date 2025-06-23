package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.CheesePhraseDao;
import dao.CheesePhraseTagDao;
import dao.CheeseTagDao;
import dto.CheesePhrase;
import dto.CheesePhraseTag;
import dto.CheeseTag;

/**
 * Servlet implementation class CheeseRegistPhraseServlet
 */
@MultipartConfig
@WebServlet("/CheeseRegistPhraseServlet")
public class CheeseRegistPhraseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean testFlag = true;
		boolean result = true;
		int userId = 1;
		
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
						dirPath  = this.getServletConfig().getServletContext().getRealPath("") + "/uploded";
//						String dirPath = "C:/plusdojo2025/B5/uploded";
					}
					else {
						dirPath = request.getContextPath() + "/uploded";
					}
					
					File dir = new File(dirPath);
					if (!dir.exists()) {
						dir.mkdir();
					}
			    	part.write(dirPath + "/" + uploadedPhrase.getPath());
					
					result = true;
				}
				catch (IOException e ) {
					result = false;
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
					CheesePhraseTag phraseTag = new CheesePhraseTag();
					
					if (!phraseTagDao.insert(uploadedPhrase.getId(), tagId)) {
						result = true;
					}
					else {
						result = false;
					}
				}
			}
		}
		
		if (result) {
			response.sendRedirect("CheesePhraseListServlet");
		}
		else {
			response.sendRedirect("CheesePhraseListServlet");
		}
	}

}
