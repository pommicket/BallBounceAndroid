package org.neocities.autoart.ballbounce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity
{

    private void startGame()
    {
        Intent intent = new Intent(getApplicationContext(), Game.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button normal_mode_button = (Button) findViewById(R.id.normal_mode);
        normal_mode_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.MODE = Game.NORMAL_MODE;
                startGame();
            }
        });

        Button crazy_gravity_mode = (Button) findViewById(R.id.crazy_gravity_mode);
        crazy_gravity_mode.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Game.MODE = Game.CRAZY_GRAVITY_MODE;
                startGame();
            }
        });

        Button colour_changing_mode = (Button) findViewById(R.id.colour_changing_mode);
        colour_changing_mode.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Game.MODE = Game.COLOUR_CHANGING_MODE;
                startGame();
            }
        });

        Button oscillation_mode = (Button) findViewById(R.id.oscillation_mode);
        oscillation_mode.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Game.MODE = Game.OSCILLATION_MODE;
                startGame();
            }
        });

        Button bw_mode = (Button) findViewById(R.id.bw_mode);
        bw_mode.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Game.MODE = Game.BW_MODE;
                startGame();
            }
        });

        Button flip_mode = (Button) findViewById(R.id.flip_mode);
        flip_mode.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Game.MODE = Game.FLIP_MODE;
                startGame();
            }
        });

        Button random_mode = (Button) findViewById(R.id.random_mode);
        random_mode.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Game.MODE = (int)Math.floor(Math.random()*Game.NUM_MODES);
                startGame();
            }
        });


    }
}
