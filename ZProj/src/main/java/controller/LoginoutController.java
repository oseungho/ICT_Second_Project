package controller;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bbs.BBSDao;

@WebServlet("/login/*")
public class LoginoutController extends HttpServlet{
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, java.io.IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo.equals("/login.do")) {
        	//파라미터 받기
    		String id = request.getParameter("id");
    		String pwd = request.getParameter("pwd");
    		BBSDao dao = new BBSDao(getServletContext());
    		boolean isMember = dao.isMember(id, pwd);
    		if(isMember){
    			request.getSession().setAttribute("USER_ID", id); //세션영역에 속성 저장
    			System.out.println("로그인된 ID : "+id);
    			//response.sendRedirect(request.getContextPath()+"/loginout/Mainform.jsp");
    			response.sendRedirect(request.getContextPath()+"/main/mainlist.do");
    		}else{
    			request.setAttribute("NOT_LOGIN", "아이디 또는 패스워드가 일치하지 않습니다.");
    			request.getRequestDispatcher("/loginout/loginform.jsp").forward(request, response);
    		}
        } else if (pathInfo.equals("/logout.do")) {
            // LogoutController에 해당하는 기능 수행
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate(); // 세션 무효화
            }
            response.sendRedirect("/loginout/loginform.jsp");
        }
    }
}

