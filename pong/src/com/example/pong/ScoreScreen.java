package com.example.pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.input.TouchListener;

public class ScoreScreen extends State implements TouchListener {
	
	private String winner;

	public ScoreScreen(String winner) {
		
		this.winner = winner;
		
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		
		int canvasHeight = canvas.getHeight();
		int canvasWidth = canvas.getWidth();
		
		Font pongFont = new Font(0, 0, 0, 50, Typeface.DEFAULT_BOLD, Typeface.BOLD);
		Font scoreFont = new Font(0, 0, 0, 25, Typeface.DEFAULT, Typeface.NORMAL);
		scoreFont.setTextAlign(Align.CENTER);
		pongFont.setTextAlign(Align.CENTER);
		canvas.drawText("PONG", canvasWidth/2, 60, pongFont);
		canvas.drawText("The winner is: " + winner, canvasWidth/2, canvasHeight/3, scoreFont);
		canvas.drawText("Tap to play a new game.", canvasWidth/2, canvasHeight/3*2, scoreFont);
		
	}

	@Override
	public boolean onTouchDown(MotionEvent event) {
		
		getGame().popState();
		getGame().pushState(new GameState());
		return super.onTouchDown(event);
		
	}
	
	

}
