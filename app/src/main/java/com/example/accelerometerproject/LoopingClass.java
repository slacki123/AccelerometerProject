package com.example.accelerometerproject;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class LoopingClass implements Runnable{
    private LinkedList<MovingObstacle> movingObstacle = MovingObstacle.movingObstacles;
    public static MovingButton movingButton = Accelerometer.movingButton;
    Thread t;
    Handler mainHandler = new Handler(Looper.getMainLooper());

    public static double currentTime;
    public static double initTime = System.currentTimeMillis();
    public static double timeElapsed;
    public static String timeElapsed2dp;
    DecimalFormat df = new DecimalFormat("####0.00");


    LoopingClass() {
        t = new Thread(this);
        t.start();
    }

    public void displayTime() {
        currentTime = System.currentTimeMillis();
        timeElapsed = currentTime - initTime;
        timeElapsed2dp = df.format((timeElapsed) / 1000);
        MainActivity.locationBox.setText("Time elapsed:  " + timeElapsed2dp + "s");

    }

    public void moveButtons() {
        int len = movingObstacle.size();
        Log.d("tag", Integer.toString(len));
        if (MovingObstacle.i < len) {
            movingObstacle.get(MovingObstacle.i).moveDown();
            MovingObstacle.i++;

            if (movingObstacle.get(MovingObstacle.i - 1).getxPosition() < movingButton.getxPosition()
                    && movingObstacle.get(MovingObstacle.i - 1).getxPosition() > 150
                    && movingObstacle.get(MovingObstacle.i - 1).getyPosition() < movingButton.getyPosition()
                    && movingObstacle.get(MovingObstacle.i - 1).getyPosition() > 50) {
                Log.d("tag", "COLLISION DETECTED");
                // Trigger game over
            }

            if (movingObstacle.get(MovingObstacle.i - 1).getyPosition() > MainActivity.display.getHeight()) {
                movingObstacle.remove(MovingObstacle.i - 1);
//                try {
//                    MainActivity.layout.removeView(movingObstacle.get(MovingObstacle.i - 1).getButton());
//                } catch (Exception e) {Log.d(" tag", e.toString());}
            }


            //Log.d("tag", Integer.toString(len));
        } else if (MovingObstacle.i == len) {
            MovingObstacle.i = 0;
        }
    }



    @Override
    public void run() {
        while(true) {
        //Log.d("tag", "run method triggered");
            // displayTime();
            moveButtons();
     }
        }
}
