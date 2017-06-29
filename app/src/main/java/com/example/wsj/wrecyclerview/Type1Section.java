package com.example.wsj.wrecyclerview;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by wangshijia on 2017/6/27 下午3:56.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public class Type1Section extends SectionEntity<String> {

    public Type1Section(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public Type1Section(String s) {
        super(s);
    }
}
