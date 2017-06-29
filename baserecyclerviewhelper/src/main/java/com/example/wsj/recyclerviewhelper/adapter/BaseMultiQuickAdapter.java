package com.example.wsj.recyclerviewhelper.adapter;

import android.support.annotation.LayoutRes;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.wsj.recyclerviewhelper.base.BaseViewHolder;
import com.example.wsj.recyclerviewhelper.entities.MultiItemEntity;

import java.util.List;

/**
 * Created by wangshijia on 2017/6/27 下午5:43.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public abstract class BaseMultiQuickAdapter<T extends MultiItemEntity> extends BaseQuickAdapter<T> {


    public static final int TYPE_NOT_FOUND = -404;

    private SparseArray<Integer> layouts;

    public BaseMultiQuickAdapter(List<T> dates) {
        super(dates);
    }

    @Override
    public int getNormalItemType(int position) {
        Log.e(TAG, "pos    " + position);
        T t = mDates.get(position);
        if (t != null) {
            return t.getItemType();
        }
        return super.getNormalItemType(position);
    }

    //没有对应的ViewType怎么办
    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        int layoutId = getLayoutId(viewType);
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }

    private int getLayoutId(int viewType) {
        return layouts.get(viewType, TYPE_NOT_FOUND);
    }

    //子类添加布局使用
    protected void addItemType(int type, @LayoutRes int layoutResId) {
        if (layouts == null) {
            layouts = new SparseArray<>();
        }
        layouts.put(type, layoutResId);
    }
}
