package com.blog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.blog.po.Type;
import com.blog.service.BlogService;
import com.blog.service.TypeService;
import com.blog.vo.BlogQuery;

@Controller
public class TypeShowController {

	 @Autowired
	    private TypeService typeService;

	 @Autowired
	    private BlogService blogService;
	    
	    @GetMapping("/types/{id}")
	    public String types(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
	                        @PathVariable Long id, Model model) {//@PathVariable传id的时候要注意加上这个注解，否则id接收不到
	        List<Type> types = typeService.listTypeTop(10000);//传10000个标签就相当于全局查询了，标签不可能有那么多
	        if (id == -1) {//如果是-1就说明是从导航里面点击进来的
	           id = types.get(0).getId();
	        }
	        BlogQuery blogQuery = new BlogQuery();
	        blogQuery.setTypeId(id);
	        model.addAttribute("types", types);
	        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
	        model.addAttribute("activeTypeId", id);
	        return "types";
	    }
	}

