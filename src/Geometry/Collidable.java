/**
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
 */
package Geometry;


/**
 * The interface Collidable.
 */
public interface Collidable {
    /**
     * All classes which implements Collidable interface should return their
     * rectangle form.
     *
     * @return the rectangle field of object.
     */
    Rectangle getCollisionRectangle();

    /**
     * All classes which implements Collidable interface should return the
     * velocity of object after hitting the Collidable.
     *
     *
     * @param collisionPoint  the collision point.
     * @param currentVelocity the current velocity of hitting object.
     * @param hitter the hitting ball.
     * @return The velocity of object after hitting.
     */
     Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}