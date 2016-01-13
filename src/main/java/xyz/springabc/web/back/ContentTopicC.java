package xyz.springabc.web.back;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.result.FlashAttributeResultMatchers;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xyz.springabc.domin.Node;
import xyz.springabc.domin.Topic;
import xyz.springabc.repository.NodeRepo;
import xyz.springabc.repository.TopicRepo;
import xyz.springabc.service.NodeServ;
import xyz.springabc.service.SectionServ;
import xyz.springabc.service.TopicServ;
import xyz.springabc.service.UserServ;

@Service
@RequestMapping("/back/content/topics")
public class ContentTopicC {
	
	@Autowired
	private UserServ userServ;
	
	@Autowired
	private TopicServ topicServ;
	
	@Autowired
	private NodeServ nodeSer;
	
	@Autowired
	private SectionServ sectionServ;
	
	/**
	 * 显示话题列表
	 * @param p
	 * @param username
	 * @param title
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"","/"})
	public String index(@RequestParam(value="p",defaultValue="1",required=false) int p,
			@CookieValue(value="username",required=false) String username,
			@CookieValue(value="title",required=false) String title,
			Model model){
		Page<Topic> topicPage=topicServ.getAllFilteByStatusOrderByDefault(p);
		model.addAttribute("topics",topicPage.getContent());
		model.addAttribute("page",topicPage);
		return "/BACK/content/topics/index";
	}
	
	@RequestMapping("/search")
	public String search(String nick,
			String title,
			@RequestParam(value="p",defaultValue="1",required=false) int p,
			Model model){
		Page<Topic> topicPage=topicServ.getByLike(nick, title, p);
		model.addAttribute("topics",topicPage.getContent());
		model.addAttribute("page",topicPage);
		return "/BACK/content/topics/index";
	}
	
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable(value="id") int id,
			Model model){
		Topic topic=topicServ.getOne(id);
		model.addAttribute("topic",topic);
		model.addAttribute("sections",sectionServ.getAll());
		return "/BACK/content/topics/edit";
	}
	
	@RequestMapping("/update")
	public String update(Topic topic,
			String topicNodeName,
			RedirectAttributes attributes){
		Node node=nodeSer.getByname(topicNodeName);
		topic.setNode(node);
		topicServ.update(topic);
		attributes.addFlashAttribute("msg","话题已更新");
		attributes.addFlashAttribute("topic",topicServ.update(topic));
		return "redirect:/back/content/topics/"+topic.getId()+"/edit";
	}
	
	/**
	 * 删除话题
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable("id") int id,
			HttpServletRequest request,
			RedirectAttributes attribute){
		topicServ.softDelete(id);
		attribute.addFlashAttribute("msg","已删除");
		String redirectUrl=request.getHeader("Referer");
		return "redirect:"+redirectUrl;
	}
	
}
