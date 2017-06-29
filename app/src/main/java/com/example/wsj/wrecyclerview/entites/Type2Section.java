package com.example.wsj.wrecyclerview.entites;


import com.example.wsj.recyclerviewhelper.entities.SectionEntity;

/**
 * Created by wangshijia on 2017/6/27 下午3:56.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public class Type2Section extends SectionEntity<String> {

    public Type2Section(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public Type2Section(String s) {
        super(s);
    }
}
