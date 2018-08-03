package com.blog.po;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="t_type")
public class Type {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message="分类名称不能为空")
	private String name;
	
	//一的一方作为关系被维护端
	@OneToMany(mappedBy="type")//和Blog里面的type创建联系
	private List<Blog> blogs = new ArrayList<>();//将博客对象放在一个集合里面和分类关联起来
	
	public Type() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	@Override
	public String toString() {
		return "Type [id=" + id + ", name=" + name + ", blogs=" + blogs + "]";
	}

	
	
	
}
