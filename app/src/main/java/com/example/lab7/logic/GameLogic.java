package com.example.lab7.logic;

import java.util.Random;

public class GameLogic {
    private int number;
    private int attempts;

    public GameLogic(String min, String max, String att, String num){
        if(num.isEmpty()){
            Random random = new Random();
            this.number = random.nextInt(Integer.parseInt(max) - Integer.parseInt(min)) + Integer.parseInt(min);
        }else{
            this.number = Integer.parseInt(num);
        }
        if(att.isEmpty()){
            this.attempts=-1;
        }else{
            this.attempts = Integer.parseInt(att);
        }
    }

    public String makeGuess(String guess){
        String result = "";
        if(Integer.parseInt(guess)==number){
            result = "win";
        }
        if(Integer.parseInt(guess)<number){
            result = "bigger";
            attempts--;
        }
        if(Integer.parseInt(guess)>number){
            result = "smaller";
            attempts--;
        }
        if(attempts==0){
            result = "lose";
        }
        return result;
    }

    public int getAttempts(){
        return attempts;
    }
}
