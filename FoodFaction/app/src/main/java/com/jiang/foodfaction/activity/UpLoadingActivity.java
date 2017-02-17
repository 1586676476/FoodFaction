package com.jiang.foodfaction.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.jiang.foodfaction.R;
import com.jiang.foodfaction.adapter.UploadingAdapter;

import java.util.ArrayList;
import java.util.List;

public class UpLoadingActivity extends AppCompatActivity {
    private ImageView imageView;
    private List<String> list;
    private ListView listView;
    private UploadingAdapter uploadingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploading);

        imageView= (ImageView) findViewById(R.id.uploading_return);

        list=new ArrayList<>();

        listView= (ListView) findViewById(R.id.uploading_listView);

        uploadingAdapter=new UploadingAdapter(this);

        uploadingAdapter.setList(list);

        listView.setAdapter(uploadingAdapter);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
