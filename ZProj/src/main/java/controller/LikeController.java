package controller;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bbs.BBSDao;

@WebServlet("/likebtn/*")
public class LikeController extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
		String pathInfo = request.getPathInfo();
		String msg ="";

		String id = (String) request.getSession().getAttribute("USER_ID");
		String no = request.getParameter("no");
		//String bbsname = (String) request.getAttribute("bbsname");
		BBSDao dao = new BBSDao(getServletContext());
		int likeno = dao.likedata(no, id);
		System.out.println(id+no);
		if(likeno == 0) {
			msg = dao.likebbscontrol(no, id, 1);
		}else{
			msg = dao.likebbscontrol(no, id, 0);
		}
		System.out.println(msg);
		
		// 클라이언트로부터 전송된 데이터를 확인
        String requestBody = request.getReader().readLine();
        // 실제로는 requestBody를 파싱하고 데이터베이스에 저장하거나 필요한 작업을 수행해야 합니다.
        System.out.println(requestBody);
        // 받은 데이터를 그대로 응답으로 다시 보내는 예시
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\": \"" + msg + "\",\"number\": \"" + no + "\"}");
    }	
}
