package com.example.accelerometerproject;

import android.os.Handler;
import android.support.constraint.ConstraintLayout;
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
    public static ConstraintLayout layout;
    public static Runnable createObstacleTimer;
    public static Handler createObstacleHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.mainLayout);

        button = findViewById(R.id.button);
        locationBox = findViewById(R.id.textView);
        reset = findViewById(R.id.reset);
        reset.setOnClickListener(new MovingButton());

        Log.d(TAG, "*** Initiating accelerometer data ****");
        Accelerometer accelerometer = new Accelerometer();
        accelerometer.accelerometerInit(this);

        display = getWindowManager().getDefaultDisplay();

        initCreateObstacles();

        Log.d("tag", "***************" + Integer.toString(MovingObstacle.movingObstacles.size()));


    }

    public void initCreateObstacles() {
        createObstacleHandler = new Handler();
        createObstacleTimer = new Runnable() {
            @Override
            public void run() {
                // Do something here on the main thread

                new MovingObstacle(MainActivity.this);
                createObstacleHandler.postDelayed(createObstacleTimer, 1000);

            }
        };
        createObstacleHandler.post(createObstacleTimer);
    }


}