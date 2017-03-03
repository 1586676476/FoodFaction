package com.jiang.foodfaction.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.activity.CollectActivity;
import com.jiang.foodfaction.activity.MyCameraActivity;
import com.jiang.foodfaction.activity.RegisterActivity;
import com.jiang.foodfaction.activity.UpLoadingActivity;
import com.jiang.foodfaction.adapter.MyAdapter;
import com.jiang.foodfaction.bean.MyBean;
import com.jiang.foodfaction.bean.RegisterBean;
import com.jiang.foodfaction.inter.OnClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class MyFragment extends Fragment implements OnClickListener {

    private ArrayReceiver arrayReceiver;
    private TextView textView, change, name;
    private static final String TAG = "MyFragment";
    private List<MyBean> list;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ImageView imageView;


    private int[] left = new int[]{R.mipmap.my_photo, R.mipmap.my_collect, R.mipmap.my_upload, R.mipmap.my_order};
    private String[] text = new String[]{"我的照片", "我的收藏", "上传食物数据", "我的订单"};
    private int[] right = new int[]{R.mipmap.arrow_right_normal, R.mipmap.arrow_right_normal, R.mipmap.arrow_right_normal, R.mipmap.arrow_right_normal};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler);
        textView = (TextView) view.findViewById(R.id.register);
        change = (TextView) view.findViewById(R.id.my_text);
        imageView = (ImageView) view.findViewById(R.id.circleImageView);
        name = (TextView) view.findViewById(R.id.my_name);

        //初始化EventBus
        //EventBus.getDefault().register(this);


        if (register()) {
            textView.setVisibility(View.GONE);
            change.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {


        //点击登录跳转到登录界面
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //判断是否登录
                Log.e(TAG, "onActivityCreated: " + register());
                if (!register()) {
                    toRegister();
                }

            }
        });

        list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            list.add(new MyBean(left[i], text[i], right[i]));
        }

        LinearLayoutManager manger = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(manger);

        myAdapter = new MyAdapter(getContext());

        myAdapter.setOnClickListener(this);

        Log.e(TAG, "onActivityCreated: " + list.size());
        myAdapter.setList(list);

        recyclerView.setAdapter(myAdapter);

        arrayReceiver = new ArrayReceiver();

        IntentFilter intentFilter = new IntentFilter("CHANGE");
        getContext().registerReceiver(arrayReceiver, intentFilter);


        super.onActivityCreated(savedInstanceState);
    }

    //登录了跳转到对应的界面
    private boolean register() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("register", Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean("register", false);
    }

    //没登录跳转到登录界面
    private void toRegister() {
        Intent ToRegister = new Intent(getContext(), RegisterActivity.class);
        startActivity(ToRegister);
    }

    @Override
    public void onItemClick(int position) {

//        //判断是否登录

        switch (position) {
            case 0:
                if (register()) {
                    Intent intent = new Intent(getContext(), MyCameraActivity.class);
                    startActivity(intent);
                } else {
                    toRegister();
                }
                break;
            case 1:
                if (register()) {
                    Intent ToCollect = new Intent(getContext(), CollectActivity.class);
                    startActivity(ToCollect);
                } else {
                    toRegister();
                }
                break;
            case 2:
                if (register()) {
                    Intent ToUploading = new Intent(getContext(), UpLoadingActivity.class);
                    startActivity(ToUploading);
                } else {
                    toRegister();
                }
                break;
            case 3:
                break;
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(arrayReceiver);
    }

    class ArrayReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //如果登录的话 文字改变

            textView.setVisibility(View.GONE);
            change.setVisibility(View.VISIBLE);
            name.setVisibility(View.VISIBLE);
            SharedPreferences sp = getContext().getSharedPreferences("register", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("register", true);
            editor.commit();
        }
    }

//    //接受数据,并让其运行在主线程
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void getData(RegisterBean registerBean) {
//        //name.setText(registerBean.getNameTv());
////        imageView.setImageResource(Integer.parseInt(registerBean.getPhoto()));
//    }
//
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        //解除注册
//        EventBus.getDefault().unregister(this);
//    }
}
