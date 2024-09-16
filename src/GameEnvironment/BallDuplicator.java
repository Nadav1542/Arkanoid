package GameEnvironment;
import Geometry.Ball;
import Geometry.Block;
import Geometry.Point;

/**
 * The Ball duplicator class.
 */
public class BallDuplicator implements HitListener {

    /**
     * reference to game.
     */
    private GameLevel game;


    /**
     * Gets game.
     *
     * @return the game
     */
    public GameLevel getGame() {
        return game;
    }

    /**
     * Sets game.
     *
     * @param game the game
     */
    public void setGame(GameLevel game) {
        this.game = game;
    }

    /**
     * Method construct instantiates of Ball duplicator listener.
     *
     * @param game the game
     */
    public BallDuplicator(GameLevel game) {
        this.game = game;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        Point newP = new Point(hitter.getX(), hitter.getY());
        Ball b = new Ball(newP, hitter.getSize(), hitter.getColor());
        b.setVelocity(-hitter.getVelocity().getDx(), hitter.getVelocity().getDy());
        this.getGame().addBallToGame(b);
    }
}
