package xyz.springabc.web.back;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xyz.springabc.domin.User;
import xyz.springabc.error.ValidateError;
import xyz.springabc.repository.UserRepo;
import xyz.springabc.service.UploadFileServ;
import xyz.springabc.service.UserServ;

@Controller
@RequestMapping("/back/users")
public class UserAndAdminC {

	@Autowired
	private UserServ userServ;
	
	@Autowired
	private UploadFileServ uploadFileServ;
	
	@RequestMapping("/admin")
	public String admin(@RequestParam(value="p",defaultValue="1") int p,
			Model model){
		Page<User> userPage=userServ.getByRole(UserServ.ROLE_ADMIIN,p);
		model.addAttribute("users",userPage.getContent());
		model.addAttribute("page",userPage);
		return "/BACK/users/admin";
	}
	
	@RequestMapping("/all")
	public String all(@RequestParam(value="p",defaultValue="1") int p,
			Model model){
		Page<User> userPage=userServ.getAll(p);
		model.addAttribute("users",userPage.getContent());
		model.addAttribute("page",userPage);
		return "/BACK/users/all";
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam(value="p",defaultValue="1") int p, String someThing,Model model){
		Page<User> userPage=userServ.getByNickLike(someThing,p);
		model.addAttribute("users",userPage.getContent());
		model.addAttribute("page",userPage);
		return "/BACK/users/all";
	}
	
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id,
			Model model){
		User user=userServ.getByUserId(id);
		model.addAttribute("user",user);
		return "/BACK/users/edit";
	}
	
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updateInfomatio(@Validated User user,
			Errors errors,
			MultipartFile file,
			HttpServletRequest request,
			Model model){
		User oldUser=(User)request.getSession().getAttribute("user");
		String avatar="";
		if(!file.isEmpty()){//判断有没有上传了头像
			try {
				avatar=uploadFileServ.upload(file).url;
				avatar=oldUser.getAvatar();//没有上传新头像就用回原来的
			} catch (Exception e1) {
				model.addAttribute("error","头像上传失败");
				return "/BACK/users/edit";
			}
		}
		avatar=oldUser.getAvatar();//没有上传新头像就用回原来的
		user.setAvatar(avatar);
		User newUser = userServ.update(oldUser,user,errors);
		if(errors.hasErrors()){
			model.addAttribute("error",errors.getAllErrors());
			return "/BACK/users/edit";
		}	
		request.getSession().setAttribute("user", newUser);
		model.addAttribute("msg","信息已更新");
		return "/BACK/users/edit";
	}
	
	@RequestMapping("/admin/add")
	public String add(String nick,
			RedirectAttributes attributes){
		User user=userServ.getUserByNickOrEmailOrUsername(nick);
		if(user==null){
			attributes.addFlashAttribute("error","用户不存在");
		}else{
			userServ.changeRole(user, UserServ.ROLE_ADMIIN);
			attributes.addFlashAttribute("msg","用户已添加");
		}
		return "redirect:/back/users/admin";
	}
	
	@RequestMapping("/admin/{id}/remove")
	public String remove(@PathVariable("id") int id,RedirectAttributes attributes){
		User user=userServ.getByUserId(id);
		userServ.changeRole(user, UserServ.ROLE_MENBER);
		return "redirect:/back/users/admin";
	}
}
