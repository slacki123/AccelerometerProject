package com.example.accelerometerproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class Accelerometer implements SensorEventListener {

    public static double currentTime;
    public static MovingButton movingButton = new MovingButton();
    public static double initTime = System.currentTimeMillis();
    public static double timeElapsed;
    public static String timeElapsed2dp;
    DecimalFormat df = new DecimalFormat("####0.00");
    private double sensitivity = 0.1;
    private LinkedList<MovingObstacle> movingObstacle = MovingObstacle.movingObstacles;

    public void accelerometerInit(Context context) {
        SensorManager sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        movingButton.setxAcceleration(-event.values[0]*sensitivity);
        movingButton.setyAcceleration(event.values[1]*sensitivity);
        movingButton.setzAcceleration(event.values[2]*sensitivity);
        movingButton.calculatePosition();

        currentTime = System.currentTimeMillis();
        timeElapsed = currentTime - initTime;
        timeElapsed2dp = df.format((timeElapsed) / 1000);
        MainActivity.locationBox.setText("Time elapsed:  " + timeElapsed2dp + "s");

        int len = movingObstacle.size();
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
                //MainActivity.layout.removeView();
            }


            //Log.d("tag", Integer.toString(len));
        } else if (MovingObstacle.i == len) {
            MovingObstacle.i = 0;
        }


    }

}

