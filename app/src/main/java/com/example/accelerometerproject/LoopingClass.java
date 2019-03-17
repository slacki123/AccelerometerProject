package com.example.accelerometerproject;

import android.util.Log;
import android.widget.Button;

import java.util.LinkedList;

public class LoopingClass implements Runnable {

    int len;
    int i = 0;
    private LinkedList<MovingObstacle> movingObstacle = MainActivity.movingObstacles;
    private MovingObstacle[] movingObstacle1 = MainActivity.movingObstaclesArr;
    private boolean collided = false;
    private MovingButton movingButton = Accelerometer.movingButton;
    private Thread t;


    public void updateArray() {
        len = movingObstacle.size();

        if (i < len) {
            movingObstacle.get(i).moveDown();

            if (movingObstacle.get(i).getxPosition() < movingButton.getxPosition()
                    && movingObstacle.get(i).getxPosition() > 150
                    && movingObstacle.get(i).getyPosition() < movingButton.getyPosition()
                    && movingObstacle.get(i).getyPosition() > 50) {
                Log.d("tag", "COLLISION DETECTED");

                if (collided == false) {
                    // emit event 'collided'
                    // new Collision().collide();

                    collided = true;
                }
                // Trigger game over
            }

            if (movingObstacle.get(i).getyPosition() > 1800) {
                movingObstacle.remove(i);
                Log.d("tag", "object removed");
                //MainActivity.layout.removeView();
            }

            Log.d("tag", Integer.toString(len));
        } else if (i == movingObstacle.size() - 1) {
            i = 0;
        } else {
            i++;
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    @Override
    public void run() {
        while (true) {

            updateArray();

        }
    }
}
