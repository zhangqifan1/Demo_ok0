


package com.as.demo_ok0;

import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.as.demo_ok0.bean.EventTimeBean;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    public List<CountDownBean> list;
    private TextView tvTime;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initView() {
        TextView tvDialog = findViewById(R.id.tvDialog);
        tvTime = findViewById(R.id.tvTime);

        CountDownBean countDownBean0 = new CountDownBean(0, false);
        CountDownBean countDownBean1 = new CountDownBean(1, false);
        CountDownBean countDownBean2 = new CountDownBean(2, false);
        CountDownBean countDownBean3 = new CountDownBean(3, false);
        CountDownBean countDownBean4 = new CountDownBean(4, false);
        CountDownBean countDownBean5 = new CountDownBean(5, false);

        list = new ArrayList<>();
        list.add(countDownBean0);
        list.add(countDownBean1);
        list.add(countDownBean2);
        list.add(countDownBean3);
        list.add(countDownBean4);
        list.add(countDownBean5);

        list.get(App.getClickpositon()).setSelect(true);
        final CustomBaseDialog customBaseDialog = new CustomBaseDialog(this);
        customBaseDialog.setList(list);


        int time = list.get(App.getClickpositon()).getTime();
        //实例化
        if (time != 0  &&  !TimeService.isstarting) {
            starttime(time);
        }

        tvDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customBaseDialog.show();

                Adapter adapter = customBaseDialog.getAdapter();
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        click(adapter, position, customBaseDialog);
                    }
                });
                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        click(adapter, position, customBaseDialog);
                    }
                });
            }
        });
    }

    private void starttime(int time) {
        Intent intent = new Intent(this, TimeService.class);
        intent.setAction(TimeService.ACTION_START);
        intent.putExtra("time", time);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    public void click(BaseQuickAdapter adapter, int position, CustomBaseDialog customBaseDialog) {
        int timelast = list.get(App.getClickpositon()).getTime();
        App.setClickpositon(position);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setSelect(false);
        }
        list.get(position).setSelect(true);
        adapter.notifyDataSetChanged();

        customBaseDialog.setList(list);
        customBaseDialog.dismiss();

        int time1 = list.get(position).getTime();
        if (time1 == 0) {
            stoptime();
        } else if (time1 == timelast) {//和当前设置的是同一个 那么不用动
            return;
        } else {
         starttime(time1);
        }
    }

    private void stoptime() {
        Intent intent = new Intent(this, TimeService.class);
        intent.setAction(TimeService.ACTION_STOP);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
        tvTime.setText("显示时间");
    }

    @Override
    public boolean useEventBus() {
        return true;
    }

    @Subscribe(sticky = true)
    public void onReceiveEvent(EventTimeBean eventTimeBean) {
        int type = eventTimeBean.getType();
        switch (type) {
            case 0:
                long millisUntilFinished = eventTimeBean.getTickBean().getMillisUntilFinished();
                tvTime.setText(millisUntilFinished / 1000 + "秒");
                break;
            case 1:
                tvTime.setText("完成倒计时");
                break;
            default:
                break;
        }

    }
}
