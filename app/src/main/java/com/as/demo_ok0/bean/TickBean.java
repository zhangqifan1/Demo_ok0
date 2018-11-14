package com.as.demo_ok0.bean;

/**
 * -----------------------------
 * Created by zqf on 2018/11/14.
 * ---------------------------
 */
public class TickBean {
    private long millisUntilFinished;


    public long getMillisUntilFinished() {
        return millisUntilFinished;
    }

    public void setMillisUntilFinished(long millisUntilFinished) {
        this.millisUntilFinished = millisUntilFinished;
    }

    public TickBean(long millisUntilFinished) {
        this.millisUntilFinished = millisUntilFinished;
    }
}
