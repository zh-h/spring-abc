package xyz.springabc.web.front;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import xyz.springabc.domin.Focus;
import xyz.springabc.domin.Node;
import xyz.springabc.domin.Topic;
import xyz.springabc.domin.User;
import xyz.springabc.service.FocusServ;
import xyz.springabc.service.NodeServ;
import xyz.springabc.service.TopicServ;

@Controller
@RequestMapping("/nodes")
public class NodeC {
	
	@Autowired
	private NodeServ nodeServ;
	
	@Autowired
	private TopicServ topicServ;
	
	@Autowired
	private FocusServ focuServ;
	
	/**
	 * page不能作为分页参数
	 * 显示所有节点
	 * @return
	 */
	@RequestMapping({"/",""})
	public String showAll(@RequestParam(value="p",defaultValue="1") int p,Model model){
		model.addAttribute("nodes",nodeServ.getAllOrderByTopicCount(p, 100).getContent());
		return "/nodes/index";
	}
	
	/**
	 * 显示节点内容，即按照节点显示话题列表
	 * @param name 节点名称
	 * @param p 分页参数
	 * @return
	 */
	@RequestMapping("/{id}")
	public String list(@PathVariable("id") int id,
			@RequestParam(defaultValue="1",value="p") int p,
			HttpServletRequest request,
			Model model){
		User user=(User)request.getSession().getAttribute("user");
		Node node=nodeServ.getOneNode(id);
		Page<Topic> topicPage=topicServ.getByNodeOrderByDefault(node, p);
		List<Topic> topics=topicPage.getContent();
		model.addAttribute("topics",topics);
		if(user!=null){
			model.addAttribute("isFocus",focuServ.isFocus(id, user.getId()));
		}
		model.addAttribute("page",topicPage);
		model.addAttribute("node",node);
		return "/nodes/list";
	}
	
	/**
	 * 按照分类获取节点名
	 * @param sectionName
	 * @return List<String> 节点名
	 */
	@RequestMapping("/list/{sectionName}")
	@ResponseBody
	public List<String> list(@PathVariable("sectionName") String sectionName){
		return nodeServ.getNodeNameBySectionName(sectionName);
	}
	
	@RequestMapping("/{id}/focus")
	@ResponseBody
	public boolean focus(@PathVariable("id") int nodeId,HttpServletRequest request){
		User user=(User)request.getSession().getAttribute("user");
		if(user==null){
			return false;
		}
		boolean isFoucs=focuServ.isFocus(nodeId, user.getId());
		if(isFoucs){
			focuServ.unFocus(nodeId, user.getId());
		}else{
			focuServ.focus(nodeId, user.getId());
		}
		return !isFoucs;
	}
	
}
