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

@WebServlet("/CheeseRegistUserServlet")
public class CheeseRegistUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/B5/CheeseLoginServlet");
			return;
		}
		
		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_login.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/B5/CheeseLoginUserServlet");
			return;
		}
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("register_name");
		String pw = request.getParameter("register_password");
		
		// 登録処理を行う
		CheeseUserDao bDao = new CheeseUserDao();
		CheeseUser user = new CheeseUser();
		user.setName(username);
		user.setPassword(pw);
		if (bDao.isRegistOK(user)){ // 登録成功
			request.setAttribute("result", "登録成功！");
		} else { // 登録失敗
			request.setAttribute("result", "登録失敗！");

			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheeseLogin.jsp");
			dispatcher.forward(request, response);
		}
	}

}
