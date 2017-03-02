package com.jiang.foodfaction.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.adapter.CollectArticeAdapter;
import com.jiang.foodfaction.greendao.Bean;
import com.jiang.foodfaction.greendao.DbTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/15.
 */

public class CollectArticleFragment extends Fragment {
    private List<Bean> data;
    private CollectArticeAdapter collectArticeAdapter;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_collect_article,container,false);
        listView = (ListView) view.findViewById(R.id.collect_artice_listView);

        data=new ArrayList<>();

        collectArticeAdapter=new CollectArticeAdapter(getContext());
        data=DbTool.getInStance().queueAll();



        collectArticeAdapter.setList(data);
        listView.setAdapter(collectArticeAdapter);


        //查询数据库

         collectArticeAdapter.notifyDataSetChanged();



        return view;
    }

}
