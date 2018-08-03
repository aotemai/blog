package com.blog.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity//声明实体类
@Table(name="t_comment")//指定对应数据库的名字
public class Comment {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nickname;
	private String email;
	private String content;
	private String avatar;//头像
	@Temporal(TemporalType.TIMESTAMP)//这个需要注意时间要添加注解来和数据库关联
	private Date creatTime;
	
	@ManyToOne
	private Blog blog;
	
	public List<Comment> getReplayComments() {
		return replayComments;
	}

	public void setReplayComments(List<Comment> replayComments) {
		this.replayComments = replayComments;
	}

	public Comment getParentComment() {
		return parentComment;
	}

	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}

	//评论的关联关系
	@OneToMany(mappedBy="parentComment")
	private List<Comment> replayComments = new ArrayList<>();
	
	@ManyToOne
	private Comment parentComment;//子类评论，只能有一个去评论的父类。
	
	
	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Comment() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", nickname=" + nickname + ", email=" + email + ", content=" + content
				+ ", avatar=" + avatar + ", creatTime=" + creatTime + "]";
	}
	
	
}
