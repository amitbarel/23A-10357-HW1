package com.example.a23a_10357_hw1;

public class GameManager {
    private int wrong = 0;
    private int life;

    public GameManager(int life) {
        this.life = life;
    }

    public int getWrong() {
        return wrong;
    }

    public boolean isLose(){
        return life == wrong;
    }
}
