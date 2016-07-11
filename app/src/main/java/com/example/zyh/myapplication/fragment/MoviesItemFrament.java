package com.example.zyh.myapplication.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zyh.myapplication.R;
import com.example.zyh.myapplication.asynctask.GetDataAsyncTask;
import com.example.zyh.myapplication.asynctask.ImageAsyncTask;
import com.example.zyh.myapplication.bean.MoviesBean;
import com.example.zyh.myapplication.bean.MoviesDetils;
import com.example.zyh.myapplication.content.ContentUtils;
import com.example.zyh.myapplication.inter.GetDateCallback;
import com.example.zyh.myapplication.inter.ImageCallback;
import com.example.zyh.myapplication.utils.SDCardHelper;

import java.io.File;

/**
 * Created by 小军 on 2016/7/8.
 */
public class MoviesItemFrament extends Fragment {
	private View view;
	private MoviesBean bean;
	private TextView t1, t2, t3, t4, t5, t6, t7, t8;
	private ImageView iv2, iv3, iv4, iv5;
	private ImageView iv1;
	private MoviesDetils moviesDetils;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = LayoutInflater.from(getActivity()).inflate(
				R.layout.fragment_movies_item, null);
		Bundle bundle = getArguments();
		bean = (MoviesBean) bundle.getSerializable("article");
		Log.i("tag", bean.getImage() + "  " + bean.getType());
		loadData();
		return view;
	}

	private void initCombo() {

		t1 = (TextView) view.findViewById(R.id.id_movies_title);
		t1.setText(bean.getTitle());
		t2 = (TextView) view.findViewById(R.id.id_movies_times);
		t2.setText("作者：" + moviesDetils.getAuthor() + "  | 阅读量："
				+ moviesDetils.getTimes());
		t3 = (TextView) view.findViewById(R.id.id_movies_content);
		t3.setText(bean.getSummary());
		t4 = (TextView) view.findViewById(R.id.jianjie);
		t4.setText(moviesDetils.getText2());
		t5 = (TextView) view.findViewById(R.id.id_jianjie_content);
		t5.setText(moviesDetils.getText3());
		t6 = (TextView) view.findViewById(R.id.id_end_content);
		t6.setText(moviesDetils.getText4());
		t7 = (TextView) view.findViewById(R.id.id_end_title);
		t7.setText(moviesDetils.getText5());
		t8 = (TextView) view.findViewById(R.id.id_movies_reason);
		t8.setText(moviesDetils.getRealtitle());
		iv1 = (ImageView) view.findViewById(R.id.movies_begin_iv);
		setImage(iv1, ContentUtils.PICTURE_DISPLAY_URL + bean.getImage());

		iv2 = (ImageView) view.findViewById(R.id.movies_jianjie_iv);
		setImage(iv2,
				ContentUtils.PICTURE_DISPLAY_URL + moviesDetils.getImage1());
		iv3 = (ImageView) view.findViewById(R.id.movies_end_iv);
		iv4 = (ImageView) view.findViewById(R.id.movies_end_iv2);
		iv5 = (ImageView) view.findViewById(R.id.movies_end_iv3);
		setImage(iv3,
				ContentUtils.PICTURE_DISPLAY_URL + moviesDetils.getImage2());

		setImage(iv4,
				ContentUtils.PICTURE_DISPLAY_URL + moviesDetils.getImage3());
		setImage(iv5,
				ContentUtils.PICTURE_DISPLAY_URL + moviesDetils.getImage4());
	}

	private void loadData() {
		new GetDataAsyncTask(new GetDateCallback.GetDataCallback() {

			@Override
			public void sendData(String json) {
				moviesDetils = MoviesDetils.getDetils(json);
				initCombo();
			}
		}, getActivity())
				.execute(ContentUtils.MOVIE_DETATLS_URL + bean.getId());
	}

	private void setImage(final ImageView iv, String path) {
		String url = Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ path.substring(path.lastIndexOf("/") + 1);
		File file = new File(url);
		if (file.exists() && file.length() > 0) {
			byte[] b = SDCardHelper.loadFileFromSDCard(url);
			Bitmap bm = BitmapFactory.decodeByteArray(b, 0, b.length);
			iv.setImageBitmap(bm);
		} else {
			new ImageAsyncTask(getActivity(), new ImageCallback() {

				@Override
				public void sendImage(Bitmap bm, String path) {
					iv.setImageBitmap(bm);
					SDCardHelper.saveBitmapToSDCardCacheDir(bm,
							path.substring(path.lastIndexOf("/") + 1),
							getActivity());
				}
			}).execute(path);
		}

	}

}
