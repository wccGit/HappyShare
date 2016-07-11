package com.example.zyh.myapplication.utils;

import com.example.zyh.myapplication.bean.MoviesBean;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by 小军 on 2016/7/8.
 */
public class ParseMovies {
	// result集合，类型为movie类型
	private List<MoviesBean> result;

	public List<MoviesBean> getResult() {
		return result;
	}

	public void setResult(List<MoviesBean> result) {
		this.result = result;
	}

	public ParseMovies(List<MoviesBean> result) {
		super();
		this.result = result;
	}

	public static List<MoviesBean> getRusults(String json) {

		return new Gson().fromJson(json, ParseMovies.class).getResult();
	}
}
