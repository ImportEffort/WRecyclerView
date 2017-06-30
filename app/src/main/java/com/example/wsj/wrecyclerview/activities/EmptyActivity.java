package com.example.wsj.wrecyclerview.activities;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.wsj.recyclerviewhelper.Logcat;
import com.example.wsj.recyclerviewhelper.adapter.BaseQuickAdapter;
import com.example.wsj.recyclerviewhelper.base.BaseViewHolder;
import com.example.wsj.recyclerviewhelper.listener.LoadMoreListener;
import com.example.wsj.wrecyclerview.R;

import java.util.ArrayList;
import java.util.TimerTask;

public class EmptyActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private BaseQuickAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutil);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new BaseQuickAdapter<String>(R.layout.simlpe_layout_item, new ArrayList<String>()) {
            @Override
            public void convert(BaseViewHolder holder, String item, int position) {
                holder.setText(R.id.textView,item);
            }
        };

        View emptyView = LayoutInflater.from(this).inflate(R.layout.layout_empty_view, mRecyclerView, false);
        final TextView text = (TextView) emptyView.findViewById(R.id.text_empty);
        final ContentLoadingProgressBar loadingProgressBar =
                (ContentLoadingProgressBar) emptyView.findViewById(R.id.content_loading_bar);
        loadingProgressBar.hide();
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setVisibility(View.GONE);
                loadingProgressBar.show();
                onRefresh();
            }
        });
        mAdapter.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void OnLoadMore() {
                Logcat.d("loadMore");
                mAdapter.addItem("123");
                mAdapter.setLoadComplete();
            }
        },mRecyclerView);

        mAdapter.setEnableNotFullScreenLoadMore(true);
        mAdapter.setEmptyView(emptyView);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onRefresh() {
        mRecyclerView.postDelayed(new TimerTask() {
            @Override
            public void run() {
                mAdapter.addItem("有条目了");
                mSwipeRefreshLayout.setRefreshing(false);
            }
        },5000);
    }
}
