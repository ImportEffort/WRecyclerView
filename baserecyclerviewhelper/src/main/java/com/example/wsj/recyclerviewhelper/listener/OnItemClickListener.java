package com.example.wsj.recyclerviewhelper.listener;

import android.view.View;

import com.example.wsj.recyclerviewhelper.adapter.BaseQuickAdapter;


/**
 * Created by wangshijia on 2017/6/27 下午2:26.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public interface OnItemClickListener {
    void OnItemClick(BaseQuickAdapter adapter, View view, int position);
}
