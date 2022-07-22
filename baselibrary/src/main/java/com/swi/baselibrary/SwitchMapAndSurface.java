package com.swi.baselibrary;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import static com.swi.baselibrary.R.*;

/**
 * Copyright (C), 2020-2030, 武汉中旗生物医疗电子有限公司
 * <p>
 * 功能描述: 地图与图像界面切换
 * <p>
 * 创建时间: 2022/7/20 17:23
 *
 * @author yuhao
 */
public class SwitchMapAndSurface {

    private static final String TAG = "SwitchMapAndSurface";

    /**
     * 窗口控件和全屏控件的切换
     *
     * @param context    上下文
     * @param viewWindow 窗口控件
     * @param FullWindow 全屏控件
     */
    public static void switchWindow(Context context, View viewWindow, View FullWindow,
                                    AnimationEndListener animationEndListener) {

        final float window_width = context.getResources().getDimension(dimen.window_width);

        final float window_height = context.getResources().getDimension(dimen.window_height);

        final float margin_left = context.getResources().getDimension(dimen.window_margin_left);

        final float margin_bottom = context.getResources().getDimension(dimen.window_margin_bottom);

        final float scaleWidth = GlobalVariable.screenWidth - window_width - margin_left;

        final float scaleHeight = GlobalVariable.screenHeight - window_height - margin_bottom;

        ConstraintLayout.LayoutParams viewWindowLayoutParams =
                (ConstraintLayout.LayoutParams) viewWindow.getLayoutParams();

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) FullWindow.getLayoutParams();

        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(1000);
        ofFloat.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                viewWindowLayoutParams.width += margin_left;
                viewWindowLayoutParams.height += margin_bottom;

                viewWindowLayoutParams.leftMargin = 0;
                viewWindowLayoutParams.rightMargin = 0;
                viewWindowLayoutParams.bottomMargin = 0;
                viewWindowLayoutParams.topMargin = 0;
                viewWindow.setLayoutParams(viewWindowLayoutParams);

                layoutParams.width = (int) window_width;
                layoutParams.height = (int) window_height;
                layoutParams.bottomMargin = (int) margin_bottom;
                layoutParams.leftMargin = (int) margin_left;
                FullWindow.setLayoutParams(layoutParams);
                FullWindow.bringToFront();

                if (animationEndListener != null) {
                    animationEndListener.animationEnd();
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        ofFloat.addUpdateListener(valueAnimator -> {
            float floatValue = (Float) valueAnimator.getAnimatedValue();
            float width = window_width + scaleWidth * floatValue;
            float height = window_height + scaleHeight * floatValue;
            viewWindowLayoutParams.width = (int) width;
            viewWindowLayoutParams.height = (int) height;
            viewWindow.setLayoutParams(viewWindowLayoutParams);
        });
        ofFloat.start();
    }


    public interface AnimationEndListener {
        void animationEnd();
    }

}
