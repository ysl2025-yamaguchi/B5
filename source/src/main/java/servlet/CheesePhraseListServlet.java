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

import dao.CheesePhraseDao;
import dao.CheesePhraseTagDao;
import dao.CheeseTagDao;
import dto.CheesePhrase;
import dto.CheeseTag;

/**
 * Servlet implementation class CheesePhraseListServlet
 */
@WebServlet("/CheesePhraseListServlet")
public class CheesePhraseListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("login_user") == null) {
//			response.sendRedirect(request.getContextPath() + "/CheeseLoginServlet");
//			return;
//		}
//		
//		List<CheeseTag> tagList;
//		CheeseTagDao tag = new CheeseTagDao();
		
		// 登録済みのフレーズ一覧を取得
		List<CheesePhrase> phraseList;
		CheesePhraseDao phraseDao =  new CheesePhraseDao();
		phraseList = phraseDao.select(new ArrayList<String>(), new ArrayList<String>(), "", 1);
		
		// 各フレーズに登録されているタグを取得
		Map<Integer, List<CheeseTag>> phraseTagMap = new HashMap<Integer, List<CheeseTag>>();
		CheesePhraseTagDao phraseTagDao = new CheesePhraseTagDao();
		CheeseTagDao TagDao = new CheeseTagDao();
		for (CheesePhrase phrase : phraseList) {
			List<Integer> TagIdList = phraseTagDao.selectPhraseTagInfo(phrase.getId());
			if (TagIdList.size() != 0) {
				phraseTagMap.put(phrase.getId(), TagDao.select(TagIdList));
			}
		}
		
		// ユーザーが登録したことのあるタグを取得
		List<CheeseTag> tagList = TagDao.selectALL(1);
		
        // リクエスト属性にセット
        request.setAttribute("phraseList", phraseList);
        request.setAttribute("phraseTagList", phraseTagMap);
        request.setAttribute("tagList", tagList);
//        request.setAttribute("total", cardList.size());

        // 検索ページにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_home.jsp");
        dispatcher.forward(request, response);
	}
// 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		
		// 検索ワードよりフレーズ名，タグ名で検索
		String searchStrLine = request.getParameter("search_str_line");
		List<String> searchWordList = new ArrayList<String>();
		List<String> searchTagList = new ArrayList<String>();
		if (searchStrLine != null && !searchStrLine.isEmpty()) {
			for (String searchStr : searchStrLine.split("[ |　]+")) {
				if (searchStr.charAt(0) == '#') {
					searchTagList.add(searchStr.substring(1));
				}
				else {
					searchWordList.add(searchStr);
				}
			}
		}
		String order = request.getParameter("order");
		if (order == null || order.isEmpty()) {
			order = "created_desc";
		} 
		List<CheesePhrase> phraseList;
		CheesePhraseDao phraseDao =  new CheesePhraseDao();
		phraseList = phraseDao.select(searchWordList, searchTagList, order, 1);
		
		// 各フレーズに登録されているタグを取得
		Map<Integer, List<CheeseTag>> phraseTagMap = new HashMap<Integer, List<CheeseTag>>();
		CheesePhraseTagDao phraseTagDao = new CheesePhraseTagDao();
		CheeseTagDao TagDao = new CheeseTagDao();
		for (CheesePhrase phrase : phraseList) {
			List<Integer> TagIdList = phraseTagDao.selectPhraseTagInfo(phrase.getId());
			if (TagIdList.size() != 0) {
				phraseTagMap.put(phrase.getId(), TagDao.select(TagIdList));
			}
		}
		
		// ユーザーが登録したことのあるタグを取得
		List<CheeseTag> tagList = TagDao.selectALL(1);

		
        // リクエスト属性にセット
        request.setAttribute("phraseList", phraseList);
        request.setAttribute("searchStrLine", searchStrLine);
        request.setAttribute("order", order);
        request.setAttribute("phraseTagList", phraseTagMap);
        request.setAttribute("tagList", tagList);
		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_home.jsp");
		dispatcher.forward(request, response);
	}

}
