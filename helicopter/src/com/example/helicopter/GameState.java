package com.example.helicopter;

import android.graphics.Canvas;
import android.graphics.Color;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.input.TouchListener;

public class GameState extends State implements TouchListener {
	
	private int canvasHeight, canvasWidth;
	private Sprite heliSprite;
	private final int HELI_WIDTH = 130;
	private final int HELI_HEIGHT = 52;
	
	
	
	public GameState() {
		heliSprite = new Sprite(new Image(R.drawable.heli_west1));
		heliSprite.setSpeed(200, 200);
	
	}
 
	@Override
	public void update(float dt) {
			
		if(heliSprite.getX() > (canvasWidth - HELI_WIDTH) || heliSprite.getX() < 0) {
			System.out.println("Krasj side");
			heliSprite.setSpeed(-heliSprite.getSpeed().getX(), heliSprite.getSpeed().getY());
		}
		
		if(heliSprite.getY() > (canvasHeight - HELI_HEIGHT) || heliSprite.getY() < 0) {
			System.out.println("Krasj top/bunn");
			heliSprite.setSpeed(heliSprite.getSpeed().getX(), -heliSprite.getSpeed().getY());
		}
		
		
		
		heliSprite.update(dt);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		canvasHeight = canvas.getHeight();
		canvasWidth = canvas.getWidth();
		heliSprite.draw(canvas);
	}

}
