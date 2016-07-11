package com.example.zyh.myapplication.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zyh.myapplication.R;
import com.example.zyh.myapplication.asynctask.GetDataAsyncTask;
import com.example.zyh.myapplication.bean.ArticleBean;
import com.example.zyh.myapplication.bean.ArticleItemBean;
import com.example.zyh.myapplication.content.ContentUtils;
import com.example.zyh.myapplication.db.DBOpenHelper;
import com.example.zyh.myapplication.inter.GetDateCallback;

/**
 * Created by 小军 on 2016/7/8.
 */
public class ArticleItemFragment extends Fragment {
	private TextView titleTv,authorTv,timesTv,textTv,summaryTv,author2Tv,occputionTv;
	private ArticleBean bean ;
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	private String pid;
	private ArticleItemBean itemBean;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		helper = new DBOpenHelper(getActivity(), "ten.db", null, 1);
		db = helper.getWritableDatabase();
		Bundle bundle = getArguments();
		bean = (ArticleBean) bundle.getSerializable("article");
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_article_item, null);
		initComponent(view);

		//网络获取数据
		loadData();
		return view;
	}
   /*
    *  以后：对Fragment中的视图，将值设置到控件上，都书写在onActivityCreated中，可以万无一失。
	* （原因：能回调该方法，视图都已经加载完毕了）
    */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);



	}
   /*
    * 控件初始化
    */
	private void initComponent(View view){
		titleTv = (TextView)view.findViewById(R.id.id_article_title);
		authorTv = (TextView)view.findViewById(R.id.id_article_author);
		timesTv = (TextView)view.findViewById(R.id.id_article_times);
		summaryTv = (TextView)view.findViewById(R.id.id_article_summary);
		author2Tv = (TextView)view.findViewById(R.id.id_article_author2);
		textTv = (TextView)view.findViewById(R.id.id_article_text);
		occputionTv = (TextView)view.findViewById(R.id.id_article_authorbrief);
	}
	
	//网络加载数据的方法
	private void loadData(){
		pid = bean.getId();
		Cursor cursor = queryDataFromDB(pid);
		if (cursor!=null&&cursor.getCount()!=0) {
			if (cursor.moveToFirst()) {
				String content = cursor.getString(cursor.getColumnIndex("content"));
				itemBean = ArticleItemBean.parseData(content);
				setView(itemBean);
			}
		}else {

			new GetDataAsyncTask(new GetDateCallback.GetDataCallback() {

				@Override
				public void sendData(String json) {

					if (!TextUtils.isEmpty(json)) {
						insertDataToDB(pid, json);
						itemBean = ArticleItemBean.parseData(json);
						setView(itemBean);
					}
				}
			}, getActivity()).execute(ContentUtils.ARTICLE_DETATLS_URL + pid);
		}
		
	}
	/*
	 * 设置控件内容
	 */
	private void setView(ArticleItemBean itemBean){
		titleTv.setText(itemBean.getTitle());
		timesTv.setText("阅读量:"+itemBean.getTimes());
		authorTv.setText("作者:"+itemBean.getAuthor());
		author2Tv.setText(itemBean.getAuthor());
		occputionTv.setText(itemBean.getAuthorbrief());
		summaryTv.setText(itemBean.getSummary());
		textTv.setText(itemBean.getText());
	}
	//向数据库当中插入数据
	private void insertDataToDB(String id,String content){
	
			ContentValues values = new ContentValues();
			values.put("_id", id);
			values.put("content", content);
			db.insert("arttb", null, values);
	}
	
	//根据id获取数据库文章表中的内容
	private Cursor queryDataFromDB(String id){
		
		return db.query("arttb", null, "_id=?", new String[]{id}, null, null, null, null);
	}
}
