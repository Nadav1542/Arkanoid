package GameEnvironment;
import Geometry.Velocity;
import Geometry.Block;
import java.util.List;


/**
 * The interface Level information. Any class who implements this, holds
 * information for creating level.
 */
public interface LevelInformation {
    /**
     * Method returns number of balls in level.
     *
     * @return (int) number of balls.
     */
    int numberOfBalls();


        // The initial velocity of each ball
        // Note that initialBallVelocities().size() == numberOfBalls()

    /**
     * Method returns initial ball velocities list.
     *
     * @return (list) list which holds velocities of balls.
     */
    List<Velocity> initialBallVelocities();


    /**
     * Method returns speed of paddle.
     *
     * @return (int) speed of paddle.
     */
    int paddleSpeed();

    /**
     * Method returns paddle width.
     *
     * @return (int) width of paddle.
     */
    int paddleWidth();


        // the level name will be displayed at the top of the screen.


    /**
     * Method returns name of level.
     *
     * @return (String) name of level.
     */
    String levelName();
        // Returns a sprite with the background of the level

    /**
     * Method returns background of level.
     *
     * @return (Sprite) background of level.
     */
    Sprite getBackground();

        // The Blocks that make up this level, each block contains
        // its size, color and location.

    /**
     * Method returns list of blocks. The Blocks that make up this level,
     * each block contains its size, color and location.
     *
     * @return (list) list of blocks.
     */
    List<Block> blocks();




    /**
     * Method returns  Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size().
     *
     * @return (int) number of blocks for removing.
     */
    int numberOfBlocksToRemove();
    }
