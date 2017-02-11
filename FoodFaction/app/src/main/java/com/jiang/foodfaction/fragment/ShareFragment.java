package com.jiang.foodfaction.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.adapter.ShareAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/11.
 */

public class ShareFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> data;
    private ShareAdapter shareAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_share,container,false);
        tabLayout= (TabLayout) view.findViewById(R.id.share_tablayout);
        viewPager= (ViewPager) view.findViewById(R.id.share_viewpager);

        data=new ArrayList<>();

        data.add(new HomeFragment());
        data.add(new EvaluateFragment());
        data.add(new KnowledgeFragment());
        data.add(new FoodFragment());

        shareAdapter=new ShareAdapter(getFragmentManager(),getContext());

        shareAdapter.setList(data);

        viewPager.setAdapter(shareAdapter);

        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setText(shareAdapter.getTabtiles(i));
        }
        return view;
    }
}
