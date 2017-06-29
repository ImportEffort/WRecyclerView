package com.example.wsj.recyclerviewhelper.itemDecoration;

import android.content.Context;

/**
 * Created by wangshijia on 2017/6/28 下午6:35.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public class NoHeadFooterLineDecoration extends BaseItemDecoration {

    private int color = 0x000000;
    private int widthDp = 0;
    private float startPadding = 0;
    private float endPadding = 0;
    private boolean isHaveLoadingFooter;

    public NoHeadFooterLineDecoration(Context context, int color, int widthDp, float startPadding, float endPadding) {
        super(context);
        this.color = color;
        this.widthDp = widthDp;
        this.startPadding = startPadding;
        this.endPadding = endPadding;
    }

    @Override
    public Divide getDivider(int itemPosition, int itemCount) {
        Divide divide;
        int footerPosition = isHaveLoadingFooter ? itemCount - 2 : itemCount - 1;
        int loadPosition = itemCount - 1;
        if (itemPosition == 0 || itemPosition == footerPosition ||
                isHaveLoadingFooter && (itemPosition == loadPosition)) {
            divide = new DivideBuilder()
                    .setBottomSideLine(false, 0, 0, 0, 0)
                    .build();
        } else if (itemPosition == 1) {
            divide = new DivideBuilder()
                    .setTopSideLine(true, color, widthDp, startPadding, endPadding)
                    .setBottomSideLine(true, color, widthDp, startPadding, endPadding)
                    .setLeftSideLine(true, color, widthDp, startPadding, endPadding)
                    .setRightSideLine(true, color, widthDp, startPadding, endPadding)
                    .build();
        } else {
            divide = new DivideBuilder()
                    .setBottomSideLine(true, color, widthDp, startPadding, endPadding)
                    .setLeftSideLine(true, color, widthDp, startPadding, endPadding)
                    .setRightSideLine(true, color, widthDp, startPadding, endPadding)
                    .build();
        }
        return divide;
    }

    public void setHaveLoadingFooter(boolean haveLoadingFooter) {
        isHaveLoadingFooter = haveLoadingFooter;
    }
}
