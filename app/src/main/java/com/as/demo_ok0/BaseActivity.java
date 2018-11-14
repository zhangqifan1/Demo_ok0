package com.as.demo_ok0;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;


import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * -----------------------------
 * Created by zqf on 2018/1/22.
 * ---------------------------
 */

public abstract class BaseActivity<B extends ViewDataBinding> extends SupportActivity {

    /**
     * 网络类型
     */
    private int netMobile;
    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * 屏幕是否发生过旋转
     */

    protected B mViewBinding;
    private View rootView;
    private boolean b = false;

    @TargetApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(this.getLayoutId(), null, false);
        rootView.setFitsSystemWindows(true);
        beforeSetContentView(rootView);
        this.setContentView(rootView);

        try {
            mViewBinding = DataBindingUtil.bind(rootView);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            initFontScale();
            beforeInitView(savedInstanceState);
            initView();
            initData();
            initListener();

        }
    }

    protected void beforeSetContentView(View rootView) {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this) && useEventBus()) {//加上判断
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDestroy() {
        if (EventBus.getDefault().isRegistered(this) && useEventBus()) {
            EventBus.getDefault().unregister(this);
//            EventBus.getDefault().removeAllStickyEvents();
        }
        super.onDestroy();
    }

    private void initFontScale() {
        Configuration configuration = getResources().getConfiguration();
        configuration.fontScale = (float) 1;
        //0.85 小, 1 标准大小, 1.15 大，1.3 超大 ，1.45 特大
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getBaseContext().getResources().updateConfiguration(configuration, metrics);
    }

    /**
     * 屏幕发生改变调用
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void setContentView(View rootView) {
        super.setContentView(rootView);
    }

    /**
     * 获取资源文件布局
     *
     * @return 资源布局文件layout
     */
    protected abstract int getLayoutId();


    protected void beforeInitView(Bundle savedInstanceState) {
        mContext = this;
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
    }

    /**
     * 获取传递的bundle数据
     *
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化对象
     */
    protected abstract void initData();


    /**
     * 初始化按钮监听
     */
    protected abstract void initListener();

    /**
     * 是否使用 {@link EventBus},默认为使用(false)，
     *
     * @return
     */
    public boolean useEventBus() {
        return false;
    }

    public void startActivity(Class clazz, boolean isFinish) {
        startActivity(new Intent(this, clazz));
        if (isFinish) {
            finish();
        }
    }

    public void startActivity(Class clazz) {
        startActivity(new Intent(this, clazz));
    }


    public static boolean isActivityRunning(Context mContext, String activityClassName) {
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> info = activityManager.getRunningTasks(1);
        if (info != null && info.size() > 0) {
            ComponentName component = info.get(0).topActivity;
            if (activityClassName.equals(component.getClassName())) {
                return true;
            }
        }
        return false;
    }



    @Override
    public void onBackPressedSupport() {
//        if (JZVideoPlayer.backPress()) {
//            return;
//        }
        super.onBackPressedSupport();
    }

}