package net.badgeindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;

import static android.graphics.Paint.Align.CENTER;
import static android.graphics.Paint.Style.FILL;

class Configuration {

    private final Paint textPaint = new Paint();
    private final Paint backgroundPaint = new Paint();

    private int value;
    private int padding;

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
    }

    String getValueToDraw() {
        return String.valueOf(value);
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
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BadgeIndicator);
        loadFromAttrArray(a);
        a.recycle();
    }

    private void loadFromAttrArray(TypedArray a) {
        value = a.getInt(R.styleable.BadgeIndicator_badge_value, value);
        padding = a.getDimensionPixelSize(R.styleable.BadgeIndicator_badge_padding, padding);

        int badgeColor = a.getColor(R.styleable.BadgeIndicator_badge_color, backgroundPaint.getColor());
        backgroundPaint.setColor(badgeColor);

        int textColor = a.getColor(R.styleable.BadgeIndicator_badge_textColor, textPaint.getColor());
        textPaint.setColor(textColor);

        int textSize = a.getDimensionPixelSize(R.styleable.BadgeIndicator_badge_textSize, (int)textPaint.getTextSize());
        textPaint.setTextSize(textSize);
    }
}
