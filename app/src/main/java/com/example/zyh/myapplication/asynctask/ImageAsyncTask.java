package com.example.zyh.myapplication.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.example.zyh.myapplication.inter.ImageCallback;
import com.example.zyh.myapplication.utils.HttpUtils;

import static android.content.Context.WINDOW_SERVICE;

/**
 * 加载图片的异步任务工具类。
 * */
public class ImageAsyncTask extends AsyncTask<String,Void, Bitmap> {

	private final Context context;
	private final ImageCallback callback;
	private int width;// 窗口的实际宽度
	private int height;// 窗口的实际高度
	private String path;

	public ImageAsyncTask(Context context, ImageCallback callback) {
		super();
		this.context = context;
		this.callback = callback;
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		path = params[0];
		//将图片数据放入图片接口时，先进行二次采样。
		byte[] bm=HttpUtils.getImageContent(params[0]);
		Bitmap bitmap=getScaleBitmap(bm,width,height);
		return bitmap;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		// 2、求得窗口的宽和高
		//--------->求得手机的高宽
		WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();

		if (result != null) {


			callback.sendImage(result, path);
		} else {
			// 如果获取不到数据，怎么办呢？
			Log.i("tag", "数据获取失败");
			Log.i("tag", path);
		}

	}

	private Bitmap getScaleBitmap(byte[] data, int width, int height) {
		// 1.得到用来设值图片属性参数的对象
		BitmapFactory.Options options = new BitmapFactory.Options();
		// 2.解码边缘(好处：不用将图片加载到内存中，也能获得图片的概要信息
		options.inJustDecodeBounds = true;
		// 3进行图片解码
		BitmapFactory.decodeByteArray(data,0,data.length,options);
		// 4.获取原来图片的宽高
		double oldwidth = options.outWidth;
		double oldheigth = options.outHeight;
		// 5.得到压缩的比例
		 int sacle =1;
		double scaleWidth = oldwidth / width;
		double scaleHeight = oldheigth / height;
		// 6.取出宽高缩放比例当中较大的一个作为缩放比例
//		int scale = (int) Math.round(scaleHeight > scaleWidth ? scaleHeight
//				: scaleWidth);
		// 考虑宽
		if( scaleWidth > scaleHeight && scaleWidth> sacle){
			sacle = (int) scaleWidth;
		}

		// 考虑高
		if( scaleHeight > scaleWidth && scaleHeight> sacle){
			sacle = (int) scaleHeight;
		}

		// 7.设置缩放比例，只能设置大于1的整数，数越大，缩的越小
		options.inSampleSize = sacle;
		// 8.锁住边缘
		options.inJustDecodeBounds = false;
		// 9.通过参数获得新的位图
		Bitmap bm = BitmapFactory
				.decodeByteArray(data, 0, data.length, options);
		return bm;
	}

	/**
	 * 
	 * @param data
	 * @param newScale
	 *            输入压缩比例
	 * @return
	 */
	private Bitmap getScaleBitmap2(byte[] data, int newScale) {
		// 1.得到用来设值图片属性参数的对象
		BitmapFactory.Options options = new BitmapFactory.Options();
		// 2.解码边缘
		options.inJustDecodeBounds = true;
		// 3进行图片解码
		BitmapFactory.decodeByteArray(data, 0, data.length, options);
		// 4.获取原来图片的宽高

		// 7.设置缩放比例，只能设置大于1的整数，数越大，缩的越小
		options.inSampleSize = newScale;
		// 8.锁住边缘
		options.inJustDecodeBounds = false;
		// 9.通过参数获得新的位图
		Bitmap bm = BitmapFactory
				.decodeByteArray(data, 0, data.length, options);
		return bm;
	}

}
