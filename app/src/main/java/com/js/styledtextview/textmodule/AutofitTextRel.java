package com.js.styledtextview.textmodule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.js.styledtextview.R;
import com.js.styledtextview.textmodule.listener.MultiTouchListener;

public class AutofitTextRel extends RelativeLayout implements MultiTouchListener.TouchCallbackListener {
    double angle = 0.0d;
    private ImageView background_iv;
    int baseh;
    int basew;
    int basex;
    int basey;
    private int bgAlpha = 255;
    private int bgColor = 0;
    private String bgDrawable = "0";
    private ImageView border_iv;
    float cX = 0.0f;
    float cY = 0.0f;
    private double centerX;
    private double centerY;
    private Context context;
    double dAngle = 0.0d;
    private ImageView delete_iv;
    private String fontName = "";
    private GestureDetector gd = null;
    private int he;
    private int hm;
    private boolean isBorderVisible = false;
    private TouchEventListener listener = null;
    private OnTouchListener mTouchListener1 = new OnTouchListener() {
        /* class com.js.styledtextview.textmodule.AutofitTextRel.AnonymousClass3 */

        @SuppressLint({"NewApi"})
        public boolean onTouch(View view, MotionEvent event) {
            int j = (int) event.getRawX();
            int i = (int) event.getRawY();
            LayoutParams layoutParams = (LayoutParams) AutofitTextRel.this.getLayoutParams();
            switch (event.getAction()) {
                case 0:
                    AutofitTextRel.this.textRelative.invalidate();
                    AutofitTextRel.this.basex = j;
                    AutofitTextRel.this.basey = i;
                    AutofitTextRel.this.basew = AutofitTextRel.this.getWidth();
                    AutofitTextRel.this.baseh = AutofitTextRel.this.getHeight();
                    AutofitTextRel.this.getLocationOnScreen(new int[2]);
                    AutofitTextRel.this.margl = layoutParams.leftMargin;
                    AutofitTextRel.this.margt = layoutParams.topMargin;
                    return true;
                case 1:
                    AutofitTextRel.this.textRelative.invalidate();
                    AutofitTextRel.this.wi = AutofitTextRel.this.getLayoutParams().width;
                    AutofitTextRel.this.he = AutofitTextRel.this.getLayoutParams().height;
                    return true;
                case 2:
                    AutofitTextRel.this.textRelative.invalidate();
                    float f2 = (float) Math.toDegrees(Math.atan2((double) (i - AutofitTextRel.this.basey), (double) (j - AutofitTextRel.this.basex)));
                    float f1 = f2;
                    if (f2 < 0.0f) {
                        f1 = f2 + 360.0f;
                    }
                    int j2 = j - AutofitTextRel.this.basex;
                    int k = i - AutofitTextRel.this.basey;
                    int i2 = (int) (Math.sqrt((double) ((j2 * j2) + (k * k))) * Math.cos(Math.toRadians((double) (f1 - AutofitTextRel.this.getRotation()))));
                    int j3 = (int) (Math.sqrt((double) ((i2 * i2) + (k * k))) * Math.sin(Math.toRadians((double) (f1 - AutofitTextRel.this.getRotation()))));
                    int k2 = (i2 * 2) + AutofitTextRel.this.basew;
                    int m = (j3 * 2) + AutofitTextRel.this.baseh;
                    Log.e("kkkkmmmmmm", "" + k2 + " ," + m);
                    if (k2 > AutofitTextRel.this.s && k2 < AutofitTextRel.this.wm + AutofitTextRel.this.dpToPx(AutofitTextRel.this.context, 30)) {
                        layoutParams.width = k2;
                        layoutParams.leftMargin = AutofitTextRel.this.margl - i2;
                    }
                    if (m > AutofitTextRel.this.s && m < AutofitTextRel.this.hm + AutofitTextRel.this.dpToPx(AutofitTextRel.this.context, 30)) {
                        layoutParams.height = m;
                        layoutParams.topMargin = AutofitTextRel.this.margt - j3;
                    }
                    AutofitTextRel.this.setLayoutParams(layoutParams);
                    if (AutofitTextRel.this.bgDrawable.equals("0")) {
                        return true;
                    }
                    AutofitTextRel.this.wi = AutofitTextRel.this.getLayoutParams().width;
                    AutofitTextRel.this.he = AutofitTextRel.this.getLayoutParams().height;
                    AutofitTextRel.this.setBgDrawable(AutofitTextRel.this.bgDrawable);
                    return true;
                default:
                    return true;
            }
        }
    };
    int margl;
    int margt;
    double onTouchAngle = 0.0d;
    Paint paint;
    int posX;
    int posY;
    private int prog_20 = 0;
    private int prog_40 = 0;
    private int prog_50 = 0;
    private int prog_60 = 0;
    private int prog_80;
    private int progress = 0;
    private OnTouchListener rTouchListener = new OnTouchListener() {
        /* class com.js.styledtextview.textmodule.AutofitTextRel.AnonymousClass2 */

        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case 0:
                    Rect rect = new Rect();
                    ((View) view.getParent()).getGlobalVisibleRect(rect);
                    AutofitTextRel.this.cX = rect.exactCenterX();
                    AutofitTextRel.this.cY = rect.exactCenterY();
                    AutofitTextRel.this.vAngle = (double) ((View) view.getParent()).getRotation();
                    AutofitTextRel.this.tAngle = (Math.atan2((double) (AutofitTextRel.this.cY - event.getRawY()), (double) (AutofitTextRel.this.cX - event.getRawX())) * 180.0d) / 3.141592653589793d;
                    AutofitTextRel.this.dAngle = AutofitTextRel.this.vAngle - AutofitTextRel.this.tAngle;
                    return true;
                case 1:
                default:
                    return true;
                case 2:
                    AutofitTextRel.this.angle = (Math.atan2((double) (AutofitTextRel.this.cY - event.getRawY()), (double) (AutofitTextRel.this.cX - event.getRawX())) * 180.0d) / 3.141592653589793d;
                    ((View) view.getParent()).setRotation((float) (AutofitTextRel.this.angle + AutofitTextRel.this.dAngle));
                    ((View) view.getParent()).invalidate();
                    ((View) view.getParent()).requestLayout();
                    return true;
            }
        }
    };
    private RelativeLayout rel_artv;
    private ImageView rotate_iv;
    private float rotation;
    private int s;
    Animation scale;
    private ImageView scale_iv;
    private int shadowColor = 0;
    private int shadowProg = 0;
    private int tAlpha = 100;
    double tAngle = 0.0d;
    private int tColor = -16777216;
    private String text = "";
    private TextRelativeDraw textRelative;
    private AutoResizeTextView text_iv;
    double vAngle = 0.0d;
    private int wi;
    private int wm;
    int x1;
    int x10;
    int x11;
    int x12;
    int x13;
    int x14;
    int x15;
    int x16;
    int x17;
    int x18;
    int x19;
    int x2;
    int x20;
    int x21;
    int x22;
    int x23;
    int x24;
    int x25;
    int x26;
    int x27;
    int x28;
    int x29;
    int x3;
    int x30;
    int x31;
    int x32;
    int x33;
    int x34;
    int x35;
    int x36;
    int x37;
    int x38;
    int x39;
    int x4;
    int x40;
    int x41;
    int x42;
    int x43;
    int x44;
    int x45;
    int x46;
    int x47;
    int x48;
    int x49;
    int x5;
    int x50;
    int x51;
    int x52;
    int x53;
    int x54;
    int x55;
    int x56;
    int x57;
    int x58;
    int x59;
    int x6;
    int x60;
    int x61;
    int x62;
    int x63;
    int x64;
    int x65;
    int x66;
    int x67;
    int x68;
    int x69;
    int x7;
    int x70;
    int x71;
    int x72;
    int x73;
    int x74;
    int x75;
    int x76;
    int x77;
    int x78;
    int x79;
    int x8;
    int x80;
    int x81;
    int x9;
    private int xRotateProg = 0;
    int y1;
    int y10;
    int y11;
    int y12;
    int y13;
    int y14;
    int y15;
    int y16;
    int y17;
    int y18;
    int y19;
    int y2;
    int y20;
    int y21;
    int y22;
    int y23;
    int y24;
    int y25;
    int y26;
    int y27;
    int y28;
    int y29;
    int y3;
    int y30;
    int y31;
    int y32;
    int y33;
    int y34;
    int y35;
    int y36;
    int y37;
    int y38;
    int y39;
    int y4;
    int y40;
    int y41;
    int y42;
    int y43;
    int y44;
    int y45;
    int y46;
    int y47;
    int y48;
    int y49;
    int y5;
    int y50;
    int y51;
    int y52;
    int y53;
    int y54;
    int y55;
    int y56;
    int y57;
    int y58;
    int y59;
    int y6;
    int y60;
    int y61;
    int y62;
    int y63;
    int y64;
    int y65;
    int y66;
    int y67;
    int y68;
    int y69;
    int y7;
    int y70;
    int y71;
    int y72;
    int y73;
    int y74;
    int y75;
    int y76;
    int y77;
    int y78;
    int y79;
    int y8;
    int y80;
    int y81;
    int y9;
    private int yRotateProg = 0;
    private int zRotateProg = 0;
    Animation zoomInScale;
    Animation zoomOutScale;

    public interface TouchEventListener {
        void onDelete();

        void onDoubleTap();

        void onEdit(View view, Uri uri);

        void onTouchDown(View view);

        void onTouchUp(View view);
    }

    public AutofitTextRel setOnTouchCallbackListener(TouchEventListener l) {
        this.listener = l;
        this.textRelative.invalidate();
        return this;
    }

    public AutofitTextRel(Context context2) {
        super(context2);
        init(context2);
        invalidate();
    }

    public AutofitTextRel(Context context2, AttributeSet attrs) {
        super(context2, attrs);
        init(context2);
    }

    public AutofitTextRel(Context context2, AttributeSet attrs, int defStyleAttr) {
        super(context2, attrs, defStyleAttr);
        init(context2);
    }

    @SuppressLint("WrongConstant")
    public void init(Context ctx) {
        this.paint = new Paint();
        this.paint.setColor(0);
        this.context = ctx;
        this.scale_iv = new ImageView(this.context);
        this.border_iv = new ImageView(this.context);
        this.background_iv = new ImageView(this.context);
        this.delete_iv = new ImageView(this.context);
        this.rotate_iv = new ImageView(this.context);
        this.rel_artv = new RelativeLayout(this.context);
        this.textRelative = new TextRelativeDraw(this.context, this.rel_artv);
        this.text_iv = new AutoResizeTextView(this.context);
        this.s = dpToPx(this.context, 25);
        this.wi = dpToPx(this.context, 300);
        this.he = dpToPx(this.context, 300);
        this.scale_iv.setImageResource(R.drawable.textlib_scale);
        this.background_iv.setImageResource(0);
        this.rotate_iv.setImageResource(R.drawable.rotate);
        this.delete_iv.setImageResource(R.drawable.textlib_clear);
        LayoutParams lp = new LayoutParams(this.wi, this.he);
        LayoutParams bglp = new LayoutParams(-1, -1);
        LayoutParams slp = new LayoutParams(this.s, this.s);
        slp.addRule(12);
        slp.addRule(11);
        slp.setMargins(5, 5, 5, 5);
        LayoutParams tlp = new LayoutParams(-1, -1);
        tlp.setMargins(5, 5, 5, 5);
        tlp.addRule(17);
        LayoutParams dlp = new LayoutParams(this.s, this.s);
        dlp.addRule(10);
        dlp.addRule(9);
        dlp.setMargins(5, 5, 5, 5);
        LayoutParams blp = new LayoutParams(-1, -1);
        setLayoutParams(lp);
        setBackgroundResource(R.drawable.textlib_border_gray);
        LayoutParams elp = new LayoutParams(this.s, this.s);
        elp.addRule(12);
        elp.addRule(9);
        elp.setMargins(5, 5, 5, 5);
        this.background_iv.setLayoutParams(bglp);
        this.background_iv.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.background_iv);
        this.text_iv.setText(this.text);
        this.text_iv.setTextColor(this.tColor);
        this.text_iv.setTextSize(200.0f);
        this.text_iv.setLayoutParams(tlp);
        this.text_iv.setGravity(17);
        this.text_iv.setMinTextSize(10.0f);
        this.rel_artv.setLayoutParams(tlp);
        this.rel_artv.addView(this.text_iv);
        addView(this.rel_artv);
        this.textRelative.setLayoutParams(tlp);
        addView(this.textRelative);
        this.rel_artv.setVisibility(4);
        addView(this.border_iv);
        this.border_iv.setLayoutParams(blp);
        this.border_iv.setTag("border_iv");
        addView(this.rotate_iv);
        this.rotate_iv.setLayoutParams(elp);
        this.rotate_iv.setOnTouchListener(this.rTouchListener);
        addView(this.delete_iv);
        this.delete_iv.setLayoutParams(dlp);
        this.delete_iv.setOnClickListener(new OnClickListener() {
            /* class com.js.styledtextview.textmodule.AutofitTextRel.AnonymousClass1 */

            public void onClick(View v) {
                final ViewGroup parent = (ViewGroup) AutofitTextRel.this.getParent();
                AutofitTextRel.this.zoomInScale.setAnimationListener(new Animation.AnimationListener() {
                    /* class com.js.styledtextview.textmodule.AutofitTextRel.AnonymousClass1.AnonymousClass1 */

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        parent.removeView(AutofitTextRel.this);
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                AutofitTextRel.this.text_iv.startAnimation(AutofitTextRel.this.zoomInScale);
                AutofitTextRel.this.background_iv.startAnimation(AutofitTextRel.this.zoomInScale);
                AutofitTextRel.this.textRelative.startAnimation(AutofitTextRel.this.zoomInScale);
                AutofitTextRel.this.setBorderVisibility(false);
                if (AutofitTextRel.this.listener != null) {
                    AutofitTextRel.this.listener.onDelete();
                }
            }
        });
        addView(this.scale_iv);
        this.scale_iv.setLayoutParams(slp);
        this.scale_iv.setTag("scale_iv");
        this.scale_iv.setOnTouchListener(this.mTouchListener1);
        this.rotation = getRotation();
        this.scale = AnimationUtils.loadAnimation(getContext(), R.anim.textlib_scale_anim);
        this.zoomOutScale = AnimationUtils.loadAnimation(getContext(), R.anim.textlib_scale_zoom_out);
        this.zoomInScale = AnimationUtils.loadAnimation(getContext(), R.anim.textlib_scale_zoom_in);
        initGD();
        setDefaultTouchListener();
    }

    public void setDefaultTouchListener() {
        setOnTouchListener(new MultiTouchListener().enableRotation(true).setOnTouchCallbackListener(this).setGestureListener(this.gd));
    }

    public void setWidthHeight(int wm2, int hm2) {
        this.wm = wm2;
        this.hm = hm2;
    }

    @SuppressLint("WrongConstant")
    public void setBorderVisibility(boolean ch) {
        this.isBorderVisible = ch;
        if (!ch) {
            this.border_iv.setVisibility(8);
            this.scale_iv.setVisibility(8);
            this.delete_iv.setVisibility(8);
            this.rotate_iv.setVisibility(8);
            setBackgroundResource(R.color.trans);
        } else if (this.border_iv.getVisibility() != 0) {
            this.border_iv.setVisibility(0);
            this.scale_iv.setVisibility(0);
            this.delete_iv.setVisibility(0);
            this.rotate_iv.setVisibility(0);
            setBackgroundResource(R.drawable.textlib_border_gray);
            this.text_iv.startAnimation(this.scale);
            this.textRelative.invalidate();
        }
    }

    public boolean getBorderVisibility() {
        return this.isBorderVisible;
    }

    public void setText(String text2) {
        this.text_iv.setText(text2);
        this.text = text2;
        this.text_iv.startAnimation(this.zoomOutScale);
        this.textRelative.invalidate();
    }

    public String getText() {
        return this.text_iv.getText().toString();
    }

    public void setTextFont(String fontName2) {
        try {
            this.text_iv.setTypeface(Typeface.createFromAsset(this.context.getAssets(), fontName2));
            this.fontName = fontName2;
            this.textRelative.invalidate();
        } catch (Exception e) {
        }
    }

    public void setTextColor(int color) {
        this.text_iv.setTextColor(color);
        this.tColor = color;
        this.textRelative.invalidate();
    }

    public void setTextAlpha(int prog) {
        this.text_iv.setAlpha(((float) prog) / 100.0f);
        this.tAlpha = prog;
        this.textRelative.invalidate();
    }

    public void setTextShadowColor(int color) {
        this.shadowColor = color;
        this.text_iv.setShadowLayer((float) this.shadowProg, 0.0f, 0.0f, this.shadowColor);
    }

    public void setTextShadowProg(int prog) {
        this.shadowProg = prog;
        this.text_iv.setShadowLayer((float) this.shadowProg, 0.0f, 0.0f, this.shadowColor);
    }

    public void setBgDrawable(String did) {
        this.bgDrawable = did;
        this.bgColor = 0;
        this.background_iv.setImageBitmap(getTiledBitmap(this.context, getResources().getIdentifier(did, "drawable", this.context.getPackageName()), dpToPx(this.context, this.wi), dpToPx(this.context, this.he)));
        this.background_iv.setBackgroundColor(this.bgColor);
    }

    public void setBgColor(int c) {
        this.bgDrawable = "0";
        this.bgColor = c;
        this.background_iv.setImageResource(0);
        this.background_iv.setBackgroundColor(c);
    }

    public void setBgAlpha(int prog) {
        this.background_iv.setAlpha(((float) prog) / 255.0f);
        this.bgAlpha = prog;
    }

    public void setTextRotateProg(int xRotateProg2, int yRotateProg2, int zRotateProg2, int x, int y, int z, int k) {
        this.xRotateProg = x;
        this.yRotateProg = y;
        this.zRotateProg = z;
        this.progress = k;
        applyTransformation(xRotateProg2, yRotateProg2, zRotateProg2, k);
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

    public int getCurveRotateProg() {
        return this.progress;
    }

    public void refreshNow() {
        this.textRelative.invalidate();
    }

    public void setTextCurveRotateProg(int curveRotateProg) {
        this.progress = curveRotateProg;
        this.prog_60 = (curveRotateProg * 60) / 100;
        this.prog_40 = (curveRotateProg * 40) / 100;
        this.prog_50 = (curveRotateProg * 50) / 100;
        this.prog_80 = (curveRotateProg * 80) / 100;
        this.prog_20 = (curveRotateProg * 20) / 100;
        this.textRelative.setTextCurveRotateProg(curveRotateProg);
        this.textRelative.invalidate();
    }

    private float[] getVertexSrc() {
        return new float[]{(float) this.x1, (float) this.y1, (float) this.x4, (float) this.y4, (float) this.x5, (float) this.y5, (float) this.x10, (float) this.y10, (float) this.x2, (float) this.y2, (float) this.x11, (float) this.y11, (float) this.x12, (float) this.y12, (float) this.x13, (float) this.y13, (float) this.x14, (float) this.y14, (float) this.x15, (float) this.y15, (float) this.x6, (float) this.y6, (float) this.x16, (float) this.y16, (float) this.x17, (float) this.y17, (float) this.x18, (float) this.y18, (float) this.x7, (float) this.y7, (float) this.x20, (float) this.y20, (float) this.x21, (float) this.y21, (float) this.x23, (float) this.y23, (float) this.x24, (float) this.y24, (float) this.x25, (float) this.y25, (float) this.x3, (float) this.y3, (float) this.x26, (float) this.y26, (float) this.x8, (float) this.y8, (float) this.x27, (float) this.y27, (float) this.x4, (float) this.y4};
    }

    public Path mPath(int x110, int y110, int x210, int y210, int radius, Canvas canvas, Paint p) {
        Path path = new Path();
        int midX = x110 + ((x210 - x110) / 2);
        int midY = y110 + ((y210 - y110) / 2);
        double angleRadians = Math.toRadians((Math.atan2((double) ((float) (midY - y110)), (double) ((float) (midX - x110))) * 57.29577951308232d) - 90.0d);
        path.moveTo((float) x110, (float) y110);
        path.cubicTo((float) x110, (float) y110, (float) (((double) midX) + (((double) radius) * Math.cos(angleRadians))), (float) (((double) midY) + (((double) radius) * Math.sin(angleRadians))), (float) x210, (float) y210);
        return path;
    }

    private float[] getVertexDst() {
        return new float[]{(float) (this.x1 + this.prog_80), (float) (this.y1 + this.prog_40), (float) (this.x2 + this.prog_60), (float) (this.y2 - this.prog_50), (float) (this.x3 + this.prog_40), (float) (this.y3 - this.prog_60), (float) (this.x4 + this.prog_20), (float) (this.y4 - this.prog_80), (float) this.x5, (float) (this.y5 - this.progress), (float) (this.x6 - this.prog_20), (float) (this.y6 - this.prog_80), (float) (this.x7 - this.prog_40), (float) (this.y7 - this.prog_60), (float) (this.x8 - this.prog_60), (float) (this.y8 - this.prog_50), (float) (this.x9 - this.prog_80), (float) (this.y9 + this.prog_40), (float) (this.x10 + this.prog_80), (float) (this.y10 + this.prog_40), (float) (this.x11 + this.prog_60), (float) (this.y11 - this.prog_50), (float) (this.x12 + this.prog_40), (float) (this.y12 - this.prog_60), (float) (this.x13 + this.prog_20), (float) (this.y13 - this.prog_80), (float) this.x14, (float) (this.y14 - this.progress), (float) (this.x15 - this.prog_20), (float) (this.y15 - this.prog_80), (float) (this.x16 - this.prog_40), (float) (this.y16 - this.prog_60), (float) (this.x17 - this.prog_60), (float) (this.y17 - this.prog_50), (float) (this.x18 - this.prog_80), (float) (this.y18 + this.prog_40), (float) (this.x19 + this.prog_80), (float) (this.y19 + this.prog_40), (float) (this.x20 + this.prog_60), (float) (this.y20 - this.prog_50), (float) (this.x21 + this.prog_40), (float) (this.y21 - this.prog_60), (float) (this.x22 + this.prog_20), (float) (this.y22 - this.prog_80), (float) this.x23, (float) (this.y23 - this.progress), (float) (this.x24 - this.prog_20), (float) (this.y24 - this.prog_80), (float) (this.x25 - this.prog_40), (float) (this.y25 - this.prog_60), (float) (this.x26 - this.prog_60), (float) (this.y26 - this.prog_50), (float) (this.x27 - this.prog_80), (float) (this.y27 + this.prog_40), (float) (this.x28 + this.prog_80), (float) (this.y28 + this.prog_40), (float) (this.x29 + this.prog_60), (float) (this.y29 - this.prog_50), (float) (this.x30 + this.prog_40), (float) (this.y30 - this.prog_60), (float) (this.x31 + this.prog_20), (float) (this.y31 - this.prog_80), (float) this.x32, (float) (this.y32 - this.progress), (float) (this.x33 - this.prog_20), (float) (this.y33 - this.prog_80), (float) (this.x34 - this.prog_40), (float) (this.y34 - this.prog_60), (float) (this.x35 - this.prog_60), (float) (this.y35 - this.prog_50), (float) (this.x36 - this.prog_80), (float) (this.y36 + this.prog_40), (float) (this.x37 + this.prog_80), (float) (this.y37 + this.prog_40), (float) (this.x38 + this.prog_60), (float) (this.y38 - this.prog_50), (float) (this.x39 + this.prog_40), (float) (this.y39 - this.prog_60), (float) (this.x40 + this.prog_20), (float) (this.y40 - this.prog_80), (float) this.x41, (float) (this.y41 - this.progress), (float) (this.x42 - this.prog_20), (float) (this.y42 - this.prog_80), (float) (this.x43 - this.prog_40), (float) (this.y43 - this.prog_60), (float) (this.x44 - this.prog_60), (float) (this.y44 - this.prog_50), (float) (this.x45 - this.prog_80), (float) (this.y45 + this.prog_40), (float) (this.x46 + this.prog_80), (float) (this.y46 + this.prog_40), (float) (this.x47 + this.prog_60), (float) (this.y47 - this.prog_50), (float) (this.x48 + this.prog_40), (float) (this.y48 - this.prog_60), (float) (this.x49 + this.prog_20), (float) (this.y49 - this.prog_80), (float) this.x50, (float) (this.y50 - this.progress), (float) (this.x51 - this.prog_20), (float) (this.y51 - this.prog_80), (float) (this.x52 - this.prog_40), (float) (this.y52 - this.prog_60), (float) (this.x53 - this.prog_60), (float) (this.y53 - this.prog_50), (float) (this.x54 - this.prog_80), (float) (this.y54 + this.prog_40), (float) (this.x55 + this.prog_80), (float) (this.y55 + this.prog_40), (float) (this.x56 + this.prog_60), (float) (this.y56 - this.prog_50), (float) (this.x57 + this.prog_40), (float) (this.y57 - this.prog_60), (float) (this.x58 + this.prog_20), (float) (this.y58 - this.prog_80), (float) this.x59, (float) (this.y59 - this.progress), (float) (this.x60 - this.prog_20), (float) (this.y60 - this.prog_80), (float) (this.x61 - this.prog_40), (float) (this.y61 - this.prog_60), (float) (this.x62 - this.prog_60), (float) (this.y62 - this.prog_50), (float) (this.x63 - this.prog_80), (float) (this.y63 + this.prog_40), (float) (this.x64 + this.prog_80), (float) (this.y64 + this.prog_40), (float) (this.x65 + this.prog_60), (float) (this.y65 - this.prog_50), (float) (this.x66 + this.prog_40), (float) (this.y66 - this.prog_60), (float) (this.x67 + this.prog_20), (float) (this.y67 - this.prog_80), (float) this.x68, (float) (this.y68 - this.progress), (float) (this.x69 - this.prog_20), (float) (this.y69 - this.prog_80), (float) (this.x70 - this.prog_40), (float) (this.y70 - this.prog_60), (float) (this.x71 - this.prog_60), (float) (this.y71 - this.prog_50), (float) (this.x72 - this.prog_80), (float) (this.y72 + this.prog_40), (float) (this.x73 + this.prog_80), (float) (this.y73 + this.prog_40), (float) (this.x74 + this.prog_60), (float) (this.y74 - this.prog_50), (float) (this.x75 + this.prog_40), (float) (this.y75 - this.prog_60), (float) (this.x76 + this.prog_20), (float) (this.y76 - this.prog_80), (float) this.x77, (float) (this.y77 - this.progress), (float) (this.x78 - this.prog_20), (float) (this.y78 - this.prog_80), (float) (this.x79 - this.prog_40), (float) (this.y79 - this.prog_60), (float) (this.x80 - this.prog_60), (float) (this.y80 - this.prog_50), (float) (this.x81 - this.prog_80), (float) (this.y81 + this.prog_40)};
    }

    /* access modifiers changed from: protected */
    @SuppressLint("WrongConstant")
    public void applyTransformation(int angle1, int angle2, int angle3, int k) {
        this.textRelative.setRotationX((float) angle1);
        this.textRelative.setRotationY((float) angle2);
        this.textRelative.setTextCurveRotateProg(k);
        setVisibility(0);
        this.textRelative.setVisibility(0);
        this.rel_artv.requestLayout();
        this.rel_artv.postInvalidate();
        this.textRelative.requestLayout();
        this.textRelative.postInvalidate();
        this.textRelative.invalidate();
        requestLayout();
        postInvalidate();
    }

    public TextInfo getTextInfo() {
        TextInfo textInfo = new TextInfo();
        textInfo.setPOS_X(getX());
        textInfo.setPOS_Y(getY());
        textInfo.setWIDTH(this.wi);
        textInfo.setHEIGHT(this.he);
        textInfo.setTEXT(this.text);
        textInfo.setFONT_NAME(this.fontName);
        textInfo.setTEXT_COLOR(this.tColor);
        textInfo.setTEXT_ALPHA(this.tAlpha);
        textInfo.setSHADOW_COLOR(this.shadowColor);
        textInfo.setSHADOW_PROG(this.shadowProg);
        textInfo.setBG_COLOR(this.bgColor);
        textInfo.setBG_DRAWABLE(this.bgDrawable);
        textInfo.setBG_ALPHA(this.bgAlpha);
        textInfo.setROTATION(getRotation());
        return textInfo;
    }

    public void setTextInfo(TextInfo textInfo, boolean b) {
        this.wi = textInfo.getWIDTH();
        this.he = textInfo.getHEIGHT();
        this.text = textInfo.getTEXT();
        this.fontName = textInfo.getFONT_NAME();
        this.tColor = textInfo.getTEXT_COLOR();
        this.tAlpha = textInfo.getTEXT_ALPHA();
        this.shadowColor = textInfo.getSHADOW_COLOR();
        this.shadowProg = textInfo.getSHADOW_PROG();
        this.bgColor = textInfo.getBG_COLOR();
        this.bgDrawable = textInfo.getBG_DRAWABLE();
        this.bgAlpha = textInfo.getBG_ALPHA();
        this.posX = (int) textInfo.getPOS_X();
        this.posY = (int) textInfo.getPOS_Y();
        this.rotation = textInfo.getROTATION();
        this.xRotateProg = textInfo.getXRotateProg();
        this.yRotateProg = textInfo.getYRotateProg();
        this.zRotateProg = textInfo.getZRotateProg();
        this.progress = textInfo.getCurveRotateProg();
        setX((float) this.posX);
        setY((float) this.posY);
        setText(this.text);
        setTextFont(this.fontName);
        setTextColor(this.tColor);
        setTextAlpha(this.tAlpha);
        setTextShadowColor(this.shadowColor);
        setTextShadowProg(this.shadowProg);
        if (this.bgColor != 0) {
            setBgColor(this.bgColor);
        } else {
            this.background_iv.setBackgroundColor(0);
        }
        if (!this.bgDrawable.equals("0")) {
            setBgDrawable(this.bgDrawable);
        } else {
            this.background_iv.setImageBitmap(null);
        }
        setBgAlpha(this.bgAlpha);
        setRotation(this.rotation);
        if (!b) {
            getLayoutParams().width = this.wi;
            getLayoutParams().height = this.he;
        }
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

    public int dpToPx(Context c, int dp) {
        c.getResources();
        return (int) (((float) dp) * Resources.getSystem().getDisplayMetrics().density);
    }

    private Bitmap getTiledBitmap(Context ctx, int resId, int width, int height) {
        Rect rect = new Rect(0, 0, width, height);
        Paint paint2 = new Paint();
        paint2.setShader(new BitmapShader(BitmapFactory.decodeResource(ctx.getResources(), resId, new BitmapFactory.Options()), Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        new Canvas(b).drawRect(rect, paint2);
        return b;
    }

    private void initGD() {
        this.gd = new GestureDetector(this.context, new GestureDetector.SimpleOnGestureListener() {
            /* class com.js.styledtextview.textmodule.AutofitTextRel.AnonymousClass4 */

            public boolean onDoubleTap(MotionEvent e) {
                if (AutofitTextRel.this.listener == null) {
                    return true;
                }
                AutofitTextRel.this.listener.onDoubleTap();
                return true;
            }

            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
            }

            public boolean onDoubleTapEvent(MotionEvent e) {
                return true;
            }

            public boolean onDown(MotionEvent e) {
                return true;
            }
        });
    }

    @Override // com.js.styledtextview.textmodule.listener.MultiTouchListener.TouchCallbackListener
    public void onTouchCallback(View v) {
        if (this.listener != null) {
            this.listener.onTouchDown(v);
        }
    }

    @Override // com.js.styledtextview.textmodule.listener.MultiTouchListener.TouchCallbackListener
    public void onTouchUpCallback(View v) {
        if (this.listener != null) {
            this.listener.onTouchUp(v);
        }
    }
}
