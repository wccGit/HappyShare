package com.example.zyh.myapplication.bean;

import java.io.Serializable;

public class ArticleBean implements Serializable{

    private String  id ; 
    private String  type ; 
    private String  publishtime ; 
    private String  title ;  
    private String  summary ; 
    private String  image ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public ArticleBean(String id, String type, String publishtime,
			String title, String summary, String image) {
		super();
		this.id = id;
		this.type = type;
		this.publishtime = publishtime;
		this.title = title;
		this.summary = summary;
		this.image = image;
	}
	public ArticleBean() {
		super();
	}   
    
    
}
