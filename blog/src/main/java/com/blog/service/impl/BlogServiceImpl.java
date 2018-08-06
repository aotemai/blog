package com.blog.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.BlogRepository;
import com.blog.po.Blog;
import com.blog.po.Type;
import com.blog.service.BlogService;
import com.blog.vo.BlogQuery;

import javassist.NotFoundException;

@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogRepository blogRepository;
	
	@Transactional
	@Override
	public Blog getBlog(Long id) {
		// TODO Auto-generated method stub
		return blogRepository.findOne(id);
	}

	@Transactional
	@Override//分页动态查询，条件查询
	public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
		
		return blogRepository.findAll(new Specification<Blog>() {
			
			@Override//参数分析：1查询Blog对象，2查询条件容器，3具体条件的表达式,模糊查询
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				//将查询条件封装在list集合里面
				List<Predicate> predicate = new ArrayList<>();
				if(!"".equals(blog.getTitle())&&blog.getTitle()!=null) {
					predicate.add(cb.like(root.<String>get("title"), "%"+blog.getTitle()+"%"));//"%"+blog.getTitle()+"%"赋值给前面那个参数
				}
				if(blog.getTypeId()!=null) {
					predicate.add(cb.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
				}
				if(blog.isRecommend()) {
					predicate.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
				}
				cq.where(predicate.toArray(new Predicate[predicate.size()]));
				return null;//直接返回null的时候springboot会自动分配
			}
		}, pageable);
		
	}

	@Transactional
	@Override
	public Blog saveBlog(Blog blog) {
		blog.setCreatTime(new Date());
		blog.setUpdateTime(new Date());
		blog.setViews(0);
		return blogRepository.save(blog);
	}

	@Transactional
	@Override
	public Blog updateBlog(Long id, Blog blog) {
		
		Blog b = blogRepository.findOne(id);
		if(b==null) {
			try {
				throw new NotFoundException("该博客不存在");
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		BeanUtils.copyProperties(b, blog);
		return blogRepository.save(b);
	}

	@Transactional
	@Override
	public void deleteBlog(Long id) {
		
		blogRepository.delete(id);
		
	}

}
