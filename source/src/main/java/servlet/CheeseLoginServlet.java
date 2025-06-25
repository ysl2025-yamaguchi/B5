package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CheeseUserDao;
import dto.CheeseUser;

@WebServlet("/CheeseLoginServlet")
public class CheeseLoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String logoutParam = request.getParameter("logout");
		if ("true".equals(logoutParam)) {
		    request.setAttribute("result", "ログアウトしました！");
		}
		
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_login.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("loginUser");
		String pw = request.getParameter("loginPass");
		
		System.out.println(username + pw);
		
		// ログイン処理を行う
		CheeseUserDao iDao = new CheeseUserDao();
		CheeseUser user = new CheeseUser();
		user.setName(username);
		user.setPassword(pw);
		if (iDao.isLoginOK(user)) { // ログイン成功
			user = iDao.getUserInfo(user);
			// セッションスコープにIDを格納する
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);

			// メニューサーブレットにリダイレクトする
			response.sendRedirect(request.getContextPath() + "/CheesePhraseListServlet");
		} else { // ログイン失敗
			// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
			request.setAttribute("result", "ログインに失敗しました。");
			
			// ログインページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
