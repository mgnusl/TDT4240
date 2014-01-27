package com.example.helicopter;

import sheep.game.Sprite;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;

public class Helicopter extends Sprite {

	// Kilde animasjon av helikopter sprite
	// http://www.javacodegeeks.com/2011/07/android-game-development-sprite.html

	private static final String TAG = null;
	private Bitmap bitmap; 
	private Rect sourceRect; 
								
	private int frameNr; 
	private int currentFrame;
	private long frameTicker; 
	private int framePeriod; 
	private int spriteWidth; 
								
	private int spriteHeight;
	private float x;
	private float y;

	public Helicopter(Bitmap bitmap, int x, int y, int width, int height, int fps, int frameCount) {
				
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		currentFrame = 0;
		frameNr = frameCount;
		spriteWidth = bitmap.getWidth() / frameCount;
		spriteHeight = bitmap.getHeight();
		sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
		framePeriod = 1000/fps;
		frameTicker = 0l;
	}

	public void update(long gameTime) {
		if (gameTime > frameTicker + framePeriod) {
			frameTicker = gameTime;
			// increment the frame
			currentFrame++;
			if (currentFrame >= frameNr) {
				currentFrame = 0;
			}
		}
		// define the rectangle to cut out sprite
		this.sourceRect.left = currentFrame * spriteWidth;
		this.sourceRect.right = this.sourceRect.left + spriteWidth;

	}

	@Override
	public void draw(Canvas canvas) {
		setX(getX() + getSpeed().getX() / 100);
		setY(getY() + getSpeed().getY() / 100);

		Rect destRect = new Rect((int) x, (int) y, (int) x + spriteWidth,(int) y + spriteHeight);
		canvas.drawBitmap(bitmap, sourceRect, destRect, null);
	}
	
	// returns a Rectangle representing the space the Sprite occupies
	public Rect getSpriteBounds(){
		Rect spriteBounds = new Rect((int)getX(), (int)getY(), (int)getX() + spriteWidth, (int)getY() + spriteHeight);
		return spriteBounds;
	}
	
	@SuppressWarnings("deprecation")
	public void flip() {
		Matrix flipMatrix = new Matrix();
		flipMatrix.preScale(-1.0f, 1.0f);
		Bitmap flipSprite = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), flipMatrix, false);
		flipSprite.setDensity(DisplayMetrics.DENSITY_DEFAULT);
		bitmap = new BitmapDrawable(flipSprite).getBitmap();
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
}