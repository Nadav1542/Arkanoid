/**
 *
 *
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
*/
package Geometry;

/**
 * Point class.
 */
public class Point {
    /**
     * x coordinate of point.
     */
    private double x;
    /**
     * y coordinate of point.
     */
    private double y;

    /**
     * constructor of instance point.
     *
     * @param x the x
     * @param y the y
     */
// constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     *
     * @param other the other
     * @return the double
     */
// distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double d1 = this.x - other.getX();
        double d2 = this.y - other.getY();
        return Math.sqrt((d1 * d1) + (d2 * d2));
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        double epsilon = 1e-10;
        if (other == null) {
            return false;
        }
        return Math.abs(this.x - other.getX()) < epsilon && Math.abs(this.y - other.getY()) < epsilon;
    }

    /**
     * Gets incline.
     *
     * @param other the other
     * @return the incline
     */
    public double getIncline(Point other) {
        double deltaY = this.y - other.getY();
        double deltaX = this.x - other.getX();
        return deltaY / deltaX;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }


    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Method prints point to user.
     *
     */
    public void pointtoString() {
        System.out.println("(" + this.x + "," + this.y + ")");
    }
}