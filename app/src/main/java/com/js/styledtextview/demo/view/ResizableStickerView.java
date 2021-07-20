package com.js.styledtextview.demo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.js.styledtextview.R;
import com.js.styledtextview.demo.ImageUtils;
import com.js.styledtextview.demo.LibContants;
import com.js.styledtextview.demo.listener.MultiTouchListener;

public class ResizableStickerView extends RelativeLayout implements MultiTouchListener.TouchCallbackListener {
    private static final int SELF_SIZE_DP = 30;
    public static final String TAG = "ResizableStickerView";
    private int alphaProg = 0;
    double angle = 0.0d;
    int baseh;
    int basew;
    int basex;
    int basey;
    private ImageView border_iv;
    private Bitmap btmp = null;
    float cX = 0.0f;
    float cY = 0.0f;
    private double centerX;
    private double centerY;
    private String colorType = "color";
    private Context context;
    double dAngle = 0.0d;
    private ImageView delete_iv;
    private String drawableId;
    private ImageView flip_iv;
    boolean ft = true;
    private int he;
    private int hueProg = 0;
    private int imgAlpha = 100;
    private int imgColor = 0;
    private boolean isBorderVisible = false;
    private boolean isColorFilterEnable = false;
    private boolean isSticker = true;
    private boolean isStrickerEditEnable = false;
    private TouchEventListener listener = null;
    private OnTouchListener mTouchListener = new OnTouchListener() {
        /* class com.js.styledtextview.demo.view.ResizableStickerView.AnonymousClass3 */

        @SuppressLint({"NewApi"})
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case 0:
                    ResizableStickerView.this.this_orgX = ResizableStickerView.this.getX();
                    ResizableStickerView.this.this_orgY = ResizableStickerView.this.getY();
                    ResizableStickerView.this.scale_orgX = event.getRawX();
                    ResizableStickerView.this.scale_orgY = event.getRawY();
                    ResizableStickerView.this.scale_orgWidth = (double) ResizableStickerView.this.getLayoutParams().width;
                    ResizableStickerView.this.scale_orgHeight = (double) ResizableStickerView.this.getLayoutParams().height;
                    ResizableStickerView.this.centerX = (double) (((View) ResizableStickerView.this.getParent()).getX() + ResizableStickerView.this.getX() + (((float) ResizableStickerView.this.getWidth()) / 2.0f));
                    int result = 0;
                    int resourceId = ResizableStickerView.this.getResources().getIdentifier("status_bar_height", "dimen", "android");
                    if (resourceId > 0) {
                        result = ResizableStickerView.this.getResources().getDimensionPixelSize(resourceId);
                    }
                    ResizableStickerView.this.centerY = ((double) (((View) ResizableStickerView.this.getParent()).getY() + ResizableStickerView.this.getY())) + ((double) result) + ((double) (((float) ResizableStickerView.this.getHeight()) / 2.0f));
                    return true;
                case 1:
                    ResizableStickerView.this.wi = ResizableStickerView.this.getLayoutParams().width;
                    ResizableStickerView.this.he = ResizableStickerView.this.getLayoutParams().height;
                    return true;
                case 2:
                    double angle_diff = (Math.abs(Math.atan2((double) (event.getRawY() - ResizableStickerView.this.scale_orgY), (double) (event.getRawX() - ResizableStickerView.this.scale_orgX)) - Math.atan2(((double) ResizableStickerView.this.scale_orgY) - ResizableStickerView.this.centerY, ((double) ResizableStickerView.this.scale_orgX) - ResizableStickerView.this.centerX)) * 180.0d) / 3.141592653589793d;
                    Log.v(ResizableStickerView.TAG, "angle_diff: " + angle_diff);
                    double length1 = ResizableStickerView.this.getLength(ResizableStickerView.this.centerX, ResizableStickerView.this.centerY, (double) ResizableStickerView.this.scale_orgX, (double) ResizableStickerView.this.scale_orgY);
                    double length2 = ResizableStickerView.this.getLength(ResizableStickerView.this.centerX, ResizableStickerView.this.centerY, (double) event.getRawX(), (double) event.getRawY());
                    int size = ResizableStickerView.this.dpToPx(ResizableStickerView.this.getContext(), 30);
                    if (length2 > length1 && (angle_diff < 25.0d || Math.abs(angle_diff - 180.0d) < 25.0d)) {
                        double offset = (double) Math.round(Math.max((double) Math.abs(event.getRawX() - ResizableStickerView.this.scale_orgX), (double) Math.abs(event.getRawY() - ResizableStickerView.this.scale_orgY)));
                        ViewGroup.LayoutParams layoutParams = ResizableStickerView.this.getLayoutParams();
                        layoutParams.width = (int) (((double) layoutParams.width) + offset);
                        ViewGroup.LayoutParams layoutParams2 = ResizableStickerView.this.getLayoutParams();
                        layoutParams2.height = (int) (((double) layoutParams2.height) + offset);
                    } else if (length2 < length1 && ((angle_diff < 25.0d || Math.abs(angle_diff - 180.0d) < 25.0d) && ResizableStickerView.this.getLayoutParams().width > size / 2 && ResizableStickerView.this.getLayoutParams().height > size / 2)) {
                        double offset2 = (double) Math.round(Math.max((double) Math.abs(event.getRawX() - ResizableStickerView.this.scale_orgX), (double) Math.abs(event.getRawY() - ResizableStickerView.this.scale_orgY)));
                        ViewGroup.LayoutParams layoutParams3 = ResizableStickerView.this.getLayoutParams();
                        layoutParams3.width = (int) (((double) layoutParams3.width) - offset2);
                        ViewGroup.LayoutParams layoutParams4 = ResizableStickerView.this.getLayoutParams();
                        layoutParams4.height = (int) (((double) layoutParams4.height) - offset2);
                    }
                    ResizableStickerView.this.scale_orgX = event.getRawX();
                    ResizableStickerView.this.scale_orgY = event.getRawY();
                    ResizableStickerView.this.postInvalidate();
                    ResizableStickerView.this.requestLayout();
                    return true;
                default:
                    return true;
            }
        }
    };
    private OnTouchListener mTouchListener1 = new OnTouchListener() {
        /* class com.js.styledtextview.demo.view.ResizableStickerView.AnonymousClass5 */

        @SuppressLint({"NewApi"})
        public boolean onTouch(View view, MotionEvent event) {
            int j = (int) event.getRawX();
            int i = (int) event.getRawY();
            LayoutParams layoutParams = (LayoutParams) ResizableStickerView.this.getLayoutParams();
            switch (event.getAction()) {
                case 0:
                    ResizableStickerView.this.invalidate();
                    ResizableStickerView.this.basex = j;
                    ResizableStickerView.this.basey = i;
                    ResizableStickerView.this.basew = ResizableStickerView.this.getWidth();
                    ResizableStickerView.this.baseh = ResizableStickerView.this.getHeight();
                    ResizableStickerView.this.getLocationOnScreen(new int[2]);
                    ResizableStickerView.this.margl = layoutParams.leftMargin;
                    ResizableStickerView.this.margt = layoutParams.topMargin;
                    return true;
                case 1:
                    ResizableStickerView.this.wi = ResizableStickerView.this.getLayoutParams().width;
                    ResizableStickerView.this.he = ResizableStickerView.this.getLayoutParams().height;
                    return true;
                case 2:
                    float f2 = (float) Math.toDegrees(Math.atan2((double) (i - ResizableStickerView.this.basey), (double) (j - ResizableStickerView.this.basex)));
                    float f1 = f2;
                    if (f2 < 0.0f) {
                        f1 = f2 + 360.0f;
                    }
                    int j2 = j - ResizableStickerView.this.basex;
                    int k = i - ResizableStickerView.this.basey;
                    int i2 = (int) (Math.sqrt((double) ((j2 * j2) + (k * k))) * Math.cos(Math.toRadians((double) (f1 - ResizableStickerView.this.getRotation()))));
                    int j3 = (int) (Math.sqrt((double) ((i2 * i2) + (k * k))) * Math.sin(Math.toRadians((double) (f1 - ResizableStickerView.this.getRotation()))));
                    int k2 = (i2 * 2) + ResizableStickerView.this.basew;
                    int m = (j3 * 2) + ResizableStickerView.this.baseh;
                    if (k2 > ResizableStickerView.this.s) {
                        layoutParams.width = k2;
                        layoutParams.leftMargin = ResizableStickerView.this.margl - i2;
                    }
                    if (m > ResizableStickerView.this.s) {
                        layoutParams.height = m;
                        layoutParams.topMargin = ResizableStickerView.this.margt - j3;
                    }
                    ResizableStickerView.this.setLayoutParams(layoutParams);
                    ResizableStickerView.this.performLongClick();
                    return true;
                default:
                    return true;
            }
        }
    };
    public ImageView main_iv;
    int margl;
    int margt;
    double onTouchAngle = 0.0d;
    private OnTouchListener rTouchListener = new OnTouchListener() {
        /* class com.js.styledtextview.demo.view.ResizableStickerView.AnonymousClass4 */

        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case 0:
                    Rect rect = new Rect();
                    ((View) view.getParent()).getGlobalVisibleRect(rect);
                    ResizableStickerView.this.cX = rect.exactCenterX();
                    ResizableStickerView.this.cY = rect.exactCenterY();
                    ResizableStickerView.this.vAngle = (double) ((View) view.getParent()).getRotation();
                    ResizableStickerView.this.tAngle = (Math.atan2((double) (ResizableStickerView.this.cY - event.getRawY()), (double) (ResizableStickerView.this.cX - event.getRawX())) * 180.0d) / 3.141592653589793d;
                    ResizableStickerView.this.dAngle = ResizableStickerView.this.vAngle - ResizableStickerView.this.tAngle;
                    return true;
                case 1:
                default:
                    return true;
                case 2:
                    ResizableStickerView.this.angle = (Math.atan2((double) (ResizableStickerView.this.cY - event.getRawY()), (double) (ResizableStickerView.this.cX - event.getRawX())) * 180.0d) / 3.141592653589793d;
                    ((View) view.getParent()).setRotation((float) (ResizableStickerView.this.angle + ResizableStickerView.this.dAngle));
                    ((View) view.getParent()).invalidate();
                    ((View) view.getParent()).requestLayout();
                    return true;
            }
        }
    };
    private Uri resUri = null;
    private ImageView rotate_iv;
    private float rotation;
    private int s;
    Animation scale;
    private int scaleRotateProg = 0;
    private ImageView scale_iv;
    private double scale_orgHeight = -1.0d;
    private double scale_orgWidth = -1.0d;
    private float scale_orgX = -1.0f;
    private float scale_orgY = -1.0f;
    double tAngle = 0.0d;
    private float this_orgX = -1.0f;
    private float this_orgY = -1.0f;
    double vAngle = 0.0d;
    private int wi;
    private int xRotateProg = 1;
    private int yRotateProg = 1;
    private float yRotation;
    private int zRotateProg = 1;
    Animation zoomInScale;
    Animation zoomOutScale;

    public interface TouchEventListener {
        void onDelete();

        void onEdit(View view, Uri uri);

        void onTouchDown(View view);

        void onTouchUp(View view);
    }

    public ResizableStickerView setOnTouchCallbackListener(TouchEventListener l) {
        this.listener = l;
        return this;
    }

    public ResizableStickerView(Context context2) {
        super(context2);
        init(context2);
    }

    public ResizableStickerView(Context context2, AttributeSet attrs) {
        super(context2, attrs);
        init(context2);
    }

    public ResizableStickerView(Context context2, AttributeSet attrs, int defStyleAttr) {
        super(context2, attrs, defStyleAttr);
        init(context2);
    }

    public void init(Context ctx) {
        this.context = ctx;
        this.main_iv = new ImageView(this.context);
        this.scale_iv = new ImageView(this.context);
        this.border_iv = new ImageView(this.context);
        this.flip_iv = new ImageView(this.context);
        this.rotate_iv = new ImageView(this.context);
        this.delete_iv = new ImageView(this.context);
        this.s = dpToPx(this.context, 25);
        this.wi = dpToPx(this.context, 200);
        this.he = dpToPx(this.context, 200);
        this.scale_iv.setImageResource(R.drawable.sticker_scale);
        this.border_iv.setImageResource(R.drawable.sticker_border_gray);
        this.flip_iv.setImageResource(R.drawable.sticker_flip);
        this.rotate_iv.setImageResource(R.drawable.rotate);
        this.delete_iv.setImageResource(R.drawable.sticker_delete1);
        LayoutParams lp = new LayoutParams(this.wi, this.he);
        LayoutParams mlp = new LayoutParams(-1, -1);
        mlp.setMargins(5, 5, 5, 5);
        mlp.addRule(17);
        LayoutParams slp = new LayoutParams(this.s, this.s);
        slp.addRule(12);
        slp.addRule(11);
        slp.setMargins(5, 5, 5, 5);
        LayoutParams flp = new LayoutParams(this.s, this.s);
        flp.addRule(10);
        flp.addRule(11);
        flp.setMargins(5, 5, 5, 5);
        LayoutParams elp = new LayoutParams(this.s, this.s);
        elp.addRule(12);
        elp.addRule(9);
        elp.setMargins(5, 5, 5, 5);
        LayoutParams dlp = new LayoutParams(this.s, this.s);
        dlp.addRule(10);
        dlp.addRule(9);
        dlp.setMargins(5, 5, 5, 5);
        LayoutParams blp = new LayoutParams(-1, -1);
        setLayoutParams(lp);
        setBackgroundResource(R.drawable.textlib_border_gray);
        addView(this.border_iv);
        this.border_iv.setLayoutParams(blp);
        this.border_iv.setScaleType(ImageView.ScaleType.FIT_XY);
        this.border_iv.setTag("border_iv");
        addView(this.main_iv);
        this.main_iv.setLayoutParams(mlp);
        this.main_iv.setTag("main_iv");
        addView(this.flip_iv);
        this.flip_iv.setLayoutParams(flp);
        this.flip_iv.setOnClickListener(new OnClickListener() {
            /* class com.js.styledtextview.demo.view.ResizableStickerView.AnonymousClass1 */

            public void onClick(View v) {
                float f = -180.0f;
                ImageView imageView = ResizableStickerView.this.main_iv;
                if (ResizableStickerView.this.main_iv.getRotationY() == -180.0f) {
                    f = 0.0f;
                }
                imageView.setRotationY(f);
                ResizableStickerView.this.main_iv.invalidate();
                ResizableStickerView.this.requestLayout();
            }
        });
        addView(this.rotate_iv);
        this.rotate_iv.setLayoutParams(elp);
        this.rotate_iv.setOnTouchListener(this.rTouchListener);
        addView(this.delete_iv);
        this.delete_iv.setLayoutParams(dlp);
        this.delete_iv.setOnClickListener(new OnClickListener() {
            /* class com.js.styledtextview.demo.view.ResizableStickerView.AnonymousClass2 */

            public void onClick(View v) {
                final ViewGroup parent = (ViewGroup) ResizableStickerView.this.getParent();
                ResizableStickerView.this.zoomInScale.setAnimationListener(new Animation.AnimationListener() {
                    /* class com.js.styledtextview.demo.view.ResizableStickerView.AnonymousClass2.AnonymousClass1 */

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        parent.removeView(ResizableStickerView.this);
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                ResizableStickerView.this.main_iv.startAnimation(ResizableStickerView.this.zoomInScale);
                ResizableStickerView.this.setBorderVisibility(false);
                if (ResizableStickerView.this.listener != null) {
                    ResizableStickerView.this.listener.onDelete();
                }
            }
        });
        addView(this.scale_iv);
        this.scale_iv.setLayoutParams(slp);
        this.scale_iv.setOnTouchListener(this.mTouchListener1);
        this.scale_iv.setTag("scale_iv");
        this.rotation = getRotation();
        this.scale = AnimationUtils.loadAnimation(getContext(), R.anim.sticker_scale_anim);
        this.zoomOutScale = AnimationUtils.loadAnimation(getContext(), R.anim.sticker_scale_zoom_out);
        this.zoomInScale = AnimationUtils.loadAnimation(getContext(), R.anim.sticker_scale_zoom_in);
        setDefaultTouchListener();
    }

    public void setDefaultTouchListener() {
        setOnTouchListener(new MultiTouchListener().enableRotation(true).setOnTouchCallbackListener(this));
    }

    public void setBorderVisibility(boolean ch) {
        this.isBorderVisible = ch;
        if (!ch) {
            this.border_iv.setVisibility(8);
            this.scale_iv.setVisibility(8);
            this.flip_iv.setVisibility(8);
            this.rotate_iv.setVisibility(8);
            this.delete_iv.setVisibility(8);
            setBackgroundResource(0);
            if (this.isColorFilterEnable) {
                this.main_iv.setColorFilter(Color.parseColor("#303828"));
            }
        } else if (this.border_iv.getVisibility() != 0) {
            this.border_iv.setVisibility(0);
            this.scale_iv.setVisibility(0);
            this.flip_iv.setVisibility(0);
            this.rotate_iv.setVisibility(0);
            this.delete_iv.setVisibility(0);
            setBackgroundResource(R.drawable.textlib_border_gray);
            this.main_iv.startAnimation(this.scale);
        }
    }

    public boolean getBorderVisbilty() {
        return this.isBorderVisible;
    }

    public void opecitySticker(int process) {
        try {
            this.main_iv.setAlpha(((float) process) / 100.0f);
            this.imgAlpha = process;
        } catch (Exception e) {
        }
    }

    public int getHueProg() {
        return this.hueProg;
    }

    public void setHueProg(int hueProg2) {
        this.hueProg = hueProg2;
        this.main_iv.setColorFilter(ColorFilterGenerator.adjustHue((float) hueProg2));
    }

    public int getXRotateProg() {
        return this.xRotateProg;
    }

    public int getYRotateProg() {
        return this.yRotateProg;
    }

    public int getZRotateProg() {
        return this.zRotateProg;
    }

    public int geScaleProg() {
        return this.scaleRotateProg;
    }

    public void setStickerRotateProg(int xRotateProg2, int yRotateProg2, int zRotateProg2, int x, int y, int z) {
        this.xRotateProg = x;
        this.yRotateProg = y;
        this.zRotateProg = z;
        applyTransformation(xRotateProg2, yRotateProg2, zRotateProg2);
    }

    public void setScaleViewProg(int scale2) {
        this.scaleRotateProg = scale2;
        float scal = ((float) scale2) / 10.0f;
        this.main_iv.setScaleX(scal);
        this.main_iv.setScaleY(scal);
    }

    public String getColorType() {
        return this.colorType;
    }

    public void setColorType(String colorType2) {
        this.colorType = colorType2;
    }

    public int getAlphaProg() {
        return this.alphaProg;
    }

    public void setAlphaProg(int alphaProg2) {
        this.alphaProg = alphaProg2;
        this.main_iv.setImageAlpha(255 - alphaProg2);
    }

    public int getColor() {
        return this.imgColor;
    }

    public void setColor(int color) {
        try {
            this.main_iv.setColorFilter(color);
            this.imgColor = color;
        } catch (Exception e) {
        }
    }

    public void setBgDrawable(String redId1) {
        this.drawableId = redId1;
        addBotmap();
    }

    public void setMainImageUri(Uri uri) {
        this.resUri = uri;
        this.main_iv.setImageURI(this.resUri);
    }

    public Uri getMainImageUri() {
        return this.resUri;
    }

    public void setMainImageBitmap(Bitmap bit) {
        this.main_iv.setImageBitmap(bit);
    }

    public Bitmap getMainImageBitmap() {
        return this.btmp;
    }

    /* access modifiers changed from: protected */
    public void applyTransformation(int angle1, int angle2, int angle3) {
        this.main_iv.setRotationX((float) angle1);
        this.main_iv.setRotationY((float) angle2);
        this.main_iv.setRotation((float) angle3);
        setVisibility(0);
        this.main_iv.setVisibility(0);
        this.main_iv.requestLayout();
        this.main_iv.postInvalidate();
        requestLayout();
        postInvalidate();
    }

    /* access modifiers changed from: protected */
    public void applyTransformationk(int angle1, int angle2, int angle3) {
        Bitmap btmpIs;
        if (!this.drawableId.equals("0")) {
            btmpIs = LibContants.resizeBitmap(BitmapFactory.decodeResource(this.context.getResources(), getResources().getIdentifier(this.drawableId, "drawable", this.context.getPackageName())), ImageUtils.dpToPx(this.context, 200), ImageUtils.dpToPx(this.context, 200));
        } else {
            btmpIs = LibContants.resizeBitmap(this.btmp, ImageUtils.dpToPx(this.context, 200), ImageUtils.dpToPx(this.context, 200));
        }
        Bitmap Bitmap2 = Bitmap.createBitmap(ImageUtils.dpToPx(this.context, 300), ImageUtils.dpToPx(this.context, 300), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(Bitmap2);
        Camera camera2 = new Camera();
        Matrix matrix = new Matrix();
        Paint paint = new Paint();
        paint.setColor(0);
        if (this.ft) {
            this.cX = (float) (((((View) getParent()).getWidth() - ImageUtils.dpToPx(this.context, 50)) / 2) + ((int) ((View) getParent()).getX()));
            this.cY = (float) (((((View) getParent()).getHeight() - ImageUtils.dpToPx(this.context, 50)) / 2) + ((int) ((View) getParent()).getY()));
        }
        camera2.save();
        camera2.rotateX((float) angle1);
        camera2.rotateY((float) angle2);
        camera2.rotateZ((float) angle3);
        camera2.getMatrix(matrix);
        matrix.preTranslate((float) (-(ImageUtils.dpToPx(this.context, 300) / 2)), (float) ((-ImageUtils.dpToPx(this.context, 300)) / 2));
        matrix.postTranslate((float) (ImageUtils.dpToPx(this.context, 300) / 2), (float) (ImageUtils.dpToPx(this.context, 300) / 2));
        canvas.concat(matrix);
        canvas.drawBitmap(btmpIs, (float) ImageUtils.dpToPx(this.context, 50), (float) ImageUtils.dpToPx(this.context, 50), (Paint) null);
        canvas.drawCircle(this.cX, this.cY, 20.0f, paint);
        this.main_iv.setImageBitmap(Bitmap2);
    }

    public void setComponentInfo(ComponentInfo ci) {
        this.wi = ci.getWIDTH();
        this.he = ci.getHEIGHT();
        this.drawableId = ci.getRES_ID();
        this.resUri = ci.getRES_URI();
        this.btmp = ci.getBITMAP();
        this.imgColor = ci.getSTC_COLOR();
        this.rotation = ci.getROTATION();
        this.yRotation = ci.getY_ROTATION();
        this.colorType = ci.getCOLORTYPE();
        this.imgAlpha = ci.getSTC_OPACITY();
        this.xRotateProg = ci.getXRotateProg();
        this.yRotateProg = ci.getYRotateProg();
        this.zRotateProg = ci.getZRotateProg();
        this.scaleRotateProg = ci.getScaleProg();
        setX(ci.getPOS_X());
        setY(ci.getPOS_Y());
        if (!this.drawableId.equals("0")) {
            setBgDrawable(this.drawableId);
        } else {
            addBotmap();
        }
        setRotation(this.rotation);
        setColor(this.imgColor);
        setColorType(this.colorType);
        opecitySticker(this.imgAlpha);
        getLayoutParams().width = this.wi;
        getLayoutParams().height = this.he;
        if (ci.getTYPE() == "SHAPE") {
            this.flip_iv.setVisibility(8);
            this.isSticker = false;
        }
        if (ci.getTYPE() == "STICKER") {
            this.flip_iv.setVisibility(0);
            this.isSticker = true;
        }
    }

    /* access modifiers changed from: protected */
    public void addBotmap() {
        Bitmap btmpIs;
        if (!this.drawableId.equals("0")) {
            btmpIs = LibContants.resizeBitmap(BitmapFactory.decodeResource(this.context.getResources(), getResources().getIdentifier(this.drawableId, "drawable", this.context.getPackageName())), ImageUtils.dpToPx(this.context, 200), ImageUtils.dpToPx(this.context, 200));
        } else {
            btmpIs = LibContants.resizeBitmap(this.btmp, ImageUtils.dpToPx(this.context, 200), ImageUtils.dpToPx(this.context, 200));
        }
        Bitmap Bitmap2 = Bitmap.createBitmap(ImageUtils.dpToPx(this.context, 300), ImageUtils.dpToPx(this.context, 300), Bitmap.Config.ARGB_8888);
        new Canvas(Bitmap2).drawBitmap(btmpIs, (float) ImageUtils.dpToPx(this.context, 50), (float) ImageUtils.dpToPx(this.context, 50), (Paint) null);
        this.main_iv.setImageBitmap(Bitmap2);
        this.main_iv.startAnimation(this.zoomOutScale);
    }

    public void optimize(float wr, float hr) {
        setX(getX() * wr);
        setY(getY() * hr);
        getLayoutParams().width = (int) (((float) this.wi) * wr);
        getLayoutParams().height = (int) (((float) this.he) * hr);
    }

    public void incrX() {
        setX(getX() + 1.0f);
    }

    public void decX() {
        setX(getX() - 1.0f);
    }

    public void incrY() {
        setY(getY() + 1.0f);
    }

    public void decY() {
        setY(getY() - 1.0f);
    }

    public ComponentInfo getComponentInfo() {
        ComponentInfo ci = new ComponentInfo();
        ci.setPOS_X(getX());
        ci.setPOS_Y(getY());
        ci.setWIDTH(this.wi);
        ci.setHEIGHT(this.he);
        ci.setRES_ID(this.drawableId);
        ci.setSTC_COLOR(this.imgColor);
        ci.setRES_URI(this.resUri);
        ci.setSTC_OPACITY(this.imgAlpha);
        ci.setCOLORTYPE(this.colorType);
        ci.setBITMAP(this.btmp);
        ci.setROTATION(getRotation());
        ci.setY_ROTATION(this.main_iv.getRotationY());
        return ci;
    }

    public int dpToPx(Context c, int dp) {
        c.getResources();
        return (int) (((float) dp) * Resources.getSystem().getDisplayMetrics().density);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private double getLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(y2 - y1, 2.0d) + Math.pow(x2 - x1, 2.0d));
    }

    public void enableColorFilter(boolean b) {
        this.isColorFilterEnable = b;
    }

    @Override // com.js.styledtextview.demo.listener.MultiTouchListener.TouchCallbackListener
    public void onTouchCallback(View v) {
        if (this.listener != null) {
            this.listener.onTouchDown(v);
        }
    }

    @Override // com.js.styledtextview.demo.listener.MultiTouchListener.TouchCallbackListener
    public void onTouchUpCallback(View v) {
        if (this.listener != null) {
            this.listener.onTouchUp(v);
        }
    }
}
