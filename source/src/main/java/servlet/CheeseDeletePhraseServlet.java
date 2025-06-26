package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CheeseMusicPhraseDao;
import dao.CheesePhraseDao;
import dao.CheesePhraseTagDao;

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
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") == null) {
			response.sendRedirect(request.getContextPath() + "/CheeseLoginServlet");
			return;
		}
		

		
		int phraseId= Integer.parseInt(request.getParameter("id"));
		
		CheeseMusicPhraseDao musicPhraseDao = new CheeseMusicPhraseDao();
		String fileName = request.getParameter("path");
		boolean fileDeleteFlag = musicPhraseDao.check(phraseId);
		
		// ファイルの削除
		if (fileDeleteFlag) {
			if (fileName != null && !fileName.isEmpty()) {
				String dirPath = getServletContext().getRealPath("/upload");
				String fullPath = dirPath + File.separator + fileName;
				File file = new File(fullPath);
				
				if (!file.delete()) {
					fileDeleteFlag = false;
				}
			}
		}
		
		// データベースの削除
		if (fileDeleteFlag) {
			CheesePhraseDao phraseDao = new CheesePhraseDao();
			CheesePhraseTagDao phraseTagDao = new CheesePhraseTagDao();
			if (phraseDao.delete(phraseId) && phraseTagDao.delete(phraseId) && phraseTagDao.deleteUnassignedTags()) { // 削除成功
				request.getSession().setAttribute("result", "削除成功しました！");
			} else { // 削除失敗
				request.getSession().setAttribute("result", "削除失敗しました。");
			}
		}
		else {
			request.getSession().setAttribute("result", "削除失敗しました。");
		}
		
		// 同じJSPにフォワードする
		response.sendRedirect(request.getContextPath() + "/CheesePhraseListServlet"); // JSPを表示するサーブレットなど
	    return;
	}
}


