package xyz.springabc.web.back;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.springabc.web.helper.Status;

@Controller
@RequestMapping("/back")
public class StatusC {
	
	@RequestMapping("/")
	public String index(){
		return "/BACK/status/index";
	}
	
	@RequestMapping("/status/overview")
	public String overview(Model model){
		model.addAttribute("status",new Status());
		return "/BACK/status/overview";
	}
	
	@RequestMapping("/status/log")
	public String log(Model model){
		
		return "/BACK/status/log";
	}
}
