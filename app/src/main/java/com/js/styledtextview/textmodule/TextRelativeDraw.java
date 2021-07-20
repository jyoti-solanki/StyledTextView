package com.js.styledtextview.textmodule;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;

public class TextRelativeDraw extends View {
    private Context context;
    int progress = 0;
    RelativeLayout rel_artv;
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

    public TextRelativeDraw(Context context2, RelativeLayout rel_artv2) {
        super(context2);
        this.context = context2;
        this.rel_artv = rel_artv2;
        init(context2);
    }

    public void init(Context ctx) {
        this.context = ctx;
    }

    public void setTextCurveRotateProg(int curveRotateProg) {
        this.progress = curveRotateProg;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Log.e("rel_artv is", "" + this.rel_artv);
        if (this.rel_artv != null) {
            Paint p = new Paint();
            p.setColor(-16711936);
            this.rel_artv.setDrawingCacheEnabled(true);
            Bitmap bit = Bitmap.createBitmap(this.rel_artv.getDrawingCache());
            this.rel_artv.setDrawingCacheEnabled(false);
            Bitmap.createBitmap(bit.getWidth(), bit.getHeight(), Bitmap.Config.ARGB_8888);
            Bitmap bit2 = Bitmap.createScaledBitmap(bit, bit.getWidth() - 50, bit.getHeight() - 50, false);
            int w = bit2.getWidth();
            int h = bit2.getHeight();
            int wd = w / 8;
            int hd = h / 8;
            this.x1 = 25;
            this.y1 = 25;
            this.x2 = (wd * 1) + 25;
            this.y2 = 25;
            this.x3 = (wd * 2) + 25;
            this.y3 = 25;
            this.x4 = (wd * 3) + 25;
            this.y4 = 25;
            this.x5 = (wd * 4) + 25;
            this.y5 = 25;
            this.x6 = (wd * 5) + 25;
            this.y6 = 25;
            this.x7 = (wd * 6) + 25;
            this.y7 = 25;
            this.x8 = (wd * 7) + 25;
            this.y8 = 25;
            this.x9 = 25 + w;
            this.y9 = 25;
            this.x10 = 25;
            this.y10 = 25 + hd;
            this.x11 = (wd * 1) + 25;
            this.y11 = 25 + hd;
            this.x12 = (wd * 2) + 25;
            this.y12 = 25 + hd;
            this.x13 = (wd * 3) + 25;
            this.y13 = 25 + hd;
            this.x14 = (wd * 4) + 25;
            this.y14 = 25 + hd;
            this.x15 = (wd * 5) + 25;
            this.y15 = 25 + hd;
            this.x16 = (wd * 6) + 25;
            this.y16 = 25 + hd;
            this.x17 = (wd * 7) + 25;
            this.y17 = 25 + hd;
            this.x18 = 25 + w;
            this.y18 = 25 + hd;
            this.x19 = 25;
            this.y19 = (hd * 2) + 25;
            this.x20 = (wd * 1) + 25;
            this.y20 = (hd * 2) + 25;
            this.x21 = (wd * 2) + 25;
            this.y21 = (hd * 2) + 25;
            this.x22 = (wd * 3) + 25;
            this.y22 = (hd * 2) + 25;
            this.x23 = (wd * 4) + 25;
            this.y23 = (hd * 2) + 25;
            this.x24 = (wd * 5) + 25;
            this.y24 = (hd * 2) + 25;
            this.x25 = (wd * 6) + 25;
            this.y25 = (hd * 2) + 25;
            this.x26 = (wd * 7) + 25;
            this.y26 = (hd * 2) + 25;
            this.x27 = 25 + w;
            this.y27 = (hd * 2) + 25;
            this.x28 = 25;
            this.y28 = (hd * 3) + 25;
            this.x29 = (wd * 1) + 25;
            this.y29 = (hd * 3) + 25;
            this.x30 = (wd * 2) + 25;
            this.y30 = (hd * 3) + 25;
            this.x31 = (wd * 3) + 25;
            this.y31 = (hd * 3) + 25;
            this.x32 = (wd * 4) + 25;
            this.y32 = (hd * 3) + 25;
            this.x33 = (wd * 5) + 25;
            this.y33 = (hd * 3) + 25;
            this.x34 = (wd * 6) + 25;
            this.y34 = (hd * 3) + 25;
            this.x35 = (wd * 7) + 25;
            this.y35 = (hd * 3) + 25;
            this.x36 = 25 + w;
            this.y36 = (hd * 3) + 25;
            this.x37 = 25;
            this.y37 = (hd * 4) + 25;
            this.x38 = (wd * 1) + 25;
            this.y38 = (hd * 4) + 25;
            this.x39 = (wd * 2) + 25;
            this.y39 = (hd * 4) + 25;
            this.x40 = (wd * 3) + 25;
            this.y40 = (hd * 4) + 25;
            this.x41 = (wd * 4) + 25;
            this.y41 = (hd * 4) + 25;
            this.x42 = (wd * 5) + 25;
            this.y42 = (hd * 4) + 25;
            this.x43 = (wd * 6) + 25;
            this.y43 = (hd * 4) + 25;
            this.x44 = (wd * 7) + 25;
            this.y44 = (hd * 4) + 25;
            this.x45 = 25 + w;
            this.y45 = (hd * 4) + 25;
            this.x46 = 25;
            this.y46 = (hd * 5) + 25;
            this.x47 = (wd * 1) + 25;
            this.y47 = (hd * 5) + 25;
            this.x48 = (wd * 2) + 25;
            this.y48 = (hd * 5) + 25;
            this.x49 = (wd * 3) + 25;
            this.y49 = (hd * 5) + 25;
            this.x50 = (wd * 4) + 25;
            this.y50 = (hd * 5) + 25;
            this.x51 = (wd * 5) + 25;
            this.y51 = (hd * 5) + 25;
            this.x52 = (wd * 6) + 25;
            this.y52 = (hd * 5) + 25;
            this.x53 = (wd * 7) + 25;
            this.y53 = (hd * 5) + 25;
            this.x54 = 25 + w;
            this.y54 = (hd * 5) + 25;
            this.x55 = 25;
            this.y55 = (hd * 6) + 25;
            this.x56 = (wd * 1) + 25;
            this.y56 = (hd * 6) + 25;
            this.x57 = (wd * 2) + 25;
            this.y57 = (hd * 6) + 25;
            this.x58 = (wd * 3) + 25;
            this.y58 = (hd * 6) + 25;
            this.x59 = (wd * 4) + 25;
            this.y59 = (hd * 6) + 25;
            this.x60 = (wd * 5) + 25;
            this.y60 = (hd * 6) + 25;
            this.x61 = (wd * 6) + 25;
            this.y61 = (hd * 6) + 25;
            this.x62 = (wd * 7) + 25;
            this.y62 = (hd * 6) + 25;
            this.x63 = 25 + w;
            this.y63 = (hd * 6) + 25;
            this.x64 = 25;
            this.y64 = (hd * 7) + 25;
            this.x65 = (wd * 1) + 25;
            this.y65 = (hd * 7) + 25;
            this.x66 = (wd * 2) + 25;
            this.y66 = (hd * 7) + 25;
            this.x67 = (wd * 3) + 25;
            this.y67 = (hd * 7) + 25;
            this.x68 = (wd * 4) + 25;
            this.y68 = (hd * 7) + 25;
            this.x69 = (wd * 5) + 25;
            this.y69 = (hd * 7) + 25;
            this.x70 = (wd * 6) + 25;
            this.y70 = (hd * 7) + 25;
            this.x71 = (wd * 7) + 25;
            this.y71 = (hd * 7) + 25;
            this.x72 = 25 + w;
            this.y72 = (hd * 7) + 25;
            this.x73 = 25;
            this.y73 = 25 + h;
            this.x74 = (wd * 1) + 25;
            this.y74 = 25 + h;
            this.x75 = (wd * 2) + 25;
            this.y75 = 25 + h;
            this.x76 = (wd * 3) + 25;
            this.y76 = 25 + h;
            this.x77 = (wd * 4) + 25;
            this.y77 = 25 + h;
            this.x78 = (wd * 5) + 25;
            this.y78 = 25 + h;
            this.x79 = (wd * 6) + 25;
            this.y79 = 25 + h;
            this.x80 = (wd * 7) + 25;
            this.y80 = 25 + h;
            this.x81 = 25 + w;
            this.y81 = 25 + h;
            Path[] arrayPath = {mPath(this.x1, this.y1, this.x9, this.y9, this.progress, canvas, p), mPath(this.x10, this.y10, this.x18, this.y18, this.progress, canvas, p), mPath(this.x19, this.y19, this.x27, this.y27, this.progress, canvas, p), mPath(this.x28, this.y28, this.x36, this.y36, this.progress, canvas, p), mPath(this.x37, this.y37, this.x45, this.y45, this.progress, canvas, p), mPath(this.x46, this.y46, this.x54, this.y54, this.progress, canvas, p), mPath(this.x55, this.y55, this.x63, this.y63, this.progress, canvas, p), mPath(this.x64, this.y64, this.x72, this.y72, this.progress, canvas, p), mPath(this.x73, this.y73, this.x81, this.y81, this.progress, canvas, p)};
            List<Float> mVerts1 = new ArrayList<>();
            for (int a = 0; a < arrayPath.length; a++) {
                PathMeasure pm = new PathMeasure(arrayPath[a], false);
                float[] centerPoint = {0.0f, 0.0f};
                for (float i = 0.0f; i <= 1.0f; i += 0.125f) {
                    pm.getPosTan(pm.getLength() * i, centerPoint, null);
                    mVerts1.add(Float.valueOf(centerPoint[0]));
                    mVerts1.add(Float.valueOf(centerPoint[1]));
                }
            }
            float[] stockArr = new float[mVerts1.size()];
            for (int k = 0; k < stockArr.length; k++) {
                stockArr[k] = mVerts1.get(k).floatValue();
            }
            canvas.drawBitmapMesh(bit2, 8, 8, stockArr, 0, null, 0, null);
            return;
        }
        Log.e("not show", "visiblity");
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

    public int dpToPx(Context c, int dp) {
        c.getResources();
        return (int) (((float) dp) * Resources.getSystem().getDisplayMetrics().density);
    }

    public static Bitmap resizeBitmap(Bitmap bit, int width, int height) {
        float wd;
        float he;
        float wr = (float) width;
        float hr = (float) height;
        float wd2 = (float) bit.getWidth();
        float he2 = (float) bit.getHeight();
        Log.i("testings", wr + "  " + hr + "  and  " + wd2 + "  " + he2);
        float rat1 = wd2 / he2;
        float rat2 = he2 / wd2;
        if (wd2 > wr) {
            float he3 = wr * rat2;
            Log.i("testings", "if (wd > wr) " + wr + "  " + he3);
            if (he3 > hr) {
                he = hr;
                wd = he * rat1;
                Log.i("testings", "  if (he > hr) " + wd + "  " + he);
            } else {
                wd = wr;
                he = wd * rat2;
                Log.i("testings", " in else " + wd + "  " + he);
            }
        } else if (he2 > hr) {
            he = hr;
            wd = he * rat1;
            Log.i("testings", "  if (he > hr) " + wd + "  " + he);
            if (wd > wr) {
                wd = wr;
                he = wd * rat2;
            } else {
                Log.i("testings", " in else " + wd + "  " + he);
            }
        } else if (rat1 > 0.75f) {
            wd = wr;
            he = wd * rat2;
            Log.i("testings", " if (rat1 > .75f) ");
        } else if (rat2 > 1.5f) {
            he = hr;
            wd = he * rat1;
            Log.i("testings", " if (rat2 > 1.5f) ");
        } else {
            Log.i("testings", " in else ");
            if (wr * rat2 > hr) {
                he = hr;
                wd = he * rat1;
                Log.i("testings", "  if (he > hr) " + wd + "  " + he);
            } else {
                wd = wr;
                he = wd * rat2;
                Log.i("testings", " in else " + wd + "  " + he);
            }
        }
        return Bitmap.createScaledBitmap(bit, (int) wd, (int) he, false);
    }
}
