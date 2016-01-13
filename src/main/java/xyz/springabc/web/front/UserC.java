package xyz.springabc.web.front;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.springabc.domin.User;
import xyz.springabc.repository.CollectionRepo;
import xyz.springabc.service.CollectionServ;
import xyz.springabc.service.CommentServ;
import xyz.springabc.service.FollowServ;
import xyz.springabc.service.NotificationServ;
import xyz.springabc.service.TopicServ;
import xyz.springabc.service.UserServ;

@Controller
@RequestMapping("/users")
public class UserC {
	
	@Autowired
	private UserServ userServ;
	
	@Autowired
	private CollectionServ collectionServ;
	
	@Autowired
	private NotificationServ  notificationServ;
	
	@Autowired
	private TopicServ topicServ;
	
	@Autowired
	private CommentServ commentServ;
	
	@Autowired
	private FollowServ followServ;
	
	@RequestMapping({"","/"})
	public String index(@RequestParam(value="p",defaultValue="1") int p,
			Model model){
		//TODO 显示前一百的用户花名册
		Page<User> userPage=userServ.getAll(p);
		model.addAttribute("users",userPage.getContent());
		model.addAttribute("page",userPage);
		return "/users/index";
	}
	/**
	 * 获取收藏列表
	 * @param id
	 * @param p
	 * @return
	 */
	@RequestMapping("/{id}/collections")
	public String favorites(@PathVariable(value="id") int id,
			@RequestParam(defaultValue="1",value="p") int p,
			Model model,
			HttpServletResponse response){
		User user=userServ.getByUserId(id);
		if(user==null){
			response.setStatus(404);
		}else{
			model.addAttribute("user",user);
			model.addAttribute("page",collectionServ.getByUserId(user, p, 12));
		}
		return "/users/collections";
	}
	
	/**
	 * 获取当前用户追随
	 * @param p 分页
	 * @param id 用户编号
	 * @return
	 */
	@RequestMapping("/{id}/followers")
	public String followers(@RequestParam(defaultValue="1",value="p") int p,
			@PathVariable(value="id") int id,
			Model model,
			HttpServletResponse response){
		User user=userServ.getByUserId(id);
		if(user==null){
			response.setStatus(404);
		}else{
			model.addAttribute("user",user);
			model.addAttribute("page",followServ.getFollowerByUser(user, p));
		}
		return "/users/followers";
	}
	
	/**
	 * 追随
	 * 需要登录
	 * @param id
	 * @return
	 */
	@RequestMapping("/follow")
	@ResponseBody
	public int follow(@RequestParam(value="id") int id,
			HttpServletRequest request){
		User self=(User)request.getSession().getAttribute("user");
		User other=userServ.getByUserId(id);
		return followServ.follow(self, other)!=null?1:0;
	}
	
	/**
	 * 不再追随
	 * 需要登录
	 * @param id
	 * @return
	 */
	@RequestMapping("/unfollow")
	@ResponseBody
	public int unfollow(@RequestParam(value="id") int id,
			HttpServletRequest request){
		User self=(User)request.getSession().getAttribute("user");
		User other=userServ.getByUserId(id);
		followServ.unfollow(self, other);
		return 1;
	}
	
	/**
	 * 判断是不是追随着
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/isFollowing")
	@ResponseBody
	public int isFollowing(@RequestParam("id") int id,
			HttpServletRequest request){
		User self=(User)request.getSession().getAttribute("user");
		User other=userServ.getByUserId(id);
		return followServ.isFollowing(self, other)==true?1:0;
	}
	
	/**
	 * 获取正在关注的用户
	 * @param p 分页
	 * @param id 当前用户id
	 * @return
	 */
	@RequestMapping("/{id}/following")
	public String following(@RequestParam(defaultValue="1",value="p") int p,
			@PathVariable(value="id") int id,
			Model model,
			HttpServletResponse response){
		User user=userServ.getByUserId(id);
		if(user==null){
			response.setStatus(404);
		}else{
			model.addAttribute("user",user);
			model.addAttribute("page",followServ.getFollowingByUser(user, p));
		}
		return "users/following";
	}
	
	/**
	 * 获取用户话题列表
	 * @param p
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}/topics")
	public String topics(@RequestParam(defaultValue="1",value="p") int p,
			@PathVariable(value="id") int id,
			Model model,
			HttpServletResponse response){
		User user=userServ.getByUserId(id);
		if(user==null){
			response.setStatus(404);
		}else{
			model.addAttribute("user", user);
			model.addAttribute("page",topicServ.getByUser(user, p, 12));
		}
		return "users/topics";
	}
	
	/**
	 * 获取用户评论列表
	 * @param p
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}/comments")
	public String comments(@RequestParam(defaultValue="1",value="p") int p,
			@PathVariable(value="id") int id,
			Model model,
			HttpServletResponse response){
		User user=userServ.getByUserId(id);
		if(user==null){
			response.setStatus(404);
		}else{
			model.addAttribute("user",user);
			model.addAttribute("page",commentServ.getByUser(user, p, 30));
		}
		return "users/comments";
	}

}
