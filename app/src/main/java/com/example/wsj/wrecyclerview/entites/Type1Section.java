package com.example.wsj.wrecyclerview.entites;


import com.example.wsj.recyclerviewhelper.entities.SectionEntity;

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
