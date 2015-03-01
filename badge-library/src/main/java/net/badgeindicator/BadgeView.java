package net.badgeindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import static android.graphics.Paint.Align.CENTER;
import static android.graphics.Paint.Style.FILL;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class BadgeView extends View {

    private final Paint textPaint = new Paint();
    private final Paint backgroundPaint = new Paint();
    private Path path = new Path();
    private RectF arcRect = new RectF();
    private Rect digitBounds = new Rect();
    private PointF textOrigin = new PointF();
    private String textToDraw;
    
    private int value;
    private int padding = 0;

    public BadgeView(Context context) {
        super(context);
        init();
    }

    public BadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        
        requestLayout();
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        validateLayoutParams();

        textToDraw = String.valueOf(value);
        
        Size digitSize = findDigitSize();

        // minimum width = the max single digit width
        float textWidth = Math.max(digitSize.width, textPaint.measureText(textToDraw));
        float textHeight = digitSize.height;

        float radius = calculateRadius(digitSize);

        float width = 2 * radius + (textWidth - digitSize.width);
        float height = 2 * radius;
      
        // text paint's alignment is Center
        textOrigin.x = width/2;
        textOrigin.y = height/2 + textHeight/2;

        calculatePathForBackground(radius, width, height);
      
        setMeasuredDimension((int)width, (int)height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        backgroundPaint.setColor(Color.RED);
        canvas.drawPath(path, backgroundPaint);
        
        canvas.drawText(textToDraw, textOrigin.x, textOrigin.y, textPaint);
    }

    private float calculateRadius(Size digitSize) {
        // hypotenuse assuming we had single digit with size of digitSize
        float hypotenuse = (float) sqrt(pow(digitSize.width, 2) + pow(digitSize.height, 2));
        return hypotenuse/2 + padding;
    }

    private void calculatePathForBackground(float radius, float width, float height) {
        path.rewind();

        float leftCircleCenterX = radius;
        float rightCircleCenterX = width - radius;

        path.moveTo(rightCircleCenterX, 0);
        arcRect.set(rightCircleCenterX - radius, 0, rightCircleCenterX + radius, height);
        path.arcTo(arcRect, -90f, 180f);

        path.lineTo(leftCircleCenterX, height);
        arcRect.set(leftCircleCenterX - radius, 0, leftCircleCenterX + radius, height);
        path.arcTo(arcRect, 90f, 180f);

        path.close();
    }
    
    private Size findDigitSize() {
        Size result = new Size();

        int i = 0;
        while (i <= 9) {
            String digit = String.valueOf(i);
            textPaint.getTextBounds(digit, 0, 1, digitBounds);
            float digitHeight = digitBounds.height();
            float digitWidth = textPaint.measureText(digit);
            
            result.height = Math.max(result.height, digitHeight);
            result.width = Math.max(result.width, digitWidth);
            i++;
        }

        return result;
    }

    private void init() {
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(CENTER);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(240);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);

        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setStyle(FILL);
    }

    private void validateLayoutParams() {
        assertWrapContent(getLayoutParams().width, "Width");
        assertWrapContent(getLayoutParams().height, "Height");
    }

    private void assertWrapContent(int measurement, String description) {
        if (measurement != WRAP_CONTENT) {
            throw new RuntimeException(description + " should be WRAP_CONTENT");
        }
    }

    private static class Size {
        float width;
        float height;
    }
}
