package net.badgeindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;

import static android.graphics.Paint.Align.CENTER;
import static android.graphics.Paint.Style.FILL;

class Configuration {

    private static final int ATTR_PADDING = android.R.attr.padding;
    private static final int ATTR_BADGE_COLOR = android.R.attr.color;
    private static final int ATTR_TEXT_COLOR = android.R.attr.textColor;
    private static final int ATTR_TEXT_SIZE = android.R.attr.textSize;

    private final Paint textPaint = new Paint();
    private final Paint backgroundPaint = new Paint();

    private int value;
    private int padding;
    private String textToDraw = "0";

    Configuration() {
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(CENTER);
        textPaint.setColor(Color.WHITE);
        textPaint.setTypeface(Typeface.DEFAULT);
        textPaint.setTextSize(15);

        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setStyle(FILL);
        backgroundPaint.setColor(Color.GREEN);
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
    
    void loadAttributes(Context context, AttributeSet attrs) {
        int[] set = {
                ATTR_PADDING, ATTR_BADGE_COLOR, ATTR_TEXT_COLOR, ATTR_TEXT_SIZE, 
        };
        
        TypedArray a = context.obtainStyledAttributes(attrs, set);
        try {
            padding = a.getDimensionPixelSize(indexOf(ATTR_PADDING, set), 0);

            int badgeColor = a.getColor(indexOf(ATTR_BADGE_COLOR, set), backgroundPaint.getColor());
            backgroundPaint.setColor(badgeColor);

            int textColor = a.getColor(indexOf(ATTR_TEXT_COLOR, set), textPaint.getColor());
            int textSize = a.getDimensionPixelSize(indexOf(ATTR_TEXT_SIZE, set), (int)textPaint.getTextSize());
            textPaint.setColor(Color.YELLOW);
            textPaint.setTextSize(200);
        } finally {
            a.recycle();
        }
        
    }
    
    private int indexOf(int id, int[] idSet) {
        for (int i = 0; i < idSet.length; i++) {
            if (idSet[i] == id) {
                return i;
            }
        }
        throw new RuntimeException("id " + id +  " not in idSet");
    }
}
