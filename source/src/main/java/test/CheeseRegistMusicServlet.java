package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CheeseMusicDao;
import dto.CheeseMusic;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/CheeseRegistMusicServlet")
public class CheeseRegistMusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
//		HttpSession session = request.getSession();
//		int userId = (int) session.getAttribute("id"); /*Integer.parseInt(request.getParameter("user_id"));*/
		int userId = 1;
		String createdAt = request.getParameter("created_at");
		String updatedAt = request.getParameter("updated_at");

		// 登録処理を行う
		CheeseMusicDao dao = new CheeseMusicDao();
		boolean success = dao.insert(new CheeseMusic(0, name, userId, createdAt, updatedAt));
		
		if (success) { // 登録成功
			request.setAttribute("result", "登録成功しました！");
		} else { // 登録失敗
			request.setAttribute("result", "登録に失敗しました。");
		}

		// 同じJSPにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_music.jsp");
		dispatcher.forward(request, response);
	}
	
}
