package net.badgeindicator;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class BadgeViewValidationTest {

    BadgeView badge;
    ViewGroup container;

    @Before
    public void setup() {
        Context context = getInstrumentation().getTargetContext();
        badge = new BadgeView(context);
        container = new FrameLayout(context);
    }

    @Test
    public void shouldValidateWidth() {
        try {
            container.addView(badge, 10, WRAP_CONTENT);
            measure();
            fail("Should warn user when width invalid");
        } catch (Exception e) {
            assertException(e, "Width should be WRAP_CONTENT");
        }
    }

    @Test
    public void shouldValidateHeight() {
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
