package com.example.zyh.myapplication.inter;

/**
 * Created by 小军 on 2016/7/8.
 */
/*
 * 所有数据的回调接口
 */
public interface GetDateCallback {

    /*
     *数据的回调接口，传值。
     */
    public interface GetDataCallback{
        public void sendData(String json);
    }

}
