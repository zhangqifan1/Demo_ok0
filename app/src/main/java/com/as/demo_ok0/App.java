package com.as.demo_ok0;

import android.app.Application;

/**
 * -----------------------------
 * Created by zqf on 2018/11/13.
 * ---------------------------
 */
public class App extends Application {
    //主要是用来记录切换到哪个分钟数了
    private static int clickpositon = 0;

    public static int getClickpositon() {
        return clickpositon;
    }

    public static void setClickpositon(int clickpositon) {
        App.clickpositon = clickpositon;
    }
}
