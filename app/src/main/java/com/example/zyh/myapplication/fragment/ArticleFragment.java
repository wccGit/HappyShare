package com.example.zyh.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zyh.myapplication.R;
import com.example.zyh.myapplication.adapter.MyFragmentAdapter;
import com.example.zyh.myapplication.asynctask.GetDataAsyncTask;
import com.example.zyh.myapplication.bean.ArticleBean;
import com.example.zyh.myapplication.content.ContentUtils;
import com.example.zyh.myapplication.inter.GetDateCallback;
import com.example.zyh.myapplication.utils.ParseArticleData;
import com.example.zyh.myapplication.utils.SDCardHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小军 on 2016/7/8.
 */
public class ArticleFragment extends Fragment {

	private ViewPager vp_contain_id;
	private List<Fragment>fragments;

	//	private int tabIndex;// 便签索引值
//	private String beautifulMovie;// 标签名
//	private Activity context;
    /*
     * 界面初始化
     */
	@Override
	public void onCreate(Bundle savedInstanceState) {
//		Bundle arguments = getArguments();
//		tabIndex = arguments.getInt("tabIndex", 0);
//		beautifulMovie = arguments.getString("beautifulMovie", "");
//
//		context = getActivity();
//		vp_contain_id.setOnTouchListener(new myOnTounchListener());
		super.onCreate(savedInstanceState);


	}

    /*
     * 布局设置
     */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_style, null);
		vp_contain_id = (ViewPager) view.findViewById(R.id.vp_contain_id);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fragments = new ArrayList<>();
		//加载数据的操作
		loadData();


	}


	private void loadData(){
		new GetDataAsyncTask(new GetDateCallback.GetDataCallback() {

			@Override
			public void sendData(String json) {
				if (!TextUtils.isEmpty(json)) {
					SDCardHelper.saveFileToSDCardCacheDir(json.getBytes(), "article.txt", getActivity());
					List<ArticleBean>list = ParseArticleData.parseData(json);
					initPager(list);
				}else {
					//网络没有获取到数据，就去读本地的数据
					String filePath = getActivity().getExternalCacheDir().getAbsolutePath() + File.separator + "article.txt";
					byte[] data = SDCardHelper.loadFileFromSDCard(filePath);
					if (data != null && data.length != 0) {
						String result = new String(data, 0, data.length);
						List<ArticleBean> list = ParseArticleData.parseData(result);
						initPager(list);
					}
				}
			}
		}, getActivity()).execute(ContentUtils.ARTICLE_URL);
	}
	
	private void initPager(List<ArticleBean>list){
		for (int i = 0; i < list.size(); i++) {
			Log.i("Tag","到底有多长？"+list.size());
			Fragment f = new ArticleItemFragment();
			Bundle bundle = new Bundle();
			bundle.putSerializable("article", list.get(i));
			f.setArguments(bundle);
			fragments.add(f);
		}
		
		MyFragmentAdapter adapter = new MyFragmentAdapter(getChildFragmentManager(), fragments);
		vp_contain_id.setAdapter(adapter);
	}

//	private class myOnTounchListener implements View.OnTouchListener {
//		/*
//		 * 实现OnTounchListener方法，解决子View与父View的触摸冲突。
//		 */
//		@Override
//		public boolean onTouch(View view, MotionEvent motionEvent) {
//
//
//			return false;
//		}
//	}
}
