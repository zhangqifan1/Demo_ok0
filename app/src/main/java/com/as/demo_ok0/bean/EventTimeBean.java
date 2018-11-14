package com.as.demo_ok0.bean;

/**
 * -----------------------------
 * Created by zqf on 2018/11/14.
 * ---------------------------
 */
public class EventTimeBean {

    //type 0 是tick 的   1是 finish
    private int type;

    public EventTimeBean(int type) {
        this.type = type;
    }

    private TickBean tickBean;

    public EventTimeBean(int type, TickBean tickBean) {
        this.type = type;
        this.tickBean = tickBean;
    }

    public TickBean getTickBean() {
        return tickBean;
    }

    public void setTickBean(TickBean tickBean) {
        this.tickBean = tickBean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
