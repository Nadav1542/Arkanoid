package Animations;
import biuoop.DrawSurface;
import GameEnvironment.SpriteCollection;

/**
 * The CountdownAnimation class.
 */
public class CountdownAnimation implements Animation {

    private SpriteCollection collection;

    private double numOfSeconds;

    private boolean running;
    private int countFrom;

    /**
     * Method construct instantiates of Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.collection = gameScreen;
        this.running = true;
    }


    @Override
    public void doOneFrame(DrawSurface d) {

        this.collection.drawAllOn(d);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        if (countFrom != 3) {
            sleeper.sleepFor(2000 / 3);
        }
        if (countFrom == 0) {
            d.drawText(400, 300, "Go!", 45);
            this.running = false;
            return;
        }
        d.drawText(400, 300, "" + countFrom, 45);
        countFrom--;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
