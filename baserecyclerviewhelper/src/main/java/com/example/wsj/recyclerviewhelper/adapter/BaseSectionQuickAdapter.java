package com.example.wsj.recyclerviewhelper.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.wsj.recyclerviewhelper.base.BaseViewHolder;
import com.example.wsj.recyclerviewhelper.entities.SectionEntity;

import java.util.List;

/**
 * Created by wangshijia on 2017/6/28 上午11:59.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public abstract class BaseSectionQuickAdapter<T extends SectionEntity> extends BaseQuickAdapter<T> {
    private int sectionHeadLayoutId;
    protected static final int SECTION_HEADER_VIEW = 0x00000444;

    public BaseSectionQuickAdapter(int layoutResId, int sectionHeadResId, List<T> dates) {
        super(layoutResId, dates);
        this.sectionHeadLayoutId = sectionHeadResId;
    }


    @Override
    public int getNormalItemType(int position) {
        T t = mDates.get(position);
        if (t != null) {
            if (t.isHeader) {
                return SECTION_HEADER_VIEW;
            } else {
                return super.getNormalItemType(position);
            }
        }
        return super.getNormalItemType(position);
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SECTION_HEADER_VIEW) {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(sectionHeadLayoutId, parent, false));
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected boolean isFixedViewType(int type) {
        return super.isFixedViewType(type) || type == SECTION_HEADER_VIEW;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder.getItemViewType() == SECTION_HEADER_VIEW) {
            covertHead(holder, mDates.get(holder.getLayoutPosition() - getHeaderLayoutCount()), position);
        } else {
            super.onBindViewHolder(holder, position);
        }
    }

    public abstract void covertHead(BaseViewHolder holder, T item, int position);
}
