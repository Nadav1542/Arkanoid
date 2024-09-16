/**
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
 */
package GameEnvironment;
import Geometry.Collidable;
import Geometry.Point;
import Geometry.Line;
import java.util.ArrayList;
import java.util.List;

/**
 * The Game environment class.
 */
public class GameEnvironment {

    /**
     * list of objects which implements Collidable interface.
     */
    private List<Collidable> list = new ArrayList<>();


    /**
     * Method adding Collidable object to list of collidable in the game.
     *
     * @param c the collidable object for adding.
     */
    public void addCollidable(Collidable c) {
        this.list.add(c);
    }




    /**
     * Method removes collidable.
     *
     * @param c the Collidable for removing.
     */
    public void removeCollidable(Collidable c) {
        this.list.remove(c);
    }

    /**
     * Method calculate collison info of ball with block in the game. the
     * collision info contains collision point and form collidable.
     *
     * @param trajectory line from starting point fo movement to ending point.
     * @return (CollisionInfo) CollisionInfo of object with collidable
     */

    public CollisionInfo getClosestCollision(Line trajectory) {
        //list of collision points.
        List<Point> listofP = new ArrayList<>();
        //list for collidable objects in collision.
        List<Collidable> listofC = new ArrayList<>();
        for (Collidable i : this.list) {
            if (i.getCollisionRectangle().intersectionPoints(trajectory) != null) {
                Point p =
                        trajectory.closestIntersectionToStartOfLine(i.getCollisionRectangle());
                listofP.add(p);
                listofC.add(i);
            }
        }
        if (listofP.size() > 0) {
            int index = cloestCollision(listofP, trajectory.start());
            CollisionInfo info = new CollisionInfo(listofP.get(index - 1),
                    listofC.get(index - 1));
            return info;
        }
        return null;
    }

    /**
     * Method return index of closest point from list  to a given point.
     *
     * @param list  list of points.
     * @param start starting point.
     * @return (int) index of closest point in the list.
     */
    public static int cloestCollision(List<Point> list, Point start) {
        double min = list.get(0).distance(start);
        int minI = 1;
        for (Point j : list) {
            if (j.distance(start) < min) {
                min = j.distance(start);
                minI = list.indexOf(j);
            }
        }
        return minI;
    }

}