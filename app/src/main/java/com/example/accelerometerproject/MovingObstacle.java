package com.example.accelerometerproject;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Date;

public class MovingObstacle {
    public static ArrayList<MovingObstacle> movingObstacles = new ArrayList<>();
    private double xPosition;
    private double yPosition = 150;
    private MovingButton movingButton = Accelerometer.movingButton;
    private ConstraintLayout layout = MainActivity.layout;
    private Button button;
    private double obstacleSpeed = 2000;
    private double timeNow;
    private Date dateAfter;

    public MovingObstacle(Context context) {
        this.timeNow = System.currentTimeMillis();
        this.xPosition = Math.random() * MainActivity.display.getWidth();
        this.button = new Button(context);
        this.button.setX((float) this.xPosition);
        this.button.setY((float) yPosition);
        this.button.setText("o");
        layout.addView(this.button);
        // movingObstacles.add(this);
        animateMoveDown();
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }


    public void animateMoveDown() {
        ObjectAnimator animation = ObjectAnimator.ofFloat(this.button, "translationY", MainActivity.display.getHeight());
        animation.setDuration((int) obstacleSpeed);
        animation.start();


        // checkCollision();
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
