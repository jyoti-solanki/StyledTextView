package com.js.styledtextview.demo.listener;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.js.styledtextview.demo.view.ResizableStickerView;

public class MultiTouchListener implements View.OnTouchListener {
    private static final int INVALID_POINTER_ID = -1;
    Bitmap bitmap = null;
    boolean bt = false;
    public boolean isRotateEnabled = true;
    public boolean isRotationEnabled = false;
    public boolean isTranslateEnabled = true;
    private TouchCallbackListener listener = null;
    private int mActivePointerId = -1;
    private float mPrevX;
    private float mPrevY;
    private ScaleGestureDetector mScaleGestureDetector = new ScaleGestureDetector(new ScaleGestureListener());
    public float maximumScale = 8.0f;
    public float minimumScale = 0.5f;

    public interface TouchCallbackListener {
        void onTouchCallback(View view);

        void onTouchUpCallback(View view);
    }

    public MultiTouchListener setOnTouchCallbackListener(TouchCallbackListener l) {
        this.listener = l;
        return this;
    }

    public MultiTouchListener enableRotation(boolean b) {
        this.isRotationEnabled = b;
        return this;
    }

    public MultiTouchListener setMinScale(float f) {
        this.minimumScale = f;
        return this;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void move(View view, TransformInfo info) {
        if (this.isRotationEnabled) {
            view.setRotation(adjustAngle(view.getRotation() + info.deltaAngle));
        }
    }

    private static float adjustAngle(float degrees) {
        if (degrees > 180.0f) {
            return degrees - 360.0f;
        }
        if (degrees < -180.0f) {
            return degrees + 360.0f;
        }
        return degrees;
    }

    private static void adjustTranslation(View view, float deltaX, float deltaY) {
        float[] deltaVector = {deltaX, deltaY};
        view.getMatrix().mapVectors(deltaVector);
        view.setTranslationX(view.getTranslationX() + deltaVector[0]);
        view.setTranslationY(view.getTranslationY() + deltaVector[1]);
    }

    private static void computeRenderOffset(View view, float pivotX, float pivotY) {
        if (view.getPivotX() != pivotX || view.getPivotY() != pivotY) {
            float[] prevPoint = {0.0f, 0.0f};
            view.getMatrix().mapPoints(prevPoint);
            view.setPivotX(pivotX);
            view.setPivotY(pivotY);
            float[] currPoint = {0.0f, 0.0f};
            view.getMatrix().mapPoints(currPoint);
            float offsetX = currPoint[0] - prevPoint[0];
            float offsetY = currPoint[1] - prevPoint[1];
            view.setTranslationX(view.getTranslationX() - offsetX);
            view.setTranslationY(view.getTranslationY() - offsetY);
        }
    }

    public boolean handleTransparency(View view, MotionEvent event) {
        try {
            if (((ResizableStickerView) view).getBorderVisbilty()) {
                return false;
            }
            if (event.getAction() == 2 && this.bt) {
                return true;
            }
            if (event.getAction() != 1 || !this.bt) {
                int[] posXY = new int[2];
                view.getLocationOnScreen(posXY);
                int x1 = posXY[0];
                int y1 = posXY[1];
                int rx = (int) (event.getRawX() - ((float) x1));
                int ry = (int) (event.getRawY() - ((float) y1));
                float r = view.getRotation();
                Matrix mat = new Matrix();
                mat.postRotate(-r);
                float[] point = {(float) rx, (float) ry};
                mat.mapPoints(point);
                int rx2 = (int) point[0];
                int ry2 = (int) point[1];
                if (event.getAction() == 0) {
                    this.bt = false;
                    view.setDrawingCacheEnabled(true);
                    this.bitmap = Bitmap.createBitmap(view.getDrawingCache());
                    rx2 = (int) (((float) rx2) * (((float) this.bitmap.getWidth()) / (((float) this.bitmap.getWidth()) * view.getScaleX())));
                    ry2 = (int) (((float) ry2) * (((float) this.bitmap.getHeight()) / (((float) this.bitmap.getHeight()) * view.getScaleX())));
                    view.setDrawingCacheEnabled(false);
                }
                if (rx2 < 0 || ry2 < 0 || rx2 > this.bitmap.getWidth() || ry2 > this.bitmap.getHeight()) {
                    return false;
                }
                boolean b = this.bitmap.getPixel(rx2, ry2) == 0;
                if (event.getAction() != 0) {
                    return b;
                }
                this.bt = b;
                return b;
            }
            this.bt = false;
            if (this.bitmap != null) {
                this.bitmap.recycle();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean onTouch(View view, MotionEvent event) {
        int newPointerIndex = 0;
        this.mScaleGestureDetector.onTouchEvent(view, event);
        if (handleTransparency(view, event)) {
            return false;
        }
        if (!this.isTranslateEnabled) {
            return true;
        }
        int action = event.getAction();
        switch (event.getActionMasked() & action) {
            case 0:
                if (this.listener != null) {
                    this.listener.onTouchCallback(view);
                }
                view.bringToFront();
                if (view instanceof ResizableStickerView) {
                    ((ResizableStickerView) view).setBorderVisibility(true);
                }
                this.mPrevX = event.getX();
                this.mPrevY = event.getY();
                this.mActivePointerId = event.getPointerId(0);
                return true;
            case 1:
                this.mActivePointerId = -1;
                if (this.listener != null) {
                    this.listener.onTouchUpCallback(view);
                }
                float rotation = view.getRotation();
                if (Math.abs(90.0f - Math.abs(rotation)) <= 5.0f) {
                    if (rotation > 0.0f) {
                        rotation = 90.0f;
                    } else {
                        rotation = -90.0f;
                    }
                }
                if (Math.abs(0.0f - Math.abs(rotation)) <= 5.0f) {
                    if (rotation > 0.0f) {
                        rotation = 0.0f;
                    } else {
                        rotation = -0.0f;
                    }
                }
                if (Math.abs(180.0f - Math.abs(rotation)) <= 5.0f) {
                    if (rotation > 0.0f) {
                        rotation = 180.0f;
                    } else {
                        rotation = -180.0f;
                    }
                }
                view.setRotation(rotation);
                Log.i("testing", "Final Rotation : " + rotation);
                return true;
            case 2:
                int pointerIndex = event.findPointerIndex(this.mActivePointerId);
                if (pointerIndex == -1) {
                    return true;
                }
                float currX = event.getX(pointerIndex);
                float currY = event.getY(pointerIndex);
                if (this.mScaleGestureDetector.isInProgress()) {
                    return true;
                }
                adjustTranslation(view, currX - this.mPrevX, currY - this.mPrevY);
                return true;
            case 3:
                this.mActivePointerId = -1;
                return true;
            case 4:
            case 5:
            default:
                return true;
            case 6:
                int pointerIndex2 = (65280 & action) >> 8;
                if (event.getPointerId(pointerIndex2) != this.mActivePointerId) {
                    return true;
                }
                if (pointerIndex2 == 0) {
                    newPointerIndex = 1;
                }
                this.mPrevX = event.getX(newPointerIndex);
                this.mPrevY = event.getY(newPointerIndex);
                this.mActivePointerId = event.getPointerId(newPointerIndex);
                return true;
        }
    }

    private class ScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private float mPivotX;
        private float mPivotY;
        private Vector2D mPrevSpanVector;

        private ScaleGestureListener() {
            this.mPrevSpanVector = new Vector2D();
        }

        @Override // com.js.styledtextview.demo.listener.ScaleGestureDetector.SimpleOnScaleGestureListener, com.js.styledtextview.demo.listener.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(View view, ScaleGestureDetector detector) {
            this.mPivotX = detector.getFocusX();
            this.mPivotY = detector.getFocusY();
            this.mPrevSpanVector.set(detector.getCurrentSpanVector());
            return true;
        }

        @Override // com.js.styledtextview.demo.listener.ScaleGestureDetector.SimpleOnScaleGestureListener, com.js.styledtextview.demo.listener.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(View view, ScaleGestureDetector detector) {
            float f;
            float f2 = 0.0f;
            TransformInfo info = new TransformInfo();
            info.deltaAngle = MultiTouchListener.this.isRotateEnabled ? Vector2D.getAngle(this.mPrevSpanVector, detector.getCurrentSpanVector()) : 0.0f;
            if (MultiTouchListener.this.isTranslateEnabled) {
                f = detector.getFocusX() - this.mPivotX;
            } else {
                f = 0.0f;
            }
            info.deltaX = f;
            if (MultiTouchListener.this.isTranslateEnabled) {
                f2 = detector.getFocusY() - this.mPivotY;
            }
            info.deltaY = f2;
            info.pivotX = this.mPivotX;
            info.pivotY = this.mPivotY;
            info.minimumScale = MultiTouchListener.this.minimumScale;
            info.maximumScale = MultiTouchListener.this.maximumScale;
            MultiTouchListener.this.move(view, info);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public class TransformInfo {
        public float deltaAngle;
        public float deltaScale;
        public float deltaX;
        public float deltaY;
        public float maximumScale;
        public float minimumScale;
        public float pivotX;
        public float pivotY;

        private TransformInfo() {
        }
    }
}
