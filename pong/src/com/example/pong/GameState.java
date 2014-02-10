package com.example.pong;

import sheep.collision.CollisionListener;
import sheep.game.Game;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.input.TouchListener;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.util.Log;
import android.view.MotionEvent;

public class GameState extends State implements CollisionListener, TouchListener {
	
	private int canvasHeight, canvasWidth;
	private Sprite ponger1, ponger2, ball;
	private Image pongerImage, ballImage;
	private int scorePlayer1, scorePlayer2;

	private static final String TAG = "APP";

    // singleton pattern
    private static GameState instance = null;

	public GameState() {
		
		// for comparison purposes
		pongerImage = new Image(R.drawable.ponger2);
		ballImage = new Image(R.drawable.ball2);
		
		ponger1 = new Sprite(new Image(R.drawable.ponger2));
		ponger2 = new Sprite(new Image(R.drawable.ponger2));
		ball = new Sprite(new Image(R.drawable.ball2));
		ball.setSpeed(200, 200);
		ball.setPosition(200, 200);
		
		ponger1.setPosition(300, 100);
		ponger2.setPosition(300, 500);
		
		scorePlayer1 = 0; 
		scorePlayer2 = 0;
		
	}

    // singleton pattern exercise
    public static synchronized GameState getInstance() {

        if(instance == null) {
            instance = new GameState();
        }

        return instance;

    }

	@Override
	public void update(float dt) {
		
		ball.update(dt);
		ponger1.update(dt);
		ponger2.update(dt);
		//Log.d(TAG, "PONG");
		
		// Crash wall
		if(ball.getX() >= canvasWidth-ballImage.getWidth() || ball.getX() <= 0) {
			ball.setSpeed(-ball.getSpeed().getX(), ball.getSpeed().getY());
		}
		
		// Crash ponger 
		if(ball.collides(ponger1)) {
			ball.setSpeed(ball.getSpeed().getX(), -ball.getSpeed().getY());
		}
		if(ball.collides(ponger2)) {
			ball.setSpeed(ball.getSpeed().getX(), -ball.getSpeed().getY());
		}
		
		// Ball passes ponger. Reset ball position and increment score (in that order)
		if(ball.getY() > ponger2.getY()) {
			//Log.d(TAG, "FORBI BOT");
			ball.setPosition(canvasWidth/2, canvasHeight/2);
			scorePlayer1++;
		}
		if(ball.getY() < ponger1.getY()) {
			//Log.d(TAG, "FORBI TOP");
			ball.setPosition(canvasWidth/2, canvasHeight/2);
			scorePlayer2++;
		}
		
		// Check if game over
		if(scorePlayer1 == 4){
			getGame().popState();
			getGame().pushState(new ScoreScreen("Player 1"));
		}
		if(scorePlayer2 == 4){
			getGame().popState();
			getGame().pushState(new ScoreScreen("Player 2"));
		}
		
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		canvasHeight = canvas.getHeight();
		canvasWidth = canvas.getWidth();
		
		ponger1.setPosition(ponger1.getX(), canvasHeight/6);
		ponger2.setPosition(ponger2.getX(), canvasHeight/6*5);
		
		ball.draw(canvas);
		ponger1.draw(canvas);
		ponger2.draw(canvas);
		
		Font pongFont = new Font(0, 0, 0, 50, Typeface.DEFAULT_BOLD, Typeface.BOLD);
		Font scoreFont = new Font(0, 0, 0, 50, Typeface.DEFAULT, Typeface.NORMAL);
		scoreFont.setTextAlign(Align.CENTER);
		pongFont.setTextAlign(Align.CENTER);
		canvas.drawText("PONG", canvasWidth/2, 60, pongFont);
		canvas.drawText(Integer.toString(scorePlayer1), canvasWidth/4, 60, scoreFont);
		canvas.drawText(Integer.toString(scorePlayer2), canvasWidth/4*3, 60, scoreFont);
		
	}

	@Override
	public void collided(Sprite a, Sprite b) {
		
	}

	// Move ponger on touch
	@Override
	public boolean onTouchMove(MotionEvent event) {
		
		// If user touches within 70y of either ponger, move the ponger to where the event occurred
		// Dragging the ponger also works
		if(event.getY() < (ponger1.getY() + 70) && event.getY() > (ponger1.getY() - 70)) {
			ponger1.setPosition(event.getX(), ponger1.getY());
		}
		
		if(event.getY() < (ponger2.getY() + 70) && event.getY() > (ponger2.getY() - 70)) {
			ponger2.setPosition(event.getX(), ponger2.getY());
		}
		
		return super.onTouchMove(event);
		
	}
	
}