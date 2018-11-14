package com.as.demo_ok0;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.as.demo_ok0.databinding.DialogCountdownBinding;
import com.flyco.animation.Attention.Swing;
import com.flyco.dialog.widget.base.BaseDialog;

import java.util.List;

public class CustomBaseDialog extends BaseDialog<CustomBaseDialog> {
    private Adapter adapter;
    private List<CountDownBean> list;

    public void setList(List<CountDownBean> list) {
        this.list = list;
    }

    public CustomBaseDialog(Context context) {
        super(context);
    }

    public Adapter getAdapter() {
        return adapter;
    }

    @Override
    public View onCreateView() {
        widthScale(0.85f);
        showAnim(new Swing());


        View inflate = View.inflate(mContext, R.layout.dialog_countdown, null);
        DialogCountdownBinding bind = DataBindingUtil.bind(inflate);
        adapter = new Adapter(R.layout.item_countdown, list);
        bind.recyclerView.setAdapter(adapter);
        bind.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        return inflate;
    }

    
    @Override
    public void setUiBeforShow() {
    }

}