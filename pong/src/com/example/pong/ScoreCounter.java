package com.example.pong;

/**
 * Created by M on 11.02.14.
 */
public class ScoreCounter {

    private int scorePlayer1, scorePlayer2;

    public ScoreCounter() {
        scorePlayer1 = 0;
        scorePlayer2 = 0;
    }

    public void incrementPlayer1Score() {
        scorePlayer1++;
    }

    public void incrementPlayer2Score() {
        scorePlayer2++;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }
}
