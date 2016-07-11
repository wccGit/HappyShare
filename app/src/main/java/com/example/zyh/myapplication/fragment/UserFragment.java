package com.example.zyh.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zyh.myapplication.R;

/**
 * Created by 小军 on 2016/7/8.
 */
public class UserFragment extends Fragment {
//	private int tabIndex;// 便签索引值
//	private String beautifulUser;// 标签名
//	private Activity context;
	@Override
	public void onCreate(Bundle savedInstanceState) {

//		Bundle arguments = getArguments();
//		tabIndex = arguments.getInt("tabIndex", 0);
//		beautifulUser = arguments.getString("beautifulUser", "");
//
//		context = getActivity();
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_style, null);
		return view;
	}
}
