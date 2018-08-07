package com.blog.web.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.dao.TypeRepository;
import com.blog.po.Type;
import com.blog.service.TypeService;

@Controller
@RequestMapping("/admin")
public class TypeContorller {
	
	@Autowired
	private TypeService typeService;

	@GetMapping("/types")
	//@PageableDefault默认的参数，sort= {"id"},direction=Sort.Direction.DESC根据id倒叙排序
	public String types(@PageableDefault(size=6,
	sort= {"id"},direction=Sort.Direction.DESC) Pageable pageable,
			Model model) {//根据前端页面传递参数自动封装成Pageable对象，springboot的特性

		model.addAttribute("page",typeService.ListType(pageable));
		//分页查询
		//返回页面
		return "admin/types";	
	}
	
	@GetMapping("/types/input")
	public String input(Model model) {
		model.addAttribute("type",new Type());
		return "admin/types-input";
		
	}
	
	@GetMapping("/types/{id}/input")
	public String editInput(@PathVariable Long id,Model model) {//根据id来修改，用model来接收,@PathVariable才能把上面/types/{id}/input"的id接收进来
		model.addAttribute("type",typeService.getType(id));
		return "admin/types-input";
	}
	
	
	//操作提示
	@PostMapping("/types")
	//@Valid Type type,BindingResult result,一定要挨在一起
	public String post(@Valid Type type,BindingResult result, RedirectAttributes attributes) {//@Valid代表要校验type对象,BindingResult result接收结果
	
		Type type1 = typeService.getTypeByName(type.getName());
		
		if(type1 != null) {
			result.rejectValue("name", "nameError","不能重复添加分类");
		}
		
		if(result.hasErrors()) {
			return "admin/types-input";
		}
		
		Type t = typeService.saveType(type);
		if(t==null) {
			//保存不成功
			attributes.addFlashAttribute("message","新增失败");
		}else {
			//保存成功
			attributes.addFlashAttribute("message","新增成功");
		}
		return "redirect:/admin/types";
	}
	
	//修改的方法
	@PostMapping("/types/{id}")
	public String editPost(@Valid Type type,BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {//@Valid代表要校验type对象,BindingResult result接收结果
	
		Type type1 = typeService.getTypeByName(type.getName());
		
		if(type1 != null) {
			result.rejectValue("name", "nameError","不能重复添加分类");
		}
		
		if(result.hasErrors()) {
			return "admin/types-input";
		}
		
		Type t = typeService.updateType(id, type);
		if(t==null) {
			//保存不成功
			attributes.addFlashAttribute("message","更新失败");
		}else {
			//保存成功
			attributes.addFlashAttribute("message","更新成功");
		}
		return "redirect:/admin/types";
	}
	
	//删除的方法
	@GetMapping("/types/{id}/delete")
	public String delete(@PathVariable Long id,RedirectAttributes attributes) {
		typeService.deleteType(id);
		attributes.addFlashAttribute("message","删除成功");
		return "redirect:/admin/types";
	}
}
