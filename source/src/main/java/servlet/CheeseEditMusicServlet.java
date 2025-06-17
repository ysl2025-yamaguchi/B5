package servlet;

import java.io.IOException;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CheeseMusicPhraseDao;
import dto.CheeseMusicPhrase;


@WebServlet("/CheeseEditMusicServlet")
public class CheeseEditMusicServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/B5/CheeseLoginServlet");
			return;
		}
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		int music_id = Integer.parseInt(request.getParameter("music_id"));
		int phrase_id = Integer.parseInt(request.getParameter("phrase_id"));
		String title = request.getParameter("title");
		String remarks = request.getParameter("remarks");
		int phrase_order = Integer.parseInt(request.getParameter("phrase_order"));
		String updated_at = request.getParameter("updated_at");
		String created_at = request.getParameter("created_at");
		
		// 更新または削除を行う
		CheeseMusicPhraseDao bDao = new CheeseMusicPhraseDao();
		if (request.getParameter("submit").equals("更新")) {
			if (bDao.update(new CheeseMusicPhrase(id, music_id, phrase_id, title, remarks, phrase_order, updated_at, created_at))) { // 更新成功
				request.setAttribute("result", new Result("更新成功！", "レコードを更新しました。", "/B5/CheeseEditMusicServlet"));
			} else { // 更新失敗
				request.setAttribute("result", new Result("更新失敗！", "レコードを更新できませんでした。", "/webappAns/MenuServlet"));
			}
		} else {
			if (bDao.delete(new Bc(number, company,  department, position, name, zipcode, address, 
					phone, fax, email, remarks))) { // 削除成功
				request.setAttribute("result", new Result("削除成功！", "レコードを削除しました。", "/webappAns/MenuServlet"));
			} else { // 削除失敗
				request.setAttribute("result", new Result("削除失敗！", "レコードを削除できませんでした。", "/webappAns/MenuServlet"));
			}
		}
	}
}
