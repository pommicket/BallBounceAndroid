package org.neocities.autoart.ballbounce;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Highscores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        SharedPreferences prefs = getSharedPreferences("ballBounceHighscores", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();


        RelativeLayout rl = (RelativeLayout) findViewById(R.id.highscores_layout);

        int n = 0;

        for (int i = 0; i < Game.NUM_MODES; i++)
        {
            if (!prefs.contains("mode"+i))
                continue;
            TextView tv = new TextView(this);
            tv.setText(Game.MODE_NAMES[i] + ": " + (prefs.getInt("mode" + i, -1)));
            tv.setId(n + 100);

            tv.setTextColor(Color.rgb(0, 0, 0));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            if (n > 0)
                params.addRule(RelativeLayout.BELOW, n+99);
            else
                params.addRule(RelativeLayout.BELOW, R.id.highscores_title);

            tv.setLayoutParams(params);
            rl.addView(tv);
            n++;


        }

    }
}
