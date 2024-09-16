package GameEnvironment;

import Geometry.Collidable;
import Geometry.Point;

/**
 * Collision info class.
 */
public class CollisionInfo {

    /**
     * point of collision.
     */
    private Point p;
    /**
     * The object which ball collide with.
     */
    private Collidable obj;


    /**
     * Method constructs instantiates of Collision info, containing point of
     * collision and the collidable object.
     *
     * @param p   Point of collision.
     * @param obj the Collidable object which the ball collide with.
     */
    public CollisionInfo(Point p, Collidable obj) {
        this.p = p;
        this.obj = obj;
    }


    /**
     * Method return Collision point.
     *
     * @return (Point) point of collision.
     */
    public Point collisionPoint() {
        return this.p;
    }

    /**
     * Method returns the collidable object.
     *
     * @return (Collidable) the object which the ball collide with.
     */
    public Collidable collisionObject() {
        return this.obj;
    }
}
