
package GameEnvironment;


import Geometry.Block;
import Geometry.Point;
import Geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class LevelOne. Holds the information for creating level one.
 */
public class LevelOne implements LevelInformation {


    @Override
    public int numberOfBalls() {
        return 5;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            ballsVelocity.add(new Velocity(-(i + 1), -5));
        }
        return ballsVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 20;
    }

    @Override
    public int paddleWidth() {
        return 250;
    }

    @Override
    public String levelName() {
        return "Level One";
    }

    @Override
    public Sprite getBackground() {
        return new BackGround4();
    }

    @Override
    public List<Block> blocks() {
        /*
        List<Block> blockList = new ArrayList<>();

         for(int i=0;i<10;i++){
                     Block b = new Block(new Point(i*80,200),80,10, Color.RED);
            blockList.add(b);
        }

        return blockList;

         */
        List<Block> blockList = new ArrayList<>();
        Block b = new Block(new Point(400, 100), 40, 40, Color.RED);
        blockList.add(b);
        return blockList;

    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}


