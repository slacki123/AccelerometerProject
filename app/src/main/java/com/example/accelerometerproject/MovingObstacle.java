package com.example.accelerometerproject;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class MovingObstacle {
    static ArrayList<MovingObstacle> movingObstacles = new ArrayList<>();
    Iterator<MovingObstacle> iter = movingObstacles.iterator();
    private double xVelocity;
    private double yVelocity;
    private double xPosition;
    private double yPosition;
    private double width;
    private double height;
    private MovingButton movingButton = Accelerometer.movingButton;
    private ConstraintLayout layout = MainActivity.layout;
    private Button button;
    private Timer movingTimer = new Timer(true);
    private Runnable animationTimer;
    private Handler handler;

    public MovingObstacle(Context context) {
        this.xVelocity = 0;
        this.yVelocity = 5;
        this.xPosition = Math.random() * MainActivity.display.getWidth();
        this.yPosition = 0;
        this.button = new Button(context);
        this.button.setX((float) this.xPosition);
        this.button.setY((float) this.yPosition);
        this.button.setText("o");
        layout.addView(this.button);
        movingObstacles.add(this);
    }

    public void moveDown() {
        this.yPosition = this.yPosition + this.yVelocity;
        this.button.setY((float) this.yPosition);
        if (this.yPosition == MainActivity.display.getHeight()) {
            //this.movingTimer.cancel();
            this.handler.removeCallbacksAndMessages(null);
            Log.d("tag", "********* TIMER CANCELLED");
            movingObstacles.remove(0);
            layout.removeView(this.button);
            // new MovingObstacle(new MainActivity()).obstacleMove();
            Log.d("tag", "***************" + Integer.toString(this.movingObstacles.size()));

        }
    }

    public void obstacleMove() {
        handler = new Handler();
        animationTimer = new Runnable() {
            @Override
            public void run() {
                // Do something here on the main thread

                for (int i = 0; i < movingObstacles.size(); i++) {
                    movingObstacles.get(i).moveDown();
                }
                handler.postDelayed(animationTimer, 100);

            }
        };
        handler.post(animationTimer);
    }

    public void checkCollision() {
        if (this.yPosition == movingButton.getyPosition() && this.xPosition == movingButton.getxPosition()) {
            movingButton.resetButton();
        }
    }

}
