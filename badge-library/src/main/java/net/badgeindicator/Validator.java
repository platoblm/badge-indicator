package net.badgeindicator;

import android.view.View;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

class Validator {

    void validateLayoutParameters(View view) {
        assertWrapContent(view.getLayoutParams().width, "Width");
        assertWrapContent(view.getLayoutParams().height, "Height");
    }

    private void assertWrapContent(int measurement, String description) {
        if (measurement != WRAP_CONTENT) {
            throw new RuntimeException(description + " should be WRAP_CONTENT");
        }
    }
}
