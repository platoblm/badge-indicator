package net.badgeindicator;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class BadgeViewTest {
    
    BadgeView badge;
    ViewGroup container;

    @Before
    public void setup() {
        Context context = getInstrumentation().getTargetContext();

        badge = new BadgeView(context);

        container = new FrameLayout(context);
        container.addView(badge, WRAP_CONTENT, WRAP_CONTENT);
    }

    @Test
    public void shouldBeSquareWhenValueSingleDigit() {
        badge.setValue(9);
        measureContainer();
        assertThat(badge.getMeasuredWidth(), is(not(0)));
        assertThat(badge.getMeasuredWidth(), is(badge.getMeasuredHeight()));
    }

    @Test
    public void shouldHaveSameSizeForAllSingleDigits() {
        int singleDigitSide = singleDigitHeight();
        assertThat(singleDigitSide, is(not(0)));

        int i = 0;
        while(i <= 9) {
            badge.setValue(i);
            measureContainer();
            assertThat(badge.getMeasuredWidth(), is(singleDigitSide));
            assertThat(badge.getMeasuredHeight(), is(singleDigitSide));
            i++;
        }
    }

    @Test
    public void shouldPreserveHeightWhenValueHasMoreDigits() {
        badge.setValue(122);
        measureContainer();
        assertThat(badge.getMeasuredHeight(), is(singleDigitHeight()));
    }

    @Test
    public void shouldRequestLayoutWhenValueChanges() {
        layoutContainer();
        assertThat(badge.isLayoutRequested(), is(false));
        
        badge.setValue(10);
        
        assertThat(badge.isLayoutRequested(), is(true));
    }
    
    private int singleDigitHeight() {
        badge.setValue(0);
        measureContainer();
        return badge.getMeasuredWidth();
    }

    private void measureContainer() {
        container.measure(makeMeasureSpec(1000, EXACTLY), makeMeasureSpec(300, EXACTLY));
    }

    private void layoutContainer() {
        container.layout(0, 0, 1000, 300);
    }
}
