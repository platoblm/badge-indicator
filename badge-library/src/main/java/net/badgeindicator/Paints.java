package net.badgeindicator;

import android.graphics.Paint;
import android.graphics.Typeface;

import static android.graphics.Paint.Align.CENTER;
import static android.graphics.Paint.Style.FILL;

class Paints {

    private final Paint textPaint = new Paint();
    private final Paint backgroundPaint = new Paint();

    Paints() {
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.setTextAlign(CENTER);
        textPaint.setTypeface(Typeface.DEFAULT);

        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setDither(true);
        backgroundPaint.setStyle(FILL);
    }

    Paint getTextPaint() {
        return textPaint;
    }

    Paint getBackgroundPaint() {
        return backgroundPaint;
    }

    public void configure(Configuration config) {
        textPaint.setColor(config.getTextColor());
        textPaint.setTextSize(config.getTextSize());

        backgroundPaint.setColor(config.getBackgroundColor());
    }
}
