package net.badgeindicator;

import android.graphics.Paint;
import android.graphics.Typeface;

import static android.graphics.Paint.Align.CENTER;
import static android.graphics.Paint.Style.FILL;

public class Configuration {

    private ConfigurationListener listener;
    private final Paint textPaint = new Paint();
    private final Paint backgroundPaint = new Paint();

    private int value;
    private int padding;
    private String textToDraw = "0";

    Configuration() {
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(CENTER);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);

        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setStyle(FILL);
    }
    
    Paint getTextPaint() {
        return textPaint;
    }

    Paint getBackgroundPaint() {
        return backgroundPaint;
    }

    public int getValue() {
        return value;
    }

    /**
     * @param value the value shown on the badge
     */
    public void setValue(int value) {
        this.value = value;
        this.textToDraw = String.valueOf(value);
        requestMeasurementsUpdate();
    }

    String getTextToDraw() {
        return textToDraw;
    }

    public int getPadding() {
        return padding;
    }

    /**
     * @param padding in pixels
     */
    public void setPadding(int padding) {
        this.padding = padding;
        requestMeasurementsUpdate();
    }

    private void requestMeasurementsUpdate() {
        listener.measurementsNeedToBeUpdated();
    }

    void setListener(ConfigurationListener listener) {
        this.listener = listener;
    }
    
    
}
