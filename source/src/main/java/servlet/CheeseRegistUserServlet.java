package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CheeseUserDao;
import dto.CheeseUser;

@WebServlet("/CheeseRegistUserServlet")
public class CheeseRegistUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	
		// リクエストパラメータを取得する
	    request.setCharacterEncoding("UTF-8");
	    String username = request.getParameter("regUser");
	    String pw = request.getParameter("regPass");
	
	    // 登録処理を行う
	    CheeseUserDao bDao = new CheeseUserDao();
	    CheeseUser user = new CheeseUser();
	    user.setName(username);
	    user.setPassword(pw);
	
	    if (bDao.isRegistOK(user)) {
	        // ✅ セッションスコープに成功メッセージを入れて…
	        request.getSession().setAttribute("register_success", "登録が完了しました！");
	        
	        // ✅ リダイレクトでログイン画面に戻す
	        response.sendRedirect(request.getContextPath() + "/CheeseLoginServlet");
	    } else {
	        // 失敗時はフォワードでメッセージ表示
	        request.setAttribute("result", "登録失敗！");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_login.jsp");
	        dispatcher.forward(request, response);
	    }
	}
}