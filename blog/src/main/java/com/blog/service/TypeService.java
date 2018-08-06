package com.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.blog.po.Type;

public interface TypeService {

	//新增
	Type saveType(Type type);
	//根据id查询
	Type getType(Long id);
	//根据名称来查询
	Type getTypeByName(String name);
	//分页查询
	Page<Type> ListType(Pageable pageable);
	

    List<Type> listType();
    
	//修改
	Type updateType(Long id,Type type);//根据id修改，返回一个type
	//删除
	void deleteType(Long id);//根据id删除，无返回类型
	
	List<Type> listTypeTop(Integer size);
	
}
