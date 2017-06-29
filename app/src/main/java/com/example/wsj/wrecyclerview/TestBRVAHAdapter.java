package com.example.wsj.wrecyclerview;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by wangshijia on 2017/6/24 下午4:36.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public class TestBRVAHAdapter<T> extends BaseQuickAdapter<T,BaseViewHolder> {

    public TestBRVAHAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        helper.setText(R.id.textView, (CharSequence) item);
    }
}
