package net.badgeindicator;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

@RunWith(AndroidJUnit4.class)
public class BadgeViewValidationTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
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
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Width should be WRAP_CONTENT");

        container.addView(badge, 10, WRAP_CONTENT);
        measure();
    }

    @Test
    public void shouldValidateHeight() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Height should be WRAP_CONTENT");

        container.addView(badge, WRAP_CONTENT, 10);
        measure();
    }

    private void measure() {
        container.measure(makeMeasureSpec(1000, EXACTLY), makeMeasureSpec(300, EXACTLY));
    }
}
