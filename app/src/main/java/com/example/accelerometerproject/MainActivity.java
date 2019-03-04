package com.example.accelerometerproject;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    // TODO: 1 Implement friction
    // TODO: 2 add obstacles that move from all around/top
    // TODO: 3 split code into separate classes

    private static final String TAG = "MainActivity";
    ImageView littleAvatar;
    public static Button button;
    TextView locationBox;
    Button reset;

    private SensorManager sensorManager;
    Sensor accelerometer;

    private double xDirection;
    private double yDirection;

    public static double accelX;
    public static double accelY;
    public static double accelZ;
    private double modulusAccel;
    private double modulusVelo;

    private double veloX;
    private double veloY;

    private double sensitivity = 0.1;

    private double dampingCoefficient = 0;
    private double frictionCoefficient = 1;
    private double frictionTreshold = 3*sensitivity;

    private double posX = 300;
    private double posY = 1500;
    private int buttonWidth;
    private int buttonHeight;
    Display display;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_main);

        Log.d(TAG, "*** Initiating accelerometer data ****");

        littleAvatar = findViewById(R.id.littleAvatar);
        button = findViewById(R.id.button);
        locationBox = findViewById(R.id.textView);
        reset = findViewById(R.id.reset);
        reset.setOnClickListener(MainActivity.this);
        // startActivity(new Intent(MainActivity.this, Accelerometer.class));

        //////////////////////
        // How do I put this in another class to make this thing more maintainable?
        Log.d(TAG, "*** Initiating accelerometer data (starting method) ****");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Log.d(TAG, "***ABOUT TO REGISTER SENSOR LISTENER ****");
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        Log.d(TAG, "*** Registered sensor listener ****");
        //////////////////////

        // new Accelerometer(MainActivity.this);
        display = getWindowManager().getDefaultDisplay();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        accelX = -event.values[0]*sensitivity;
        accelY = event.values[1]*sensitivity;
        accelZ = event.values[2]*sensitivity;
        modulusAccel = Math.sqrt(Math.pow(accelX, 2) + Math.pow(accelY,2));


        veloX = veloX + accelX -dampingCoefficient*veloX;
        veloY = veloY + accelY -dampingCoefficient*veloY;
        modulusVelo = Math.sqrt(Math.pow(veloX, 2) + Math.pow(veloY,2));

//        try {
//            xDirection = veloX/Math.abs(veloX);
//            yDirection = veloY/Math.abs(veloY);
//        } catch (Exception e) {
//            Log.d("Main", "Division by zero");
//        }


        posX = posX + veloX;
        posY = posY + veloY;

        stayInFrame(250,100);

        button.setX((int)Math.round(posX));
        button.setY((int)Math.round(posY));

        //locationBox.setText(getPointOfView(button).toString());
        locationBox.setText("Velocity(" + (int)veloX  + "," + (int)veloY + ")");
//
//        locationBox.setText(String.valueOf(event.values[0] + "  " + event.values[1] + "  " + event.values[2]));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public Point getPointOfView(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        return new Point(location[0], location[1]);
    }

    public void stayInFrame(int objectWidth, int objectHeight) {
        if (posY < -objectHeight) {
            posY = display.getHeight();
        } else if (posY > display.getHeight()) {
            posY = -objectHeight;
        }

        if (posX < -objectWidth) {
            posX = display.getWidth();
        } else if (posX > display.getWidth()){
            posX = -objectWidth;
        }
    }

    @Override
    public void onClick(View v) {
        button.setX(300);
        button.setY(1500);

        posX = 300;
        posY = 1500;
        veloX = 0;
        veloY = 0;

    }


}