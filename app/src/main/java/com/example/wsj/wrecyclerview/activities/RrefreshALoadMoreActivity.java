package com.example.wsj.wrecyclerview.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wsj.recyclerviewhelper.adapter.BaseQuickAdapter;
import com.example.wsj.recyclerviewhelper.base.BaseViewHolder;
import com.example.wsj.recyclerviewhelper.listener.LoadMoreListener;
import com.example.wsj.wrecyclerview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import static com.example.wsj.wrecyclerview.R.id.recyclerView;

public class RrefreshALoadMoreActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private BaseQuickAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutil);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = (RecyclerView) findViewById(recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new BaseQuickAdapter<String>(this, R.layout.simlpe_layout_item, getDates()) {
            @Override
            public void convert(BaseViewHolder holder, String item, int position) {
                holder.setText(R.id.textView, item);
            }
        };

        mAdapter.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void OnLoadMore() {
                mSwipeRefreshLayout.setEnabled(false);
                mRecyclerView.postDelayed(mRunnable, 2000);

            }
        }, mRecyclerView);

        mAdapter.setEnableNotFullScreenLoadMore(true);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    boolean isFirstLoad;

    private List<String> getDates() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            items.add("我是条目" + i);
        }
        return items;
    }

    private Runnable mRunnable = new TimerTask() {
        @Override
        public void run() {
            if (isFirstLoad) {
                mAdapter.addItem("上拉加载出来的");
                mAdapter.setLoadComplete();
                isFirstLoad = false;
            } else {
                mAdapter.setLoadEnd();
            }
            mSwipeRefreshLayout.setEnabled(true);
        }
    };


    @Override
    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                isFirstLoad = true;
                mAdapter.setNewDates(getDates());
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.setEnableLoadMore(true);
            }
        }, 2000);

    }
}
