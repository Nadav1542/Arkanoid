/**
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
 */
package GameEnvironment;

import Geometry.Ball;
import Geometry.Block;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {

    /**
     * reference to the game.
     */
    private GameLevel game;

    /**
     * reference to Counter of balls of the game.
     */
    private Counter remainingBalls;

    /**
     * Method returns the game.
     *
     * @return the game
     */
    public GameLevel getGame() {
        return game;
    }

    /**
     * Method returns Counter of remaining balls in the game.
     *
     * @return Counter of balls in the game.
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * Sets Counter remaining balls.
     *
     * @param remainingBalls Counter of remaining balls
     */
    public void setRemainingBalls(Counter remainingBalls) {
        this.remainingBalls = remainingBalls;
    }

    /**
     * Method sets the game.
     *
     * @param game the game
     */
    public void setGame(GameLevel game) {
        this.game = game;
    }

    /**
     * Method construct instantiates of new Ball remover.
     *
     * @param game     reference to the game
     * @param numBalls reference to Counter of balls of the game.
     */
    public BallRemover(GameLevel game, Counter numBalls) {
        this.game = game;
        this.remainingBalls = numBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
    }
}