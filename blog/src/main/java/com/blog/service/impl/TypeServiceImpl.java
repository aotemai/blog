package com.blog.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.dao.TypeRepository;
import com.blog.po.Type;
import com.blog.service.TypeService;

import javassist.NotFoundException;

@Service
public class TypeServiceImpl implements TypeService{

	@Autowired
	private TypeRepository typeRepository;
	
	@Transactional
	@Override
	public Type saveType(Type type) {
		// TODO Auto-generated method stub
		return typeRepository.save(type);
	}

	@Transactional
	@Override
	public Type getType(Long id) {//分页查询
		// TODO Auto-generated method stub
		return typeRepository.findOne(id);
	}

	@Transactional
	@Override
	public Page<Type> ListType(Pageable pageable) {
		// TODO Auto-generated method stub
		return typeRepository.findAll(pageable);
	}

	@Transactional
	@Override
	public Type updateType(Long id, Type type) {
		// TODO Auto-generated method stub
		Type t = typeRepository.findOne(id);
		//如果id为空的话就抛出异常
		if(t==null) {
			try {
				throw new NotFoundException("不存在该类型");
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//否则把type的值复制到t里面
		BeanUtils.copyProperties(type, t);
		return typeRepository.save(t);
		
	}

	@Transactional
	@Override
	public void deleteType(Long id) {
		typeRepository.delete(id);
	}

	@Override
	public Type getTypeByName(String name) {
		// TODO Auto-generated method stub
		return typeRepository.findByName(name);
	}

	@Override
	public List<Type> listType() {
		// TODO Auto-generated method stub
		  return typeRepository.findAll();
	}

	//按大小排序
	@Override
	public List<Type> listTypeTop(Integer size) {		
		Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");		
		Pageable pageable = new PageRequest(0,size,sort);	
		return typeRepository.findTop(pageable);
	}

}
