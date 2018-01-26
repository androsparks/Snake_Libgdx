package com.bosowski.snake.com.bosowski.snake.game.objects;

import com.badlogic.gdx.math.Vector2;
import com.bosowski.snake.com.bosowski.snake.game.Assets;
import com.bosowski.snake.com.bosowski.snake.game.Level;

/**
 * Created by crevo on 30/05/2017.
 */

public class PlayerBody extends AbstractGameObject{

    private Vector2 lastPosition;
    private float lastRotation;
    protected boolean turningPoint;

    public PlayerBody(Level level) {
        super(level);
        origin = new Vector2(dimension.x/2, dimension.y/2);
        texture = Assets.instance.snakeBodyTexture;
        turningPoint = false;
    }

    @Override
    public void setPosition(Vector2 position){
        lastPosition = this.position;
        this.position = position;
    }

    public void setRotation(float rotation){
        lastRotation = this.rotation;
        this.rotation = rotation;
    }

    public Vector2 getLastPosition(){
        return lastPosition;
    }
    public float getLastRotation(){ return lastRotation;}
    public void setLastPosition(Vector2 lastPosition){
        this.lastPosition = lastPosition;
    }

}
