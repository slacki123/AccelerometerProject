package com.example.accelerometerproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Accelerometer implements SensorEventListener {

    private double sensitivity = 0.1;
    public static MovingButton movingButton = new MovingButton();


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
    }

}

