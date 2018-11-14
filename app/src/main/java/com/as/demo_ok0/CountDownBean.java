package com.as.demo_ok0;

/**
 * -----------------------------
 * Created by zqf on 2018/11/13.
 * ---------------------------
 */
public class CountDownBean {
    private int time;
    private boolean isSelect;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public CountDownBean(int time, boolean isSelect) {
        this.time = time;
        this.isSelect = isSelect;
    }
}
