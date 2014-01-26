package com.example.helicopter;

import sheep.game.Sprite;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;

public class Helicopter extends Sprite {

	// Kilde animasjon av helikopter sprite
	// http://www.javacodegeeks.com/2011/07/android-game-development-sprite.html

	private Bitmap bitmap; // the animation sequence
	private Rect sourceRect; // the rectangle to be drawn from the animation
								// bitmap
	private int frameNr; // number of frames in animation
	private int currentFrame; // the current frame
	private long frameTicker; // the time of the last frame update
	private int framePeriod; // milliseconds between each frame (1000/fps)
	private int spriteWidth; // the width of the sprite to calculate the cut out
								// rectangle
	private int spriteHeight; // the height of the sprite
	private float x; // the X coordinate of the object (top left of the image)
	private float y; // the Y coordinate of the object (top left of the image)

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

	public Rect getSpriteRect() {
		return new Rect((int) getX(), (int) getY(), (int) getX() + spriteWidth,
				(int) getY() + spriteHeight);
	}
}