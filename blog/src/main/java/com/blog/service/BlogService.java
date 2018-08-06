package com.blog.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.blog.po.Blog;
import com.blog.vo.BlogQuery;

public interface BlogService {
	
	Blog getBlog(Long id);
	
	 Page<Blog> listBlog(Pageable pageable);
	
	//分页查询
	Page<Blog> listBlog(Pageable pageable,BlogQuery blog);
	
	Blog saveBlog(Blog blog);
	
	Blog updateBlog(Long id,Blog blog);//先根据id查询出来再将更新的内容放到blog对象里面
	
	void deleteBlog(Long id);
	
	 List<Blog> listRecommendBlogTop(Integer size);
	 
	 Page<Blog> listBlog(String query,Pageable pageable);
	 
	 Blog getAndConvert(Long id);
	 
	   Page<Blog> listBlog(Long tagId,Pageable pageable);
	   
	   Map<String,List<Blog>> archiveBlog();

	    Long countBlog();
}
