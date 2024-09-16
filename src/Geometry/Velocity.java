/**
 *
 *
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
*/
package Geometry;
/**
 * Velocity class. Velocity specifies the change in position on the `x` and the `y` axes.
 */

public class Velocity {
    /**
     * presents change in position on the 'x' axis.
     */
    private double dx;
    /**
     * presents change in position on the 'y' axis.
     */
    private double dy;

    /**
     * constructor instance of velocity.
     *
     * @param dx change in position at 'x' axis.
     * @param dy change in position at 'y' axis.
     */

    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Method converting speed from angle & speed into changes in positions at
     * axes - dx and dy.
     *
     * @param angle direction of movement.
     * @param speed change in position in certain direction.
     * @return (Velocity) return speed in terms of dx and dy.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleRad = ((angle - 90) / 180) * Math.PI;
        double dx = Math.cos(angleRad) * speed;
        double dy = Math.sin(angleRad) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Method updates change in position on 'x' axis.
     *
     * @param dx wanted position change in 'x' axis.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Method updates change in position on 'y' axis.
     *
     * @param dy wanted position change in 'y' axis.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * returns change in position on 'x' axis.
     *
     * @return (double) The change in position on 'x' axis.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * returns change in position on 'y' axis.
     *
     * @return (double) The change in position on 'y' axis.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p current point (x,y)
     * @return (Point) returns updated point (x+dx,y+dy)
     */
    public Point applyToPoint(Point p) {
        Point p1 = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return p1;
    }


}