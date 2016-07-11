package com.example.zyh.myapplication.utils;

import com.example.zyh.myapplication.bean.PictureBean;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by 小军 on 2016/7/8.
 */
public class ParsePictureData {

	private List<PictureBean>result;
	public List<PictureBean> getResult() {
		return result;
	}
	public void setResult(List<PictureBean> result) {
		this.result = result;
	}
	public static List<PictureBean>parseData(String json){
		return new Gson().fromJson(json, ParsePictureData.class).getResult();
	}
}
