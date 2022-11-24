package com.example.a23a_10357_hw1;

import java.util.ArrayList;

public class GameManager {
    private int wrong = 0;
    private int life;
    private Player pilot;
    private ArrayList<Obstacle> cones;

    public GameManager(int life) {
        this.life = life;
    }

    public int getWrong() {
        return wrong;
    }

    public void updateWrong(){
        this.wrong++;
    }

    public boolean isLose(){
        return life == wrong;
    }

    public ArrayList<Obstacle> getCones() {
        return cones;
    }

    public GameManager setCones(ArrayList<Obstacle> cones) {
        this.cones = cones;
        return this;
    }
}
