package com.jiang.foodfaction.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.adapter.CollectAdapter;
import com.jiang.foodfaction.fragment.CollectArticleFragment;
import com.jiang.foodfaction.fragment.CollectFoodFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/15.
 */

public class CollectActivity extends BaseActivity {

    private ImageView imageView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> list;

    private CollectAdapter collectAdapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_collect;
    }

    @Override
    public void initView() {

        imageView=bindView(R.id.collect_return);
        tabLayout=bindView(R.id.collect_tablayout);
        viewPager=bindView(R.id.collect_viewpager);
    }

    @Override
    public void initData() {
        list=new ArrayList<>();

        list.add(new CollectArticleFragment());
        list.add(new CollectFoodFragment());

        collectAdapter=new CollectAdapter(getSupportFragmentManager(),this);

        collectAdapter.setList(list);

        viewPager.setAdapter(collectAdapter);

        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setText(collectAdapter.getTabTitles(i));

         }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void bindEvent() {

    }
}
