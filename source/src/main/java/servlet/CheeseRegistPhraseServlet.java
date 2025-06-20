package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.CheesePhraseDao;
import dto.CheesePhrase;

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
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		
		CheesePhraseDao phraseDao = new CheesePhraseDao();
		CheesePhrase phrase = new CheesePhrase();
		
		String phraseName = request.getParameter("name");
		String phraseRemarks = request.getParameter("remarks");
		
		
		// 音声ファイルを取得
		Part part = request.getPart("uploded_file");
		if (part != null) {
			// ファイルのファイル名を取得
			String uplodedFileName = part.getSubmittedFileName();
			
			// ファイルの拡張子を取得
			int dotPosition = uplodedFileName.lastIndexOf(".");
			String extension = uplodedFileName.substring(dotPosition);
			
			// 保存時のファイル名を決定
			CheesePhraseDao dao = new CheesePhraseDao();
			String fileName = "phrase_" + dao.getNextId() + extension;
			String filePath;
			
			try {
				if (testFlag) {
					File dir = new File("c:/plusdojo2025/B5/uploded/");
					if (!dir.exists()) {
						dir.mkdir();
					}
					filePath = "c:/plusdojo2025/B5/uploded/" + fileName;
			    	part.write(filePath);
				}
				else {
					File dir = new File(request.getContextPath() + "/uploded/");
					if (!dir.exists()) {
						dir.mkdir();
					}
					filePath = request.getContextPath() + "/uploded/" + fileName;
			    	part.write(filePath);
				}
				
				phrase.setName(phraseName);
				phrase.setRemarks(phraseRemarks);
				phrase.setPath(filePath);
				phrase.setUserId(1);
				if (phraseDao.insert(phrase)) {
					request.setAttribute("result", "登録が成功しました!!!!!!!!!!!!!!!!!!!!!!!!");
				}
				else {
					throw new IOException();
				}
			}
			catch (IOException e ) {
				request.setAttribute("result", "登録登録に失敗しました");
			}
		}
		
    	RequestDispatcher dispatcher = request.getRequestDispatcher("CheesePhraseListServlet");
    	dispatcher.forward(request, response);
	}

}
