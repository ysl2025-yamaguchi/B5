package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CheeseMusicDao;
import dto.CheeseMusic;

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
//		response.sendRedirect("/b5/CheeseLoginServlet");
//		return;
//	}
		// 曲ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_music.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/B5/CheeseLoginServlet");
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
		String name = request.getParameter("name");
		String sort = request.getParameter("sort");

		// 検索処理を行う
		CheeseMusic condition = new CheeseMusic();
		condition.setName(name);
		
		CheeseMusicDao bDao = new CheeseMusicDao();
		List<CheeseMusic> cardList = bDao.select(condition, sort);

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("cardList", cardList);

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_music.jsp");
		dispatcher.forward(request, response);
	}
}
