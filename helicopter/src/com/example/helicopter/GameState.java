package com.example.helicopter;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.input.TouchListener;

public class GameState extends State {
	
	private int canvasHeight, canvasWidth;
	//private final int HELI_WIDTH = 130;
	//private final int HELI_HEIGHT = 52;
	
	
	private Helicopter heli1;
	private Helicopter heli2;
	private Helicopter heli3;
	//private Helicopter heli4;
	
	private static final String TAG = "APP";	
	
	
	public GameState(Resources r) {		 
		
		//heli1
		heli1 = new Helicopter(BitmapFactory.decodeResource(r, R.drawable.heli_all_frames), 
				0, 0, 130, 52, 30, 4);
		heli1.setSpeed(700, 700);
		heli1.flip(); //initial flip to get heli to face the right direction
		heli1.update(System.currentTimeMillis());
		
		//heli2
		heli2 = new Helicopter(BitmapFactory.decodeResource(r, R.drawable.heli_all_frames), 
				300, 700, 130, 52, 30, 4);
		heli2.setSpeed(400, 400);
		heli2.flip(); //initial flip to get heli to face the right direction
		heli2.update(System.currentTimeMillis());
		
		//heli3
		heli3 = new Helicopter(BitmapFactory.decodeResource(r, R.drawable.heli_all_frames), 
				500, 500, 130, 52, 30, 4);
		heli3.setSpeed(420, 830);
		heli3.flip(); //initial flip to get heli to face the right direction
		heli3.update(System.currentTimeMillis());
	

	
	}
 
	@Override
	public void update(float dt) {
		
		//heli1
		heli1.update(System.currentTimeMillis());
		if(heli1.getX() > (canvasWidth - 130) || heli1.getX() < 0) {
			Log.d(TAG, "Krasj side");
			heli1.setSpeed(-heli1.getSpeed().getX(), heli1.getSpeed().getY());
			heli1.flip();
		}
	
		
		if(heli1.getY() > (canvasHeight - 52) || heli1.getY() < 0) {
			Log.d(TAG, "Krasj topp/bunn");
			heli1.setSpeed(heli1.getSpeed().getX(), -heli1.getSpeed().getY());
		}
		
		//heli2
		heli2.update(System.currentTimeMillis());
		if(heli2.getX() > (canvasWidth - 130) || heli2.getX() < 0) {
			heli2.setSpeed(-heli2.getSpeed().getX(), heli2.getSpeed().getY());
			heli2.flip();
		}
		
		if(heli2.getY() > (canvasHeight - 52) || heli2.getY() < 0) {
			heli2.setSpeed(heli2.getSpeed().getX(), -heli2.getSpeed().getY());
		}
		
		//heli3
		heli3.update(System.currentTimeMillis());
		if(heli3.getX() > (canvasWidth - 130) || heli3.getX() < 0) {
			heli3.setSpeed(-heli3.getSpeed().getX(), heli3.getSpeed().getY());
			heli3.flip();
		}
		
		if(heli3.getY() > (canvasHeight - 52) || heli3.getY() < 0) {
			heli3.setSpeed(heli3.getSpeed().getX(), -heli3.getSpeed().getY());
		}
		
		
		//heli krasj i hverandre
		if(heli1.getSpriteBounds().intersect(heli2.getSpriteBounds())) {
			// do something
			Log.d(TAG, "Krasj i hverandre");
			heli1.setSpeed(-heli1.getSpeed().getX(), -heli1.getSpeed().getY());
			heli1.flip();
			heli2.setSpeed(-heli2.getSpeed().getX(), -heli2.getSpeed().getY());
			heli2.flip();
		}
		
		if(heli1.getSpriteBounds().intersect(heli3.getSpriteBounds())) {
			// do something
			Log.d(TAG, "Krasj i hverandre");
			heli1.setSpeed(-heli1.getSpeed().getX(), -heli1.getSpeed().getY());
			heli1.flip();
			heli3.setSpeed(-heli3.getSpeed().getX(), -heli3.getSpeed().getY());
			heli3.flip();
		}
				
		if(heli2.getSpriteBounds().intersect(heli3.getSpriteBounds())) {
			// do something
			Log.d(TAG, "Krasj i hverandre");
			heli2.setSpeed(-heli2.getSpeed().getX(), -heli2.getSpeed().getY());
			heli2.flip();
			heli3.setSpeed(-heli3.getSpeed().getX(), -heli3.getSpeed().getY());
			heli3.flip();
		}

		
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		canvasHeight = canvas.getHeight();
		canvasWidth = canvas.getWidth();
		heli1.draw(canvas);
		heli2.draw(canvas);
		heli3.draw(canvas);
		
		// coordinates
		Font f = new Font(0, 255, 0 , 30, Typeface.DEFAULT_BOLD, Typeface.BOLD);
		canvas.drawText("Heli1 coordinates. x: " + heli1.getX() + " y: " + heli1.getY() , 40, 50, f);
	}

}
