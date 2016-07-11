package com.example.zyh.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.zyh.myapplication.AboutTen;
import com.example.zyh.myapplication.MainActivity;
import com.example.zyh.myapplication.R;
import com.example.zyh.myapplication.Sygesst;


import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by zyh on 2016/7/7.
 */

public class UserFragment extends Fragment {
    private List<Map<String, Object>> ds;
    @Override
    public void onCreate(Bundle savedInstanceState) {
       String [] name ={"我的收藏","字体设置","关于十个","意见反馈"};
        int [] picture ={R.mipmap.setting_favorite,R.mipmap.setting_aboutus,R.mipmap.setting_feedback,R.mipmap.setting_font};
        super.onCreate(savedInstanceState);
        ds = new LinkedList<>();
        for (int i=0;i<4;i++){
           Map<String, Object> map =new LinkedHashMap<>();
            map.put("tv",name[i]);
map.put("iv",picture[i]);
            ds.add(map);



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ListView lv = (ListView) view.findViewById(R.id.id_lv_user);
        ImageView iv = (ImageView) view.findViewById(R.id.id_login_user);
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), ds, R.layout.item_user,
                new String[] { "tv","iv" },
                new int[] { R.id.tv_id, R.id.iv_id });
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getActivity(),"我被点了",Toast.LENGTH_LONG).show();
                switch(i){
                    case 0:
                        Toast.makeText(getActivity(),"我被点了",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(),"我被点了,呵呵",Toast.LENGTH_LONG).show();

                        break;
                    case 2:
                        Intent intent =new Intent(getActivity(),AboutTen.class);
                        startActivity(intent);
                        break;
                    case 3:
                        Intent intent2 =new Intent(getActivity(),Sygesst.class);
                        startActivity(intent2);
                        break;
                }

            }
        });
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(getActivity(),"我被点了",Toast.LENGTH_LONG).show();
                return false;
            }
        });
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
