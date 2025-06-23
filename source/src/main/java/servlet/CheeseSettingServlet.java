package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheeseSettingServlet
 */
@WebServlet("/CheeseSettingServlet")
public class CheeseSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException {
	
	 int count = Integer.parseInt(request.getParameter("duplicateCount"));
	 
	// セッションスコープに保存
	HttpSession session = request.getSession();
	session.setAttribute("duplicateCount", count);

     response.setStatus(HttpServletResponse.SC_OK);
     
	 }
}
