package com.bosowski.snake.com.bosowski.snake.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.bosowski.snake.com.bosowski.snake.game.*;
import com.bosowski.snake.com.bosowski.snake.game.util.Constants;

import java.util.ArrayList;

/**
 * Created by crevo on 30/05/2017.
 */

public class Player extends PlayerBody{

    public static final String TAG = Player.class.getName();

    public boolean isAlive;
    public ArrayList<PlayerBody> body;
    private float rotationToApply;
    public String turn;
    private boolean addBodyPart;
    public boolean goThroughWalls;
    public boolean hard;



    public Player(com.bosowski.snake.com.bosowski.snake.game.Level level) {
        super(level);
        body = new ArrayList<PlayerBody>();
        body.add(this);
        isAlive = true;
        texture = Assets.instance.snakeHeadTexture;
        rotationToApply = 90;
        turn = "";
        addBodyPart = true;
        turnRight();
        goThroughWalls = true;
        hard = true;
    }

    @Override
    public void update(float deltaTime){
        if(isAlive) {
            counter+=deltaTime;

            //update position
            if(counter >= 1/Constants.SNAKE_SPEED) {
                checkTurnAtTurnTime();
                for (int i = 0; i < body.size(); i++) {
                    if (i == 0) {

                        body.get(i).setPosition(new Vector2(position.x + velocity.x, position.y + velocity.y));
                        body.get(i).setRotation(rotationToApply);

                        if(!isInScreen() && !goThroughWalls){
                            isAlive = false;
                            body.get(i).setPosition(body.get(i).getLastPosition());
                            return;
                        }
                        else{
                            goThroughWalls();
                        }

                        continue;
                    }

                    body.get(i).setRotation(body.get(i-1).getLastRotation());
                    body.get(i).setPosition(body.get(i-1).getLastPosition());

                }
                if(addBodyPart){
                    addBodyPart();
                }
                counter = 0;
            }

            for(int i = 0; i < body.size(); i++){
                if(i == 0){
                    continue;
                }
                if(i == body.size()-1){
                    body.get(i).setRotation(body.get(i-1).getRotation());
                }
                else if(body.get(i).getRotation() != body.get(i-1).getRotation()){
                    if((body.get(i).getRotation() == 90 && body.get(i-1).getRotation() == 180) || (body.get(i).getRotation() == 180 && body.get(i-1).getRotation() == -90) ||
                      (body.get(i).getRotation() == -90 && body.get(i-1).getRotation() == 0) || (body.get(i).getRotation() == 0 && body.get(i-1).getRotation() == 90)){
                        body.get(i).setTexture(Assets.instance.snakeTurningLeftTexture);
                    }
                    else{
                        body.get(i).setTexture(Assets.instance.snakeTurningRightTexture);
                    }

                }
                else{
                    body.get(i).setTexture(Assets.instance.snakeBodyTexture);
                }
            }

           // super.update(deltaTime);
        }
        //Gdx.app.debug(TAG, "position x :"+super.position.x + "  position y :"+super.position.y);
    }

    public boolean isInScreen()  {
        return ((position.x>-Constants.VIEWPORT_WIDTH/2 && position.x<Constants.VIEWPORT_WIDTH/2) &&
                (position.y>-Constants.VIEWPORT_HEIGHT/2 && position.y<Constants.VIEWPORT_HEIGHT/2));
    }

    @Override
    public void render(SpriteBatch batch){
        for(int i = body.size()-1; i>0; i--){
            body.get(i).render(batch);
        }
        super.render(batch);
    }

    private void checkTurnAtTurnTime(){
        if(velocity.x == 1 ){
            turn = "right";
        }
        if(velocity.x == -1){
            turn = "left";
        }
        if(velocity.y == 1){
            turn = "up";
        }
        if(velocity.y == -1){
            turn = "down";
        }
    }

    public void goThroughWalls(){
        if(position.x>Constants.VIEWPORT_WIDTH/2 ){
            position.x = -Constants.VIEWPORT_WIDTH/2+dimension.y/2;
        }
        else if(position.x<-Constants.VIEWPORT_WIDTH/2){
            position.x = Constants.VIEWPORT_WIDTH/2-dimension.x/2;
        }
        else if(position.y>Constants.VIEWPORT_HEIGHT/2){
            position.y = -Constants.VIEWPORT_HEIGHT/2+dimension.y/2;
        }
        else if(position.y<-Constants.VIEWPORT_HEIGHT/2){
            position.y = Constants.VIEWPORT_HEIGHT/2-dimension.y/2;
        }
    }

    public void grow(){
        addBodyPart = true;
    }

    private void addBodyPart(){
        PlayerBody newPart = new PlayerBody(level);
        newPart.setPosition(body.get(body.size()-1).getLastPosition());
        newPart.setTexture(Assets.instance.snakeTailTexture);
        body.add(newPart);
        addBodyPart = false;
    }

    public void turnRight(){
        rotationToApply = 90;
        super.velocity.y = 0;
        super.velocity.x = 1;
    }

    public void turnLeft(){
        rotationToApply = -90;
        super.velocity.y = 0;
        super.velocity.x = -1;
    }

    public void turnUp(){
        rotationToApply = 180;
        super.velocity.y = 1;
        super.velocity.x = 0;
    }

    public void turnDown(){
        rotationToApply = 0;
        super.velocity.y = -1;
        super.velocity.x = 0;
    }

}
