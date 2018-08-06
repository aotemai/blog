package com.blog.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.TagRepository;
import com.blog.po.Tag;
import com.blog.service.TagService;

import javassist.NotFoundException;

@Service
public class TagServiceImpl implements TagService{


	@Autowired
	private TagRepository tagRepository;
	
	@Transactional
	@Override
	public Tag saveTag(Tag tag) {
		// TODO Auto-generated method stub
		return tagRepository.save(tag);
	}

	@Transactional
	@Override
	public Tag getTag(Long id) {
		// TODO Auto-generated method stub
		return tagRepository.findOne(id);
	}

	@Transactional
	@Override
	public Tag getTagByName(String name) {
		// TODO Auto-generated method stub
		return tagRepository.findByName(name);
	}

	@Transactional
	@Override
	public Page<Tag> listTag(Pageable pageable) {
		// TODO Auto-generated method stub
		return tagRepository.findAll(pageable);
	}

	@Transactional
	@Override
	public Tag updateTag(Long id, Tag type) {//根据id和类型去更新
		// TODO Auto-generated method stub
		Tag t = tagRepository.findOne(id);
		if(t == null) {
			try {
				throw new NotFoundException("不存在该标签");
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//否则吧type的值复制到t里面
		BeanUtils.copyProperties(type, t);
		return tagRepository.save(t);
	}
	
	@Transactional
	@Override
	public void deleteTag(Long id) {
		// TODO Auto-generated method stub
		tagRepository.delete(id);
		
	}

	@Transactional
	@Override
	public List<Tag> listTag() {
		// TODO Auto-generated method stub
		return tagRepository.findAll();
	}

	@Transactional
	@Override
	public List<Tag> listTag(String ids) {//1,2,3要把id转化成数组
	     return tagRepository.findAll(convertToList(ids));
	     
	    }
	     //1,2,3要把id转化成数组
	    private List<Long> convertToList(String ids) {
	        List<Long> list = new ArrayList<>();
	        if (!"".equals(ids) && ids != null) {
	            String[] idarray = ids.split(",");
	            for (int i=0; i < idarray.length;i++) {
	                list.add(new Long(idarray[i]));
	            }
	        }
	        return list;
	    }
	    
	    
}
