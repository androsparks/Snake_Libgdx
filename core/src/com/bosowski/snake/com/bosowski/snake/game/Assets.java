package com.bosowski.snake.com.bosowski.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.bosowski.snake.com.bosowski.snake.game.util.Constants;

/**
 * Created by crevo on 29/05/2017.
 */

public class Assets implements Disposable, AssetErrorListener{
    public static Assets instance = new Assets();
    public static final String TAG = Assets.class.getName();
    private AssetManager assetManager;

    //Assets
    public TextureRegion snakeHeadTexture;
    public TextureRegion snakeBodyTexture;
    public TextureRegion snakeTailTexture;
    public TextureRegion snakeTurningRightTexture;
    public TextureRegion snakeTurningLeftTexture;
    public TextureRegion bugTexture;

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);

        //load texture from game sprites
        assetManager.load(Constants.TEXTURE_ATLAS_GAME, TextureAtlas.class);

        assetManager.finishLoading();

        Gdx.app.debug(TAG, "# of assets loaded : " + assetManager.getAssetNames().size);
        for(String name: assetManager.getAssetNames()){
            Gdx.app.debug(TAG, "asset '" + name+"' loaded.");
        }

        //create atlas for game sprites
        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_GAME);
        for(TextureAtlas.AtlasRegion region: atlas.getRegions()){
            Gdx.app.debug(TAG, "Region '"+region.name + "' loaded.");
        }

        snakeHeadTexture = atlas.findRegion("snakeHead");
        snakeBodyTexture = atlas.findRegion("snakeBody");
        snakeTailTexture = atlas.findRegion("snakeTail");
        snakeTurningRightTexture = atlas.findRegion("snakeTurningRight");
        snakeTurningLeftTexture = atlas.findRegion("snakeTurningLeft");
        bugTexture = atlas.findRegion("bug");


    }

    private Assets(){}

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset '" + asset + "'", (Exception) throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
