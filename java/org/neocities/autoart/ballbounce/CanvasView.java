package org.neocities.autoart.ballbounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class CanvasView extends View
{
    public static Game activity;
    public static int score = 0;

    public ArrayList<double[]> ballPositions;
    public double[] currentBallPosition;
    public double[] paddlePosition;

    public double ballSize;
    public double paddleSize;

    public int w;
    public int h;


    public static boolean started = false;


    public ArrayList<int[]> colours;

    public Handler handler = new Handler();

    MoveBall moveBall = new MoveBall();


    public CanvasView(Context context)
    {
        super(context);
    }

    public CanvasView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public double dist(double x1, double y1, double x2, double y2)
    {
        return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    }

    public void init()
    {
        moveBall.cv = this;
        w = getWidth();
        h = getHeight();
        moveBall.t = 0;
        moveBall.a = Math.random()*0.00001;
        moveBall.b = Math.random()*0.002;

        colours = new ArrayList<>();

        score = 0;
        moveBall.running = true;

        currentBallPosition = new double[2];
        paddlePosition = new double[2];


        currentBallPosition[0] = -w;
        currentBallPosition[1] = -h;

        paddlePosition[0] = w/2;
        paddlePosition[1] = h/2;

        ballSize = w*0.05;
        paddleSize = w*0.2;

        ballPositions = new ArrayList<>();


        moveBall.ballVel = new double[2];
        moveBall.ballVel[0] = 0;
        moveBall.ballVel[1] = 0;


        setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                int x = (int)event.getX();
                int y = (int)event.getY();
                paddlePosition[0] = x;
                paddlePosition[1] = y;

                return true;
            }
        });



        handler.postDelayed(moveBall, 50);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);

        if (started)
            return;

        started = true;

        w = getWidth();
        h = getHeight();

        currentBallPosition = new double[2];
        paddlePosition = new double[2];

        currentBallPosition[0] = w/2;
        currentBallPosition[1] = 0;

        paddlePosition[0] = -w;
        paddlePosition[1] = -h;

        ballSize = w*0.05;
        paddleSize = w*0.2;


    }

    @Override
    public void onDraw(Canvas canvas)
    {

        if (w == 0)
            return;

        Paint paint = new Paint();

        for (int i = 0; i < ballPositions.size(); i++)
        {
            double[] ballPosition = ballPositions.get(i);

            if (colours.size() <= Math.ceil((double)i/100))
            {
                int[] c = new int[3];
                c[0] = (int)(Math.random()*255);
                c[1] = (int)(Math.random()*255);
                c[2] = (int)(Math.random()*255);

                colours.add(c);
            }

            paint.setStyle(Paint.Style.FILL);
            if (Game.MODE == Game.NORMAL_MODE || Game.MODE == Game.CRAZY_GRAVITY_MODE || Game.MODE == Game.FLIP_MODE)
            {
                paint.setARGB(255, 0, 0, 0);
            }
            else if (Game.MODE == Game.COLOUR_CHANGING_MODE)
            {
                paint.setARGB(255, colours.get(i / 100)[0], colours.get(i / 100)[1], colours.get(i / 100)[2]);
            }
            else if (Game.MODE == Game.OSCILLATION_MODE)
            {
                int val = (int)(255*Math.sin(0.001*i));
                paint.setARGB(255, val, val, val);
            }
            else if (Game.MODE == Game.BW_MODE)
            {
                int val = 255*((i/500)%2);
                paint.setARGB(255, val, val, val);
            }
            canvas.drawCircle((float) ballPosition[0], (float) ballPosition[1], (float) ballSize, paint);
        }


        paint.setStyle(Paint.Style.STROKE);
        paint.setARGB(255, 0, 0, 255);
        paint.setStrokeWidth(10);
        canvas.drawLine((float)(paddlePosition[0]-paddleSize/2),  (float)(paddlePosition[1]), (float)(paddlePosition[0]+paddleSize/2), (float)(paddlePosition[1]), paint);
    }
}
