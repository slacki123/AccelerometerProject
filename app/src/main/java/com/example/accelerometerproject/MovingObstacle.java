package com.example.accelerometerproject;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class MovingObstacle {
    private double xPosition;
    private double yPosition = 150;
    private MovingButton movingButton = Accelerometer.movingButton;
    private ConstraintLayout layout = MainActivity.layout;
    private Button button;
    private double obstacleSpeed = 2000;
    private double timeNow;
    private Date dateAfter;
    public static int i = 0;

    public MovingObstacle(Context context) {
        this.timeNow = System.currentTimeMillis();
        this.xPosition = Math.random() * MainActivity.display.getWidth();

        if (MainActivity.movingObstacles.size() < 10) {
            this.button = new Button(context);
            this.button.setX((float) this.xPosition);
            this.button.setY((float) this.yPosition);
            this.button.setText("o");
            layout.addView(this.button);
            MainActivity.movingObstacles.add(this);
        }

        // MainActivity.movingObstaclesArr[MainActivity.obstacleNumber % 3] = this;
        // animateMoveDown();
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public double getyPosition() {
        return this.yPosition;
    }

    public double getxPosition() {
        return this.xPosition;
    }



    public void moveDown() {
        this.yPosition = this.yPosition + 1;
        this.button.setY((float) this.yPosition);
//        if (this.yPosition > MainActivity.display.getHeight()) {
//            //MovingObstacle.movingObstacles.remove(i-1);
//            // layout.removeView(this.button);
//        }
    }



}
