package com.example.wsj.wrecyclerview.recyclerView.itemDecoration;

/**
 * Created by wangshijia on 2017/6/28 下午6:05.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

//单条线的实体类 负责单条线的属性
public class SideLine {
    private  boolean isHave = false;
    public  int color;
    public float width;
    public float startPadding;
    public float endPadding;

    public SideLine(boolean isHave, int color, float width, float startPadding, float endPadding) {
        this.isHave = isHave;
        this.color = color;
        this.width = width;
        this.startPadding = startPadding;
        this.endPadding = endPadding;
    }

    public float getStartPaddingDp() {
        return startPadding;
    }

    public void setStartPaddingDp(float startPaddingDp) {
        this.startPadding = startPaddingDp;
    }

    public float getEndPaddingDp() {
        return endPadding;
    }

    public void setEndPaddingDp(float endPaddingDp) {
        this.endPadding = endPaddingDp;
    }

    public boolean isHave() {
        return isHave;
    }

    public void setHave(boolean have) {
        isHave = have;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getWidthDp() {
        return width;
    }

    public void setWidthDp(float widthDp) {
        this.width = widthDp;
    }
}
