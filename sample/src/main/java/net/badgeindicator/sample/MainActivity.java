package net.badgeindicator.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.badge_single_digit).setOnClickListener(emptyClickListener);
        findViewById(R.id.badge_more_digits).setOnClickListener(emptyClickListener);
    }

    private final View.OnClickListener emptyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {}
    };
}
