package com.example.pong;

import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.input.TouchListener;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;

public class GameState extends State implements CollisionListener, TouchListener {
	
	private int canvasHeight, canvasWidth;
	private Sprite ponger1, ponger2, ball;
	private Image pongerImage;
	
	private static final String TAG = "APP";
	
	
	public GameState() {
		
		// for comparison purposes
		pongerImage = new Image(R.drawable.ponger2);
		
		ponger1 = new Sprite(new Image(R.drawable.ponger2));
		ponger2 = new Sprite(new Image(R.drawable.ponger2));
		ball = new Sprite(new Image(R.drawable.ball));
		ball.setSpeed(200, 200);
		ball.setPosition(100, 100);
		
		ponger1.setPosition(200, 100);
		ponger2.setPosition(200, 500);
		
		
	}
	
	
	
	@Override
	public void update(float dt) {
		
		ball.update(dt);
		ponger1.update(dt);
		ponger2.update(dt);
		
		//Log.d(TAG, "PONG");
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		canvasHeight = canvas.getHeight();
		canvasWidth = canvas.getWidth();
		ball.draw(canvas);
		ponger1.draw(canvas);
		ponger2.draw(canvas);
		
		Font f = new Font(0, 0, 0, 50, Typeface.DEFAULT_BOLD, Typeface.BOLD);
		canvas.drawText("PONG", canvasWidth/2-60, 60, f);
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















