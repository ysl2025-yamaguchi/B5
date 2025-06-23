package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheeseSettingServlet
 */
@WebServlet("/CheeseCheckMusicServlet")

public class CheeseCheckMusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		List<String> phrases = new ArrayList<>();
        for (int i = 0; ; i++) {
            String p = request.getParameter("phrase" + i);
            if (p == null) break;
            phrases.add(p.trim());
        
         // セッションスコープから取得（なければ3をデフォルト）
    		HttpSession session = request.getSession();
            int limit = session.getAttribute("duplicateCount") != null
                ? (int) session.getAttribute("duplicateCount")
                : 3;
         // フレーズ重複カウント処理
        Map<String, Integer> map = new HashMap<>();
            for (String ph : phrases) {
                map.put(ph, map.getOrDefault(ph, 0) + 1);
                if (map.get(ph) > limit) {
                    response.getWriter().write("フレーズ 「" + ph + "」 が"+ limit + "回を超えて使用されています。");
                    return;
        }	
	}
            response.getWriter().write("重複なし");
        }
	}
}
