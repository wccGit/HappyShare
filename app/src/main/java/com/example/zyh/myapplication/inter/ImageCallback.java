package com.example.zyh.myapplication.inter;

import android.graphics.Bitmap;

/**
 * Created by 小军 on 2016/7/8.
 */
  /*
   * 图片数据接口
   */
public interface ImageCallback {
    public void sendImage(Bitmap bm, String path);


}
