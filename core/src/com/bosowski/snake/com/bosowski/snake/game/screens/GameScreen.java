package com.bosowski.snake.com.bosowski.snake.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.bosowski.snake.com.bosowski.snake.game.WorldController;
import com.bosowski.snake.com.bosowski.snake.game.WorldRenderer;

/**
 * Created by crevo on 29/05/2017.
 */

public class GameScreen extends AbstractGameScreen {

    private WorldController worldController;
    private WorldRenderer worldRenderer;
    private boolean paused;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldRenderer.render();
        // Do not update game world when paused.
        if (!paused) {
            worldController.update(deltaTime);
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        worldController = new WorldController(game);
        worldRenderer = new WorldRenderer(worldController);
    }

    @Override
    public void hide() {
        worldRenderer.dispose();
        Gdx.input.setCatchBackKey(false);
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume(){
        super.resume();
        paused = false;
    }
}
