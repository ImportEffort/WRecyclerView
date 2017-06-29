package com.example.wsj.wrecyclerview.adapter;

import com.example.wsj.recyclerviewhelper.adapter.BaseMultiQuickAdapter;
import com.example.wsj.recyclerviewhelper.base.BaseViewHolder;
import com.example.wsj.recyclerviewhelper.entities.MultiItemEntity;
import com.example.wsj.wrecyclerview.R;

import java.util.List;

/**
 * Created by wangshijia on 2017/6/27 下午6:14.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public class MultiTypeAdapter extends BaseMultiQuickAdapter<MultiItemEntity> {

    public MultiTypeAdapter(List<MultiItemEntity> dates) {
        super(dates);
        addItemType(0, R.layout.section_layout);
        addItemType(1, R.layout.mutil_layout_item);
        addItemType(2, R.layout.mutil_layout_item1);
        addItemType(3, R.layout.mutil_layout_item2);
    }

    @Override
    public void convert(BaseViewHolder holder, MultiItemEntity item, int position) {

    }
}
