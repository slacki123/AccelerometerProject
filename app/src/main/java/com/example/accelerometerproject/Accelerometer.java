package com.example.accelerometerproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.text.DecimalFormat;

public class Accelerometer implements SensorEventListener {

    public static double currentTime;
    public static MovingButton movingButton = new MovingButton();
    public static double initTime = System.currentTimeMillis();
    public static double timeElapsed;
    public static String timeElapsed2dp;
    DecimalFormat df = new DecimalFormat("####0.00");
    private double sensitivity = 1;

    public void accelerometerInit(Context context) {
        SensorManager sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
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
    }

}

