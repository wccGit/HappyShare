package com.example.zyh.myapplication.bean;

import com.google.gson.Gson;

public class ArticleItemBean {

	private String id;
	private String title;
	private String times;
	private String author;
	private String authorbrief;
	private String summary;
	private String text;
	private String image;
	private String publishtime;
	private String status;
	private String errMsg;
	
	
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthorbrief() {
		return authorbrief;
	}
	public void setAuthorbrief(String authorbrief) {
		this.authorbrief = authorbrief;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public ArticleItemBean(String id, String title, String author,
			String authorbrief, String summary, String text, String image,
			String publishtime, String status, String errMsg) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.authorbrief = authorbrief;
		this.summary = summary;
		this.text = text;
		this.image = image;
		this.publishtime = publishtime;
		this.status = status;
		this.errMsg = errMsg;
	}
	public ArticleItemBean() {
		super();
	}
	
	
	public static ArticleItemBean parseData(String json){
		return new Gson().fromJson(json, ArticleItemBean.class);
	}
}
