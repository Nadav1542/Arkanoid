/**
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
 */
package GameEnvironment;

import Geometry.Ball;
import Geometry.Block;

/**
 * Class Block remover.
 */
public class BlockRemover implements HitListener {
    /**
     * reference to game.
     */
    private GameLevel game;
    /**
     * reference to Counter of blocks of the game.
     */
    private Counter remainingBlocks;

    /**
     * Method returns the game.
     *
     * @return the game
     */
    public GameLevel getGame() {
        return game;
    }

    /**
     * Method sets the game.
     *
     * @param game the game for setting.
     */
    public void setGame(GameLevel game) {
        this.game = game;
    }


    /**
     * Method returns Counter of remaining blocks in the game.
     *
     * @return Counter of balls in the game.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * Sets Counter remaining balls.
     *
     * @param remainingBlocks Counter of remaining blocks.
     */
    public void setRemainingBlocks(Counter remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Addnum blocks.
     *
     * @param numBlocks the num blocks
     */
    public void addnumBlocks(int numBlocks) {
        remainingBlocks.increase(numBlocks);
    }


    /**
     * Method construct instantiates of new Block remover.
     *
     * @param game          the reference to the game.
     * @param removedBlocks reference to Counter of the game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        this.getGame().removeFromGame(beingHit);
        beingHit.removeHitListener(this);

    }
}
