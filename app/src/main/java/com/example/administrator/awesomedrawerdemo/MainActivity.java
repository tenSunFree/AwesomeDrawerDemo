package com.example.administrator.awesomedrawerdemo;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.hx.curtain.drawer.CurtainContentLayout;
import com.like.LikeButton;

public class MainActivity extends AppCompatActivity {

    private LinearLayout
            selectLinearLayout, resultDisplayLinearLayout,
            borderLinearLayout1, borderLinearLayout2, borderLinearLayout3;
    private CurtainContentLayout curtainContentLayout1, curtainContentLayout2, curtainContentLayout3;
    private StateListDrawable stateListDrawable;
    private GradientDrawable gradientDrawable;
    private LikeButton likeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,                          // 去除状态栏
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initializationView();
        initializationCurtainLayoutListener();
    }

    private void initializationView() {
        curtainContentLayout1 = findViewById(R.id.curtainContentLayout1);
        curtainContentLayout2 = findViewById(R.id.curtainContentLayout2);
        curtainContentLayout3 = findViewById(R.id.curtainContentLayout3);
        selectLinearLayout = findViewById(R.id.selectLinearLayout);
        resultDisplayLinearLayout = findViewById(R.id.resultDisplayLinearLayout);
        borderLinearLayout1 = findViewById(R.id.borderLinearLayout1);
        borderLinearLayout2 = findViewById(R.id.borderLinearLayout2);
        borderLinearLayout3 = findViewById(R.id.borderLinearLayout3);
        likeButton = findViewById(R.id.likeButton);
    }

    private void initializationCurtainLayoutListener() {
        curtainContentLayout1.setCurtainLayoutListener(new CurtainContentLayout.OnCurtainLayoutListener() {
            @Override
            public void onSlide(View caurtainLayout, float slideOffset) {
                if (slideOffset == 1) {
                    showSelectedResults(borderLinearLayout1);
                }
            }
        });
        curtainContentLayout2.setCurtainLayoutListener(new CurtainContentLayout.OnCurtainLayoutListener() {
            @Override
            public void onSlide(View caurtainLayout, float slideOffset) {
                if (slideOffset == 1) {
                    showSelectedResults(borderLinearLayout2);
                }
            }
        });
        curtainContentLayout3.setCurtainLayoutListener(new CurtainContentLayout.OnCurtainLayoutListener() {
            @Override
            public void onSlide(View caurtainLayout, float slideOffset) {
                if (slideOffset == 1) {
                    showSelectedResults(borderLinearLayout3);
                }
            }
        });
    }

    @NonNull
    private View.OnTouchListener setStopTouchListener(boolean isStopTouch) {
        if (isStopTouch) {
            return new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            };
        } else {
            return new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return false;
                }
            };
        }
    }

    private void showSelectedResults(final LinearLayout layoutId) {
        gradientDrawable = getGradientDrawable(getResources().getColor(R.color.colorBrightenTheBorder));
        stateListDrawable = getSelector(gradientDrawable);
        layoutId.setBackground(stateListDrawable);
        curtainContentLayout1.setOnTouchListener(setStopTouchListener(true));
        curtainContentLayout2.setOnTouchListener(setStopTouchListener(true));
        curtainContentLayout3.setOnTouchListener(setStopTouchListener(true));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                selectLinearLayout.setVisibility(View.INVISIBLE);
                resultDisplayLinearLayout.setVisibility(View.VISIBLE);
                if (layoutId == borderLinearLayout1) {
                    likeButton.setLikeDrawableRes(R.drawable.ghost2);
                } else if (layoutId == borderLinearLayout2) {
                    likeButton.setLikeDrawableRes(R.drawable.mushroom3);
                } else if (layoutId == borderLinearLayout3) {
                    likeButton.setLikeDrawableRes(R.drawable.co_star_luma2);
                }
                likeButton.performClick();
                likeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
            }
        }, 2000);
    }

    /**
     * 設定背景選擇器
     */
    private StateListDrawable getSelector(Drawable normalDraw) {
        stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{}, normalDraw);
        return stateListDrawable;
    }

    /**
     * 設定shape
     */
    private GradientDrawable getGradientDrawable(int fillColor) {
        gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(fillColor);
        return gradientDrawable;
    }
}
