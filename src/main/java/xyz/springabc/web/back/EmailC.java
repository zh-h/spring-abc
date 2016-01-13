package xyz.springabc.web.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.springabc.service.MailServ;
import xyz.springabc.web.form.EmailForm;

@Controller
@RequestMapping("/back/emails")
public class EmailC {
	
	@Autowired
	private MailServ mailServ;
	
	@RequestMapping("/overview")
	public String index(){
		return "/BACK/emails/overview";
	}
	@RequestMapping(value="/send",method=RequestMethod.GET)
	public String sedn(){
		return "/BACK/emails/send";
	}
	
	@RequestMapping(value="/send",method=RequestMethod.POST)
	@ResponseBody
	public boolean sendAction(EmailForm emailForm){
		
		return mailServ.send(emailForm);
	}
}
