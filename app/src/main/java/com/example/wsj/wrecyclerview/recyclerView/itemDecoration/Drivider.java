package com.example.wsj.wrecyclerview.recyclerView.itemDecoration;

/**
 * Created by wangshijia on 2017/6/28 下午5:04.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

//每个条目分为左右上下4条线
public class Drivider {

    public SideLine leftSideLine;
    public SideLine topSideLine;
    public SideLine rightSideLine;
    public SideLine bottomSideLine;


    public Drivider(SideLine leftSideLine, SideLine topSideLine, SideLine rightSideLine, SideLine bottomSideLine) {
        this.leftSideLine = leftSideLine;
        this.topSideLine = topSideLine;
        this.rightSideLine = rightSideLine;
        this.bottomSideLine = bottomSideLine;
    }

    public SideLine getLeftSideLine() {
        return leftSideLine;
    }

    public void setLeftSideLine(SideLine leftSideLine) {
        this.leftSideLine = leftSideLine;
    }

    public SideLine getTopSideLine() {
        return topSideLine;
    }

    public void setTopSideLine(SideLine topSideLine) {
        this.topSideLine = topSideLine;
    }

    public SideLine getRightSideLine() {
        return rightSideLine;
    }

    public void setRightSideLine(SideLine rightSideLine) {
        this.rightSideLine = rightSideLine;
    }

    public SideLine getBottomSideLine() {
        return bottomSideLine;
    }

    public void setBottomSideLine(SideLine bottomSideLine) {
        this.bottomSideLine = bottomSideLine;
    }
}
