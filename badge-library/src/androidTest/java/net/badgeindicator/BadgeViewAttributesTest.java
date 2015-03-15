package net.badgeindicator;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.view.LayoutInflater;

import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BadgeViewAttributesTest extends InstrumentationTestCase {

    public void testShouldUseDefaultValues() {
        Configuration config = new BadgeView(getContext()).getConfiguration();

        assertThat(config.getValueToDraw(), is("0"));
        assertThat(config.getTextSize(), is(dimension(R.dimen.badge_indicator_default_text_size)));
        assertThat(config.getPadding(), is(dimension(R.dimen.badge_indicator_default_padding)));
    }

    public void testShouldLoadAttributesFromLayoutFile() {
        Configuration config = inflateSampleView().getConfiguration();

        assertThat(config.getValueToDraw(), is("5"));
        assertThat(config.getPadding(), is(dimension(R.dimen.badge_indicator_sample_padding)));
        assertThat(config.getTextSize(), is(dimension(R.dimen.badge_indicator_sample_text_size)));
        assertThat(config.getTextColor(), is(WHITE));
        assertThat(config.getBackgroundColor(), is(RED));
    }

    private BadgeView inflateSampleView() {
        return (BadgeView) LayoutInflater.from(getContext())
                .inflate(R.layout.sample_badge_view_configuration, null);
    }

    private Context getContext() {
        return getInstrumentation().getTargetContext();
    }

    private int dimension(int resourceId) {
        return getContext()
                .getResources()
                .getDimensionPixelOffset(resourceId);
    }
}
