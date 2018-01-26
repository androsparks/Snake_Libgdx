package com.bosowski.snake.com.bosowski.snake.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.bosowski.snake.com.bosowski.snake.game.objects.Bug;
import com.bosowski.snake.com.bosowski.snake.game.objects.PlayerBody;

/**
 * Created by crevo on 29/05/2017.
 */

public class WorldController extends InputAdapter{

    public static final String TAG = WorldController.class.getName();
    private Game game;
    public Level level;

    public WorldController(Game game){
        this.game = game;
        init();
    }

    private void init(){
        Gdx.input.setInputProcessor(this);
        level = new Level();
    }

    public void update(float deltaTime){
        checkPlayerBugCollisions();
        checkPlayerBodyCollisions();
        checkBugBodyCollision();
        handleGameInput(deltaTime);
        level.update(deltaTime);
    }

    private void checkBugBodyCollision() {
        Rectangle bugRect = new Rectangle();
        bugRect.set(level.bug.getPosition().x, level.bug.getPosition().y, level.bug.getDimension().x, level.bug.getDimension().y);

        Rectangle bodyRect = new Rectangle();

        for(int i = 0; i<level.player.body.size(); i++){
            if(i == 0){
                continue;
            }

            bodyRect.set(level.player.body.get(i).getPosition().x, level.player.body.get(i).getPosition().y, level.player.body.get(i).getDimension().x, level.player.body.get(i).getDimension().y);

            if(bugRect.overlaps(bodyRect)){
                level.bug.init();
            }
        }

    }

    private void checkPlayerBodyCollisions() {
        Rectangle playerRect = new Rectangle();
        playerRect.set(level.player.getPosition().x, level.player.getPosition().y, level.player.getDimension().x, level.player.getDimension().y);

        Rectangle bodyRect = new Rectangle();

        for(int i = 0; i<level.player.body.size(); i++){
            if(i == 0){
                continue;
            }

            bodyRect.set(level.player.body.get(i).getPosition().x, level.player.body.get(i).getPosition().y, level.player.body.get(i).getDimension().x, level.player.body.get(i).getDimension().y);

            if(playerRect.overlaps(bodyRect)){
                level.player.isAlive = false;
                level.player.body.get(0).setPosition(level.player.body.get(0).getLastPosition());
                level.player.body.get(0).setLastPosition(level.player.body.get(0).getPosition());
            }
        }


    }

    private void checkPlayerBugCollisions() {
        Rectangle playerRect = new Rectangle();
        playerRect.set(level.player.getPosition().x, level.player.getPosition().y, level.player.getDimension().x, level.player.getDimension().y);

        Rectangle bugRect = new Rectangle();
        bugRect.set(level.bug.getPosition().x, level.bug.getPosition().y, level.bug.getDimension().x, level.bug.getDimension().y);

        if(playerRect.overlaps(bugRect)) {
            level.bug.init();
            level.player.grow();
        }
    }

    public void render(SpriteBatch batch){

        level.render(batch);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK) {
            Gdx.app.exit();
        }
        return false;
    }

    public void handleGameInput(float deltaTime){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)|| Gdx.input.getDeltaY() < -30){
            if(!level.player.turn.equals("down")){
                level.player.turnUp();
            }

        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)||Gdx.input.getDeltaY() > 30){
            if(!level.player.turn.equals("up")) {
                level.player.turnDown();
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)|| Gdx.input.getDeltaX() < -30){
            if(!level.player.turn.equals("right")) {
                level.player.turnLeft();
            }
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)|| Gdx.input.getDeltaX() > 30){
            if(!level.player.turn.equals("left")) {
                level.player.turnRight();
            }
        }


        if((Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.justTouched()) && !level.player.isAlive){
            level = new Level();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            level.player.grow();
        }
    }

}
