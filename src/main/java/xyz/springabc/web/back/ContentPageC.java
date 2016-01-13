package xyz.springabc.web.back;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xyz.springabc.domin.ThePage;
import xyz.springabc.service.ThePageServ;

@Controller
@RequestMapping("/back/content/pages")
public class ContentPageC {

	@Autowired
	private ThePageServ thePageServ;
	
	@RequestMapping("")
	public String index(@RequestParam(value="p",defaultValue="1") int p,Model model){
		org.springframework.data.domain.Page<ThePage> thePagePage=thePageServ.getPage(p);
		model.addAttribute("page",thePagePage);
		model.addAttribute("pages",thePagePage.getContent());
		return "/BACK/content/pages/index";
	}
	
	@RequestMapping("/create")
	public String create(){
		return "/BACK/content/pages/create";
	}
	
	@RequestMapping("/save")
	public String save(ThePage thePage,RedirectAttributes attributes){
		thePageServ.save(thePage);
		attributes.addFlashAttribute("thePage",thePage);
		attributes.addFlashAttribute("msg","已经保存");
		return "redirect:/back/content/pages/"+thePage.getId()+"/edit";
	}
	
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id,Model model){
		model.addAttribute("thePage",thePageServ.getOne(id));
		return "/BACK/content/pages/edit";
	}
	
	@RequestMapping("/update")
	public String update(@Valid @ModelAttribute ThePage thePage,Errors result,Model model){
		if(result.hasErrors()){
			model.addAttribute("error",result.getAllErrors());
			return "/BACK/content/pages/edit";
		}else{
			thePageServ.update(thePage);
			model.addAttribute("msg","已经更改");
			return "/BACK/content/pages/edit";
		}
	}
	
	
	@RequestMapping("/{id}/delete")
	@ResponseBody
	public boolean delete(@PathVariable("id") int id){
		thePageServ.delete(id);
		return true;
	}
}
