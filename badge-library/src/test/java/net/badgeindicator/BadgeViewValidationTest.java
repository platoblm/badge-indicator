package net.badgeindicator;

import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

@RunWith(UnitTestRunner.class)
public class BadgeViewValidationTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    BadgeView indicator;
    ViewGroup container;
    int measureSpec;

    @Before
    public void setUp() {
        indicator = new BadgeView(Robolectric.application);
        container = new FrameLayout(Robolectric.application);
        measureSpec = makeMeasureSpec(1000, EXACTLY);
    }

    @Test
    public void shouldValidateWidth() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Width should be WRAP_CONTENT");
        container.addView(indicator, 10, WRAP_CONTENT);
        container.measure(measureSpec, measureSpec);
    }

    @Test
    public void shouldValidateHeight() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Height should be WRAP_CONTENT");
        container.addView(indicator, WRAP_CONTENT, 10);
        container.measure(measureSpec, measureSpec);
    }

}
