package com.js.styledtextview.demo;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class CustomTypefaceSpan extends MetricAffectingSpan {
    private final Typeface typeface;

    public CustomTypefaceSpan(Typeface typeface2) {
        this.typeface = typeface2;
    }

    public void updateDrawState(TextPaint drawState) {
        apply(drawState);
    }

    public void updateMeasureState(TextPaint paint) {
        apply(paint);
    }

    private void apply(Paint paint) {
        paint.setFakeBoldText(true);
        paint.setTypeface(this.typeface);
    }
}
