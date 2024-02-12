package mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTest extends Thread {
	private String code;
	private String toEamil;
	private final String title = "제목";
	private static class SMTPAuthenticator extends Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			//지메일 아이디 , 비밀번호
			return new PasswordAuthentication("ostraderos@gmail.com", "knep aohi fxmf dtvh");
		}
	}
	
	public static void send(String title, String content, String toEmail) {
		Properties p = new Properties();
		p.put("mail.smtp.host", "smtp.gmail.com"); // 이메일 발송을 처리해줄 SMTP서버	
		p.put("mail.smtp.starttls.enable", "true");// 로그인시 TLS를 사용할 것인지 설정
		p.put("mail.smtp.port", "587");// TLS의 포트번호는 587이며 SSL의 포트번호는 465이다.		
		p.put("mail.smtp.auth", "true");// SMTP 서버의 인증을 사용한다는 의미		
		p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		p.put("mail.smtp.socketFactory.fallback", "false");
		p.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(p, auth);
			session.setDebug(true); 
			MimeMessage msg = new MimeMessage(session);
			String message = content;
			msg.setSubject(title);
			//InternetAddress(메일주소, 보내는사람 이름, 문자셋)
			Address fromAddr = new InternetAddress("ostraderos@gmail.com"); 
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(toEmail); 
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(message, "text/html;charset=KSC5601");
			Transport.send(msg);
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	public MailTest(String code,String toEamil ) {
		this.code = code;
		this.toEamil = toEamil;
	}
	@Override
	public void run() {
		send(title, code, toEamil==null ? "ostraderos@gmail.com" : toEamil);
		super.run();
	}
}
