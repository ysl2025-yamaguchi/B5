package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.CheesePhraseDAO;

/**
 * Servlet implementation class CheeseRegistPhraseServlet
 */
@MultipartConfig
@WebServlet("/CheeseRegistPhraseServlet")
public class CheeseRegistPhraseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean testFlag = true;
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		
		Part part = request.getPart("uploded_file");
		String uplodedFileName = part.getSubmittedFileName();
		
		int dotPosition = uplodedFileName.lastIndexOf(".");
		String extension = uplodedFileName.substring(dotPosition);
		
		CheesePhraseDAO dao = new CheesePhraseDAO();
		String fileName = "phrase_" + dao.getNextId() + extension;
		
		if (testFlag) {
	    	part.write(getServletContext().getRealPath("") + "/" + fileName);
	    	request.setAttribute("result", "登録が成功しました!!!!!!!!!!!!!!!!!!!!!!!!");
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("CheesePhraseListServlet");
	    	dispatcher.forward(request, response);
		}
		else {
	    	part.write(request.getContextPath() + "/" + fileName);
	    	request.setAttribute("result", "登録が成功しました!!!!!!!!!!!!!!!!!!!!!!!!");
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("CheesePhraseListServlet");
	    	dispatcher.forward(request, response);
		}
	}

}
