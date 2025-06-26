package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CheeseMusicDao;
import dto.CheeseMusic;
import dto.CheeseUser;

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
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		CheeseUser user = (CheeseUser)session.getAttribute("loginUser");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/CheeseLoginServlet");
			return;
		}
		int userId = user.getId();

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String createdAt = request.getParameter("created_at");
		String updatedAt = request.getParameter("updated_at");

		// 登録処理を行う
		CheeseMusicDao dao = new CheeseMusicDao();
		boolean success = dao.insert(new CheeseMusic(0, name, userId, createdAt, updatedAt));
		
		if (success) { // 登録成功
			request.getSession().setAttribute("result", "登録成功しました！");
		} else { // 登録失敗
			request.getSession().setAttribute("result", "登録に失敗しました。");
		}
		
		// 同じJSPにフォワードする
		response.sendRedirect("CheeseMusicListServlet"); // JSPを表示するサーブレットなど
	    return;
	}
	
}
