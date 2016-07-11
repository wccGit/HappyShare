package com.example.zyh.myapplication.utils;

import com.example.zyh.myapplication.bean.ArticleBean;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by 小军 on 2016/7/8.
 */
public class ParseArticleData {

	private List<ArticleBean>result;
	
	public List<ArticleBean> getResult() {
		return result;
	}
	public void setResult(List<ArticleBean> result) {
		this.result = result;
	}
	
	public static List<ArticleBean>parseData(String json){
		return new Gson().fromJson(json, ParseArticleData.class).getResult();
	}
}
