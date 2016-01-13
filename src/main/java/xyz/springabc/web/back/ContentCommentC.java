package xyz.springabc.web.back;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xyz.springabc.domin.Comment;
import xyz.springabc.service.CommentServ;
import xyz.springabc.service.TopicServ;
import xyz.springabc.service.UserServ;

@Controller
@RequestMapping("/back/content/comments")
public class ContentCommentC {

	@Autowired
	private UserServ userServ;

	@Autowired
	private CommentServ commentServ;

	@Autowired
	private TopicServ topicServ;

	@RequestMapping("")
	public String index(@RequestParam(value = "p", defaultValue = "1") int p, Model model) {
		Page<Comment> commentPage = commentServ.getAll(p);
		model.addAttribute("comments", commentPage.getContent());
		model.addAttribute("page", commentPage);
		return "/BACK/content/comments/index";
	}

	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable(value = "id") int id, Model model) {
		Comment comment = commentServ.getOne(id);
		model.addAttribute("comment", comment);
		return "/BACK/content/comments/edit";
	}

	@RequestMapping("/update")
	public String update(Comment comment, RedirectAttributes attributes) {
		commentServ.update(comment);
		attributes.addFlashAttribute("comment",comment);
		attributes.addFlashAttribute("msg","已更新");
		return "redirect:/back/content/comments/" + comment.getId() + "/edit";
	}

	@RequestMapping("/{id}/delete")
	public String edit(@PathVariable(value = "id") int id, HttpServletRequest request, RedirectAttributes attributes) {
		String redirectUrl = request.getHeader("Referer");
		attributes.addFlashAttribute("msg", "已经删除");
		return "redirect:" + redirectUrl;
	}

}
