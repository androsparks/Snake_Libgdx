package com.bosowski.snake;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.bosowski.snake.com.bosowski.snake.game.Assets;
import com.bosowski.snake.com.bosowski.snake.game.screens.GameScreen;

public class Snake extends Game {
	
	@Override
	public void create () {

		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Assets.instance.init(new AssetManager());
		setScreen(new GameScreen(this));

	}

}
