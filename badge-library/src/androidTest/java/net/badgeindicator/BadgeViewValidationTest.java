package net.badgeindicator;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.hamcrest.CoreMatchers;

import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BadgeViewValidationTest extends InstrumentationTestCase {

    BadgeView indicator;
    ViewGroup container;

    public void setUp() {
        Context context = getInstrumentation().getTargetContext();
        indicator = new BadgeView(context);
        container = new FrameLayout(context);
    }
    
    public void testShouldValidateWidth() {
        try {
            container.addView(indicator, 10, WRAP_CONTENT);
            measureContainer();
            fail("Should warn user when width not WRAP_CONTENT");
        } catch (Exception exception) {
            assertThat(exception, CoreMatchers.instanceOf(RuntimeException.class));
            assertThat(exception.getMessage(), is("Width should be WRAP_CONTENT"));
        }
    }

    public void testShouldValidateHeight() {
        try {
            container.addView(indicator, WRAP_CONTENT, 10);
            measureContainer();
            fail("Should warn user when height not WRAP_CONTENT");
        } catch (Exception exception) {
            assertThat(exception, CoreMatchers.instanceOf(RuntimeException.class));
            assertThat(exception.getMessage(), is("Height should be WRAP_CONTENT"));
        }
    }

    private void measureContainer() {
        container.measure(makeMeasureSpec(1000, EXACTLY), makeMeasureSpec(300, EXACTLY));
    }
}
