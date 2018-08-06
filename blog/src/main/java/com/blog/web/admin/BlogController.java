package com.blog.web.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.po.Blog;
import com.blog.po.User;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.blog.service.TypeService;
import com.blog.vo.BlogQuery;

@Controller
@RequestMapping("/admin")
public class BlogController {


	
	@Autowired
	private BlogService blogService;
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private TagService tagService;
	
	@GetMapping("/blogs")
	public String blogs(@PageableDefault(size=2,sort=("updateTime"),direction = Sort.Direction.DESC) Pageable pageable,
			BlogQuery blog, Model model) {
		  model.addAttribute("types", typeService.listType());
		model.addAttribute("page",blogService.listBlog(pageable, blog));
		return "admin/blogs";
	}
	

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
    		BlogQuery blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blogs :: blogList";
    }
    
    //新增页面
    @GetMapping("/blogs/input")
    public String input(Model model) {
    	//初始化
    	model.addAttribute("blog", new Blog());
    	model.addAttribute("types", typeService.listType());
    	model.addAttribute("tags", tagService.listTag());
    	return "admin/blogs-input";
    }
    
    @PostMapping("blogs")
    public String post(Blog blog,RedirectAttributes attributes,HttpSession session) {
    	blog.setUser((User) session.getAttribute("user"));
    	blog.setType(typeService.getType(blog.getType().getId()));
    	blog.setTags(tagService.listTag(blog.getTagIds()));
    	
    	Blog b = blogService.saveBlog(blog);
    	 if (b == null ) {
	            attributes.addFlashAttribute("message", "操作失败");
	        } else {
	            attributes.addFlashAttribute("message", "操作成功");
	        }
    	
    	return "redirect:/admin/blogs";
    }
    
    
    
    
  /* @GetMapping("/blogs/input")
    public String input(Model model) {
       setTypeAndTag(model);
       model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

  
    
	private void setTypeAndTag(Model model) {
		 model.addAttribute("types", typeService.listType());
	     model.addAttribute("tags", tagService.listTag());
	}*/

}