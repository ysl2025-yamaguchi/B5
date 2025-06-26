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
		
		String result = (String) session.getAttribute("result");
		if (result != null) {
			request.setAttribute("result", result);
			session.removeAttribute("result");  // 1回だけ表示
		}
		
        //get parameters
		int phraseId = Integer.parseInt(request.getParameter("id"));
		CheesePhraseDao pDao = new CheesePhraseDao();
		CheesePhrase phrase = pDao.findById(phraseId);
		
		// セッションスコープのuserIdとphraseのuserIdの比較
		if (phrase.getUserId() != userId) {
			// 違うならログイン画面へ
			response.sendRedirect(request.getContextPath() + "/CheeseLoginServlet");
			return;
		}
		
        CheesePhraseTagDao ptDao = new CheesePhraseTagDao();
		CheeseTagDao tDao = new CheeseTagDao();
		
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
//			
	    request.setCharacterEncoding("UTF-8");
	
        //get parameters
	    boolean result = false;
		int phraseId = Integer.parseInt(request.getParameter("id"));
		String phraseName = request.getParameter("phraseName");
	    String phraseRemarks = request.getParameter("phraseRemarks");
		
		String[] tagIdArray = request.getParameterValues("registed_tag_id");
		System.out.println(tagIdArray.length);
		String[] tagNameArray = request.getParameterValues("registed_tag_name");
		System.out.println(tagNameArray.length);
		
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
				
				result = ptDao.deleteUnassignedTags();
			}
		}
		
		if (result) {
			request.getSession().setAttribute("result", "保存しました。");
		}
		else {
			request.getSession().setAttribute("result", "保存に失敗しました。");
		}
		
		// 同じJSPにリダイレクトする
		response.sendRedirect(request.getContextPath() + "/CheesePhraseEditServlet?id=" + phraseId); 
		return;
		
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
	          
            
           
            
 

