package com.example.wsj.wrecyclerview.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.example.wsj.recyclerviewhelper.entities.SectionEntity;
import com.example.wsj.recyclerviewhelper.itemDecoration.BaseItemDecoration;
import com.example.wsj.recyclerviewhelper.itemDecoration.Divide;
import com.example.wsj.recyclerviewhelper.itemDecoration.DivideBuilder;
import com.example.wsj.wrecyclerview.R;
import com.example.wsj.wrecyclerview.adapter.SectionAdapter;
import com.example.wsj.wrecyclerview.entites.Type1Section;
import com.example.wsj.wrecyclerview.entites.Type2Section;

import java.util.ArrayList;
import java.util.List;

public class SectionActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutil);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initRecyclerView();
    }

    private void initRecyclerView() {

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new BaseItemDecoration(this) {
            @Override
            public Divide getDivider(int itemPosition, int itemCount) {
                DivideBuilder divideBuilder = new DivideBuilder();
                divideBuilder.setRightSideLine(true, Color.parseColor("#ffffff"), 4, 0, 0);
                divideBuilder.setLeftSideLine(true, Color.parseColor("#ffffff"), 4, 0, 0);
                divideBuilder.setBottomSideLine(true, Color.parseColor("#ffffff"), 4, 0, 0);
                return divideBuilder.build();
            }
        });

        List<SectionEntity> sectionEntities = new ArrayList<>();
        sectionEntities.add(new Type1Section(true, "类型1"));
        sectionEntities.add(new Type1Section(false, "类型1item"));
        sectionEntities.add(new Type1Section(false, "类型1item"));
        sectionEntities.add(new Type2Section(true, "类型2"));
        sectionEntities.add(new Type1Section(false, "类型1item"));
        sectionEntities.add(new Type2Section(false, "类型2item"));
        sectionEntities.add(new Type2Section(false, "类型2item"));
        sectionEntities.add(new Type2Section(false, "类型2item"));

        final SectionAdapter sectionAdapter = new SectionAdapter(sectionEntities);
//        sectionAdapter.setLoadMoreListener(new LoadMoreListener() {
//            @Override
//            public void OnLoadMore() {
//
//                mRecyclerView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        sectionAdapter.addItem(new Type1Section(false, "上拉出来的"));
//                        sectionAdapter.setLoadComplete();
//                    }
//                }, 2000);
//            }
//        }, mRecyclerView);
//
//        sectionAdapter.setEnableNotFullScreenLoadMore(true);
        sectionAdapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.layout_footer_view, mRecyclerView, false));
        sectionAdapter.addHeadView(LayoutInflater.from(this).inflate(R.layout.layout_head_view, mRecyclerView, false));
        mRecyclerView.setAdapter(sectionAdapter);
    }
}
