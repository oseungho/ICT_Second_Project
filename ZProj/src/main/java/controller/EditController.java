package controller;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bbs.BBSDao;
import model.bbs.BBSDto;

@WebServlet("/edit/*")
public class EditController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(pathInfo.equals("/edit.do")) {
			String no = request.getParameter("no");
			BBSDao dao = new BBSDao(getServletContext());
			BBSDto bto = dao.bbsdata(no);
			String title = bto.getTitle();
			String id = bto.getId();
			String content = bto.getContent();
			String hitcount = bto.getHitCount();
			String name = bto.getName();
			Date postdate = bto.getPostDate();
			String bbsname = bto.getBbsName();
			
			request.setAttribute("no", no);
			request.setAttribute("id", id);
			request.setAttribute("title", title);
			request.setAttribute("content", content);
			request.setAttribute("postdate", postdate);
			request.setAttribute("name", name);
			request.setAttribute("bbsname", bbsname);
			request.getRequestDispatcher("/loginout/Editform.jsp").forward(request, response);
		}
		else if(pathInfo.equals("/complete.do")) {
			String no = request.getParameter("no");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			BBSDao dao = new BBSDao(getServletContext());
			String msg = dao.bbsupdate(no, title, content);
			System.out.println(msg);
			request.setAttribute("no", no);
			request.getRequestDispatcher("/view/click.do?no=" + no).forward(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
