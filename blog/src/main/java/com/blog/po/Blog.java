package com.blog.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity//声明实体类
@Table(name="t_blog")//指定对应数据库的名字
public class Blog {

	@Id//主键
	@GeneratedValue//生成策略
	private Long id;
	
	private String title;
	private String content;// 内容
	private String firstPicture;
	private String flag;// 标记,,例如转载、原创
	private Integer views;// 游览次数
	private boolean appreciation;
	private boolean shareStatement;
	private boolean commentabled;
	private boolean published;
	private boolean recommend;
	@Temporal(TemporalType.TIMESTAMP)//这个需要注意时间要添加注解来和数据库关联
	private Date creatTime;
	@Temporal(TemporalType.TIMESTAMP)//这个需要注意时间要添加注解来和数据库关联
	private Date updateTime;

	@ManyToOne
	private Type type;
	
	@ManyToOne
	private User user;
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(mappedBy="blog")
	private List<Comment> comments = new ArrayList<>();
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToMany(cascade= {CascadeType.PERSIST})//级联关系,新增一个博客的时候连同tags标签也新增进去
	private List<Tag> tags = new ArrayList<>();
	
	public Blog() {

	}

	public Long getId() {
		return id;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFirstPicture() {
		return firstPicture;
	}

	public void setFirstPicture(String firstPicture) {
		this.firstPicture = firstPicture;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public boolean isAppreciation() {
		return appreciation;
	}

	public void setAppreciation(boolean appreciation) {
		this.appreciation = appreciation;
	}

	public boolean isShareStatement() {
		return shareStatement;
	}

	public void setShareStatement(boolean shareStatement) {
		this.shareStatement = shareStatement;
	}

	public boolean isCommentabled() {
		return commentabled;
	}

	public void setCommentabled(boolean commentabled) {
		this.commentabled = commentabled;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public boolean isRecommend() {
		return recommend;
	}

	public void setRecommend(boolean recommend) {
		this.recommend = recommend;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", firstPicture=" + firstPicture
				+ ", flag=" + flag + ", views=" + views + ", appreciation=" + appreciation + ", shareStatement="
				+ shareStatement + ", commentabled=" + commentabled + ", published=" + published + ", recommend="
				+ recommend + ", creatTime=" + creatTime + ", updateTime=" + updateTime + ", type=" + type + "]";
	}


}
