package com.example.accelerometerproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // TODO: 1 Implement friction
    // TODO: 2 add obstacles that move from all around/top
    // TODO: 3 split code into separate classes

    private static final String TAG = "MainActivity";
    public static Button button;
    public static TextView locationBox;
    public static Button reset;
    public static Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        locationBox = findViewById(R.id.textView);
        reset = findViewById(R.id.reset);
        MovingButton movingButton = new MovingButton();
        movingButton.initClickListener();

        Log.d(TAG, "*** Initiating accelerometer data ****");
        Accelerometer accelerometer = new Accelerometer();
        accelerometer.accelerometerInit(this);

        // new Accelerometer(MainActivity.this);
        display = getWindowManager().getDefaultDisplay();

    }
}