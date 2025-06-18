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
import dao.CheesePhraseTagDao;
import dao.CheeseTagDao;
import dto.CheesePhrase;
import dto.CheesePhraseTag;
import dto.CheeseTag;

/**
 * Servlet implementation class CheesePhraseEditServlet
 */
@WebServlet("/CheesePhraseEditServlet")
public class CheesePhraseEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheesePhraseEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
//		HttpSession session = request.getSession(false);
//		Integer userId = (Integer) session.getAttribute("userId");
		
		request.setCharacterEncoding("UTF-8");
		List<CheesePhrase> phraseList;
		CheesePhraseDAO phraseDAO =  new CheesePhraseDAO();
		phraseList = phraseDAO.select(new ArrayList<String>(), new ArrayList<String>(), "", 1);
		
		List<CheeseTag> tagList;
		CheeseTagDao tagDao = new CheeseTagDao();
		tagList= tagDao.select(new CheeseTag(0,"", 0,"","")); 

		
	    request.setAttribute("tagList", tagList);
		request.setAttribute("phraseList", phraseList);
	   
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_edit_phrase.jsp");
        dispatcher.forward(request, response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		 HttpSession session = request.getSession();
//		    if (session.getAttribute("id") == null) {
//		        response.sendRedirect("/B5/CheeseLoginServlet");
//		        return;
//		    }

		 request.setCharacterEncoding("UTF-8");

		    // Get parameters from the form
		    int id = Integer.parseInt(request.getParameter("id"));
		    int phraseId = Integer.parseInt(request.getParameter("phraseId"));
		    int tagId = Integer.parseInt(request.getParameter("tagId"));
		  
		    // Prepare DAO and model
		    CheesePhraseTagDao dao = new CheesePhraseTagDao();
			if (request.getParameter("submit").equals("更新")) {
				if (dao.update(new CheesePhraseTag(id,phraseId,tagId,"",""))) { // 更新成功
					request.setAttribute("result", "更新成功！");
				} else { // 更新失敗
					request.setAttribute("result","更新失敗！");
				}
			} else {
				if (dao.delete(new CheesePhraseTag(id,phraseId,tagId,"",""))) {
						// 削除成功
					request.setAttribute("result", "削除成功！");
				} else { // 削除失敗					request.setAttribute("result", "削除失敗！");
				}
			}

////		    // Forward to result page
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		    dispatcher.forward(request, response);
	}
}


//	        int phraseId = Integer.parseInt(request.getParameter("phraseId"));
//	        String phraseName = request.getParameter("phraseName");
//	        String phraseRemarks = request.getParameter("phraseRemarks");
//
//	        String[] tagIds = request.getParameterValues("tagIds");       
//	        String[] tagNames = request.getParameterValues("tagNames");   
//	        String[] deleteTagIds = request.getParameterValues("deleteTagIds"); 
//	        String newTagNamesStr = request.getParameter("newTagNames");  
//
//	        CheesePhraseDAO phraseDao = new CheesePhraseDAO();
//	        CheeseTagDao tagDao = new CheeseTagDao();
//	        CheesePhraseTagDao phraseTagDao = new CheesePhraseTagDao();
//
//	        //フレーズ情報更新
//	        CheesePhrase phrase = new CheesePhrase();
//	        phrase.setId(phraseId);
//	        phrase.setName(phraseName);
//	        phrase.setRemarks(phraseRemarks);
//	        phraseDao.update(phrase);
//
//	     
//	       
//
//	       // 既存タグ名更新
//	        if (tagIds != null && tagNames != null) {
//	            for (int i = 0; i < tagIds.length; i++) {
//	                int tagId = Integer.parseInt(tagIds[i]);
//	                String tagName = tagNames[i];
//	                CheeseTag tag = new CheeseTag();
//	                tag.setId(tagId);
//	                tag.setName(tagName);
//	                tagDao.update(tag);
//	            }
//	        }

	        // 新規タグ追加とフレーズとの紐づけ
//	        if (newTagNamesStr != null && !newTagNamesStr.trim().isEmpty()) {
//	            String[] newTagNames = newTagNamesStr.split(",");
//	            for (String newTagName : newTagNames) {
//	                newTagName = newTagName.trim();
//	                if (!newTagName.isEmpty()) {
//	                    CheeseTag newTag = new CheeseTag();
//	                    newTag.setName(newTagName);
//	                    int newTagId = tagDao.insert(newTag);
//
//	                    if (newTagId > 0) {
//	                        CheesePhraseTag pt = new CheesePhraseTag();
//	                        pt.setPhraseId(phraseId);
//	                        pt.setTagId(newTagId);
//	                        phraseTagDao.insert(pt);
//	                    }
//	                }
//	            
//	        
//	            }
	      
//	        request.getSession().setAttribute("message", "フレーズとタグの更新が完了しました。");
//	        response.sendRedirect("CheesePhraseEditServlet?phraseId=" + phraseId);
	
	


