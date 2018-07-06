package com.renyi.maxsin.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by zhangyuliang on 2018/5/18.
 */

public class ConflictViewPager extends ViewPager {
    private float xDown;
    private float xMove;
    private float yDown;
    private float yMove;
    private boolean viewpagersroll = false;

    public ConflictViewPager(Context context) {
        super(context);
    }

    public ConflictViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            xDown = ev.getRawX();
            yDown = ev.getRawY();
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            xMove = ev.getRawX();
            yMove = ev.getRawY();
            if (viewpagersroll) {
                getParent().requestDisallowInterceptTouchEvent(true);
                return super.dispatchTouchEvent(ev);
            }
            if (Math.abs(yMove - yDown) < 5 && Math.abs(xMove - xDown) > 20) {
                viewpagersroll = true;
            } else {
                viewpagersroll = false;
                return false;
            }
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            viewpagersroll = false;
        }
        return super.dispatchTouchEvent(ev);
    }
}
