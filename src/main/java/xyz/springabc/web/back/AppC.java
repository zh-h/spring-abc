package xyz.springabc.web.back;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import xyz.springabc.domin.Property;
import xyz.springabc.service.PropertyServ;
import xyz.springabc.service.support.Constant;

@Controller
@RequestMapping("/back/app")
public class AppC {
	
	public static final String POST="POST";
	
	@Autowired
	private PropertyServ propertyServ;

	@RequestMapping("/")
	public String index() {
		return "/BACK/app/index";
	}

	@RequestMapping("/infomation")
	public String information(Model model) {
		return "/BACK/app/infomation";
	}
	
	/**
	 * 每个post请求都来更新设置
	 * @param request
	 * @param model
	 */
	@ModelAttribute
	public void propertyUpdate(HttpServletRequest request, Model model) {
		if (request.getMethod()==POST) {
			Map<String, String[]> parames = request.getParameterMap();
			propertyServ.update(parames);
			model.addAttribute("msg", "设置已更新");
		}
	}

	@RequestMapping("/nav")
	public String nav(Model model) {
		return "/BACK/app/nav";
	}

	@RequestMapping("/footer")
	public String footer(Model model) {
		return "/BACK/app/footer";
	}

	@RequestMapping("/ad")
	public String ad(Model model) {
		return "/BACK/app/ad";
	}

	@RequestMapping("/tips")
	public String tips(Model model) {
		return "/BACK/app/tips";
	}

	@RequestMapping("authority")
	public String authority(Model model) {
		return "/BACK/app/authority";
	}
}
