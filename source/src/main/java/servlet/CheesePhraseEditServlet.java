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

import dao.CheesePhraseDao;
import dao.CheesePhraseTagDao;
import dao.CheeseTagDao;
import dto.CheesePhrase;
import dto.CheeseTag;
import dto.CheeseUser;

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
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		CheeseUser user = (CheeseUser)session.getAttribute("loginUser");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/CheeseLoginServlet");
			return;
		}
		int userId = user.getId();
		
		String deleteResult = request.getParameter("editResult");
		System.out.println(deleteResult);
		if (deleteResult != null && !deleteResult.isEmpty()) {
			if (deleteResult.equals("successed")) {
				request.setAttribute("result", "編集に成功しました");
			}
			else {
				request.setAttribute("result", "編集に失敗しました");
			}
		}
		
        //get parameters
		int phraseId = Integer.parseInt(request.getParameter("id"));
		
        CheesePhraseTagDao ptDao = new CheesePhraseTagDao();
		CheesePhraseDao pDao = new CheesePhraseDao();
		CheeseTagDao tDao = new CheeseTagDao();
		
		CheesePhrase phrase = pDao.findById(phraseId);
		List<Integer> assignedTagIdList = ptDao.selectPhraseTagInfo(phraseId);
		List<CheeseTag> assignedTagList = tDao.select(assignedTagIdList);
		
		
		
		//fetch all phrases
//		List<CheesePhrase> phraseList;
//		CheesePhraseDao phraseDao =  new CheesePhraseDao();
//		phraseList = phraseDao.select(new ArrayList<String>(), new ArrayList<String>(), "", userId);
//		
//		
//		Map<Integer, List<CheeseTag>> phraseTagMap = new HashMap<Integer, List<CheeseTag>>();
//		CheesePhraseTagDao phraseTagDao = new CheesePhraseTagDao();
//		CheeseTagDao TagDao = new CheeseTagDao();
//		for (CheesePhrase p : phraseList) {
//			List<Integer> TagIdList = phraseTagDao.selectPhraseTagInfo(p.getId());
//			phraseTagMap.put(p.getId(), TagDao.select(TagIdList));
//		}
		
		
		List<CheeseTag> tagList = tDao.selectALL(userId);
		
       //set data
//        request.setAttribute("phraseList", phraseList);
//        request.setAttribute("phraseTagList", phraseTagMap);
//        request.setAttribute("tagList", tagList);
		
		request.setAttribute("phrase", phrase);
		request.setAttribute("tagList", tagList);
		request.setAttribute("assignedTagList", assignedTagList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_edit_phrase.jsp");
        dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		CheeseUser user = (CheeseUser)session.getAttribute("loginUser");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/CheeseLoginServlet");
			return;
		}
		int userId = user.getId();
		System.out.println(userId);
//			
	    request.setCharacterEncoding("UTF-8");
	
        //get parameters
	    boolean result = false;
		int phraseId = Integer.parseInt(request.getParameter("id"));
		String phraseName = request.getParameter("phraseName");
	    String phraseRemarks = request.getParameter("phraseRemarks");
		
		String[] tagIdArray = request.getParameterValues("registed_tag_id");
		String[] tagNameArray = request.getParameterValues("registed_tag_name");
		
        CheesePhraseTagDao ptDao = new CheesePhraseTagDao();
		CheesePhraseDao pDao = new CheesePhraseDao();
		CheeseTagDao tDao = new CheeseTagDao();
		
		CheesePhrase phrase = new CheesePhrase(phraseId, phraseName, phraseRemarks, "", userId, "", "");

		if (pDao.update(phrase)) {
			if (ptDao.delete(phraseId)) {
				int tagId;
				String tagName;
				if (tagIdArray != null && tagNameArray != null) {
					for (int i = 0; i < tagIdArray.length; i++) {
						tagId = Integer.parseInt(tagIdArray[i]);
						System.out.println(tagId);
						CheeseTag tag = new CheeseTag();
						
						if (tagId == 0) {
							tagName = tagNameArray[i];
							System.out.println(tagName);
							tag.setName(tagName);
							tag.setUserId(userId);
							
							tagId = tDao.insert(tag);
						}
						
						// フレーズとタグの中間テーブルに追加
						result = ptDao.insert(phraseId, tagId);
					}
				}
				result = true;
			}
		}
		
		if (result) {
			response.sendRedirect("CheesePhraseEditServlet?id=" + phraseId + "&editResult=successed");
		}
		else {
			response.sendRedirect("CheesePhraseEditServlet?id=" + phraseId + "&editResult=failed");
		}
		
//		CheesePhrase phrase = pDao.findById(phraseId);
		
//	    List<CheesePhrase> phraseList;
//		CheesePhraseDao phraseDao =  new CheesePhraseDao();
//		phraseList = phraseDao.select(new ArrayList<String>(), new ArrayList<String>(), "", userId);
//		
//		
//		Map<Integer, List<CheeseTag>> phraseTagMap = new HashMap<Integer, List<CheeseTag>>();
//		CheesePhraseTagDao phraseTagDao = new CheesePhraseTagDao();
//		CheeseTagDao TagDao = new CheeseTagDao();
//		for (CheesePhrase p : phraseList) {
//			List<Integer> TagIdList = phraseTagDao.selectPhraseTagInfo(p.getId());
//			phraseTagMap.put(p.getId(), TagDao.select(TagIdList));
//		}
//		
//		
//		List<CheeseTag> tagList = TagDao.selectALL(0);
//		
//		//set data
//		request.setAttribute("phrase", phrase);
//        request.setAttribute("phraseList", phraseList);
//        request.setAttribute("phraseTagList", phraseTagMap);
//        request.setAttribute("tagList", tagList);
//		 
//		 
//	      StringBuilder result = new StringBuilder();
//		 //when click update button
//          if ("登録".equals(request.getParameter("regist"))) {
//                 //validate
//	    	    if ((phraseName == null || phraseName.trim().isEmpty()) &&
//	    	        (phraseRemarks == null || phraseRemarks.trim().isEmpty())) {
//
//	    	        result.append("フレーズ名とメモの両方を入力してください");
//
//	    	    } else if (phraseName == null || phraseName.trim().isEmpty()) {
//	    	        result.append("フレーズ名を入力してください");
//
//	    	    } else if (phraseRemarks == null || phraseRemarks.trim().isEmpty()) {
//	    	        result.append("フレーズメモを入力してください");
//
//	    	    } else {
//	    	    	//update phrase
//	    	        boolean phraseUpdated = pDao.update(
//	    	            new CheesePhrase(phraseId, phraseName, phraseRemarks, phrasePath, userId, "", "")
//	    	        );
//
//	    	        if (phraseUpdated) {
//	    	            result.append("フレーズ更新成功！");
//                    //add tags
//	    	            String[] addedTags = request.getParameterValues("addedTags");
//	    	            String[] tagModes = request.getParameterValues("tagMode");
//
//	    	            if (addedTags != null && tagModes != null && addedTags.length == tagModes.length) {
//	    	                ptDao.delete(phraseId); //delete tag
//	    	                for (int i = 0; i < addedTags.length; i++) {
//	    	                    String tagName = addedTags[i];
//	    	                    String mode = tagModes[i];
//	    	                    CheeseTag tag = null;
//
//	    	                    if ("registed".equals(mode)) {
//	    	                        tag = tDao.findByName(tagName);
//
//	    	                    } else if ("new".equals(mode)) {
//	    	                        CheeseTag existing = tDao.findByName(tagName);
//	    	                        if (existing != null) {
//	    	                            tag = existing;
//	    	                        } else {
//	    	                            tag = tDao.insertAndReturn(tagName, userId);
//	    	                        }
//	    	                    }
//
//	    	                    if (tag != null) {
//	    	                        ptDao.insert(phraseId, tag.getId());
//	    	                    }
//	    	                }
//	    	            }
//
//	    	        } else {
//	    	            result.append("フレーズ更新失敗！");
//	    	        }
//	    	    }
//	    	}
//
//		request.setAttribute("result", result.toString());
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_edit_phrase.jsp");
//		dispatcher.forward(request, response);
			
	}
}
	          
            
           
            
 

