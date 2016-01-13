package xyz.springabc.web.front;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xyz.springabc.domin.Comment;
import xyz.springabc.domin.Node;
import xyz.springabc.domin.Topic;
import xyz.springabc.domin.User;
import xyz.springabc.service.CollectionServ;
import xyz.springabc.service.CommentServ;
import xyz.springabc.service.NodeServ;
import xyz.springabc.service.SectionServ;
import xyz.springabc.service.TopicServ;
import xyz.springabc.service.UserServ;

@Controller
@RequestMapping("/topics")
public class TopicC {

	@Autowired
	private TopicServ topicServ;

	@Autowired
	private NodeServ nodeServ;

	@Autowired
	private SectionServ sectionServ;

	@Autowired
	private CollectionServ collectionServ;

	@Autowired
	private CommentServ commentServ;

	/**
	 * 不能匹配话题，值作为显示话题公告使用
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "/topics/show";
	}

	/**
	 * 显示话题详细
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}")
	public String show(@PathVariable("id") int id, @RequestParam(value = "p", defaultValue = "1") int p, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		Topic topic = topicServ.getOne(id);
		User user = (User) request.getSession().getAttribute("user");
		boolean isCollected = collectionServ.isCollected(topic, user);
		model.addAttribute("isCollected", isCollected);
		if (topic != null) {
			Page<Comment> commentPage = commentServ.getByTopicWithLike(topic, p, user);// 按照话题查找
			model.addAttribute("page", commentPage);// 存在文章的时候才回复
			model.addAttribute("comments", commentPage.getContent());
			model.addAttribute("otherTopics",topicServ.getByUser(user, 1, 8).getContent());
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);// 抛出404
		}
		model.addAttribute("topic", topic);
		return "/topics/show";
	}

	/**
	 * 发表评论
	 * @param topicId	话题id
	 * @param comment	评论实体，还没有发表用户属性
	 * @return
	 */
	@RequestMapping("/comment")
	public String comment(@RequestParam(value = "topicId", required = true) int topicId,
			@Validated Comment comment,
			Errors result,
			HttpServletRequest request,
			RedirectAttributes attributes) {
		User user = (User) request.getSession().getAttribute("user");
		String contextPath=request.getContextPath();
		if(result.hasErrors()){
			attributes.addFlashAttribute("error",result.getAllErrors());
			return "redirect:/topics/" + topicId;
		}else{
			comment.setUser(user);
			commentServ.create(comment, topicId, contextPath);
			attributes.addFlashAttribute("msg","评论已经发表");
		}
		return "redirect:/topics/" + topicId;
	}

	/**
	 * 新建话题页面
	 * 
	 * @return
	 */
	@RequestMapping("/create")
	public String newPage(Model model) {
		model.addAttribute("sections", sectionServ.getAll());
		return "/topics/create";
	}

	/**
	 * 保存话题动作 post提交话题参数
	 * String node 节点名
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Validated Topic topic,Errors resutlt, String topicNodeName, RedirectAttributes redirectAttrs,
			HttpServletRequest requset) {
		User user = (User) requset.getSession().getAttribute("user");
		if(resutlt.hasErrors()){
			redirectAttrs.addFlashAttribute("error", resutlt.getAllErrors());
			redirectAttrs.addFlashAttribute("topic",topic);
			return "redirect:/topics/create";
		}else{
			topicServ.save(topic, topicNodeName, user);
			return "redirect:/topics/" + topic.getId();
		}
		
	}

	/**
	 * 按照id进入到话题编辑页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable int id,Model model) {
		model.addAttribute("topic",topicServ.getOne(id));
		model.addAttribute("sections", sectionServ.getAll());
		return "/topics/edit";
	}

	/**
	 * 处理更新动作 post提交话题参数
	 * 
	 * @return
	 */
	@RequestMapping("/update")
	public String update(@Validated @ModelAttribute Topic topic,
			Errors error,
			String topicNodeName,
			Model model,
			RedirectAttributes attributes){
		
		if(error.hasErrors()){
			model.addAttribute("error",error.getAllErrors());
			return "/topics/edit";
		}else{
			Node node=nodeServ.getByname(topicNodeName);
			topic.setNode(node);
			topicServ.update(topic);
			attributes.addFlashAttribute("msg","话题已更新");
			attributes.addFlashAttribute("topic",topicServ.update(topic));
			return "redirect:/topics/"+topic.getId();
		}
	}
	
	@RequestMapping("/{id}/delete")
	@ResponseBody
	public boolean delete(@PathVariable("id") int id){
		topicServ.softDelete(id);
		return true;
	}

	/**
	 * 收藏
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/{id}/collect")
	@ResponseBody
	public int collect(@PathVariable("id") int id, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Topic topic = topicServ.getOne(id);
		if (user != null && topic != null) {
			return collectionServ.collect(topic, user) != null ? 1 : 0;
		} else {
			return 0;
		}
	}

	/**
	 * 是否收藏
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/{id}/isCollected")
	@ResponseBody
	public int isCollected(@PathVariable("id") int id, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Topic topic = topicServ.getOne(id);
		if (user != null && topic != null) {
			return collectionServ.isCollected(topic, user) == true ? 1 : 0;
		} else {
			return 0;
		}
	}

	/**
	 * 取消收藏
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/{id}/unCollect")
	@ResponseBody
	public int unCollect(@PathVariable("id") int id, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Topic topic = topicServ.getOne(id);
		if (user != null && topic != null) {
			collectionServ.unCollect(topic, user);
		}
		return 1;
	}
}
