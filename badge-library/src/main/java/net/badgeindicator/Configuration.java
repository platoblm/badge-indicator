package net.badgeindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.util.Arrays;

import java.util.Arrays;

import static android.graphics.Paint.Align.CENTER;
import static android.graphics.Paint.Style.FILL;

class Configuration {

    private static final int ATTR_PADDING = android.R.attr.padding;
    private static final int ATTR_BADGE_COLOR = android.R.attr.color;
    private static final int ATTR_TEXT_COLOR = android.R.attr.textColor;
    private static final int ATTR_TEXT_SIZE = android.R.attr.textSize;
    private static final int ATTR_VALUE = android.R.attr.value;
    private static final int[] ATTR_SET = createAttrSet();

    private static int[] createAttrSet() {
        int[] attrs = {
                ATTR_PADDING,
                ATTR_BADGE_COLOR,
                ATTR_TEXT_COLOR,
                ATTR_TEXT_SIZE,
                ATTR_VALUE
        };
        Arrays.sort(attrs);
        return attrs;
    }

    private final Paint textPaint = new Paint();
    private final Paint backgroundPaint = new Paint();

    private int value;
    private int padding;
    private String textToDraw = "0";

    Configuration() {
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.setTextAlign(CENTER);
        textPaint.setColor(Color.WHITE);
        textPaint.setTypeface(Typeface.DEFAULT);

        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setDither(true);
        backgroundPaint.setStyle(FILL);
        backgroundPaint.setColor(Color.RED);
    }


    Paint getTextPaint() {
        return textPaint;
    }

    Paint getBackgroundPaint() {
        return backgroundPaint;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
        this.textToDraw = String.valueOf(value);
    }

    String getTextToDraw() {
        return textToDraw;
    }

    int getPadding() {
        return padding;
    }

    void loadDefaults(Context context) {
        Resources resources = context.getResources();
        textPaint.setTextSize(resources.getDimensionPixelSize(R.dimen.badge_indicator_default_text_size));
        padding = resources.getDimensionPixelSize(R.dimen.badge_indicator_default_padding);
    }

    void loadAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, ATTR_SET);
        loadFromAttrArray(a);
        a.recycle();
    }

    private void loadFromAttrArray(TypedArray a) {
        value = a.getInt(indexOfAttr(ATTR_VALUE), value);
        padding = a.getDimensionPixelSize(indexOfAttr(ATTR_PADDING), padding);

        int badgeColor = a.getColor(indexOfAttr(ATTR_BADGE_COLOR), backgroundPaint.getColor());
        backgroundPaint.setColor(badgeColor);

        int textColor = a.getColor(indexOfAttr(ATTR_TEXT_COLOR), textPaint.getColor());
        textPaint.setColor(textColor);

        int textSize = a.getDimensionPixelSize(indexOfAttr(ATTR_TEXT_SIZE), (int)textPaint.getTextSize());
        textPaint.setTextSize(textSize);
    }


    private int indexOfAttr(int id) {
        for (int i = 0; i < ATTR_SET.length; i++) {
            if (ATTR_SET[i] == id) return i;
        }

        throw new RuntimeException("id " + id +  " not in ATTR_SET");
    }
}
