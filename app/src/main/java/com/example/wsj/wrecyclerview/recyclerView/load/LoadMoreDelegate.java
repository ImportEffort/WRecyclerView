package com.example.wsj.wrecyclerview.recyclerView.load;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wsj.wrecyclerview.recyclerView.adapter.BaseQuickAdapter;
import com.example.wsj.wrecyclerview.recyclerView.listener.LoadMoreListener;

/**
 * Created by wangshijia on 2017/6/26 下午1:38.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public class LoadMoreDelegate {

    private RecyclerView mRecyclerView;
    private LoadMoreListener mLoadMoreListener;
    private BaseQuickAdapter mAdapter;
    private LoadMoreView mLoadMoreView;
    private boolean isLoading;

    public LoadMoreDelegate(RecyclerView recyclerView,
                            LoadMoreListener loadMoreListener,
                            BaseQuickAdapter adapter, LoadMoreView loadMoreView) {
        mRecyclerView = recyclerView;
        mLoadMoreListener = loadMoreListener;
        mLoadMoreView = loadMoreView;
        mAdapter = adapter;
        initLoadMore();
    }

    private void initLoadMore() {
        if (mRecyclerView != null) {
            mRecyclerView.addOnScrollListener(new LoadMoreScrollListener());
        } else {
            throw new RuntimeException("recyclerView need not null");
        }
    }


    private class LoadMoreScrollListener extends RecyclerView.OnScrollListener {

        private int lastItemPosition;
        private LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (loadMoreEnable
                    && lastItemPosition + 1 == mAdapter.getItemCount()
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                    && canScrollNotFullScreen()
                    && !isLoading) {
                startLoading();
            }

        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
            listScrollY = dy;
        }
    }

    public void startLoading() {
        if (mLoadMoreListener != null) {
            isLoading = true;
            mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_LOADING);
            mLoadMoreListener.OnLoadMore();
            mAdapter.notifyItemChanged(mAdapter.getItemCount() - 1);
        }
    }

    public void setLoadComplete() {
        isLoading = false;
        mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_DEFAULT);
        mAdapter.notifyItemChanged(mAdapter.getItemCount() - 1);
    }

    public void setLoadEnd() {
        isLoading = false;
        mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_END);
        mAdapter.notifyItemChanged(mAdapter.getItemCount() - 1);
    }

    public void setLoadError() {
        isLoading = false;
        mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_FAIL);
        mAdapter.notifyItemChanged(mAdapter.getItemCount() - 1);
    }

    private int listScrollY = 0;

    private boolean enableNoFullScreenLoadMore = false;

    public void setEnableNoFullScreenLoadMore(boolean enableNoFullScreenLoadMore) {
        this.enableNoFullScreenLoadMore = enableNoFullScreenLoadMore;
    }

    private boolean loadMoreEnable = true;

    public void setLoadMoreEnable(boolean loadMoreEnable) {
        this.loadMoreEnable = loadMoreEnable;
    }

    private boolean canScrollNotFullScreen() {
        //如果不允许不满屏幕滑动//如果允许不满屏幕滑动直接返回true
        return enableNoFullScreenLoadMore || listScrollY > 0;
    }
}
