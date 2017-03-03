package com.jiang.foodfaction.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.activity.DetailsActivity;
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
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collect_article, container, false);
        listView = (ListView) view.findViewById(R.id.collect_artice_listView);

        data = new ArrayList<>();

        collectArticeAdapter = new CollectArticeAdapter(getContext());
        //查询数据库
        data = DbTool.getInStance().queueAll();

        collectArticeAdapter.setList(data);
        listView.setAdapter(collectArticeAdapter);

        collectArticeAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("url",data.get(position).getUrl());
                intent.putExtra("titles",data.get(position).getTitle());
                startActivity(intent);
            }
        });

        return view;
    }

}
