package com.example.wsj.wrecyclerview.recyclerView.itemDecoration;

import android.content.Context;

/**
 * Created by wangshijia on 2017/6/28 下午6:35.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public class NoHeadFooterLineDeoration extends BaseItemDecoration {

    private int color = 0x000000;
    private int widthDp = 0;
    private float startPadding = 0;
    private float endPadding = 0;

    public NoHeadFooterLineDeoration(Context context, int color, int widthDp, float startPadding, float endPadding) {
        super(context);
        this.color = color;
        this.widthDp = widthDp;
        this.startPadding = startPadding;
        this.endPadding = endPadding;
    }

    public NoHeadFooterLineDeoration(Context context) {
        super(context);
    }

    @Override
    public Drivider getDivider(int itemPosition, int itemCount) {
        Drivider drivider = null;
        int footerPosition = itemCount - 2;
        int loadPosition = itemCount - 1;
        if (itemPosition == 0 || itemPosition == footerPosition || itemPosition == loadPosition) {
            drivider = new DrividerBuilder()
                    .setBottomSideLine(false, 0, 0, 0, 0)
                    .build();
        } else if (itemPosition == 1) {
            drivider = new DrividerBuilder()
                    .setTopSideLine(true, color, widthDp, startPadding, endPadding)
                    .setBottomSideLine(true, color, widthDp, startPadding, endPadding)
                    .setLeftSideLine(true, color, widthDp, startPadding, endPadding)
                    .setRightSideLine(true, color, widthDp, startPadding, endPadding)
                    .build();
        } else {
            drivider = new DrividerBuilder()
                    .setBottomSideLine(true, color, widthDp, startPadding, endPadding)
                    .setLeftSideLine(true, color, widthDp, startPadding, endPadding)
                    .setRightSideLine(true, color, widthDp, startPadding, endPadding)
                    .build();
        }
        return drivider;
    }
}
