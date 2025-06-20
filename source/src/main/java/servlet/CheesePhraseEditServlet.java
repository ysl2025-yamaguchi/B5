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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		List<CheeseTag> tagList;
		CheeseTagDao tagDao = new CheeseTagDao();
		tagList= tagDao.selectALL(1); 
		request.setAttribute("tagList", tagList);
		
		
	    CheesePhraseTagDao list = new CheesePhraseTagDao();
		List<Integer> phraseTagIdList = list.selectPhraseTagInfo(1);
		List<CheeseTag> phraseTagList = tagDao.select(phraseTagIdList);
		
		List<CheesePhrase> phraseList;
		CheesePhraseDao phraseDao =  new CheesePhraseDao();
		phraseList = phraseDao.select(new ArrayList<String>(), new ArrayList<String>(), "", 1);
		request.setAttribute("phraseList", phraseList);
		
		
		//int phraseId = Integer.parseInt(request.getParameter("phraseId"));
	    //CheesePhraseDao phraseDao = new CheesePhraseDao();
	   // CheesePhrase phrase = phraseDao.findById(phraseId);
	   // request.setAttribute("phrase", phrase);
		

		request.setAttribute("phraseTagList", phraseTagList);
		
		
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

	    // Get parameters from the form
//			int id= Integer.parseInt(request.getParameter("id"));
//			int phraseId= Integer.parseInt(request.getParameter("phraseId"));
//			String phraseName=request.getParameter("phraseName");
//		    String phraseRemarks=request.getParameter("phraseRemarks");
//			String phrasePath=request.getParameter("phrasePath");
//			int userId= Integer.parseInt(request.getParameter("userId"));
//			int tagId= Integer.parseInt(request.getParameter("tagId"));
//			String tagName=request.getParameter("tagName");
   
			
			int id=9;
			int phraseId= 1;
			String phraseName="A";
		    String phraseRemarks="B";
			String phrasePath="/path/to/phraseA.wav";
			int userId=1;
			int tagId=1;
			String tagName="d";
	 
		    
			
		   
			CheesePhraseTagDao ptDao=new CheesePhraseTagDao();
			CheesePhraseDao pDao=new CheesePhraseDao();
			CheeseTagDao tDao=new CheeseTagDao();
			// 変数準備
			StringBuilder result = new StringBuilder();

			// フレーズ更新
			if ("登録".equals(request.getParameter("regist"))) {
				boolean phraseUpdated = pDao.update(
			        new CheesePhrase(phraseId, phraseName, phraseRemarks, phrasePath, userId, "", "")
			    );
				 boolean tagInserted = tDao.insert(
					        new CheeseTag(tagId, tagName, userId, "", "")
			    );
				 boolean phraseTagInserted= ptDao.insert(
							new CheesePhraseTag(id, phraseId, tagId, "", "")
					);
				boolean phraseTagUpdated= ptDao.update(
						new CheesePhraseTag(id, phraseId, tagId, "", "")
				);

			    if (phraseUpdated) {
			        result.append("フレーズ更新成功！<br>");
			    } else {
			        result.append("フレーズ更新失敗！<br>");
			    }
			    if (tagInserted) {
			        result.append("タグ追加成功！<br>");
			    } else {
			        result.append("タグ追加失敗！<br>");
			    }
//			    if (phraseTagUpdated) {
//			        result.append("フレーズ更新成功！<br>");
//			    } else {
//			        result.append("フレーズ更新失敗！<br>");
//			    }
			}
				
	

			// タグ登録（新規タグがあれば）
			
			// フレーズとタグの関連更新
//			boolean phraseTagUpdated = ptDao.update(
//			    new CheesePhraseTag(id, phraseId, tagId, "", "")
//			);
//
//			if (phraseTagUpdated) {
//			    result.append("フレーズとタグの関連付け成功！<br>");
//			} else {
//			    result.append("フレーズとタグの関連付け失敗！<br>");
//			}

			// 結果をセットして画面へ
			request.setAttribute("result", result.toString());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cheese_edit_phrase.jsp");
			dispatcher.forward(request, response);
			
}
}
	          
            
           
            
            
           
//		    if(request.getParameter("submit").equals("更新")) {
//		    	
//		    }
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
	


