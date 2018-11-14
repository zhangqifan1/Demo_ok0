package com.as.demo_ok0;

import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * -----------------------------
 * Created by zqf on 2018/11/13.
 * ---------------------------
 */
public class Adapter extends BaseQuickAdapter<CountDownBean, BaseViewHolder> {

    public Adapter(int layoutResId, @Nullable List<CountDownBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CountDownBean item) {
        View view1 = helper.getView(R.id.rela);
        int[] attrs = new int[]{R.attr.selectableItemBackground};
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs);
        int backgroundResource = typedArray.getResourceId(0, 0);
        view1.setBackgroundResource(backgroundResource);

        int time = item.getTime();
        if(time==0){
            helper.setText(R.id.tvText, "不开启");
        }else{
            helper.setText(R.id.tvText, time + "分钟");
        }
        RadioButton butSelect = helper.getView(R.id.butSelect);
        butSelect.setChecked(item.isSelect());

        helper.addOnClickListener(R.id.butSelect);
    }
}
