package com.example.zyh.myapplication.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 获取http请求的工具类
 *
 * Created by 小军 on 2016/7/8.
 */
public class HttpUtils {
	/**
	 * 判断获取网络状态是否联通
	 * @param context
	 * @return
	 */
	public static boolean isNetWorkConn(Context context){
		boolean bl=false;
		ConnectivityManager manager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info=manager.getActiveNetworkInfo();
		if(info!=null){
			return info.isConnected();
		}
		return bl;
	}
	/**
	 * 通过HttpURLConnection获得字符串数据
	 * @param ：params--->网络请求地址
	 * 
	 * @return  返回请求获得字符串数据
	 * */
	public static String getJsonContent(String params){
		String result = "";
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			URL url = new URL(params);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			is = connection.getInputStream();
			isr = new InputStreamReader(is,"utf-8");
			br = new BufferedReader(isr);
			String line = "";
			while ((line=br.readLine())!=null) {
				result+=line;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (isr!=null) {
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	/**
	 * 通过HttpURLConnection获得字节数组
	 * @param ：params--->网络请求地址
	 * 
	 * @return  返回请求获得字节数组
	 * */
	public static byte[] getByteContent(String params){
		BufferedInputStream bis = null;
		try {
			URL url = new URL(params);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			 bis = new BufferedInputStream(connection.getInputStream());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte buffer[] = new  byte[1024*8];
			int len = 0;
			while ((len = bis.read(buffer))!=-1) {
				baos.write(buffer,0,len);
				baos.flush();
			}
			byte data[] = baos.toByteArray();
			return data;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (bis!=null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	/**
	 * 通过HttpURLConnection获得位图Bitmap对象
	 * @param ：params--->网络请求地址
	 * 
	 * @return  返回请求获得位图Bitmap对象
	 * */
	public static byte[] getImageContent(String params){
		//获取网络图片
		URL url;
		Bitmap bm = null;
		try {
			url = new URL(params);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream is = connection.getInputStream();
//			BufferedInputStream bis = new BufferedInputStream(is);
			//能够把输入字节流转化为bitmap对象。
			//bitmap-->位图，可以直接显示在imageview当中
//			 bm = BitmapFactory.decodeStream(bis);
			 //关闭流，最好写在finally当中
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte buffer[] = new  byte[1024*8];
			int len = 0;
			while ((len = is.read(buffer))!=-1) {
				baos.write(buffer,0,len);
				baos.flush();
			}
			byte data[] = baos.toByteArray();
			return data;
//			baos.close();
//			 bis.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
