package net.badgeindicator.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import net.badgeindicator.BadgeView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((BadgeView) findViewById(R.id.badge_single_digit))
                .setValue(9);
        
         ((BadgeView) findViewById(R.id.badge_more_digits))
                .setValue(138);
    }
}
