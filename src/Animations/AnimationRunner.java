package Animations;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * The type Animation runner.
 */
public class AnimationRunner {

    private GUI gui;
    private int framesPerSecond;


    /**
     * Instantiates a new Animation runner.
     *
     * @param framesPerSecond the frames per second
     */
    public AnimationRunner(int framesPerSecond) {
        this.gui = new GUI("Arkanoid", 800, 600);
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * Gets gui.
     *
     * @return the gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Sets gui.
     *
     * @param gui the gui
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     * Run.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}