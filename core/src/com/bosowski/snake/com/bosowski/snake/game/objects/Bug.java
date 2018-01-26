package com.bosowski.snake.com.bosowski.snake.game.objects;

import com.badlogic.gdx.math.Vector2;
import com.bosowski.snake.com.bosowski.snake.game.*;
import com.bosowski.snake.com.bosowski.snake.game.util.Constants;

import java.util.Random;

/**
 * Created by crevo on 30/05/2017.
 */

public class Bug extends AbstractGameObject{

    Random random = new Random(System.currentTimeMillis());

    public Bug(com.bosowski.snake.com.bosowski.snake.game.Level level) {
        super(level);
        init();
    }

    public void init(){

        super.setPosition(new Vector2(-Constants.VIEWPORT_WIDTH/2+dimension.x/2, -Constants.VIEWPORT_HEIGHT/2+dimension.y/2));
        super.position.x += random.nextInt((int)Constants.VIEWPORT_WIDTH-2)+1;
        super.position.y += random.nextInt((int)Constants.VIEWPORT_HEIGHT-2)+1;
        super.texture = Assets.instance.bugTexture;
    }
}
