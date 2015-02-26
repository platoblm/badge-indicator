package net.platoblm;

import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class EmptyTest {

    @Test
    public void shouldFail() {
        assertThat(new Calculator().add(1, 2), is(4));
    }
    
    @Test
    public void shouldUseRobolectric() {
        assertThat(TextUtils.isEmpty(null), is(true));
    }
}