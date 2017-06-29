package com.example.wsj.wrecyclerview.recyclerView.load;

import com.example.wsj.wrecyclerview.R;

/**
 * Created by wangshijia on 2017/6/24 下午5:22.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public class CustomLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.layout_swipe_refresh_footer;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
