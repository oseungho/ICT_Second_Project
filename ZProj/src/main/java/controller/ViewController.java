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

@WebServlet("/view/*")
public class ViewController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(pathInfo.equals("/click.do")) {
			String no = request.getParameter("no");
			BBSDao dao = new BBSDao(getServletContext());
			BBSDto bto = dao.bbsdata(no);
			String title = bto.getTitle();
			String id = bto.getId();
			String content = bto.getContent().replace("\r\n", "<br>");
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
			request.getRequestDispatcher("/loginout/Viewform.jsp").forward(request, response);
		}else if(pathInfo.equals("/delete.do")) {
			String no = request.getParameter("no");
            if (no != null && !no.isEmpty()) {
                BBSDao dao = new BBSDao(getServletContext());
                boolean success = dao.deleteBBSData(no);
                if (success) {
                    // 삭제가 성공했을 경우 다른 페이지로 이동하거나, 적절한 응답을 보여줄 수 있습니다.
                    response.sendRedirect(request.getContextPath() + "/list/list.do");
                } else {
                    // 삭제가 실패했을 경우에 대한 처리
                    // 실패 메시지를 설정하고 해당 페이지로 다시 이동하거나, 에러 페이지를 보여줄 수 있습니다.
                    request.setAttribute("deleteError", "삭제 실패");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            }
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
