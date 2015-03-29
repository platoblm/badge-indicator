package net.badgeindicator;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.view.LayoutInflater;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class BadgeViewAttributesTest {

    @Test
    public void shouldUseDefaultValues() {
        BadgeView defaultView = new BadgeView(getContext());
        Configuration config = getConfiguration(defaultView);

        assertThat(config.getValueToDraw(), is("0"));
        assertThat(config.getCurrentBackgroundColor(), is(RED));
        assertThat(config.getCurrentTextColor(), is(WHITE));
        assertThat(config.getTextSize(), is(dimension(R.dimen.badge_indicator_default_text_size)));
        assertThat(config.getPadding(), is(dimension(R.dimen.badge_indicator_default_padding)));
    }

    @Test
    public void shouldLoadAttributesFromLayoutFile() {
        BadgeView sampleView = inflateSampleView();
        Configuration config = getConfiguration(sampleView);

        assertThat(config.getValueToDraw(), is("5"));
        assertThat(config.getCurrentBackgroundColor(), is(GREEN));
        assertThat(config.getCurrentTextColor(), is(BLACK));
        assertThat(config.getTextSize(), is(dimension(R.dimen.badge_indicator_sample_text_size)));
        assertThat(config.getPadding(), is(dimension(R.dimen.badge_indicator_sample_padding)));
    }

    private Configuration getConfiguration(BadgeView view) {
        view.drawableStateChanged();
        return view.getConfiguration();
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
