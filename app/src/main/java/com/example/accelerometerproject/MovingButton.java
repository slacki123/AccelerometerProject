package com.example.accelerometerproject;

import android.view.View;

public class MovingButton implements View.OnClickListener {

    private double xAcceleration, yAcceleration, zAcceleration;
    private double xVelocity, yVelocity, zVelocity;
    private double xPosition, yPosition, zPosition;
    private double dampingCoefficient = 0;
    MovingButton movingButton = Accelerometer.movingButton;

    public double getyPosition() {
        return yPosition;
    }

    public double getxPosition() {
        return xPosition;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }
    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }
    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }
    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }
    public void setxAcceleration(double xAcceleration) {
        this.xAcceleration = xAcceleration;
    }
    public void setyAcceleration(double yAcceleration) {
        this.yAcceleration = yAcceleration;
    }
    public void setzAcceleration(double zAcceleration) {
        this.zAcceleration = zAcceleration;
    }

    public void calculatePosition(){
        xVelocity = xVelocity + xAcceleration -dampingCoefficient*xVelocity;
        yVelocity = yVelocity + yAcceleration -dampingCoefficient*yVelocity;

        xPosition = xPosition + xVelocity;
        yPosition = yPosition + yVelocity;

        stayInFrame(250,100);

        MainActivity.button.setX((int)Math.round(xPosition));
        MainActivity.button.setY((int)Math.round(yPosition));

    }

    public void stayInFrame(int objectWidth, int objectHeight) {
        if (yPosition < -objectHeight) {
            yPosition = MainActivity.display.getHeight();
        } else if (yPosition > MainActivity.display.getHeight()) {
            yPosition = -objectHeight;
        }

        if (xPosition < -objectWidth) {
            xPosition = MainActivity.display.getWidth();
        } else if (xPosition > MainActivity.display.getWidth()){
            xPosition = -objectWidth;
        }
    }

    @Override
    public void onClick(View v) {
        resetButton();
    }

    public void resetButton() {
        MainActivity.button.setX(300);
        MainActivity.button.setY(1500);

        movingButton.setxPosition(300);
        movingButton.setyPosition(1500);
        movingButton.setxVelocity(0);
        movingButton.setyVelocity(0);

        Accelerometer.initTime = System.currentTimeMillis();
    }
}
