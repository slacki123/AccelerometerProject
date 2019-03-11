package com.example.accelerometerproject;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class MovingObstacle {
    public static LinkedList<MovingObstacle> movingObstacles = new LinkedList<>();
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
        this.button = new Button(context);
        this.button.setX((float) this.xPosition);
        this.button.setY((float) yPosition);
        this.button.setText("o");
        layout.addView(this.button);
        movingObstacles.add(this);
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


    public void animateMoveDown() {
        ObjectAnimator animation = ObjectAnimator.ofFloat(this.button, "translationY", MainActivity.display.getHeight());
        animation.setDuration((int) obstacleSpeed);
        animation.start();


        // checkCollision();
    }

    public void moveDown() {
        this.yPosition = this.yPosition + 15;
        this.button.setY((float) yPosition);
        if (this.yPosition == MainActivity.display.getHeight()) {
            //MovingObstacle.movingObstacles.remove(i-1);
            layout.removeView(this.button);
        }
    }

    public void iterateObstacleArray() {
        int len = movingObstacles.size();
        if (i < len) {
            movingObstacles.get(i).moveDown();
            i++;
        } else if (i == len) {
            i = 0;
        }
    }

    public void checkCollision() {
        int[] locationOnScreent = new int[2];
        this.button.getLocationOnScreen(locationOnScreent);
        this.yPosition = locationOnScreent[1];
        this.xPosition = locationOnScreent[0];
        //MainActivity.locationBox.setText("Velocity(" + (int)this.xPosition + "," + (int)this.yPosition + ")");
//        if (this.yPosition - movingButton.getyPosition() < 50 && this.yPosition - movingButton.getyPosition() > 0
////            && this.xPosition == movingButton.getxPosition()
//            ) {
//            movingButton.resetButton();
        //       }
    }

}
