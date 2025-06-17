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
		PhraseList = phraseDAO.select(new ArrayList<String>(), new ArrayList<String>(), 1);
		
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
