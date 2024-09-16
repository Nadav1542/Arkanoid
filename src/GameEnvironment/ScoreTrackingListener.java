/**
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
 */
package GameEnvironment;

import Geometry.Ball;
import Geometry.Block;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {

    /**
     * reference to current score in the game.
     */
    private Counter currentScore;

    /**
     * Method constructs instantiates of new Score tracking listener.
     *
     * @param scoreCounter the score counter of the game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {

        this.currentScore = scoreCounter;

    }

    /**
     * Method returns current score.
     *
     * @return the current score
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }

    /**
     * Method sets current score.
     *
     * @param currentScore the current score
     */
    public void setCurrentScore(Counter currentScore) {
        this.currentScore = currentScore;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        this.currentScore.increase(5);

    }
}
