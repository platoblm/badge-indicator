package net.badgeindicator;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.Suppress;
import android.view.LayoutInflater;
import android.view.View;

@Suppress
public class BadgeViewAttributesTest extends InstrumentationTestCase {
    
    BadgeView indicator;

    @Override
    public void setUp() {
        Context context = getInstrumentation().getTargetContext();
        
        View view = LayoutInflater.from(context)
                .inflate(R.layout.sample_badge_view_configuration, null);
        indicator = (BadgeView) view;
    }

    public void testShouldLoadAttributes() {
        fail();
    }

}
