package com.js.styledtextview.seekbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.js.styledtextview.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class VerticalSeekBar extends AppCompatSeekBar {
    public static final int ROTATION_ANGLE_CW_270 = 270;
    public static final int ROTATION_ANGLE_CW_90 = 90;
    private boolean mIsDragging;
    private Method mMethodSetProgress;
    private int mRotationAngle = 90;
    private Drawable mThumb_;

    public VerticalSeekBar(Context context) {
        super(context);
        initialize(context, null, 0, 0);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs, 0, 0);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context, attrs, defStyle, 0);
    }

    @SuppressLint("WrongConstant")
    private void initialize(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        ViewCompat.setLayoutDirection(this, 0);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.VerticalSeekBar, defStyleAttr, defStyleRes);
            int rotationAngle = a.getInteger(0, 0);
            if (isValidRotationAngle(rotationAngle)) {
                this.mRotationAngle = rotationAngle;
            }
            a.recycle();
        }
    }

    public void setThumb(Drawable thumb) {
        this.mThumb_ = thumb;
        super.setThumb(thumb);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (useViewRotation()) {
            return onTouchEventUseViewRotation(event);
        }
        return onTouchEventTraditionalRotation(event);
    }

    private boolean onTouchEventTraditionalRotation(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }
        switch (event.getAction()) {
            case 0:
                setPressed(true);
                onStartTrackingTouch();
                trackTouchEvent(event);
                attemptClaimDrag(true);
                invalidate();
                break;
            case 1:
                if (this.mIsDragging) {
                    trackTouchEvent(event);
                    onStopTrackingTouch();
                    setPressed(false);
                } else {
                    onStartTrackingTouch();
                    trackTouchEvent(event);
                    onStopTrackingTouch();
                    attemptClaimDrag(false);
                }
                invalidate();
                break;
            case 2:
                if (this.mIsDragging) {
                    trackTouchEvent(event);
                    break;
                }
                break;
            case 3:
                if (this.mIsDragging) {
                    onStopTrackingTouch();
                    setPressed(false);
                }
                invalidate();
                break;
        }
        return true;
    }

    private boolean onTouchEventUseViewRotation(MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                attemptClaimDrag(true);
                break;
            case 1:
                attemptClaimDrag(false);
                break;
        }
        return super.onTouchEvent(event);
    }

    private void trackTouchEvent(MotionEvent event) {
        float scale;
        int paddingLeft = super.getPaddingLeft();
        int paddingRight = super.getPaddingRight();
        int height = getHeight();
        int available = (height - paddingLeft) - paddingRight;
        int y = (int) event.getY();
        float value = 0.0f;
        switch (this.mRotationAngle) {
            case 90:
                value = (float) (y - paddingLeft);
                break;
            case ROTATION_ANGLE_CW_270 /*{ENCODED_INT: 270}*/:
                value = (float) ((height - paddingLeft) - y);
                break;
        }
        if (value < 0.0f || available == 0) {
            scale = 0.0f;
        } else if (value > ((float) available)) {
            scale = 1.0f;
        } else {
            scale = value / ((float) available);
        }
        setProgress((int) (scale * ((float) getMax())), true);
    }

    private void attemptClaimDrag(boolean active) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(active);
        }
    }

    private void onStartTrackingTouch() {
        this.mIsDragging = true;
    }

    private void onStopTrackingTouch() {
        this.mIsDragging = false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean handled;
        if (isEnabled()) {
            int direction = 0;
            switch (keyCode) {
                case 19:
                    if (this.mRotationAngle == 270) {
                        direction = 1;
                    } else {
                        direction = -1;
                    }
                    handled = true;
                    break;
                case 20:
                    if (this.mRotationAngle == 90) {
                        direction = 1;
                    } else {
                        direction = -1;
                    }
                    handled = true;
                    break;
                case 21:
                case 22:
                    handled = true;
                    break;
                default:
                    handled = false;
                    break;
            }
            if (handled) {
                int keyProgressIncrement = getKeyProgressIncrement();
                int progress = getProgress() + (direction * keyProgressIncrement);
                if (progress < 0 || progress > getMax()) {
                    return true;
                }
                setProgress(progress - keyProgressIncrement, true);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        if (!useViewRotation()) {
            refreshThumb();
        }
    }

    public synchronized void setProgress(int progress, boolean fromUser) {
        if (this.mMethodSetProgress == null) {
            try {
                Method m = getClass().getMethod("setProgress", Integer.TYPE, Boolean.TYPE);
                m.setAccessible(true);
                this.mMethodSetProgress = m;
            } catch (NoSuchMethodException e) {
            }
        }
        if (this.mMethodSetProgress != null) {
            try {
                this.mMethodSetProgress.invoke(this, Integer.valueOf(progress), Boolean.valueOf(fromUser));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
            }
        } else {
            super.setProgress(progress);
        }
        refreshThumb();
    }

    /* access modifiers changed from: protected */
    public synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (useViewRotation()) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            super.onMeasure(heightMeasureSpec, widthMeasureSpec);
            ViewGroup.LayoutParams lp = getLayoutParams();
            if (!isInEditMode() || lp == null || lp.height < 0) {
                setMeasuredDimension(super.getMeasuredHeight(), super.getMeasuredWidth());
            } else {
                setMeasuredDimension(super.getMeasuredHeight(), lp.height);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (useViewRotation()) {
            super.onSizeChanged(w, h, oldw, oldh);
        } else {
            super.onSizeChanged(h, w, oldh, oldw);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v7.widget.AppCompatSeekBar
    public synchronized void onDraw(Canvas canvas) {
        if (!useViewRotation()) {
            switch (this.mRotationAngle) {
                case 90:
                    canvas.rotate(90.0f);
                    canvas.translate(0.0f, (float) (-super.getWidth()));
                    break;
                case ROTATION_ANGLE_CW_270 /*{ENCODED_INT: 270}*/:
                    canvas.rotate(-90.0f);
                    canvas.translate((float) (-super.getHeight()), 0.0f);
                    break;
            }
        }
        super.onDraw(canvas);
    }

    public int getRotationAngle() {
        return this.mRotationAngle;
    }

    public void setRotationAngle(int angle) {
        if (!isValidRotationAngle(angle)) {
            throw new IllegalArgumentException("Invalid angle specified :" + angle);
        } else if (this.mRotationAngle != angle) {
            this.mRotationAngle = angle;
            if (useViewRotation()) {
                VerticalSeekBarWrapper wrapper = getWrapper();
                if (wrapper != null) {
                    wrapper.applyViewRotation();
                    return;
                }
                return;
            }
            requestLayout();
        }
    }

    private void refreshThumb() {
        onSizeChanged(super.getWidth(), super.getHeight(), 0, 0);
    }

    /* access modifiers changed from: package-private */
    public boolean useViewRotation() {
        boolean isSupportedApiLevel;
        if (Build.VERSION.SDK_INT >= 11) {
            isSupportedApiLevel = true;
        } else {
            isSupportedApiLevel = false;
        }
        return isSupportedApiLevel && !isInEditMode();
    }

    private VerticalSeekBarWrapper getWrapper() {
        ViewParent parent = getParent();
        if (parent instanceof VerticalSeekBarWrapper) {
            return (VerticalSeekBarWrapper) parent;
        }
        return null;
    }

    private static boolean isValidRotationAngle(int angle) {
        return angle == 90 || angle == 270;
    }
}
