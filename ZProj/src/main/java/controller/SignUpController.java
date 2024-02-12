
package controller;

import java.io.IOException;
import java.text.Format;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MemberDTO;
import model.bbs.BBSDao;

@WebServlet("/signup/*")
public class SignUpController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Back
		String referer = request.getHeader("referer").substring(request.getHeader("referer").lastIndexOf("/")+1);
		System.out.println(request.getHeader("referer"));
		request.setAttribute("referer", referer);

		String pathInfo = request.getPathInfo();
		if(pathInfo.equals("/signup.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String pwd2 = request.getParameter("pwd2");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String grade = request.getParameter("grade");
			String self = request.getParameter("self");
			String email = request.getParameter("email");
			String[] interests = request.getParameterValues("inter");
			String interestStr = String.join(",", interests);
			
			BBSDao dao = new BBSDao(getServletContext());
			String msg = dao.signUp(id, pwd, pwd2,name, gender, grade, self, interestStr,email);
			
			if(msg.equals("이미 존재하는 아이디입니다.")) {
				System.out.println(msg);
				request.setAttribute("ERROR_MSG", msg);
				request.getRequestDispatcher("/loginout/SignUpform.jsp").forward(request, response);				
			}
			else if(msg.equals("비밀번호가 일치하지 않습니다.")){
				request.setAttribute("ERROR_MSG", msg);
				request.getRequestDispatcher("/loginout/SignUpform.jsp").forward(request, response);
				System.out.println(msg);
				System.out.println(1111);				
			}else if(msg.equals("유효하지 않은 이메일 주소입니다.")) {
				request.setAttribute("ERROR_MSG", msg);
				request.getRequestDispatcher("/loginout/SignUpform.jsp").forward(request, response);
				System.out.println(1111);
			}
			else{
				request.getSession().setAttribute("USER_ID", id);
				response.sendRedirect(request.getContextPath()+"/loginout/Mainform.jsp");
				System.out.println(msg);
			}
		}		
		else if(pathInfo.equals("/mypage.do")){
			referer = request.getHeader("referer").substring(request.getHeader("referer").lastIndexOf("/")+1);
			System.out.println(referer); //mainlist.do
			request.getSession().setAttribute("referer", referer);
			
			MemberDTO mto = new MemberDTO();
			BBSDao dao = new BBSDao(getServletContext());
			String id = (String)request.getSession().getAttribute("USER_ID");
			System.out.println(id);
			mto = dao.mydata(id);
			request.getSession().setAttribute("USER_NAME", mto.getName());
			request.getSession().setAttribute("USER_GENDER", mto.getGender());
			request.getSession().setAttribute("USER_EDU", mto.getEducation());
			request.getSession().setAttribute("USER_INTERS", mto.getInters());
			request.getSession().setAttribute("USER_INFO", mto.getSelfintroduce());
			request.getSession().setAttribute("USER_EMAIL", mto.getEmail());
			
			// interests 배열을 문자열로 변환하여 JSP로 전달
		    String interestsAsString = "";
		    if(mto.getInters() !=null) {
		    	interestsAsString = String.join(",", mto.getInters());
		    }
		    
		    request.getSession().setAttribute("USER_INTERS", interestsAsString);
			
			System.out.println(interestsAsString);
			request.getRequestDispatcher("/loginout/MyPageform.jsp").forward(request, response);
		}		
		else if(pathInfo.equals("/mypageupdate.do")) {
			referer = (String) request.getSession().getAttribute("referer");
			System.out.println(referer);
			request.setAttribute("referer", referer);
					
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String pwd2 = request.getParameter("pwd2");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String grade = request.getParameter("grade");
			String self = request.getParameter("self");
			String[] interests = request.getParameterValues("inter");
			String interestStr = String.join(",", interests);
			String email = request.getParameter("email");
			
			BBSDao dao = new BBSDao(getServletContext());
			if(pwd.equals(pwd2)) {
				String msg = dao.updatemyinfo(id, pwd, name, gender, grade, self, interestStr,email);
				System.out.println(msg);				
				MemberDTO mto = dao.mydata(id);
				dao = new BBSDao(getServletContext());
				request.getSession().setAttribute("USER_ID", mto.getId());
				request.getSession().setAttribute("USER_NAME", mto.getName());
				request.getSession().setAttribute("USER_GENDER", mto.getGender());
				request.getSession().setAttribute("USER_EDU", mto.getEducation());
				request.getSession().setAttribute("USER_INTERS", mto.getInters());
				request.getSession().setAttribute("USER_INFO", mto.getSelfintroduce());
				request.getSession().setAttribute("USER_EMAIL", mto.getEmail());
				
				// interests 배열을 문자열로 변환하여 JSP로 전달
			    String interestsAsString = "";
			    if(mto.getInters() !=null) {
			    	interestsAsString = String.join(",", mto.getInters());
			    }		    
			    request.getSession().setAttribute("USER_INTERS", interestsAsString);
				System.out.println(String.format("아이디:%s\n비밀번호:%s\n이름:%s\n학력:%s\n관심사항:%s\n", id, pwd, mto.getName(),mto.getEducation(), interestStr));
				//request.getRequestDispatcher("/loginout/MyPageform.jsp").forward(request, response);
				response.sendRedirect(request.getContextPath()+"/loginout/MyPageform.jsp");
				
			}else {
				System.out.println("비밀번호 불일치");
				//request.getRequestDispatcher("/loginout/MyPageform.jsp").forward(request, response);
				response.sendRedirect(request.getContextPath()+"/loginout/MyPageform.jsp");
			}
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
}
