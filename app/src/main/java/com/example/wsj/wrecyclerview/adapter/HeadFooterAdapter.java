package com.example.wsj.wrecyclerview.adapter;

import com.example.wsj.recyclerviewhelper.adapter.BaseQuickAdapter;
import com.example.wsj.recyclerviewhelper.base.BaseViewHolder;
import com.example.wsj.wrecyclerview.R;

import java.util.List;

/**
 * Created by wangshijia on 2017/6/29 上午11:13.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public class HeadFooterAdapter extends BaseQuickAdapter<String> {

    public HeadFooterAdapter(int layoutId, List<String> dates) {
        super(layoutId, dates);
    }


    @Override
    public void convert(BaseViewHolder holder, String item, int position) {
        holder.setText(R.id.textView, item);
    }
}
