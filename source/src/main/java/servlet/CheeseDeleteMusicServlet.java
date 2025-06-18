package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CheeseMusicDao;

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
//		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//		response.sendRedirect("/b5/CheeseLoginServlet");
//		return;
//	}
		
		// IDを取得
	    int id = Integer.parseInt(request.getParameter("id"));
		
		//削除を行う
		CheeseMusicDao dao = new CheeseMusicDao();
		boolean success = dao.deleteById(id);
		if (success) { // 削除成功
			request.getSession().setAttribute("result", "削除成功しました！");
		} else { // 削除失敗
			request.getSession().setAttribute("result", "削除失敗しました。");
		}
		
		// 同じJSPにリダイレクトする
		response.sendRedirect("CheeseMusicListServlet"); 
		return;
	}

}
