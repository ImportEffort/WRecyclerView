package com.example.wsj.wrecyclerview.recyclerView.itemDecoration;

import android.support.annotation.ColorInt;

/**
 * Created by wangshijia on 2017/6/28 下午6:29.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public class DrividerBuilder {

    private SideLine leftSideLine;
    private SideLine topSideLine;
    private SideLine rightSideLine;
    private SideLine bottomSideLine;

    public DrividerBuilder setLeftSideLine(boolean isHave, @ColorInt int color, float widthDp, float startPaddingDp, float endPaddingDp) {
        this.leftSideLine = new SideLine(isHave, color, widthDp, startPaddingDp, endPaddingDp);
        return this;
    }

    public DrividerBuilder setTopSideLine(boolean isHave, @ColorInt int color, float widthDp, float startPaddingDp, float endPaddingDp) {
        this.topSideLine = new SideLine(isHave, color, widthDp, startPaddingDp, endPaddingDp);
        return this;
    }

    public DrividerBuilder setRightSideLine(boolean isHave, @ColorInt int color, float widthDp, float startPaddingDp, float endPaddingDp) {
        this.rightSideLine = new SideLine(isHave, color, widthDp, startPaddingDp, endPaddingDp);
        return this;
    }

    public DrividerBuilder setBottomSideLine(boolean isHave, @ColorInt int color, float widthDp, float startPaddingDp, float endPaddingDp) {
        this.bottomSideLine = new SideLine(isHave, color, widthDp, startPaddingDp, endPaddingDp);
        return this;
    }

    public Drivider build() {
        SideLine defaultSideLine = new SideLine(false, 0x000000, 0, 0, 0);
        leftSideLine = leftSideLine != null ? leftSideLine : defaultSideLine;
        topSideLine = topSideLine != null ? topSideLine : defaultSideLine;
        rightSideLine = rightSideLine != null ? rightSideLine : defaultSideLine;
        bottomSideLine = bottomSideLine != null ? bottomSideLine : defaultSideLine;
        return new Drivider(leftSideLine,topSideLine, rightSideLine, bottomSideLine);
    }
}
