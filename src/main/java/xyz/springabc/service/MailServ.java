package xyz.springabc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.springabc.web.form.EmailForm;
import xyz.springabc.web.helper.MyMailSender;

@Service
public class MailServ {
	
	@Autowired
	private MyMailSender myMailSender;
	
	public boolean send(EmailForm emailForm){
		return myMailSender.send(emailForm.getTo(), emailForm.getTitle(), emailForm.getContent());
	}
}
