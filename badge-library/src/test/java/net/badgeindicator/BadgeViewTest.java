package net.badgeindicator;

import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowPaint;

import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

@RunWith(UnitTestRunner.class)
public class BadgeViewTest {
    
    BadgeView indicator;
    ViewGroup container;
    
    @Before
    public void setUp() {
        indicator = new BadgeView(Robolectric.application);
        
        container = new FrameLayout(Robolectric.application);
        container.addView(indicator, WRAP_CONTENT, WRAP_CONTENT);
    }

    @Test
    public void shouldBeSquareWhenValueSingleDigit() {
        indicator.setValue(9);
        measureContainer();
        assertThat(indicator.getMeasuredWidth(), is(not(0)));
        assertThat(indicator.getMeasuredWidth(), is(indicator.getMeasuredHeight()));
    }

    @Test
    public void shouldHaveSameSizeForAllSingleDigits() {
        int singleDigitSide = singleDigitHeight();
        assertThat(singleDigitSide, is(not(0)));

        int i = 0;
        while(i <= 9) {
            indicator.setValue(i);
            measureContainer();
            assertThat(indicator.getMeasuredWidth(), is(singleDigitSide));
            assertThat(indicator.getMeasuredHeight(), is(singleDigitSide));
            i++;
        }
    }

    @Test
    public void shouldPreserveHeightWhenValueHasMoreDigits() {
        indicator.setValue(122);
        measureContainer();
        assertThat(indicator.getMeasuredHeight(), is(singleDigitHeight()));
    }
    
    private int singleDigitHeight() {
        indicator.setValue(0);
        measureContainer();
        return indicator.getMeasuredWidth();
    }

    private void measureContainer() {
        container.measure(makeMeasureSpec(1000, EXACTLY), makeMeasureSpec(300, EXACTLY));
    }
}
