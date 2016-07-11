package com.example.zyh.myapplication.bean;

import com.google.gson.Gson;

public class PictureItemBean {

	    private String id ;// 10519,
	    private String title ;//  山寺桃花始盛开 ,
	    private String author ;//  Osamu Shibata ,
	    private String authorbrief ;//  来自日本画家Osamu Shibata ,
	    private String text1 ;//  人间四月芳菲尽，山寺桃花始盛开。长恨春归无觅处，不知转入此中来。 ,
	    private String image1 ;//  images/639DA1ADDED5BA53920A465CD1F2F7AC.jpg ,
	    private String text2 ;//  —白居易《大林寺桃花》 ,
	    private String times ;// 4814,
	    private String publishtime ;// 635989536000000000,
	    private String status ;// 0,
	    private String errMsg ;// null
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
		public String getText1() {
			return text1;
		}
		public void setText1(String text1) {
			this.text1 = text1;
		}
		public String getImage1() {
			return image1;
		}
		public void setImage1(String image1) {
			this.image1 = image1;
		}
		public String getText2() {
			return text2;
		}
		public void setText2(String text2) {
			this.text2 = text2;
		}
		public String getTimes() {
			return times;
		}
		public void setTimes(String times) {
			this.times = times;
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
		public PictureItemBean(String id, String title, String author,
				String authorbrief, String text1, String image1, String text2,
				String times, String publishtime, String status, String errMsg) {
			super();
			this.id = id;
			this.title = title;
			this.author = author;
			this.authorbrief = authorbrief;
			this.text1 = text1;
			this.image1 = image1;
			this.text2 = text2;
			this.times = times;
			this.publishtime = publishtime;
			this.status = status;
			this.errMsg = errMsg;
		}
		public PictureItemBean() {
			super();
		}
		public static PictureItemBean parseData(String json){
			return new Gson().fromJson(json, PictureItemBean.class);
		}
}
