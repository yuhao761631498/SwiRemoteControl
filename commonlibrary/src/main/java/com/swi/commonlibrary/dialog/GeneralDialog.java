package com.swi.commonlibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import com.swi.commonlibrary.R;

import static android.view.View.OVER_SCROLL_NEVER;


/**
 * Created by shangbeibei on 2016/12/13.
 * 2.0通用白色dialog (可滚动TextView，可设置Content的位置 【居左/居中】)
 * <p>针对不同的风格</p>
 * <p>这款清爽风格</p>
 */
public abstract class GeneralDialog extends Dialog implements View.OnClickListener {

    private TextView mGeneral_Title;//标题
    private TextView mGeneral_center_content;// content （单行/多行）
    private TextView mNegative;//取消
    private TextView mPositive;//确定
    private ScrollView mScrollView;
    private View mView_SplitLine;
    private LinearLayout mGeneral_layout;

    private boolean isAutoDismiss = true;
    private int mCountNum = -1;
    private CountDownTime mTime;
    private String mPositiveText;

    public GeneralDialog(Context context, int theme) {
        super(context, theme);
        isAutoDismiss = true;
        initView();
    }

    public GeneralDialog(Context context, int theme, boolean isAutoDismiss) {
        super(context, theme);
        this.isAutoDismiss = isAutoDismiss;
        initView();
    }

    private void initView() {
        setContentView(R.layout.general_title_dialog);
        mGeneral_layout = (LinearLayout) findViewById(R.id.general_dialog);
        mGeneral_Title = ((TextView) findViewById(R.id.general_title));
        mScrollView = ((ScrollView) findViewById(R.id.scrollview));
        mView_SplitLine = ((View) findViewById(R.id.view_split_line));
        mGeneral_center_content = ((TextView) findViewById(R.id.general_center_content));
        mNegative = ((TextView) findViewById(R.id.general_negative));
        mPositive = ((TextView) findViewById(R.id.general_positive));
        mNegative.setOnClickListener(this);
        mPositive.setOnClickListener(this);
        mScrollView.setOverScrollMode(OVER_SCROLL_NEVER);
    }

    public void setPositiveButtonText(int id) {
        mPositive.setText(id);
    }
    public void setPositiveButtonText(String text) {
        mPositive.setText(text);
    }

    public void setPositiveButtonText(String text, int countNum) {
        mCountNum = countNum;
        mPositiveText = text;
        mPositive.setText(text+"("+countNum+")");
    }

    public void setNegativeButtonText(int id) {
        mNegative.setText(id);
    }

    public void setTitleText(String text) {
        mGeneral_Title.setText(text);
    }

    public void setNoTitle() {mGeneral_Title.setVisibility(View.GONE);}

    public void setOnlycontent() {
        mGeneral_Title.setVisibility(View.GONE);
    }

    public void setTitleText(int id) {
        mGeneral_Title.setText(id);
    }

    public void setContentText(String text) {
        mGeneral_center_content.setText(text);
    }

    public void setContentText(int id) {
        mGeneral_center_content.setText(id);
    }

    class CountDownTime extends CountDownTimer {

        //构造函数  第一个参数代表总的计时时长  第二个参数代表计时间隔  单位都是毫秒
        public CountDownTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) { //每计时一次回调一次该方法
            mPositive.setText( mPositiveText+"("+l/1000+")");
        }

        @Override
        public void onFinish() { //计时结束回调该方法
            mPositive.setText( mPositiveText+"(0)");
            if(GeneralDialog.this != null && isShowing()) {
                positiveOnClick();
                dismiss();
            }
        }
    }

    @Override
    public void show() {
        super.show();
        if(mCountNum > 0){
            mTime = new CountDownTime(mCountNum * 1000, 1000);
            mTime.start();
        }
    }

    public void setGeneralDialogWidth(int size) {
        ViewGroup.LayoutParams layoutParams = mGeneral_layout.getLayoutParams();
        layoutParams.width = size;
        mGeneral_layout.setLayoutParams(layoutParams);
    }

    public void setScrollViewHeight(int size) {
        ViewGroup.LayoutParams scrollparams = mScrollView.getLayoutParams();
        scrollparams.height = size;
        mScrollView.setLayoutParams(scrollparams);
    }

    public void setScrollViewPadding(int size) {
        mScrollView.setPadding(size,0,size,0);
    }

    public abstract void positiveOnClick();

    public abstract void negativeOnClick();

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.general_negative) {
            negativeOnClick();
            if (isAutoDismiss) {
                dismiss();
            }
        } else if (id == R.id.general_positive) {
            positiveOnClick();
            if (isAutoDismiss) {
                dismiss();
            }
        } else {
            dismiss();
        }
    }



    /**
     * <p>设置Content的 位置 【居左/居中】</p>
     *
     * @param gravity
     */
    public void setContentGravity(int gravity) {

        mGeneral_center_content.setGravity(gravity);
    }

    public void setOnlyButton() {
        mView_SplitLine.setVisibility(View.GONE);
        mNegative.setVisibility(View.GONE);
    }

    /**
     * <p>设置 Content 过多时候的 ScrollView 的最大高度 </p>
     *
     * @param height
     */
    public void setScrollViewHight(int height) {
        ViewGroup.LayoutParams layout = mScrollView.getLayoutParams();
        layout.height = height;
        mScrollView.setLayoutParams(layout);
    }

    private boolean isCheck;

    private class DialogCheckBox implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            isCheck = isChecked;
        }
    }
}
