package com.imwmw.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by simon on 6/30/15.
 */
public class PullDownView extends View{
    private static final String TAG = "imwmw";

    public PullDownView(Context context) {
        super(context);
    }

    public PullDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float mPastX;
    private float mPastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();

        float x = event.getX();
        float y = event.getY();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                mPastX = x;
                mPastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.v(TAG, "motion event down/move: " + x + ":" + y);

                float movedY = y - mPastY;
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) getLayoutParams();

                lp.setMargins(0, (int) (lp.topMargin + movedY), 0, 0);
                setLayoutParams(lp);
                invalidate();

                mPastX = x;
                mPastY = y;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }
}
