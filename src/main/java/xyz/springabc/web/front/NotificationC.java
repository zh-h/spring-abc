package xyz.springabc.web.front;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.springabc.domin.Notification;
import xyz.springabc.domin.User;
import xyz.springabc.service.NotificationServ;
import xyz.springabc.service.UserServ;

@Controller
@RequestMapping("/notifications")
public class NotificationC {

	@Autowired
	private NotificationServ notificationServ;
	
	@Autowired
	private UserServ userServ;
	
	/**
	 * 显示未读通知
	 * @param p
	 * @param order
	 * @return
	 */
	@RequestMapping({"/",""})
	public String index(@RequestParam(defaultValue="1",value="p") int p ,
			@RequestParam(defaultValue="",value="order") String order,
			Model model,
			HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		Page<Notification> notificationPage=notificationServ.getByUser(user, NotificationServ.NONE, p);
		model.addAttribute("notifications",notificationPage.getContent());
		model.addAttribute("page",notificationPage);
		notificationServ.readAll(notificationPage.getContent());//阅读所有通知
		model.addAttribute("user",user);
		return "notifications/index";
	}
	
	/**
	 * 获取全部通知
	 * @param p 分页
	 * @return
	 */
	@RequestMapping("/all")
	public String all(@RequestParam(defaultValue="1",value="p") int p,
			Model model,
			HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		Page<Notification> notificationPage=
				notificationServ.getByUser(user,
						NotificationServ.ALL, p);
		model.addAttribute("notifications",notificationPage.getContent());
		model.addAttribute("page",notificationPage);
		model.addAttribute("user",user);
		return "notifications/all";
	}
	
	/**
	 * 获取未读通知
	 * @param request
	 * @return
	 */
	@RequestMapping("/count")
	@ResponseBody
	public long count(HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		return notificationServ.countNotificationNoneRead(user);
	}
}
