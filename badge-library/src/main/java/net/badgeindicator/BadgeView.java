package net.badgeindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

public class BadgeView extends View implements ConfigurationListener {

    private final Validator validator = new Validator();
    private final Measurements measurements = new Measurements();
    private final Configuration configuration = new Configuration();
    
    public BadgeView(Context context) {
        super(context);
        init();
    }

    public BadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Configuration getConfiguration() {
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
        // background
        canvas.drawPath(measurements.getBackgroundPath(), configuration.getBackgroundPaint());

        // text
        PointF textOrigin = measurements.getTextOrigin();
        canvas.drawText(configuration.getTextToDraw(), textOrigin.x, textOrigin.y, configuration.getTextPaint());
    }

    private void init() {
        configuration.setListener(this);
    }

    @Override
    public void measurementsNeedToBeUpdated() {
        requestLayout();
    }
}
