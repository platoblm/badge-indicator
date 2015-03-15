package net.badgeindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;

class Configuration {

    private int value;
    private int backgroundColor;
    private int textColor;
    private int textSize;
    private int padding;

    public int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    String getValueToDraw() {
        return String.valueOf(value);
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public int getPadding() {
        return padding;
    }

    void loadDefaults(Context context) {
        Resources resources = context.getResources();
        backgroundColor = Color.RED;
        textColor = Color.WHITE;
        textSize= resources.getDimensionPixelSize(R.dimen.badge_indicator_default_text_size);
        padding = resources.getDimensionPixelSize(R.dimen.badge_indicator_default_padding);
    }

    void loadAttributes(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BadgeIndicator);
        loadFromAttributeArray(array);
        array.recycle();
    }

    private void loadFromAttributeArray(TypedArray array) {
        value = array.getInt(R.styleable.BadgeIndicator_badge_value, value);
        backgroundColor = array.getColor(R.styleable.BadgeIndicator_badge_color, backgroundColor);
        textColor = array.getColor(R.styleable.BadgeIndicator_badge_textColor, textColor);
        textSize = array.getDimensionPixelSize(R.styleable.BadgeIndicator_badge_textSize, textSize);
        padding = array.getDimensionPixelSize(R.styleable.BadgeIndicator_badge_padding, padding);
    }
}
