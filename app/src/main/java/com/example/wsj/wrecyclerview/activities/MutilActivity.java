package com.example.wsj.wrecyclerview.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wsj.recyclerviewhelper.entities.MultiItemEntity;
import com.example.wsj.recyclerviewhelper.itemDecoration.BaseItemDecoration;
import com.example.wsj.recyclerviewhelper.itemDecoration.Divide;
import com.example.wsj.recyclerviewhelper.itemDecoration.DivideBuilder;
import com.example.wsj.wrecyclerview.R;
import com.example.wsj.wrecyclerview.adapter.MultiTypeAdapter;
import com.example.wsj.wrecyclerview.entites.MultiItemOne;
import com.example.wsj.wrecyclerview.entites.MultiItemTitle;
import com.example.wsj.wrecyclerview.entites.MultilItemThree;
import com.example.wsj.wrecyclerview.entites.MultilItemTwo;

import java.util.ArrayList;
import java.util.List;

public class MutilActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutil);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter(getDates());
        mRecyclerView.addItemDecoration(new BaseItemDecoration(this) {
            @Override
            public Divide getDivider(int itemPosition, int itemCount) {
                DivideBuilder divideBuilder = new DivideBuilder();
                if (itemPosition != itemCount-1) {
                    divideBuilder.setBottomSideLine(true, Color.parseColor("#ffffff"), 6, 0, 0);
                    return divideBuilder.build();
                }else {
                    return divideBuilder.build();
                }
            }
        });
        mRecyclerView.setAdapter(multiTypeAdapter);
    }

    private List<MultiItemEntity> getDates() {
        List<MultiItemEntity> itemEntities = new ArrayList<>();

        itemEntities.add(new MultiItemTitle());
        List<MultiItemEntity> one = new ArrayList<>();
        one.add(new MultiItemOne());
        one.add(new MultiItemOne());
        one.add(new MultiItemOne());
        itemEntities.addAll(one);

        itemEntities.add(new MultiItemTitle());
        List<MultiItemEntity> two = new ArrayList<>();
        two.add(new MultilItemTwo());
        two.add(new MultilItemTwo());
        two.add(new MultilItemTwo());
        itemEntities.addAll(two);

        itemEntities.add(new MultiItemTitle());
        List<MultiItemEntity> three = new ArrayList<>();
        three.add(new MultilItemThree());
        three.add(new MultilItemThree());
        three.add(new MultilItemThree());
        three.add(new MultilItemThree());
        itemEntities.addAll(three);
        return itemEntities;
    }
}
