package com.example.helicopter;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import sheep.game.State;
import sheep.input.TouchListener;

public class GameState extends State implements TouchListener {
	
	private int canvasHeight, canvasWidth;
	//private final int HELI_WIDTH = 130;
	//private final int HELI_HEIGHT = 52;
	
	private Helicopter heli1;
	
	
	
	public GameState(Resources r) {
		
		heli1 = new Helicopter(BitmapFactory.decodeResource(r, R.drawable.heli_all_frames), 
				50, 50, 130, 52, 30, 4);
		heli1.setSpeed(700, 700);
		heli1.update(System.currentTimeMillis());
	
	}
 
	@Override
	public void update(float dt) {
		
		//heli1
		heli1.update(System.currentTimeMillis());
		if(heli1.getX() > (canvasWidth - 130) || heli1.getX() < 0) {
			System.out.println("Krasj side");
			heli1.setSpeed(-heli1.getSpeed().getX(), heli1.getSpeed().getY());
		}
		
		if(heli1.getY() > (canvasHeight - 52) || heli1.getY() < 0) {
			System.out.println("Krasj top/bunn");
			heli1.setSpeed(heli1.getSpeed().getX(), -heli1.getSpeed().getY());
		}
		
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		canvasHeight = canvas.getHeight();
		canvasWidth = canvas.getWidth();
		heli1.draw(canvas);
	}

}
