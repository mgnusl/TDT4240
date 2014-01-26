package com.example.helicopter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import sheep.game.Game;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Create the game.
		Game game = new Game(this, null);
		// Push the main state.
		game.pushState(new GameState(game.getResources()));
		// View the game.
		setContentView(game);
		
	}
}
