package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PagingUtil;
import model.bbs.BBSDao;
import model.bbs.BBSDto;

@WebServlet("/list/*")
public class ListController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		BBSDao dao = new BBSDao(getServletContext());
		List<BBSDto> items;
		String navmenu = (String) request.getParameter("list");
		if(pathInfo.equals("/python.do")) {
			items= dao.selectList(null, "PYTHON");
			request.setAttribute("bbsname", "PYTHON");
			dao.close();
		}else if(pathInfo.equals("/html.do")) {
			items= dao.selectList(null, "HTML");
			request.setAttribute("bbsname", "HTML");
			dao.close();
		}else if(pathInfo.equals("/css.do")) {
			items= dao.selectList(null, "CSS");
			request.setAttribute("bbsname", "CSS");
			dao.close();
		}else if(pathInfo.equals("/java.do")) {
			items= dao.selectList(null, "JAVA");
			request.setAttribute("bbsname", "JAVA");
			dao.close();
		}else {
			items= dao.selectList(null, "");
			request.setAttribute("bbsname", "");
			dao.close();
		}
		request.setAttribute("items", items);
		for(BBSDto item:items) {
			String title = item.getTitle();
			String name = item.getName();
		}
		request.getRequestDispatcher("/loginout/Listform.jsp").forward(request, response);
		
	}
}
