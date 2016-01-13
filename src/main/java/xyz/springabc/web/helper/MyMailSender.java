package xyz.springabc.web.helper;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MyMailSender {

	private Properties properties=System.getProperties();

	private String host;
	private String port;
	private String username;
	private String password;


	public MyMailSender(String host,String port,String username,String password) {
		this.host=host;
		this.port=port;
		this.username=username;
		this.password=password;
	}

	@SuppressWarnings("deprecation")
	public boolean send(String to, String subject, String content) {

		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", port);
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.EnableSSL.enable", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("username", username);
		properties.setProperty("password", password);
		MyAuthenticator myauth = new MyAuthenticator(username, password);

		// 获取默认session对象
		Session session = Session.getDefaultInstance(properties, myauth);

		try {
			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);

			// Set From: 头部头字段
			message.setFrom(new InternetAddress(username));

			// Set To: 头部头字段（type:要被设置为TO, CC 或者BCC. 这里CC 代表抄送、BCC 代表秘密抄送y.
			// 举例：Message.RecipientType.TO）
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));

			// Set Subject: 头部头字段
			message.setSubject(subject);

			// 设置消息体
			// 设置编码和类型
			message.setContent(content, "text/html;charset=utf-8");

			// 设置时区
			Date ChinaDate = Calendar.getInstance(Locale.CHINA).getTime();
			message.setSentDate(ChinaDate);

			// 发送消息
			Transport.send(message);
			return true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}
	}

}

class MyAuthenticator extends javax.mail.Authenticator {
	private String strUser;
	private String strPwd;

	public MyAuthenticator(String user, String password) {
		this.strUser = user;
		this.strPwd = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(strUser, strPwd);
	}
}
