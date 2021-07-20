package com.js.styledtextview.seekbar;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class VerticalSeekBarWrapper extends FrameLayout {
    public VerticalSeekBarWrapper(Context context) {
        this(context, null, 0);
    }

    public VerticalSeekBarWrapper(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalSeekBarWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (useViewRotation()) {
            onSizeChangedUseViewRotation(w, h, oldw, oldh);
        } else {
            onSizeChangedTraditionalRotation(w, h, oldw, oldh);
        }
    }

    @SuppressLint("WrongConstant")
    private void onSizeChangedTraditionalRotation(int w, int h, int oldw, int oldh) {
        VerticalSeekBar seekBar = getChildSeekBar();
        if (seekBar != null) {
            LayoutParams lp = (LayoutParams) seekBar.getLayoutParams();
            lp.width = -2;
            lp.height = h;
            seekBar.setLayoutParams(lp);
            seekBar.measure(0, 0);
            int seekBarWidth = seekBar.getMeasuredWidth();
            seekBar.measure(MeasureSpec.makeMeasureSpec(w, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(h, 1073741824));
            lp.gravity = 51;
            lp.leftMargin = (w - seekBarWidth) / 2;
            seekBar.setLayoutParams(lp);
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @SuppressLint("WrongConstant")
    private void onSizeChangedUseViewRotation(int w, int h, int oldw, int oldh) {
        VerticalSeekBar seekBar = getChildSeekBar();
        if (seekBar != null) {
            seekBar.measure(MeasureSpec.makeMeasureSpec(h, 1073741824), MeasureSpec.makeMeasureSpec(w, Integer.MIN_VALUE));
        }
        applyViewRotation(w, h);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int seekBarWidth;
        int seekBarHeight;
        VerticalSeekBar seekBar = getChildSeekBar();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (seekBar == null || widthMode == 1073741824) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        if (useViewRotation()) {
            seekBar.measure(heightMeasureSpec, widthMeasureSpec);
            seekBarWidth = seekBar.getMeasuredHeight();
            seekBarHeight = seekBar.getMeasuredWidth();
        } else {
            seekBar.measure(widthMeasureSpec, heightMeasureSpec);
            seekBarWidth = seekBar.getMeasuredWidth();
            seekBarHeight = seekBar.getMeasuredHeight();
        }
        setMeasuredDimension(ViewCompat.resolveSizeAndState(seekBarWidth, widthMeasureSpec, 0), ViewCompat.resolveSizeAndState(seekBarHeight, heightMeasureSpec, 0));
    }

    /* access modifiers changed from: package-private */
    public void applyViewRotation() {
        applyViewRotation(getWidth(), getHeight());
    }

    private void applyViewRotation(int w, int h) {
        VerticalSeekBar seekBar = getChildSeekBar();
        if (seekBar != null) {
            ViewGroup.LayoutParams lp = seekBar.getLayoutParams();
            int rotationAngle = seekBar.getRotationAngle();
            int w2 = seekBar.getPaddingTop() + seekBar.getPaddingBottom() + (seekBar.getThumbOffset() * 2);
            lp.width = h;
            lp.height = -2;
            seekBar.setLayoutParams(lp);
            ViewCompat.setRotation(seekBar, (float) toRotationAngleToDegrees(rotationAngle));
            ViewCompat.setTranslationX(seekBar, ((float) (-(h - w))) * 0.5f);
            ViewCompat.setTranslationY(seekBar, Math.max(0.0f, ((float) (h - Math.max(w, w2))) * 0.5f));
        }
    }

    private VerticalSeekBar getChildSeekBar() {
        View child;
        if (getChildCount() > 0) {
            child = getChildAt(0);
        } else {
            child = null;
        }
        if (child instanceof VerticalSeekBar) {
            return (VerticalSeekBar) child;
        }
        return null;
    }

    private boolean useViewRotation() {
        VerticalSeekBar seekBar = getChildSeekBar();
        if (seekBar != null) {
            return seekBar.useViewRotation();
        }
        return false;
    }

    private static int toRotationAngleToDegrees(int angle) {
        switch (angle) {
            case 90:
                return 90;
            case VerticalSeekBar.ROTATION_ANGLE_CW_270:
                return -90;
            default:
                return 0;
        }
    }
}
