package com.bosowski.snake.com.bosowski.snake.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.bosowski.snake.com.bosowski.snake.game.util.Constants;

/**
 * Created by crevo on 29/05/2017.
 */

public class WorldRenderer implements Disposable{

    private WorldController worldController;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private OrthographicCamera GUIcamera;

    public WorldRenderer(WorldController worldController){
        this.worldController = worldController;
        init();
    }

    private void init(){
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0,0,0);
        camera.update();
        GUIcamera = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
        GUIcamera.position.set(0,0,0);
        GUIcamera.update();
    }

    public void render(){
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        worldController.render(batch);
        batch.end();
    }



    @Override
    public void dispose() {
        batch.dispose();
    }
}
