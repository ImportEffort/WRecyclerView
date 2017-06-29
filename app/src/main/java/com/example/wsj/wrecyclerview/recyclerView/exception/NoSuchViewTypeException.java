package com.example.wsj.wrecyclerview.recyclerView.exception;

/**
 * Created by wangshijia on 2017/6/27 下午7:08.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public class NoSuchViewTypeException extends RuntimeException {
    private String msg = "没有对应的ViewType异常";

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
