package net.badgeindicator;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

class Measurements {

    private float width, height;
    private final PointF textOrigin = new PointF();
    private final Path path = new Path();
   
    private final RectF arcRect = new RectF();
    private final Rect digitBounds = new Rect();

    int getWidth() {
        return (int)width;
    }

    int getHeight() {
        return (int)height;
    }

    PointF getTextOrigin() {
        return textOrigin;
    }

    Path getBackgroundPath() {
        return path;
    }

    void update(Configuration configuration) {
        Paint textPaint = configuration.getTextPaint();
        Size digitSize = findDigitSize(textPaint);
        
        float measuredWidth = textPaint.measureText(configuration.getTextToDraw());
        float textWidth = Math.max(digitSize.width, measuredWidth); // minimum width = max single digit width
        float textHeight = digitSize.height;

        float radius = calculateRadius(digitSize, configuration.getPadding());

        width = 2 * radius + (textWidth - digitSize.width);
        height = 2 * radius;

        // text paint's alignment is Center
        textOrigin.x = width/2;
        textOrigin.y = height/2 + textHeight/2;

        calculatePathForBackground(radius);
    }
    
    private float calculateRadius(Size digitSize, float padding) {
        // hypotenuse assuming we had single digit with size of digitSize
        float hypotenuse = (float) sqrt(pow(digitSize.width, 2) + pow(digitSize.height, 2));
        return hypotenuse/2 + padding;
    }

    private void calculatePathForBackground(float radius) {
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

    private Size findDigitSize(Paint textPaint) {
        Size result = new Size();

        int i = 0;
        while (i <= 9) {
            String digit = String.valueOf(i);

            // use measureText for width
            result.width = Math.max(result.width, textPaint.measureText(digit));

            textPaint.getTextBounds(digit, 0, 1, digitBounds);
            result.height = Math.max(result.height, (float) digitBounds.height());
            i++;
        }

        return result;
    }

    private static class Size {
        float width;
        float height;
    }

}
