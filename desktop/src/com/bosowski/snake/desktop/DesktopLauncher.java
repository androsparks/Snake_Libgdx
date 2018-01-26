package com.bosowski.snake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.bosowski.snake.Snake;

public class DesktopLauncher {

	private static boolean rebuildAtlas = true;
	private static boolean drawDebugOutline = false;

	public static void main (String[] arg) {

		if(rebuildAtlas) {
			TexturePacker.Settings settings = new TexturePacker.Settings();
			settings.maxHeight = 1024;
			settings.maxWidth = 1024;
			settings.debug = drawDebugOutline;
			TexturePacker.process(settings, "../Assets-RAW", "images", "game.atlas");
		}

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Snake";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new Snake(), config);
	}
}
