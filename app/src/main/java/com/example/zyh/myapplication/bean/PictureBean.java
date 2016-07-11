package com.example.zyh.myapplication.bean;

import java.io.Serializable;

public class PictureBean implements Serializable{
	 private String id ;// 10519,
	 private String type ;// 3,
	 private String publishtime ;// 635989536000000000,
	 private String title ;//  山寺桃花始盛开 ,
	 private String summary ;//  人间四月芳菲尽，山寺桃花始盛开。长恨春归无觅处，不知转入此中来。 ,
	 private String image ;//  images/639DA1ADDED5BA53920A465CD1F2F7AC.jpg 
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
	public PictureBean(String id, String type, String publishtime,
			String title, String summary, String image) {
		super();
		this.id = id;
		this.type = type;
		this.publishtime = publishtime;
		this.title = title;
		this.summary = summary;
		this.image = image;
	}
	public PictureBean() {
		super();
	}
}
