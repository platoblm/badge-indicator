package net.badgeindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

public class BadgeView extends View {

    private final Validator validator = new Validator();
    private final Measurements measurements = new Measurements();
    private Configuration configuration;
    

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
        
        measurements.update(configuration);
        setMeasuredDimension(measurements.getWidth(), measurements.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(measurements.getBackgroundPath(), configuration.getBackgroundPaint());

        PointF textOrigin = measurements.getTextOrigin();
        canvas.drawText(configuration.getTextToDraw(), textOrigin.x, textOrigin.y, configuration.getTextPaint());
    }

    private void init() {
        configuration = new Configuration(getContext());
    }
}
