/**
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
 */
package GameEnvironment;
import Geometry.Block;
import Geometry.Ball;

/**
 * The interface Hit listener.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.


    /**
     * Method runs relevant action when hitting occurs between block beingHit
     * and Ball hitter.
     *
     * @param beingHit block which being hit.
     * @param hitter   ball which hits.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
