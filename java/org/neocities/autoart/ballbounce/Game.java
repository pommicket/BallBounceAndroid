package org.neocities.autoart.ballbounce;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Game extends AppCompatActivity
{
    public static int MODE;
    public static final int NORMAL_MODE = 0;
    public static final int CRAZY_GRAVITY_MODE = 1;
    public static final int COLOUR_CHANGING_MODE = 2;
    public static final int OSCILLATION_MODE = 3;
    public static final int BW_MODE = 4;
    public static final int FLIP_MODE = 5;
    public static final int NUM_MODES = 6;
    public static final String[] MODE_NAMES = {"Normal mode", "Crazy gravity mode", "Colour changing mode", "Oscillation mode",
                                                "Black & white mode", "Flip mode"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        CanvasView.activity = this;
        CanvasView cv = (CanvasView) findViewById(R.id.canvas);
        cv.init();

    }

}
