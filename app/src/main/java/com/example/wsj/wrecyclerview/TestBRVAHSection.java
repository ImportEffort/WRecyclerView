package com.example.wsj.wrecyclerview;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

/**
 * Created by wangshijia on 2017/6/27 下午3:48.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public class TestBRVAHSection extends BaseSectionQuickAdapter<SectionEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public TestBRVAHSection(int layoutResId, int sectionHeadResId, List<SectionEntity> data) {
        super(layoutResId, sectionHeadResId, data);


    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionEntity item) {
        helper.setText(R.id.section_title, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionEntity item) {
        helper.setText(R.id.textView, (String) item.header);
    }
}
