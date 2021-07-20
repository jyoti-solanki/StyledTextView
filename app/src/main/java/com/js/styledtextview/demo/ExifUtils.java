package com.js.styledtextview.demo;

import android.media.ExifInterface;
import android.text.TextUtils;

import com.js.styledtextview.seekbar.VerticalSeekBar;

public class ExifUtils {
    private ExifUtils() {
    }

    public static int getExifRotation(String imgPath) {
        try {
            String rotationAmount = new ExifInterface(imgPath).getAttribute("Orientation");
            if (TextUtils.isEmpty(rotationAmount)) {
                return 0;
            }
            switch (Integer.parseInt(rotationAmount)) {
                case 1:
                case 2:
                case 4:
                case 5:
                case 7:
                default:
                    return 0;
                case 3:
                    return 180;
                case 6:
                    return 90;
                case 8:
                    return VerticalSeekBar.ROTATION_ANGLE_CW_270;
            }
        } catch (Exception e) {
            return 0;
        }
    }
}
