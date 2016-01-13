package xyz.springabc.web.front;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xyz.springabc.domin.Comment;
import xyz.springabc.domin.User;
import xyz.springabc.service.CommentServ;
import xyz.springabc.service.UserServ;

@Controller
@RequestMapping("/comments")
public class CommentC {
	
	@Autowired
	private CommentServ commentServ;
	
	@RequestMapping("/create")
	public String create(@RequestParam(value = "topicId", required = true) int topicId,
			@Validated Comment comment,
			Errors result,
			HttpServletRequest request,
			Model model){
		User user = (User) request.getSession().getAttribute("user");
		String contextPath=request.getContextPath();
		if(result.hasErrors()){
			model.addAttribute("error",result.getAllErrors());
			return "/comments/_msg";
		}else{
			comment.setUser(user);
			commentServ.create(comment, topicId, contextPath);
			model.addAttribute("comment",comment);
			return "/comments/_show";
		}
	}
	
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id,Model model){
		model.addAttribute("comment",commentServ.getOne(id));
		return "/comments/edit";
	}
	
	@RequestMapping("/update")
	public String eidt(Comment comment,RedirectAttributes attributes){
		commentServ.update(comment);
		attributes.addFlashAttribute("msg","已经更改");
		return "redirect:/comments/"+comment.getId()+"/edit";
	}
	
	@RequestMapping("/{id}/delete")
	@ResponseBody
	public boolean delete(@PathVariable("id") int id,
			HttpServletRequest request){
		User user=(User)request.getSession().getAttribute("user");
		Comment comment=commentServ.getOne(id);
		if(comment.getUser().equals(user)||user.getRole().equals(UserServ.ROLE_ADMIIN)){
			commentServ.delete(comment);
			return true;
		}else{
			return false;
		}
	}
	
	@RequestMapping("/{id}/like")
	@ResponseBody
	public boolean like(@PathVariable("id") int id,
			HttpServletRequest request){
		User user=(User)request.getSession().getAttribute("user");
		Comment comment=commentServ.getOne(id);
		return commentServ.like(comment, user);
	}
}
