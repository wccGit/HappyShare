package com.example.zyh.myapplication.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zyh.myapplication.R;
import com.example.zyh.myapplication.asynctask.GetDataAsyncTask;
import com.example.zyh.myapplication.bean.MoviesBean;
import com.example.zyh.myapplication.content.ContentUtils;
import com.example.zyh.myapplication.inter.GetDateCallback;
import com.example.zyh.myapplication.utils.ParseMovies;
import com.example.zyh.myapplication.utils.SDCardHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小军 on 2016/7/8.
 */
public class MovieFragment extends Fragment {

	private ViewPager vp_top_style_id;
	private List<Fragment> fragments;
	private List<MoviesBean> list;
//	private int tabIndex;// 便签索引值
//	private String beautifulArticle;// 标签名
//	private Activity context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
//		Bundle arguments = getArguments();
//		tabIndex = arguments.getInt("tabIndex", 0);
//		beautifulArticle = arguments.getString("beautifulArticle", "");
//
//		context = getActivity();
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_style, null);

		vp_top_style_id = (ViewPager) view.findViewById(R.id.vp_contain_id);

		fragments = new ArrayList<Fragment>();

		loadData();

		return view;
	}

	private void inintCombo() {
		for (int i = 0; i < list.size(); i++) {
			Fragment fragment = new MoviesItemFrament();
			Bundle bundle = new Bundle();
			bundle.putSerializable("article", list.get(i));
			fragment.setArguments(bundle);
			fragments.add(fragment);

		}

		MyAdapter adapter = new MyAdapter(getChildFragmentManager());
		vp_top_style_id.setAdapter(adapter);
	}

	private void loadData() {
		String path = Environment.getExternalStorageDirectory()
				+ File.separator + "movies.txt";
		File file = new File(path);
		if (file.exists()) {
			byte[] b = SDCardHelper.loadFileFromSDCard(path);
			String json = b.toString();
			list = ParseMovies.getRusults(json);

			inintCombo();

		} else {
			new GetDataAsyncTask(new GetDateCallback.GetDataCallback() {

				@Override
				public void sendData(String json) {

					list = ParseMovies.getRusults(json);

					inintCombo();
					SDCardHelper.saveFileToSDCardCacheDir(json.getBytes(),
							"movies.txt", getActivity());

				}
			}, getActivity()).execute(ContentUtils.MOVIE_URL);

		}

	}

	class MyAdapter extends FragmentPagerAdapter {

		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int pos) {
			return fragments.get(pos);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}

	}
}
