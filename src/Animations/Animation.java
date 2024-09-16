package Animations;
import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {

    /**
     * Method creates one frame of animation on the screen.
     *
     * @param d the surface for drawing on.
     */
     void doOneFrame(DrawSurface d);

    /**
     * Method returns true if animation should stop and false if should
     * continue running.
     *
     * @return (false) should continue running and (true) otherwise.
     */
    boolean shouldStop();
}
