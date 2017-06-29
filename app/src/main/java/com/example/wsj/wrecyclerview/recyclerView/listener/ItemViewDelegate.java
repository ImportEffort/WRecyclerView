package com.example.wsj.wrecyclerview.recyclerView.listener;

import com.example.wsj.wrecyclerview.recyclerView.base.BaseViewHolder;

/**
 * Created by wangshijia on 2017/6/24 下午4:20.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public interface ItemViewDelegate<T> {
     void convert(BaseViewHolder holder, T item, int position);
}
