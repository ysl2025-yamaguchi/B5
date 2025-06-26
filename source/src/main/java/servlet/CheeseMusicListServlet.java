package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CheeseMusicDao;
import dao.CheeseMusicPhraseDao;
import dao.CheesePhraseDao;
import dto.CheeseMusic;
import dto.CheeseMusicPhrase;
import dto.CheesePhrase;
import dto.CheeseUser;

/**
 * Servlet implementation class CheeseMusicListServlet
 */
@WebServlet("/CheeseMusicListServlet")
public class CheeseMusicListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		CheeseUser user = (CheeseUser)session.getAttribute("loginUser");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/CheeseLoginServlet");
			return;
		}
		int userId = user.getId();
		
		List<String> searchWordList = new ArrayList<String>();
		CheeseMusicDao musicDao = new CheeseMusicDao();
		List<CheeseMusic> cardList = musicDao.select(searchWordList, "", userId);
		
		// DAO初期化
	    CheeseMusicPhraseDao musicPhraseDao = new CheeseMusicPhraseDao();
	    CheesePhraseDao phraseDao = new CheesePhraseDao();
	    
	 // 曲ID → フレーズ一覧のMap
	    Map<Integer, List<CheesePhrase>> musicPhraseMap = new HashMap<>();

	    for (CheeseMusic music : cardList) {
	        List<CheeseMusicPhrase> musicPhraseList = musicPhraseDao.select(music.getId());
	        List<CheesePhrase> phraseList = new ArrayList<>();

	        for (CheeseMusicPhrase mp : musicPhraseList) {
	            CheesePhrase phrase = phraseDao.findById(mp.getPhraseId());
	            if (phrase != null) {
	                phraseList.add(phrase);
	            }
	        }
	        musicPhraseMap.put(music.getId(), phraseList);
	    }

	    request.setAttribute("cardList", cardList);
	    request.setAttribute("musicPhraseMap", musicPhraseMap);

	    RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/jsp/cheese_music.jsp");
	    dispatcher.forward(request, response);
	}
	    
//		// 検索結果をリクエストスコープに格納する
//		request.setAttribute("cardList", cardList);
//		List<CheesePhrase> phraseList;
//		CheesePhraseDao phraseDao =  new CheesePhraseDao();
//		phraseList = phraseDao.select(new ArrayList<String>(), new ArrayList<String>(), "", 1);
//		request.setAttribute("phraseList", phraseList);
//		
//		// 曲ページにフォワードする
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_music.jsp");
//		dispatcher.forward(request, response);
		
//	request.setAttribute("musicMap", musicMap);
//	List<CheesePhrase> phraseList;
//	CheesePhraseDao phraseDao =  new CheesePhraseDao();
//	phraseList = phraseDao.select(new ArrayList<String>(), new ArrayList<String>(), "", 1);
//	request.setAttribute("phraseList", phraseList);
//	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		CheeseUser user = (CheeseUser)session.getAttribute("loginUser");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/CheeseLoginServlet");
			return;
		}
		int userId = user.getId();
		
		String result = (String) session.getAttribute("result");
		if (result != null) {
			request.setAttribute("result", result);
			session.removeAttribute("result");  // 1回だけ表示
		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String searchStrLine = request.getParameter("search_str_line");
		
		List<String> searchWordList = new ArrayList<String>();
		if (searchStrLine != null && !searchStrLine.isEmpty()) {
			for (String searchStr : searchStrLine.split("[ |　]+")) {
				searchWordList.add(searchStr);
			}
		}
		
		String sort = request.getParameter("sort");
		    if (sort == null || sort.isEmpty()) {
		        sort = "created_desc";
		    }

		// 検索処理を行う
		
		CheeseMusicDao musicDao = new CheeseMusicDao();
		List<CheeseMusic> cardList = musicDao.select(searchWordList, sort, userId);
		
		 // DAO初期化
	    CheeseMusicPhraseDao musicPhraseDao = new CheeseMusicPhraseDao();
	    CheesePhraseDao phraseDao = new CheesePhraseDao();
	    
	    // 曲ID → フレーズ一覧のMap
	    Map<Integer, List<CheesePhrase>> musicPhraseMap = new HashMap<>();
	    
	    for (CheeseMusic music : cardList) {
	        List<CheeseMusicPhrase> musicPhraseList = musicPhraseDao.select(music.getId());
	        List<CheesePhrase> phraseList = new ArrayList<>();

	        for (CheeseMusicPhrase mp : musicPhraseList) {
	            CheesePhrase phrase = phraseDao.findById(mp.getPhraseId());  // 追加実装（下にコードあり）
	            if (phrase != null) {
	                phraseList.add(phrase);
	            }
	        }
	        musicPhraseMap.put(music.getId(), phraseList);
	    }
	    

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("cardList", cardList);
		request.setAttribute("musicPhraseMap", musicPhraseMap);
		request.setAttribute("searchStrLine", searchStrLine);
		request.setAttribute("sort", sort);

		
		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/jsp/cheese_music.jsp");
		dispatcher.forward(request, response);
	}
}
