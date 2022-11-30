package com.example.a23a_10357_hw1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private Vibrator v;
    private Toast toast;
    private ExtendedFloatingActionButton goLeft;
    private ExtendedFloatingActionButton goRight;
    private RelativeLayout ConeSpace;
    private ShapeableImageView[][] Cones;
    private ShapeableImageView[] Hearts;
    private ShapeableImageView carMid;
    private ShapeableImageView carRight;
    private ShapeableImageView carLeft;
    private AppCompatImageView background;
    private GameManager GM;
    private View leftLane;
    private View middleLane;
    private View rightLane;
    private long startTime;
    private Timer timer;
    private final int DELAY = 1000;

    private int currentSpot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        currentSpot = 1;
        findViews();
        initViews();
        GM = new GameManager(Hearts.length);
        goLeft.setOnClickListener(view->slideLeft());
        goRight.setOnClickListener(view->slideRight());
        timer = new Timer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toast.cancel();
    }

    private void initViews() {
        Glide
                .with(this)
                .load("https://i.imgur.com/8whojL1.png")
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(background);

        startGame();
    }

    private void slideLeft() {
        if (currentSpot == 1){
            carMid.setVisibility(View.INVISIBLE);
            carLeft.setVisibility(View.VISIBLE);
        }
        else if (currentSpot == 2){
            carRight.setVisibility(View.INVISIBLE);
            carMid.setVisibility(View.VISIBLE);
        }
        if (currentSpot == 1 || currentSpot == 2){
            currentSpot--;
        }
    }

    private void slideRight() {
        if (currentSpot == 1){
            carMid.setVisibility(View.INVISIBLE);
            carRight.setVisibility(View.VISIBLE);
        }
        else if (currentSpot == 0){
            carLeft.setVisibility(View.INVISIBLE);
            carMid.setVisibility(View.VISIBLE);
        }
        if (currentSpot == 0 || currentSpot == 1){
            currentSpot++;
        }
    }

    private void findViews() {
        background = findViewById(R.id.game_IMG_background);
        goLeft = findViewById(R.id.game_FAB_goLeft);
        goRight = findViewById(R.id.game_FAB_goRight);
        carLeft = findViewById(R.id.game_IMG_carLeft);
        carMid = findViewById(R.id.game_IMG_carMid);
        carRight = findViewById(R.id.game_IMG_carRight);
        ConeSpace = findViewById(R.id.game_RL_cones);
        leftLane = findViewById(R.id.game_VIEW_leftLane);
        Hearts = new ShapeableImageView[]{
                findViewById(R.id.game_IMG_heart_1),findViewById(R.id.game_IMG_heart_2),
                findViewById(R.id.game_IMG_heart_3)};
        Cones = new ShapeableImageView[][]{
                {findViewById(R.id.game_cone_L1), findViewById(R.id.game_cone_M1), findViewById(R.id.game_cone_R1)},
                {findViewById(R.id.game_cone_L2), findViewById(R.id.game_cone_M2), findViewById(R.id.game_cone_R2)},
                {findViewById(R.id.game_cone_L3), findViewById(R.id.game_cone_M3), findViewById(R.id.game_cone_R3)},
                {findViewById(R.id.game_cone_L4), findViewById(R.id.game_cone_M4), findViewById(R.id.game_cone_R4)},
                {findViewById(R.id.game_cone_L5), findViewById(R.id.game_cone_M5), findViewById(R.id.game_cone_R5)},
                {findViewById(R.id.game_cone_L6), findViewById(R.id.game_cone_M6), findViewById(R.id.game_cone_R6)}
        };
    }

    private void startGame(){
        startTime = System.currentTimeMillis();
        timer = new Timer();
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(() -> GameActivity.this.updateConeLocation());
                    }
                }
                , DELAY, DELAY);
    }

    private void updateConeLocation() {
        lowerLocations();
        initRandomCone();
        checkHit();
    }

    private void checkHit() {
        if (Cones[Cones.length-1][currentSpot].getVisibility() == View.VISIBLE){
            GM.updateWrong();
            Cones[Cones.length-1][currentSpot].setVisibility(View.INVISIBLE);
            if (GM.getWrong() > 0){
                Hearts[Hearts.length-GM.getWrong()].setVisibility(View.INVISIBLE);
            }
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                Log.d("Vibrations","Vibrate!");
                v.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
            }else{
                v.vibrate(500);
            }
            toast.makeText(this,"You just got hit!",Toast.LENGTH_SHORT).show();
        }
    }

    private void lowerLocations() {
        for (int i = Cones.length-1; i > 0 ; i--) {
            for (int j = 0; j < Cones[i].length; j++) {
                Cones[i][j].setVisibility(Cones[i-1][j].getVisibility());
            }
        }
    }

    private void initRandomCone() {
        Random rand = new Random();
        int rnd = rand.nextInt(4);
        for (int i = 0; i < Cones[0].length; i++) {
            Cones[0][i].setVisibility(View.INVISIBLE);
        }
        if(rnd >= 0 && rnd <3) {
            Cones[0][rnd].setVisibility(View.VISIBLE);
        }
    }
}