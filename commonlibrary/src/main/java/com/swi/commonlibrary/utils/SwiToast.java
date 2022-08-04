package com.swi.commonlibrary.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swi.commonlibrary.R;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by xx on 2017-7-1.
 */
public class SwiToast {
    private final int WHAT_CANCEL = 0;
    private final int WHAT_ADD = 1;
    private final int SHOW_DURATION = 3000;
    private View mToastView;//自定义toast view
    private TextView[] mTextView;
    private Boolean mIsShow;//记录状态 是否在显示
    /**************************
     * 是否已经初始化状态了---xx
     */
    private boolean isOpen;
    private Thread thread;
    private ToastBean[] showBeans;//已经显示的----xx

    private boolean UIisShow;

    private BlockingQueue<String> needShowBeans;//即将需要显示的----xx

    private ViewGroup decorView;
    private Animation anim_in, anim_out;

    private void init(Activity context) {
        if (decorView != null) return;
        decorView = (ViewGroup) context.findViewById(R.id.content_show_toast); //(ViewGroup) context.getWindow()
        // .getDecorView().findViewById(android.R.id.content);
        anim_in = AnimationUtils.loadAnimation(context, R.anim.toast_enter);
        anim_out = AnimationUtils.loadAnimation(context, R.anim.toast_exit);
        mIsShow = false;// 记录当前Toast的内容是否已经在显示
        mTextView = new TextView[5];
        //这里初始化toast view
        mToastView = LayoutInflater.from(context).inflate(R.layout.common_toast, null);

        mTextView[0] = ((TextView) mToastView.findViewById(R.id.toast_text));
        mTextView[1] = ((TextView) mToastView.findViewById(R.id.toast_text1));
        mTextView[2] = ((TextView) mToastView.findViewById(R.id.toast_text2));
        mTextView[3] = ((TextView) mToastView.findViewById(R.id.toast_text3));
        mTextView[4] = ((TextView) mToastView.findViewById(R.id.toast_text4));
        setParams(context);
        showBeans = new ToastBean[5];
        needShowBeans = new LinkedBlockingDeque<>();
        isOpen = true;
        thread = new Thread(runnable);
        thread.start();
    }

    public SwiToast(Activity context) {
        init(context);
    }

    private LinearLayout.LayoutParams mParams;

    private void setParams(Context context) {
        int popX =
                ViewUtils.getWindowWidth(context) - context.getResources().getDimensionPixelOffset(R.dimen.Pad_151_Ron);//屏幕宽度的一半 - popwindow的一半   //余浩
        int popY = context.getResources().getDimensionPixelOffset(R.dimen.Pad_35_Ron);
        mParams = new LinearLayout.LayoutParams
                (context.getResources().getDimensionPixelOffset(R.dimen.Pad_151_Ron),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        mParams.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        mParams.setMargins(popX / 2, popY, 0, 0);
    }

    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_CANCEL://cancel
                    cancel(msg.arg1);
                    break;

                case WHAT_ADD://add
                    addShow(msg.obj.toString());
                    break;
            }
        }
    };

    private void cancel(int index) {
        {
            mTextView[index].setVisibility(View.GONE);
            showBeans[index] = null;
        }

        for (ToastBean toastBean : showBeans) {
            if (toastBean != null)
                return;
        }
        {
            decorView.removeView(mToastView);
        }
        mIsShow = false;
    }

    private void addShow(String text) {
        ToastBean bean = new ToastBean(text);
        if (mIsShow) {// 如果Toast已经在显示 就先给隐藏了
            for (int i = 0; i < showBeans.length; i++) {
                if (showBeans[i] == null) {
                    mTextView[i].setText(text);
                    mTextView[i].setVisibility(View.VISIBLE);
                    showBeans[i] = bean;
                    break;
                }
            }
        } else {
            showBeans[0] = bean;
            //设置显示内容
            mTextView[0].setText(text);
            mTextView[0].setVisibility(View.VISIBLE);
            //设置显示状态
            mIsShow = true;
            // 将其加载到windowManager上s
            {
                decorView.addView(mToastView);
            }
        }
    }

    private long currentTime;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            try {
                while (isOpen) {
                    if (needShowBeans == null) break;
                    String txt = needShowBeans.poll(500, TimeUnit.MILLISECONDS);
                    currentTime = System.currentTimeMillis();
                    //先减后加
                    for (int i = 0; i < showBeans.length; i++) {
                        if (showBeans[i] != null) {
                            if (currentTime - showBeans[i].happenTime > SHOW_DURATION) {
                                handler.obtainMessage(WHAT_CANCEL, i, 0).sendToTarget();
                            }
                        }
                    }
                    if (txt != null) {
                        handler.obtainMessage(WHAT_ADD, txt).sendToTarget();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private long lastShowTime;

    /********************
     * 需要Toast显示的字符串，可以在线程中调用
     * @param str
     */
    public synchronized void showMsg(String str) {
        if (!UIisShow || !isOpen) return;
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShowTime < 50) {
            return;
        }
        lastShowTime = currentTime;
        for (ToastBean toastBean : showBeans) {
            if (toastBean != null)
                if (toastBean != null && toastBean.showTxt.equals(str)) return;
        }

        for (String str1 : needShowBeans) {
            if (str1 != null && str1.equals(str)) return;
        }
        needShowBeans.add(str);
    }

    public void onDestroy() {
        decorView.removeAllViews();
        isOpen = false;
        thread.interrupt();
    }

    public void onResume() {
        UIisShow = true;
    }

    public void onPause() {
        UIisShow = false;
    }

    public static class ToastBean {
        public String showTxt;

        public long happenTime;

        public ToastBean(String showTxt) {
            this.showTxt = showTxt;
            this.happenTime = System.currentTimeMillis();
        }
    }


}
