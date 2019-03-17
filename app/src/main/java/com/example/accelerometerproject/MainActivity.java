package com.example.accelerometerproject;

import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;

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
    public static LinkedList<MovingObstacle> movingObstacles = new LinkedList<>();
    public static MovingObstacle[] movingObstaclesArr = new MovingObstacle[3];
    public static int obstacleNumber = 0;


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

        Collision collision = new Collision();
        collision.setCollisionListener(new Collision.CollisionListener() {
            @Override
            public void onCollision() {
                Intent gameOverIntent = new Intent(MainActivity.this, GameOver.class);
                startActivity(gameOverIntent);
            }
        });

        Log.d(TAG, "*** Initiating accelerometer data ****");
        Accelerometer accelerometer = new Accelerometer();
        accelerometer.accelerometerInit(this);

        display = getWindowManager().getDefaultDisplay();

        initCreateObstacles();
        new LoopingClass().start();

        // Log.d("tag", "***************" + Integer.toString(this.movingObstacles.size()));
    }


    public void gameOver() {
        Intent gameOverIntent = new Intent(MainActivity.this, GameOver.class);
        startActivity(gameOverIntent);

    }

    public void initCreateObstacles() {
        createObstacleHandler = new Handler();
        createObstacleTimer = new Runnable() {
            @Override
            public void run() {
                // Do something here on the main thread

                new MovingObstacle(MainActivity.this);
                createObstacleHandler.postDelayed(createObstacleTimer, 2000);

            }
        };
        obstacleNumber++;
        createObstacleHandler.post(createObstacleTimer);
    }


}