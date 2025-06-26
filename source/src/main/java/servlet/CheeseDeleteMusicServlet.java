package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CheeseMusicDao;
import dao.CheeseMusicPhraseDao;

/**
 * Servlet implementation class CheeseDeleteMusicServlet
 */
@WebServlet("/CheeseDeleteMusicServlet")
public class CheeseDeleteMusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") == null) {
			response.sendRedirect(request.getContextPath() + "/CheeseLoginServlet");
			return;
		}
		
		// IDを取得
	    int id = Integer.parseInt(request.getParameter("id"));
		
		//削除を行う
		CheeseMusicDao musicDao = new CheeseMusicDao();
		boolean success = musicDao.deleteById(id);
		
		CheeseMusicPhraseDao musicPhraseDao = new CheeseMusicPhraseDao();
		boolean success2 = musicPhraseDao.deleteById(id);
		if (success && success2) { // 削除成功
			request.getSession().setAttribute("result", "削除成功しました！");
		} else { // 削除失敗
			request.getSession().setAttribute("result", "削除失敗しました。");
		}
		
		// 同じJSPにリダイレクトする
		response.sendRedirect(request.getContextPath() + "/CheeseMusicListServlet"); 
		return;
	}

}
