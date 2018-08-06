package com.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.blog.po.Blog;
import com.blog.vo.BlogQuery;

public interface BlogService {
	
	Blog getBlog(Long id);
	
	//分页查询
	Page<Blog> listBlog(Pageable pageable,BlogQuery blog);
	
	Blog saveBlog(Blog blog);
	
	Blog updateBlog(Long id,Blog blog);//先根据id查询出来再将更新的内容放到blog对象里面
	
	void deleteBlog(Long id);
}
