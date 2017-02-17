package com.jiang.foodfaction.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.adapter.KnowledgeAdapter;
import com.jiang.foodfaction.bean.KnowledgeBean;
import com.jiang.foodfaction.inter.CallBack;
import com.jiang.foodfaction.packaging.NetTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class KnowledgeFragment extends Fragment {

    private String url="http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=3&per=10";

    private List<KnowledgeBean.FeedsBean> list;
    private ListView listView;
    private KnowledgeAdapter knowledgeAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_knowledge,container,false);
        listView= (ListView) view.findViewById(R.id.knowledge_listView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list=new ArrayList<>();

        knowledgeAdapter=new KnowledgeAdapter(getContext());

        knowledgeAdapter.setFeedsBeen(list);

        listView.setAdapter(knowledgeAdapter);

        NetTool.getInstance().startRequest(url, KnowledgeBean.class, new CallBack<KnowledgeBean>() {
            @Override
            public void onSuccess(KnowledgeBean respomse) {
                list=respomse.getFeeds();
                knowledgeAdapter.setFeedsBeen(list);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
