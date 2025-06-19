package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CheesePhraseTagDao;
import dao.CheeseTagDao;
import dto.CheeseTag;

/**
 * Servlet implementation class CheesePhraseEditServlet
 */
@WebServlet("/CheesePhraseEditServlet")
public class CheesePhraseEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		
		List<CheeseTag> tagList;
		CheeseTagDao tagDao = new CheeseTagDao();
		tagList= tagDao.selectALL(1); 
		request.setAttribute("tagList", tagList);
//		int phraseId = Integer.parseInt(request.getParameter("phraseId"));
	
		CheesePhraseTagDao list = new CheesePhraseTagDao();
		List<Integer> phraseTagIdList = list.selectPhraseTagInfo(1);
		List<CheeseTag> phraseTagList = tagDao.select(phraseTagIdList);
	    request.setAttribute("phraseTagList", phraseTagList);
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_edit_phrase.jsp");
        dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession();
//		    
//if (session.getAttribute("id") == null) {
//		        response.sendRedirect("/B5/CheeseLoginServlet");
//		        return;
//		    }
//		 request.setCharacterEncoding("UTF-8");
//
//		    // Get parameters from the form
//			int id= Integer.parseInt(request.getParameter("id"));
//			int phraseId= Integer.parseInt(request.getParameter("phraseId"));
//			int tagId= Integer.parseInt(request.getParameter("tagId"));
//			int userId= Integer.parseInt(request.getParameter("userId"));
//			String phraseName = request.getParameter("phraseName");
//			String phraseRemarks = request.getParameter("phraseRemarks");
//			String phrasePath = request.getParameter("phrasePath");
//			String tagName = request.getParameter("tagName");
//            String updated_at = request.getParameter("updated_at");
//			String created_at = request.getParameter("created_at");
//		    // Prepare DAO and model
//		    CheesePhraseTagDao dao = new CheesePhraseTagDao();
//		    CheesePhraseDao pDao=new CheesePhraseDao();
//		    CheeseTagDao tDao=new CheeseTagDao();
//			if (request.getParameter("submit").equals("更新")) {
//				dao.update(new CheesePhraseTag(id,phraseId,tagId,created_at,updated_at));
//				pDao.update(new CheesePhrase(phraseId,phraseName,phraseRemarks,phrasePath,userId,created_at, updated_at));
//			}
//			// For tag
//			if (request.getParameter("submit").equals("追加")) {
//                tDao.insert(new CheeseTag(tagId,tagName,userId,created_at,updated_at));
//			}
//		    // Forward to result page
//		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_edit_phrase.jsp");
//		    dispatcher.forward(request, response);
	}

}
