package com.example.pong;

import sheep.game.Game;

import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {

        // singleton pattern exercise

		super.onCreate(savedInstanceState);
		// Create the game.
		Game game = new Game(this, null);
		// Push the main state.
		game.pushState(GameStateEx2.getInstance());
		// View the game.
		setContentView(game);
		
	}

}
