package com.bosowski.snake.com.bosowski.snake.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.bosowski.snake.com.bosowski.snake.game.util.Constants;

/**
 * Created by crevo on 29/05/2017.
 */

public abstract class AbstractGameObject {

    protected com.bosowski.snake.com.bosowski.snake.game.Level level;
    protected Vector2 dimension;
    protected Vector2 position;
    protected Vector2 origin;
    protected Vector2 scale;
    protected Vector2 velocity;
    protected float rotation;
    protected TextureRegion texture;
    protected double counter;

    public AbstractGameObject(com.bosowski.snake.com.bosowski.snake.game.Level level){
        this.level = level;
        dimension = new Vector2(1,1);
        scale = new Vector2(1,1);
        velocity = new Vector2();
        rotation = 0;
        counter = 0;
        origin = new Vector2(dimension.x/2, dimension.y/2);
        position = new Vector2(origin.x, origin.y);
    }

    public void render(SpriteBatch batch){
        batch.draw(texture.getTexture(), position.x-origin.x, position.y-origin.y, origin.x, origin.y,
                dimension.x, dimension.y, scale.x, scale.y, rotation,
                texture.getRegionX(), texture.getRegionY(), texture.getRegionWidth(), texture.getRegionHeight(),
                false, false);
    }

    public void update(float deltaTime){

    }

    public Vector2 getDimension() {
        return dimension;
    }

    public void setDimension(Vector2 dimension) {
        this.dimension = dimension;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getOrigin() {
        return origin;
    }

    public void setOrigin(Vector2 origin) {
        this.origin = origin;
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setTexture(TextureRegion texture){
        this.texture = texture;
    }

    public TextureRegion getTexture(){return this.texture;}
}
