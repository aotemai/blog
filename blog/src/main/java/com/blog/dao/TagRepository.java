package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.po.Tag;

public interface TagRepository extends JpaRepository<Tag,Long> {

	Tag findByName(String name);
}
