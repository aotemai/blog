package com.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.blog.po.Tag;

public interface TagService {

	//新增
	Tag saveTag(Tag tag);

	//根据id获取标签
	Tag getTag(Long id);
	
	//根据标签名字获取标签
	Tag getTagByName(String name);
	
	//分页查询!
	Page<Tag> listTag(Pageable pageable);
	
	List<Tag> listTag();
	
	List<Tag> listTag(String ids);//以字符串的形式获取多个id值
 
	//修改
	Tag updateTag(Long id,Tag type);
	
	//删除
	void deleteTag(Long id);
	
	 List<Tag> listTagTop(Integer size);
	
}
