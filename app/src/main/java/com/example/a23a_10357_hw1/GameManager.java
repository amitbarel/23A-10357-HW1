package com.example.a23a_10357_hw1;

import java.util.ArrayList;

public class GameManager {
    private int wrong = 0;
    private int life;

    public GameManager(int life) {
        this.life = life;
    }

    public int getWrong() {
        return wrong;
    }

    public void updateWrong(){
        if(wrong<life)
            this.wrong++;
    }

    public boolean isLose(){
        return life == wrong;
    }

}
