package com.example.wsj.wrecyclerview;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.example.wsj.wrecyclerview.recyclerView.adapter.BaseSectionQuickAdapter;
import com.example.wsj.wrecyclerview.recyclerView.base.BaseViewHolder;

import java.util.List;

/**
 * Created by wangshijia on 2017/6/28 下午12:16.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */


public class TestMySectionAdapter extends BaseSectionQuickAdapter<SectionEntity> {


    public TestMySectionAdapter(List<SectionEntity> dates) {
        super(R.layout.simlpe_layout_item, R.layout.section_layout, dates);
    }

    @Override
    public void convert(BaseViewHolder holder, SectionEntity item, int position) {
        holder.setText(R.id.textView,item.header);
    }

    @Override
    public void covertHead(BaseViewHolder holder, SectionEntity item, int position) {
        holder.setText(R.id.section_title,item.header);
    }
}
