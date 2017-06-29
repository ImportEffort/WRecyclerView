package com.example.wsj.wrecyclerview;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by wangshijia on 2017/6/27 下午3:40.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public class TestBRVAHMutilType extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public TestBRVAHMutilType(List<MultiItemEntity> data) {
        super(data);
        addItemType(1, R.layout.mutil_layout_item);
        addItemType(2, R.layout.mutil_layout_item1);
        addItemType(3, R.layout.mutil_layout_item2);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {

    }

}
