package controller;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mail.MailTest;
import model.MemberDTO;
import model.bbs.BBSDao;

@WebServlet("/find/*")
public class PwdFindSetController extends HttpServlet{
	
	public String randomCode() {
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		int length = 6;
		for(int i = 0; i<length; i++) {
			int randomType = random.nextInt(3);
			switch(randomType) {
				case 0:
					sb.append((char) (random.nextInt(10) + '0')); // 숫자
	                break;
	            case 1:
	                sb.append((char) (random.nextInt(26) + 'A')); // 대문자 알파벳
	                break;
	            case 2:
	                sb.append((char) (random.nextInt(26) + 'a')); // 소문자 알파벳
	                break;
			}
		}
		return sb.toString();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		String msg = "";
		//파라미터 받기
		
		if(pathInfo.equals("/find.do")) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			BBSDao dao = new BBSDao(getServletContext());
			MemberDTO mto = dao.mydata(id);
			
			if(!(mto == null)) {
				String namechk = mto.getName();			
				if(name.equals(namechk)) {
					if(email.equals(mto.getEmail())) {
						String code = randomCode();
						MailTest mail = new MailTest(code, email);
						mail.start();
						request.getSession().setAttribute("code", code);
						//이후 입력한 입력코드랑 코드랑 같은지 유효성 체크 후 같으면 비밀번호 변경창으로..
					}
					System.out.println("비밀번호는 : "+mto.getPwd());
					request.getSession().setAttribute("MTO_ID", mto.getId());
					request.setAttribute("MTO_NAME", mto.getName());
					//request.getSession().setAttribute("MTO", mto);
					request.getRequestDispatcher("/loginout/PwdSettingForm.jsp").forward(request, response);
				}
				else {
					msg = "그 아이디와 이름에 맞는 회원은 없습니다.";
					request.setAttribute("MSG", msg);
					System.out.println(msg);
					request.getRequestDispatcher("/loginout/PwdFinderForm.jsp").forward(request, response);
				}
			}else {
				msg = "아이디와 동일한 회원은 없습니다.";
				request.setAttribute("MSG", msg);
				System.out.println(msg);
				request.getRequestDispatcher("/loginout/PwdFinderForm.jsp").forward(request, response);
			}	
		}else if(pathInfo.equals("/set.do")) {
			String code = (String) request.getSession().getAttribute("code");
			String codeinput = request.getParameter("code");
			if(code.equals(codeinput)) {
				BBSDao dao = new BBSDao(getServletContext());
				String cpwd = request.getParameter("cpwd");
				String cpwdchk = request.getParameter("cpwdchk");
				String id = (String)request.getSession().getAttribute("MTO_ID");
				if(cpwd.equals(cpwdchk)) {				
					System.out.println("일치"+id);
					try {
						boolean pwdChangedSuccessfully = dao.setpwd(cpwd, id);
						request.setAttribute("pwdChangedSuccessfully", pwdChangedSuccessfully);
						request.getRequestDispatcher("/loginout/PwdSettingForm.jsp").forward(request, response);
					} catch (SQLException e) {
						msg = "비밀번호 변경 실패";
						request.setAttribute("MSG", msg);
						System.out.println(msg);
					}
				}else {
					msg = "비밀번호가 일치하지 않습니다.";
					request.setAttribute("MSG", msg);
					System.out.println(msg);
					request.getRequestDispatcher("/loginout/PwdSettingForm.jsp").forward(request, response);
				}
			}else {
				msg = "올바르지 않은 인증 코드입니다.";
				request.setAttribute("MSG", msg);
				System.out.println(msg);
				request.getRequestDispatcher("/loginout/PwdSettingForm.jsp").forward(request, response);
			}
		}
	}
}
