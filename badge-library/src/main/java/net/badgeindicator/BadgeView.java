package net.badgeindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

public class BadgeView extends View {

    private final Validator validator = new Validator();
    private final Configuration configuration = new Configuration();
    private final Measurements measurements = new Measurements();
    private final Paints paints = new Paints();

    public BadgeView(Context context) {
        super(context);
        init();
    }

    public BadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        configuration.loadAttributes(context, attrs);
    }

    /**
     * @param value the value shown on the badge
     */
    public void setValue(int value) {
        configuration.setValue(value);
        requestLayout();
    }
    
    public int getValue() {
        return configuration.getValue();
    }

    Configuration getConfiguration() {
        return configuration;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        validator.validateLayoutParameters(this);

        paints.configure(configuration);
        measurements.update(configuration, paints);

        setMeasuredDimension(measurements.getWidth(), measurements.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // background
        canvas.drawPath(measurements.getBackgroundPath(), paints.getBackgroundPaint());

        // text
        PointF textOrigin = measurements.getTextOrigin();
        canvas.drawText(configuration.getValueToDraw(), textOrigin.x, textOrigin.y, paints.getTextPaint());
    }

    private void init() {
        configuration.loadDefaults(getContext());
    }
}
