package com.blog.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.blog.po.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long>,JpaSpecificationExecutor<Blog>{
	
	//查询推荐的博客
	 @Query("select b from Blog b where b.recommend = true")
	List<Blog> findTop(Pageable pageable);
	 
	 @Query("select b from Blog b where b.title like ?1 or b.content like ?1")// ?1代表第一个参数
	    Page<Blog> findByQuery(String query,Pageable pageable);
	 
	 @Transactional
	    @Modifying//修改的注解
	    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
	    int updateViews(Long id);
	 
	 @Query("select function('date_format',b.updateTime,'%Y-%m') as year from Blog b group by function('date_format',b.updateTime,'%Y-%m') order by year desc ")
	    List<String> findGroupYear();

	    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y-%m') = ?1")
	    List<Blog> findByYear(String year);
}
