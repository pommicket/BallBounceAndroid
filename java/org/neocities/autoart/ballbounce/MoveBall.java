package org.neocities.autoart.ballbounce;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;

import java.util.Date;

public class MoveBall implements Runnable
{
    public CanvasView cv;
    public double[] ballVel;
    public double gravity = 1;
    public boolean running = true;
    public long lastBounce;
    public double a;
    public double b;
    public int t = 0;

    @Override
    public void run()
    {




        if (cv.w == 0 || !running)
        {
            cv.handler.postDelayed(this, 10);
            return;
        }

        if (Game.MODE != Game.CRAZY_GRAVITY_MODE && Game.MODE != Game.FLIP_MODE)
            gravity = 0.0007*cv.w;
        else if (Game.MODE == Game.CRAZY_GRAVITY_MODE)
            gravity = Math.abs(cv.w*(t*a*Math.sin(b*t)));
        else if (Game.MODE == Game.FLIP_MODE && (t/500) % 2 == 0)
            gravity = 0.0007*cv.w;
        else if (Game.MODE == Game.FLIP_MODE)
            gravity = -0.0007*cv.w;

        cv.currentBallPosition[0] += ballVel[0];
        cv.currentBallPosition[1] += ballVel[1];


        if (cv.currentBallPosition[1] > cv.h || cv.currentBallPosition[1] < 0 || cv.currentBallPosition[0] > cv.w || cv.currentBallPosition[0] < 0)
        {
            running = false;
            SharedPreferences prefs = CanvasView.activity.getSharedPreferences("ballBounceHighscores", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();


            int record = prefs.getInt("mode" + Game.MODE, -1);
            if (CanvasView.score > record)
                editor.putInt("mode" + Game.MODE, CanvasView.score);

            editor.apply();

            new AlertDialog.Builder(CanvasView.activity)
                    .setTitle("You lost!")
                    .setMessage("Score: " + CanvasView.score)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            CanvasView.started = false;
                            Intent intent = new Intent(CanvasView.activity, Menu.class);
                            CanvasView.activity.startActivity(intent);
                        }
                    }).create().show();
        }

        if (Math.abs(cv.currentBallPosition[0]-cv.paddlePosition[0]) < cv.paddleSize/2 &&
                Math.abs(cv.currentBallPosition[1]-cv.paddlePosition[1]) < 5+cv.ballSize)
        {
            Date d = new Date();

            if (d.getTime()-lastBounce < 100)
            {
                ballVel[1] += gravity;
                cv.ballPositions.add(cv.currentBallPosition.clone());
                t++;
                cv.invalidate();
                cv.handler.postDelayed(this, 10);

                return;
            }

            ballVel[0] += cv.w*0.00005 * (cv.currentBallPosition[0]-cv.paddlePosition[0]) + Math.random()*cv.w*0.0005-cv.w*0.00025;
            ballVel[1] = -gravity*cv.w*0.02;
            CanvasView.score++;

            lastBounce = d.getTime();
        }


        ballVel[1] += gravity;


        cv.ballPositions.add(cv.currentBallPosition.clone());

        cv.invalidate();

        t++;

        cv.handler.postDelayed(this, 10);


    }
}
