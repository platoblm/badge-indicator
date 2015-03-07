package net.badgeindicator;

import android.content.Context;
import android.graphics.Color;
import android.test.InstrumentationTestCase;
import android.view.LayoutInflater;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BadgeViewAttributesTest extends InstrumentationTestCase {

    public void testShouldUseDefaultValues() {
        Configuration configuration = new BadgeView(getContext()).getConfiguration();

        assertThat(configuration.getValue(), is(0));
        assertThat(configuration.getTextPaint().getTextSize(), is(getDimen(R.dimen.badge_indicator_default_text_size)));
        assertThat(configuration.getPadding(), is((int)getDimen(R.dimen.badge_indicator_default_padding)));
    }

    public void testShouldLoadAttributesFromLayoutFile() {
        Configuration configuration = inflateSampleView().getConfiguration();

        assertThat(configuration.getValue(), is(5));
        assertThat(configuration.getPadding(), is((int)getDimen(R.dimen.badge_indicator_sample_padding)));
        assertThat(configuration.getTextPaint().getTextSize(), is(getDimen(R.dimen.badge_indicator_sample_text_size)));
        assertThat(configuration.getBackgroundPaint().getColor(), is(Color.RED));
        assertThat(configuration.getTextPaint().getColor(), is(Color.WHITE));
    }

    private BadgeView inflateSampleView() {
        Context context = getContext();

        return (BadgeView) LayoutInflater.from(context)
                .inflate(R.layout.sample_badge_view_configuration, null);
    }

    private Context getContext() {
        return getInstrumentation().getTargetContext();
    }

    private float getDimen(int resourceId) {
        return getContext().getResources().getDimensionPixelOffset(resourceId);
    }
}
