/**
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
 */
package GameEnvironment;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The GameIndicator Class. Holds details for user through the game.
 */
public class GameIndicator implements Sprite {


    private String levelName;

    /**
     * The Score.
     */
    private Counter score;

    /**
     * Gets score.
     *
     * @return the score
     */
    public Counter getScore() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(Counter score) {
        this.score = score;
    }


    /**
     * Method constructs instantiates of new Score indicator.
     *
     * @param score     reference to score of user.
     * @param levelName the level name
     */
    public GameIndicator(Counter score, String levelName) {
        this.score = score;
        this.levelName = levelName;
    }


    /**
     * Method return String which tells the score of user.
     *
     * @return the string
     */
    public String displayScore() {
        return "Score:" + this.score.getValue();
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawRectangle(0, 0, 800, 600);
        d.drawText(400, 15, this.displayScore(), 20);
        d.drawText(100, 15, this.levelName, 20);
    }

    @Override
    public void timePassed() {
    }
}
