package GameEnvironment;

import Geometry.Block;
import Geometry.Point;
import Geometry.Velocity;

import java.awt.Color;

import java.util.List;
import java.util.ArrayList;

/**
 * The Class Level two. Holds the information for creating level 2.
 */
public class LevelTwo implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        // Create a list to store the velocities
        List<Velocity> velocities = new ArrayList<>();

        // Add ten velocities with angles differing by 10 degrees
        int angle = -50;
        for (int i = 0; i < 10; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(angle, 5));
            angle += 10;
        }
        return velocities;

    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 250;
    }

    @Override
    public String levelName() {
        return "Level Two";
    }

    @Override
    public Sprite getBackground() {
        return new BackGround1();
    }

    @Override
    public List<Block> blocks() {
        // Define rainbow colors
        Color[] rainbowColors = {
                Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW,
                Color.YELLOW, Color.green, Color.green, Color.green, Color.blue,
                Color.blue, Color.blue, Color.pink, Color.pink, Color.pink,
                Color.CYAN, Color.CYAN};

        // Calculate block dimensions
        int blockSize = 52;
        int startX = 10;
        int startY = 250;

        // Create rainbow blocks
        List<Block> rainbowBlocks = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int x = startX + i * blockSize;
            int y = startY;
            Color color = rainbowColors[i % rainbowColors.length];
            Block block = new Block(new Point(x, y), blockSize, 20, color);
            rainbowBlocks.add(block);
        }

        return rainbowBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }



}

