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

import dao.CheesePhraseDAO;
import dto.CheesePhrase;

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
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect(request.getContextPath() + "/CheeseLoginServlet");
//			return;
//		}
		
		List<CheesePhrase> PhraseList;
		CheesePhraseDAO phraseDAO =  new CheesePhraseDAO();
		PhraseList = phraseDAO.select(new ArrayList<String>(), new ArrayList<String>(), "", 1);
		
        // リクエスト属性にセット
        request.setAttribute("phraseList", PhraseList);
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
		
		List<CheesePhrase> PhraseList;
		CheesePhraseDAO phraseDAO =  new CheesePhraseDAO();
		PhraseList = phraseDAO.select(searchWordList, searchTagList, order, 1);
		
        // リクエスト属性にセット
        request.setAttribute("phraseList", PhraseList);
        request.setAttribute("searchStrLine", searchStrLine);
        request.setAttribute("order", order);
		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_home.jsp");
		dispatcher.forward(request, response);
	}

}
