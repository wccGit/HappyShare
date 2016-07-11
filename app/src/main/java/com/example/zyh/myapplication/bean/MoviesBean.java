package com.example.zyh.myapplication.bean;

import java.io.Serializable;

public class MoviesBean implements Serializable {

	private String id;// 10508,
	private String type;// 1,
	private String publishtime;// 635989536000000000,
	private String title;// 《二十四城记》我不是怀旧，我是要记得 ,
	private String summary;// 今晚推荐一部与成都城市有关的电影《》评分7.4分。\r\n\r\n贾樟柯2008年的《二十四城记》初衷就很明确，要做一部关于成都现代化变迁的记录。他找来成都最著名的女诗人翟永明合作编剧，以成都420工厂的拆迁为由头，通过多段访谈的形式，对这座城市的现代化进程做出了一次影像思考。贾樟柯把成都当成了自己的汾阳，特别会找切入点，但缺乏一份热爱。与其说是一封城市情书，不如说是一纸不错的报告。倒是李玉导演的《观音山》，虽然只是部分在成都取景，但出色地抓住了这座城市躁动迷离的一面。
							// ,
	private String image;// images/EAFC

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

	public MoviesBean(String id, String type, String publishtime, String title,
			String summary, String image) {
		super();
		this.id = id;
		this.type = type;
		this.publishtime = publishtime;
		this.title = title;
		this.summary = summary;
		this.image = image;
	}

	public MoviesBean() {
		super();
		// TODO Auto-generated constructor stub
	}

}
