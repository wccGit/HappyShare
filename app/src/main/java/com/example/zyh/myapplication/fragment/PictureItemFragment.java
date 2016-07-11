package com.example.zyh.myapplication.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zyh.myapplication.R;
import com.example.zyh.myapplication.asynctask.GetDataAsyncTask;
import com.example.zyh.myapplication.asynctask.ImageAsyncTask;
import com.example.zyh.myapplication.bean.PictureBean;
import com.example.zyh.myapplication.bean.PictureItemBean;
import com.example.zyh.myapplication.content.ContentUtils;
import com.example.zyh.myapplication.db.DBOpenHelper;
import com.example.zyh.myapplication.inter.GetDateCallback;
import com.example.zyh.myapplication.inter.ImageCallback;
import com.example.zyh.myapplication.utils.SDCardHelper;

import java.io.File;

/**
 * Created by 小军 on 2016/7/8.
 */
public class PictureItemFragment extends Fragment {

	private TextView titleTv,authorTv,poemTv,sourceTv;
	private ImageView bigIv;
	private SQLiteDatabase db;
	private DBOpenHelper helper;
	private PictureBean bean ;
	private String pid;
	private PictureItemBean itemBean;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		helper = new DBOpenHelper(getActivity(), "ten.db", null, 1);
		db = helper.getWritableDatabase();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_picture_item, null);
		Bundle bundle = getArguments();
		bean = (PictureBean) bundle.getSerializable("picture");
		pid = bean.getId();
		initComponent(view);
		loadData();
		return view;
	}
	private void loadData() {
		Cursor cursor = queryDataFromDB(pid);
		if (cursor!=null&&cursor.getCount()!=0) {
			if (cursor.moveToFirst()) {
				String content = cursor.getString(cursor.getColumnIndex("content"));
				itemBean = PictureItemBean.parseData(content);
				setViews(itemBean);
			}
		}else {
			new GetDataAsyncTask(new GetDateCallback.GetDataCallback() {
				@Override
				public void sendData(String json) {
					itemBean = PictureItemBean.parseData(json);
					insertDataToDB(pid, json);
					setViews(itemBean);
				}
			}, getActivity()).execute(ContentUtils.PICTURE_DETATLS_URL+pid);
		}
		
		if (!bean.getImage().isEmpty()) {
			loadImage(ContentUtils.PICTURE_DISPLAY_URL+bean.getImage());
		}
	}
	private void loadImage(String imgurl){
		final String fileName = imgurl.substring(imgurl.lastIndexOf("/")+1);
		String filePath = getActivity().getExternalCacheDir().getAbsolutePath()+File.separator+fileName;
		if (new File(filePath).exists()) {
			byte[]data = SDCardHelper.loadFileFromSDCard(filePath);
			Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
			bigIv.setImageBitmap(bm);
		}else {
			new ImageAsyncTask(getActivity(), new ImageCallback() {
				@Override
				public void sendImage(Bitmap bm, String path) {
					bigIv.setImageBitmap(bm);
					SDCardHelper.saveBitmapToSDCardCacheDir(bm, fileName, getActivity());
				}
			}).execute(imgurl);
		}
		
	}
	private void setViews(PictureItemBean itemBean){
		authorTv.setText(itemBean.getAuthorbrief());
		titleTv.setText(itemBean.getTitle());
		poemTv.setText(itemBean.getText1());
		sourceTv.setText(itemBean.getText2());
	}
	private void initComponent(View view){
		titleTv = (TextView)view.findViewById(R.id.id_picture_item_title);
		authorTv = (TextView)view.findViewById(R.id.id_picture_item_authorbrief);
		poemTv = (TextView)view.findViewById(R.id.id_picture_item_summary);
		sourceTv = (TextView)view.findViewById(R.id.id_picture_item_summary_author);
		bigIv = (ImageView)view.findViewById(R.id.id_picture_item_image);
		
	}
	
	//数据库当中查询数据的方法
	private Cursor queryDataFromDB(String id){
		return db.query("pictb", null, "_id=?", new String[]{id}, null, null, null, null);
	}
	
	//数据库的插入方法
	private void insertDataToDB(String id,String content){
		ContentValues values = new ContentValues();
		values.put("_id", id);
		values.put("content", content);
		db.insert("pictb", null, values);
	}
	
}
