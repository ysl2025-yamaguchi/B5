package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CheesePhraseDao;
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
		request.setCharacterEncoding("UTF-8");
//		HttpSession session = request.getSession(false);
//		Integer userId = (Integer) session.getAttribute("userId");
//	    List<CheesePhrase> phraseList;
//		CheesePhraseDao phraseDao =  new CheesePhraseDao();
//		phraseList = phraseDao.select(new ArrayList<String>(), new ArrayList<String>(), "", 1); 
//		
		List<CheeseTag> tagList;
		CheeseTagDao tagDao = new CheeseTagDao();
		tagList= tagDao.select(new CheeseTag(0,"", 0,"","")); 
		request.setAttribute("tagList", tagList);
//		int phraseId = Integer.parseInt(request.getParameter("phraseId"));
		int phraseId = 1;
		CheesePhraseTagDao list = new CheesePhraseTagDao();
		List<CheesePhraseTag> phraseTagList=list.selectPhraseTagInfo(phraseId);
	    request.setAttribute("phraseTagList", phraseTagList);
		
	   
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
			int id= Integer.parseInt(request.getParameter("id"));
			int phraseId= Integer.parseInt(request.getParameter("phraseId"));
			int tagId= Integer.parseInt(request.getParameter("tagId"));
			int userId= Integer.parseInt(request.getParameter("userId"));
			String phraseName = request.getParameter("phraseName");
			String phraseRemarks = request.getParameter("phraseRemarks");
			String phrasePath = request.getParameter("phrasePath");
			String tagName = request.getParameter("tagName");
            String updated_at = request.getParameter("updated_at");
			String created_at = request.getParameter("created_at");
		    // Prepare DAO and model
		    CheesePhraseTagDao dao = new CheesePhraseTagDao();
		    CheesePhraseDao pDao=new CheesePhraseDao();
		    CheeseTagDao tDao=new CheeseTagDao();
			if (request.getParameter("submit").equals("更新")) {
				dao.update(new CheesePhraseTag(id,phraseId,tagId,created_at,updated_at));
				pDao.update(new CheesePhrase(phraseId,phraseName,phraseRemarks,phrasePath,userId,created_at, updated_at));
			}
			// For tag
			if (request.getParameter("submit").equals("追加")) {
                tDao.insert(new CheeseTag(tagId,tagName,userId,created_at,updated_at));
			}
		    // Forward to result page
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_edit_phrase.jsp");
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
	
	


