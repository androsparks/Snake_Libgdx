package com.bosowski.snake.com.bosowski.snake.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bosowski.snake.com.bosowski.snake.game.objects.AbstractGameObject;
import com.bosowski.snake.com.bosowski.snake.game.objects.Bug;
import com.bosowski.snake.com.bosowski.snake.game.objects.Player;

import java.util.ArrayList;

/**
 * Created by crevo on 30/05/2017.
 */

public class Level{

    private ArrayList<AbstractGameObject> gameObjects;
    public Player player;
    public Bug bug;

    public Level() {
        init();
    }

    private void init() {
        gameObjects = new ArrayList<AbstractGameObject>();
        player = new Player(this);
        gameObjects.add(player);
        bug = new Bug(this);
        gameObjects.add(bug);
    }

    public void render(SpriteBatch batch) {
        for(AbstractGameObject object: gameObjects){
            object.render(batch);
        }
    }

    public void update(float deltaTime){
        for(AbstractGameObject object: gameObjects){
            object.update(deltaTime);
        }
    }
}
