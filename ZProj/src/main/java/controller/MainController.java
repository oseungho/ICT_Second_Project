package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bbs.BBSDao;
import model.bbs.BBSDto;

@WebServlet("/main/*")
public class MainController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		BBSDao dao = new BBSDao(getServletContext());
		List<BBSDto> items;
		String navmenu = (String) request.getParameter("list");
		items= dao.selectList(null, 5);
		dao.close();
		request.setAttribute("items", items);
		for(BBSDto item:items) {
			String title = item.getTitle();
			String name = item.getName();
		}
		request.getRequestDispatcher("/loginout/Mainform.jsp").forward(request, response);
	}
}
