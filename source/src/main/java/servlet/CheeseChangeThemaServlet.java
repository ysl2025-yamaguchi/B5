package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CheeseUserDao;
import dto.CheeseUser;

@WebServlet("/CheeseChangeThemaServlet")
public class CheeseChangeThemaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") == null) {
			response.sendRedirect(request.getContextPath() + "/CheeseLoginServlet");
			return;
		}

        // パラメータ取得
        String themaStr = request.getParameter("thema");
        int themaId = 1;
        try {
            themaId = Integer.parseInt(themaStr);
        } catch (NumberFormatException e) {
            themaId = 1; // デフォルト
        }

        // ログインユーザー取得
        CheeseUser loginUser = (CheeseUser) session.getAttribute("loginUser");
        
        if (loginUser != null) {
            // データベースを更新
        	CheeseUserDao cheeseUserDao = new CheeseUserDao();
            boolean updated = cheeseUserDao.updateThema(loginUser.getId(), themaId);

            if (updated) {
                // セッション内のユーザー情報も更新
                loginUser.setThema(themaId);
                session.setAttribute("loginUser", loginUser);
            }
        }

        // 元のページにリダイレクト（または固定先へ）
        String referer = request.getHeader("referer");
        response.sendRedirect(referer);
//        response.sendRedirect("CheeseMainServlet"); // メイン画面などへ
    }
}

