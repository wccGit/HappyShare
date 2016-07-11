package com.example.zyh.myapplication.asynctask;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.example.zyh.myapplication.inter.GetDateCallback;
import com.example.zyh.myapplication.utils.HttpUtils;

/**
 * 获取网络数据的异步任务的工具类
 * */
public class GetDataAsyncTask extends AsyncTask<String, Void, String>{

	private GetDateCallback.GetDataCallback callback;
	private Context context;
	private ProgressDialog dialog;    //进度条对话框
	public GetDataAsyncTask(GetDateCallback.GetDataCallback callback, Context context) {
		super();
		this.callback = callback;
		this.context = context;
		dialog = new ProgressDialog(context);
		dialog.setTitle("提示信息");
		dialog.setMessage("正在加载中，请稍候...");
	}
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
//		dialog.show();     //把对话框显示出来
	}
	@Override
	protected String doInBackground(String... params) {
		return HttpUtils.getJsonContent(params[0]);
	}
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
//		dialog.dismiss();
		if (!TextUtils.isEmpty(result)) {
			callback.sendData(result);
		}
	}


}
