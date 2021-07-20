package com.js.styledtextview.demo;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import java.io.File;

public class LibContants {
    public static String getPackageId() {
        return "Generic";
    }

    public static File getSaveFileLocation() {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "/Snappy Photo/.data");
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
