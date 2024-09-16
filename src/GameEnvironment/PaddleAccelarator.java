package GameEnvironment;
import Geometry.Ball;
import Geometry.Block;

/**
 * The Paddle accelarator listener. makes the paddle faster when ball hitting
 * certain block.
 */
public class PaddleAccelarator implements HitListener {

    /**
     * reference to the game.
     */
    private GameLevel game;


    /**
     * Construct instantiates of the listener.
     *
     * @param game the game which holds the paddle.
     */
    public PaddleAccelarator(GameLevel game) {
        this.game = game;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //increasing paddle speed by 3.
        game.setPaddleSpeed(game.getPaddleSpeed() + 3);
    }
}
