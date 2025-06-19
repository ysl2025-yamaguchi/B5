package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CheesePhraseDao;
import dto.CheesePhrase;

/**
 * Servlet implementation class CheeseDeletePhraseServlet
 */
@WebServlet("/CheeseDeletePhraseServlet")
public class CheeseDeletePhraseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheeseDeletePhraseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		int id= Integer.parseInt(request.getParameter("id"));
		String name= request.getParameter("name");
		String remarks = request.getParameter("remarks");
		String path = request.getParameter("path");
		int userId= Integer.parseInt(request.getParameter("userId"));
		String updated_at = request.getParameter("updated_at");
		String created_at = request.getParameter("created_at");
		 
			//削除を行う
		if (request.getParameter("submit").equals("削除")) {
			CheesePhraseDao dao = new CheesePhraseDao();
			boolean success = dao.delete(new CheesePhrase(id,name,remarks,path,userId,updated_at,created_at));
			if (success) { // 削除成功
				request.getSession().setAttribute("result", "削除成功しました！");
			} else { // 削除失敗
				request.getSession().setAttribute("result", "削除失敗しました。");
			}
		}
			// 同じJSPにリダイレクトする
			response.sendRedirect("CheesePhraseListServlet"); 
			return;
		}
	}


