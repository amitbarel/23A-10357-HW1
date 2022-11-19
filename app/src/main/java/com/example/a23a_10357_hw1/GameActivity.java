package com.example.a23a_10357_hw1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

public class GameActivity extends AppCompatActivity {

    private ExtendedFloatingActionButton goLeft;
    private ExtendedFloatingActionButton goRight;
    private ShapeableImageView[] Hearts;
    private ShapeableImageView carMid;
    private ShapeableImageView carRight;
    private ShapeableImageView carLeft;
    private AppCompatImageView background;
    private GameManager GM;
//    private View left;
//    private View middle;
//    private View right;

    private int currentSpot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        currentSpot = 0;
        findViews();
        initViews();
        GM = new GameManager(Hearts.length);
        goLeft.setOnClickListener(view->slideLeft());
        goRight.setOnClickListener(view->slideRight());
    }

    private void initViews() {
        Glide
                .with(this)
                .load("https://i.imgur.com/8whojL1.png")
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(background);
    }

    private void slideLeft() {
        if (currentSpot == 0){
            carMid.setVisibility(View.INVISIBLE);
            carLeft.setVisibility(View.VISIBLE);
        }
        else if (currentSpot == 1){
            carRight.setVisibility(View.INVISIBLE);
            carMid.setVisibility(View.VISIBLE);
        }
        if (currentSpot == 0 || currentSpot == 1){
            currentSpot--;
        }
    }

    private void slideRight() {
        if (currentSpot == 0){
            carMid.setVisibility(View.INVISIBLE);
            carRight.setVisibility(View.VISIBLE);
        }
        else if (currentSpot == -1){
            carLeft.setVisibility(View.INVISIBLE);
            carMid.setVisibility(View.VISIBLE);
        }
        if (currentSpot == 0 || currentSpot == -1){
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
        Hearts = new ShapeableImageView[]{
                findViewById(R.id.game_IMG_heart_1),findViewById(R.id.game_IMG_heart_2),
                findViewById(R.id.game_IMG_heart_3)};
//        left = findViewById(R.id.game_VIEW_left);
//        middle = findViewById(R.id.game_VIEW_middle);
//        right = findViewById(R.id.game_VIEW_right);
    }
}