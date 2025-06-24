package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CheeseMusicPhraseDao;
import dao.CheesePhraseDao;
import dto.CheeseMusicPhrase;
import dto.CheesePhrase;


@WebServlet("/CheeseEditMusicServlet")
public class CheeseEditMusicServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		CheeseUser user = (CheeseUser)session.getAttribute("loginUser");
//		if (user == null) {
//			response.sendRedirect("/CheeseLoginServlet");
//			return;
//		}
//		// musicを作成したuserIdとログイン済みのuserIdが違うならログイン画面へ
//		// musicIdの取得
		int musicId = Integer.parseInt(request.getParameter("id"));
//		// 曲データの取得
//		CheeseMusicDao musicDao = new CheeseMusicDao();
//		CheeseMusic music = musicDao.select(musicId);
//		 // セッションスコープのuserIdとmusicのuserIdの比較
//		if (user.getId() != music.getUserId()) {
//			// 違うならログイン画面へ
//			response.sendRedirect("/CheeseLoginServlet");
//			return;
//		}
		
		List<CheesePhrase> phraseList;
		CheesePhraseDao phraseDao =  new CheesePhraseDao();
		phraseList = phraseDao.select(new ArrayList<String>(), new ArrayList<String>(), "", 1);
		request.setAttribute("phraseList", phraseList);
			
		CheeseMusicPhraseDao musicPhraseDao = new CheeseMusicPhraseDao();	
		// music_phrase_tableからデータ取得
		List<CheeseMusicPhrase> assignedMusicPhraseList = musicPhraseDao.select(musicId)	;
		// phraseテーブルからデータ取得
		List<CheesePhrase>  assignedPhraseList = phraseDao.select(assignedMusicPhraseList);
		
		// jspへセット
		request.setAttribute("assignedMusicPhraseList", assignedMusicPhraseList);
		request.setAttribute("assignedPhraseList", assignedPhraseList);
		
//		for (CheeseMusicPhrase m : assignedMusicPhraseList) {
//			System.out.println(m.getId());
//		}
		for (CheesePhrase m : assignedPhraseList) {
			System.out.println(m.getId());
		}
		
		// 曲編集画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_edit_music.jsp");
		dispatcher.forward(request, response);
				
				
//		// CheeseEditMusicServlet の doGet 内
//		CheeseMusicPhraseDao phraseDao = new CheeseMusicPhraseDao();
//		List<CheeseMusicPhrase> phraseOptions = phraseDao.findAll(); // ユーザーIDに応じて絞るなら別途条件追加
//		request.setAttribute("phraseOptions", phraseOptions);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") == null) {
			response.sendRedirect("/CheeseLoginServlet");
			return;

		}
		
		
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		int music_id = Integer.parseInt(request.getParameter("music_id"));
		int phrase_id = Integer.parseInt(request.getParameter("phrase_id"));
		String title = request.getParameter("title");
		String remarks = request.getParameter("remarks");
		int phrase_order = Integer.parseInt(request.getParameter("phrase_order"));
		String updated_at = request.getParameter("updated_at");
		String created_at = request.getParameter("created_at");
		
		String name = request.getParameter("name");
		String path = request.getParameter("path");
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		
		// 保存を行う
		CheeseMusicPhraseDao bDao = new CheeseMusicPhraseDao();
		if (request.getParameter("submit").equals("更新")) {
			if (bDao.update(new CheeseMusicPhrase(id, music_id, phrase_id, title, remarks, phrase_order, updated_at, created_at))) { // 更新成功
				request.setAttribute("result", "保存しました。");
			} else { // 更新失敗
				request.setAttribute("result", "失敗");
			}
		} 
		
		CheesePhraseDao cDao = new CheesePhraseDao();
		if (request.getParameter("submit").equals("更新")) {
			if (cDao.update(new CheesePhrase(id, name, remarks, path, userId, updated_at, created_at))) { // 更新成功
				request.setAttribute("result", "保存しました。");
			} else { // 更新失敗
				request.setAttribute("result", "失敗");
			}
		} 
		
		// 結果ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_edit_music.jsp");
				dispatcher.forward(request, response);
				
	}
}
