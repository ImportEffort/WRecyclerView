package com.example.wsj.wrecyclerview.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wsj.recyclerviewhelper.adapter.BaseQuickAdapter;
import com.example.wsj.recyclerviewhelper.base.BaseViewHolder;
import com.example.wsj.recyclerviewhelper.itemDecoration.BaseItemDecoration;
import com.example.wsj.recyclerviewhelper.itemDecoration.Divide;
import com.example.wsj.recyclerviewhelper.itemDecoration.DivideBuilder;
import com.example.wsj.recyclerviewhelper.itemDecoration.NoHeadFooterLineDecoration;
import com.example.wsj.recyclerviewhelper.listener.OnItemClickListener;
import com.example.wsj.wrecyclerview.R;
import com.example.wsj.wrecyclerview.adapter.HeadFooterAdapter;

import java.util.ArrayList;
import java.util.List;

public class HeadFooterActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private HeadFooterAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_footer);

        Button addHeader = (Button) findViewById(R.id.add_header);
        Button addFooter = (Button) findViewById(R.id.add_footer);
        addHeader.setOnClickListener(this);
        addFooter.setOnClickListener(this);
        Button removeHeader = (Button) findViewById(R.id.re_header_header);
        Button removeFooter = (Button) findViewById(R.id.remove_footer);
        removeHeader.setOnClickListener(this);
        removeFooter.setOnClickListener(this);

        initRV();
    }

    private void initRV() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearlayoutmanager);
        mRecyclerView.addItemDecoration(new NoHeadFooterLineDecoration(this, Color.parseColor("#ffffff"), 10, 0, 0));

        mAdapter = new HeadFooterAdapter(R.layout.simlpe_layout_item, getDates());
        View headerView = LayoutInflater.from(this).inflate(R.layout.layout_head_view, null);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeadFooterActivity.this, "我是一个头布局", Toast.LENGTH_SHORT).show();
            }
        });

        initRvHeaderView();

        mAdapter.addHeadView(headerView);
        View footerView = LayoutInflater.from(this).inflate(R.layout.layout_footer_view, null);
        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeadFooterActivity.this, "我是一个脚布局", Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.addFooterView(footerView);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(HeadFooterActivity.this, "我是条目" + position, Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setAdapter(mAdapter);
    }

    private void initRvHeaderView() {
        View rvHeaderView = LayoutInflater.from(this).inflate(R.layout.layout_rv_head_view, null);
        RecyclerView recyclerView = (RecyclerView) rvHeaderView.findViewById(R.id.recyclerView_h);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        BaseQuickAdapter<String> adapter = new BaseQuickAdapter<String>(this, R.layout.simlpe_h_layout_item, getDates()) {
            @Override
            public void convert(BaseViewHolder holder, String item, int position) {
                holder.setText(R.id.textView, item);
            }
        };
        recyclerView.addItemDecoration(new BaseItemDecoration(this) {
            @Override
            public Divide getDivider(int itemPosition, int itemCount) {
                DivideBuilder divideBuilder = new DivideBuilder();
                divideBuilder.setLeftSideLine(true,Color.parseColor("#ffffff"),2,0,0);
                divideBuilder.setBottomSideLine(true,Color.parseColor("#ffffff"),2,0,0);
                divideBuilder.setTopSideLine(true,Color.parseColor("#ffffff"),2,0,0);
                divideBuilder.setRightSideLine(true,Color.parseColor("#ffffff"),2,0,0);
                return divideBuilder.build();
            }
        });
        recyclerView.setAdapter(adapter);

        mAdapter.addHeadView(rvHeaderView);
    }

    private List<String> getDates() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            items.add("我是条目" + i);
        }
        return items;
    }


    private ArrayList<View> headerViews = new ArrayList<>();
    private ArrayList<View> footerViews = new ArrayList<>();
    private int addPostion = 0;
    private int addPostion1 = 0;
    private int removePostion = 0;
    private int removePostion1 = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_header:
                View headerView = LayoutInflater.from(this).inflate(R.layout.layout_head_view, null);
                headerViews.add(headerView);
                addPostion++;
                mAdapter.addHeadView(headerView);
                break;
            case R.id.re_header_header:
                if (removePostion < addPostion) {
                    mAdapter.removeHeadView(headerViews.get(removePostion));
                    removePostion++;
                }
                break;
            case R.id.add_footer:
                View footer = LayoutInflater.from(this).inflate(R.layout.layout_footer_view, null);
                footerViews.add(footer);
                addPostion1++;
                mAdapter.addFooterView(footer);
                break;
            case R.id.remove_footer:
                if (removePostion1 < addPostion1) {
                    mAdapter.removeFooterView(footerViews.get(removePostion1));
                    removePostion1++;
                }
                break;
        }
    }
}
