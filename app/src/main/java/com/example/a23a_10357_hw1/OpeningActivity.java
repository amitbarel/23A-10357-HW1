package com.example.a23a_10357_hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class OpeningActivity extends AppCompatActivity {

    private MaterialButton startGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        findViews();
        startGame.setOnClickListener(view -> start());
    }

    private void start() {
        Intent gameIntent = new Intent(this,GameActivity.class);
        startActivity(gameIntent);
        finish();
    }

    private void findViews() {
        startGame = findViewById(R.id.opening_button_start);
    }
}