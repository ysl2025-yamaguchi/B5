package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		List<CheesePhrase> phraseList;
		CheesePhraseDao phraseDao =  new CheesePhraseDao();
		phraseList = phraseDao.select(new ArrayList<String>(), new ArrayList<String>(), "", 1);
		
		// 各フレーズに登録されているタグを取得
		Map<Integer, List<CheeseTag>> phraseTagMap = new HashMap<Integer, List<CheeseTag>>();
		CheesePhraseTagDao phraseTagDao = new CheesePhraseTagDao();
		CheeseTagDao TagDao = new CheeseTagDao();
		for (CheesePhrase p : phraseList) {
			List<Integer> TagIdList = phraseTagDao.selectPhraseTagInfo(p.getId());
			phraseTagMap.put(p.getId(), TagDao.select(TagIdList));
		}
		
		// ユーザーが登録したことのあるタグを取得
		List<CheeseTag> tagList = TagDao.selectALL(1);
		
        // リクエスト属性にセット
        request.setAttribute("phraseList", phraseList);
        request.setAttribute("phraseTagList", phraseTagMap);
        request.setAttribute("tagList", tagList);

		
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_edit_phrase.jsp");
        dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		 HttpSession session = request.getSession();
//		    
//       if (session.getAttribute("id") == null) {
//		        response.sendRedirect("/B5/CheeseLoginServlet");
//		        return;
//		    }
		 request.setCharacterEncoding("UTF-8");
		
	        
		    int userId= 1;
			int phraseId= Integer.parseInt(request.getParameter("id"));
			String phraseName=request.getParameter("phraseName");
		    String phraseRemarks=request.getParameter("phraseRemarks");
			String phrasePath=request.getParameter("phrasePath");
			
	
			
             
			CheesePhraseTagDao ptDao=new CheesePhraseTagDao();
			CheesePhraseDao pDao=new CheesePhraseDao();
			CheeseTagDao tDao=new CheeseTagDao();
			
			 CheesePhrase phrase = pDao.findById(phraseId);
			 request.setAttribute("phrase", phrase);
			 
			 
			
			 List<CheesePhrase> phraseList;
				CheesePhraseDao phraseDao =  new CheesePhraseDao();
				phraseList = phraseDao.select(new ArrayList<String>(), new ArrayList<String>(), "", 1);
				
				// 各フレーズに登録されているタグを取得
				Map<Integer, List<CheeseTag>> phraseTagMap = new HashMap<Integer, List<CheeseTag>>();
				CheesePhraseTagDao phraseTagDao = new CheesePhraseTagDao();
				CheeseTagDao TagDao = new CheeseTagDao();
				for (CheesePhrase p : phraseList) {
					List<Integer> TagIdList = phraseTagDao.selectPhraseTagInfo(p.getId());
					phraseTagMap.put(p.getId(), TagDao.select(TagIdList));
				}
				
				// ユーザーが登録したことのあるタグを取得
				List<CheeseTag> tagList = TagDao.selectALL(1);
				
		        // リクエスト属性にセット
		        request.setAttribute("phraseList", phraseList);
		        request.setAttribute("phraseTagList", phraseTagMap);
		        request.setAttribute("tagList", tagList);
			 
              StringBuilder result = new StringBuilder();
			
            
			// フレーズ更新
			if ("登録".equals(request.getParameter("regist"))) {
			    boolean phraseUpdated = pDao.update(
			        new CheesePhrase(phraseId, phraseName, phraseRemarks, phrasePath, userId, "", "")
			    );

			    if (phraseUpdated) {
			        result.append("フレーズ更新成功！<br>");

			        String[] addedTags = request.getParameterValues("addedTags");
			        String[] tagModes = request.getParameterValues("tagMode");

			        if (addedTags != null && tagModes != null && addedTags.length == tagModes.length) {
			            ptDao.delete(phraseId); // 中間テーブルから既存関連を削除

			            for (int i = 0; i < addedTags.length; i++) {
			                String tagName = addedTags[i];
			                String mode = tagModes[i];
			                CheeseTag tag = null;

			                if ("registed".equals(mode)) {
			                    tag = tDao.findByName(tagName);

			                } else if ("new".equals(mode)) {
			                    CheeseTag existing = tDao.findByName(tagName);
			                    if (existing != null) {
			                        tag = existing;
			                    } else {
			                        tag = tDao.insertAndReturn(tagName, userId); // 新規タグ挿入
			                    }
			                }

			                if (tag != null) {
			                    ptDao.insert(phraseId, tag.getId()); // 中間テーブルに関連付け
			                }
			            
			        }


			        }
			    } else {
			        result.append("フレーズ更新失敗！<br>");
			    }
			}

			request.setAttribute("result", result.toString());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_edit_phrase.jsp");
			dispatcher.forward(request, response);
			
}
	
}
	          
            
           
            
 

