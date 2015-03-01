package net.badgeindicator;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class BadgeViewTest extends InstrumentationTestCase {
    
    BadgeView indicator;
    ViewGroup container;
    
    @Override
    public void setUp() {
        Context context = getInstrumentation().getTargetContext();
        
        indicator = new BadgeView(context);
        
        container = new FrameLayout(context);
        container.addView(indicator, WRAP_CONTENT, WRAP_CONTENT);
    }

    public void testShouldBeSquareWhenValueSingleDigit() {
        indicator.getConfiguration().setValue(9);
        measureContainer();
        assertThat(indicator.getMeasuredWidth(), is(not(0)));
        assertThat(indicator.getMeasuredWidth(), is(indicator.getMeasuredHeight()));
    }

    public void testShouldHaveSameSizeForAllSingleDigits() {
        int singleDigitSide = singleDigitHeight();
        assertThat(singleDigitSide, is(not(0)));

        int i = 0;
        while(i <= 9) {
            indicator.getConfiguration().setValue(i);
            measureContainer();
            assertThat(indicator.getMeasuredWidth(), is(singleDigitSide));
            assertThat(indicator.getMeasuredHeight(), is(singleDigitSide));
            i++;
        }
    }

    public void testShouldPreserveHeightWhenValueHasMoreDigits() {
        indicator.getConfiguration().setValue(122);
        measureContainer();
        assertThat(indicator.getMeasuredHeight(), is(singleDigitHeight()));
    }

    public void testShouldRequestLayoutWhenValueChanges() {
        layoutContainer();
        assertThat(indicator.isLayoutRequested(), is(false));
        
        indicator.getConfiguration().setValue(10);
        
        assertThat(indicator.isLayoutRequested(), is(true));
    }
    
    public void testShouldRequestLayoutWhenPaddingChanges() {
        layoutContainer();
        assertThat(indicator.isLayoutRequested(), is(false));
        
        indicator.getConfiguration().setPadding(10);
        
        assertThat(indicator.isLayoutRequested(), is(true));
    }
    
    private int singleDigitHeight() {
        indicator.getConfiguration().setValue(0);
        measureContainer();
        return indicator.getMeasuredWidth();
    }

    private void measureContainer() {
        container.measure(makeMeasureSpec(1000, EXACTLY), makeMeasureSpec(300, EXACTLY));
    }

    private void layoutContainer() {
        container.layout(0, 0, 1000, 300);
    }
}
