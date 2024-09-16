package GameEnvironment;

import Geometry.Block;
import Geometry.Point;
import Geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Level three. Holds the information for creating level three.
 */
public class LevelThree implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        ballsVelocity.add(new Velocity(-5, -5));
        ballsVelocity.add(new Velocity(5, -5));
        return ballsVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 20;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Level Three";
    }

    @Override
    public Sprite getBackground() {
        return new BackGround3();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        Color[] c = {Color.yellow, Color.pink, Color.blue, Color.green,
                Color.red, Color.gray};
        for (int i = 0; i < 6; i++) {
            int y = 100 + 25 * i;
            for (int j = i + 1; j < 12; j++) {
                int x = 185 + 50 * j;
                Block block = new Block(new Point(x, y), 50,
                        25, c[i]);
                blockList.add(block);
            }
        }
            return blockList;

    }

    @Override
    public int numberOfBlocksToRemove() {
        return 12;
    }
}

