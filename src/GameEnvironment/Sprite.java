/**
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
 */
package GameEnvironment;
import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * Method draw the sprite on given surface.
     *
     * @param d the surface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * Method notify the sprite that time has passed.
     */
    void timePassed();

}
