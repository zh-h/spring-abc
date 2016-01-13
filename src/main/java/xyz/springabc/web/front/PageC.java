package xyz.springabc.web.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.springabc.service.ThePageServ;

@Controller
public class PageC {
	
	@Autowired
	private ThePageServ pageServ;
	
	/**
	 * 显示页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value="{url}.html")
	public String page(@PathVariable("url") String url,Model model){
		model.addAttribute("page",pageServ.getOneByUrl(url));
		return "/page/show";
	}

}
