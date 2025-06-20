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

import dao.CheeseMusicDao;
import dao.CheesePhraseDao;
import dto.CheeseMusic;
import dto.CheesePhrase;

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
//		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//		response.sendRedirect("/CheeseLoginServlet");
//		return;
//	}
		
		List<String> searchWordList = new ArrayList<String>();
		CheeseMusicDao bDao = new CheeseMusicDao();
		List<CheeseMusic> cardList = bDao.select(searchWordList, "", 1);
		
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("cardList", cardList);
		List<CheesePhrase> phraseList;
		CheesePhraseDao phraseDao =  new CheesePhraseDao();
		phraseList = phraseDao.select(new ArrayList<String>(), new ArrayList<String>(), "", 1);
		request.setAttribute("phraseList", phraseList);
		
		// 曲ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_music.jsp");
		dispatcher.forward(request, response);
		
	}
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
//		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/CheeseLoginServlet");
//			return;
//		}
		
		HttpSession session = request.getSession();
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

		// 検索処理を行う
		
		CheeseMusicDao bDao = new CheeseMusicDao();
		List<CheeseMusic> cardList = bDao.select(searchWordList, sort, 1);

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("cardList", cardList);
		request.setAttribute("searchStrLine", searchStrLine);
		request.setAttribute("sort", sort);

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_music.jsp");
		dispatcher.forward(request, response);
	}
}
