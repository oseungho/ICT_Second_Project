package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bbs.BBSDao;
import model.bbs.BBSDto;

@WebServlet("/write/*")
public class writeController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(pathInfo.equals("/complete.do")) {
			String id = (String) request.getSession().getAttribute("USER_ID");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String bbsname = request.getParameter("bbsname");
			
			BBSDao dao = new BBSDao(getServletContext());
			String msg = dao.writesuccess(id, title, content, bbsname);
			System.out.println(msg);
			response.sendRedirect(request.getContextPath()+"/list/list.do");
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
