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
    
    BadgeView badge;
    ViewGroup container;
    
    @Override
    public void setUp() {
        Context context = getInstrumentation().getTargetContext();
        
        badge = new BadgeView(context);
        
        container = new FrameLayout(context);
        container.addView(badge, WRAP_CONTENT, WRAP_CONTENT);
    }

    public void testShouldBeSquareWhenValueSingleDigit() {
        badge.setValue(9);
        measureContainer();
        assertThat(badge.getMeasuredWidth(), is(not(0)));
        assertThat(badge.getMeasuredWidth(), is(badge.getMeasuredHeight()));
    }

    public void testShouldHaveSameSizeForAllSingleDigits() {
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

    public void testShouldPreserveHeightWhenValueHasMoreDigits() {
        badge.setValue(122);
        measureContainer();
        assertThat(badge.getMeasuredHeight(), is(singleDigitHeight()));
    }

    public void testShouldRequestLayoutWhenValueChanges() {
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
