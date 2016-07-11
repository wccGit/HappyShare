package com.example.zyh.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zyh.myapplication.R;
import com.example.zyh.myapplication.adapter.MyFragmentAdapter;
import com.example.zyh.myapplication.asynctask.GetDataAsyncTask;
import com.example.zyh.myapplication.bean.PictureBean;
import com.example.zyh.myapplication.content.ContentUtils;
import com.example.zyh.myapplication.inter.GetDateCallback;
import com.example.zyh.myapplication.utils.ParsePictureData;
import com.example.zyh.myapplication.utils.SDCardHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小军 on 2016/7/8.
 */
public class PictureFragment extends Fragment {
	private ViewPager vp_top_style_id;
	private List<Fragment>fragments;

//	private int tabIndex;// 便签索引值
//	private String beautifulPicture;// 标签名
//	private Activity context;
	@Override
	public void onCreate(Bundle savedInstanceState) {
//		Bundle arguments = getArguments();
//		tabIndex = arguments.getInt("tabIndex", 0);
//		beautifulPicture = arguments.getString("beautifulPicture", "");
//
//		context = getActivity();
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_style, null);
		vp_top_style_id = (ViewPager)view.findViewById(R.id.vp_contain_id);
		fragments = new ArrayList<Fragment>();
		loadData();
		return view;
	}
	
	private void loadData(){
		new GetDataAsyncTask(new GetDateCallback.GetDataCallback() {
			@Override
			public void sendData(String json) {
				if (!TextUtils.isEmpty(json)) {
					SDCardHelper.saveFileToSDCardCacheDir(json.getBytes(), "picture.txt", getActivity());
					List<PictureBean>list = ParsePictureData.parseData(json);
					initPager(list);
				}else {
					//网络没有获取到数据，就去读本地的数据
					String filePath = getActivity().getExternalCacheDir().getAbsolutePath()+File.separator+"picture.txt";
					byte[]data = SDCardHelper.loadFileFromSDCard(filePath);
					if (data!=null&&data.length!=0) {
						String result = new String(data, 0, data.length);
						List<PictureBean>list = ParsePictureData.parseData(json);
						initPager(list);
					}
				}
				
			}
		}, getActivity()).execute(ContentUtils.PICTURE_URL);
	}
	private void initPager(List<PictureBean>list){
		for (int i = 0; i < list.size(); i++) {
			Fragment f = new PictureItemFragment();
			Bundle bundle = new Bundle();
			bundle.putSerializable("picture", list.get(i));
			f.setArguments(bundle);
			fragments.add(f);
		}
		
		MyFragmentAdapter adapter = new MyFragmentAdapter(getChildFragmentManager(), fragments);
		vp_top_style_id.setAdapter(adapter);
	}
	
}
