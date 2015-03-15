package net.badgeindicator;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;

class Configuration {

    private int value;
    private ColorStateList backgroundColors;
    private ColorStateList textColors;
    private int textSize;
    private int padding;

    private int currentBackgroundColor;
    private int currentTextColor;

    public int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    String getValueToDraw() {
        return String.valueOf(value);
    }

    public int getCurrentBackgroundColor() {
        return currentBackgroundColor;
    }

    public int getCurrentTextColor() {
        return currentTextColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public int getPadding() {
        return padding;
    }

    void loadDefaults(Context context) {
        Resources resources = context.getResources();
        backgroundColors = ColorStateList.valueOf(RED);
        textColors = ColorStateList.valueOf(WHITE);
        textSize= resources.getDimensionPixelSize(R.dimen.badge_indicator_default_text_size);
        padding = resources.getDimensionPixelSize(R.dimen.badge_indicator_default_padding);
    }

    void loadAttributes(Context context, AttributeSet attrs) {
        TypedArray appearance = context.obtainStyledAttributes(attrs, R.styleable.BadgeIndicator);
        loadFromAttributeArray(appearance);
        appearance.recycle();
    }

    /**
     * @param stateSet the current drawable state
     * @return true if colors have changed, false if not
     */
    boolean updateCurrentColors(int[] stateSet) {
        final int backgroundColor = currentBackgroundColor;
        final int textColor = currentTextColor;

        currentBackgroundColor = backgroundColors.getColorForState(stateSet, 0);
        currentTextColor = textColors.getColorForState(stateSet, 0);

        return backgroundColor != currentBackgroundColor ||
               textColor != currentTextColor;
    }

    private void loadFromAttributeArray(TypedArray appearance) {
        value = appearance.getInt(R.styleable.BadgeIndicator_badge_value, value);
        backgroundColors = getColorStateList(appearance,R.styleable.BadgeIndicator_badge_color, backgroundColors);
        textColors = getColorStateList(appearance, R.styleable.BadgeIndicator_badge_textColor, textColors);
        textSize = appearance.getDimensionPixelSize(R.styleable.BadgeIndicator_badge_textSize, textSize);
        padding = appearance.getDimensionPixelSize(R.styleable.BadgeIndicator_badge_padding, padding);
    }

    private ColorStateList getColorStateList(TypedArray appearance, int resourceIndex, ColorStateList defaultColors) {
        ColorStateList colors = appearance.getColorStateList(resourceIndex);
        return colors != null ? colors : defaultColors;
    }
}
