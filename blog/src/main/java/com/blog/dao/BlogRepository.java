package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.blog.po.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long>,JpaSpecificationExecutor<Blog>{
	
	
	
}
