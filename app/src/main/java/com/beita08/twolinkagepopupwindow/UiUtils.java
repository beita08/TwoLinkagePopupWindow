package com.beita08.twolinkagepopupwindow;

import android.content.Context;

public class UiUtils {

    //dp尺寸转为px
    public static int dp2px(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    //px转为dp尺寸
    public static int px2dp(Context context, int px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
    }
}
