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

    BadgeView badge;
    ViewGroup container;

    public void setUp() {
        Context context = getInstrumentation().getTargetContext();
        badge = new BadgeView(context);
        container = new FrameLayout(context);
    }
    
    public void testShouldValidateWidth() {
        try {
            container.addView(badge, 10, WRAP_CONTENT);
            measure();
            fail("Should warn user when width invalid");
        } catch (Exception e) {
            assertException(e, "Width should be WRAP_CONTENT");
        }
    }

    public void testShouldValidateHeight() {
        try {
            container.addView(badge, WRAP_CONTENT, 10);
            measure();
            fail("Should warn user when height invalid");
        } catch (Exception e) {
            assertException(e, "Height should be WRAP_CONTENT");
        }
    }

    private void assertException(Exception e, String message) {
        assertThat(e, CoreMatchers.instanceOf(RuntimeException.class));
        assertThat(e.getMessage(), is(message));
    }

    private void measure() {
        container.measure(makeMeasureSpec(1000, EXACTLY), makeMeasureSpec(300, EXACTLY));
    }
}
