/**
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
 */
package Geometry;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Rectangle.
 */
public class Rectangle {
    /**
     * Upper-left corner point of rectangle.
     */
    private Point upperLeft;
    /**
     * Width of rectangle.
     */
    private double width;
    /**
     * Height of rectangle.
     */
    private double height;


    /**
     * Method delete duplicated points in given list. it returns the updated
     * list.
     *
     * @param points list of points.
     * @return the list after delete all duplicate points.
     */
    public List<Point> updatedlist(List<Point> points) {
        List<Point> list = new ArrayList<>();
        if (points.isEmpty()) {
            return null;
        }
        list.add(points.get(0));
        for (Point i : points) {
            int flag = 0;
            for (Point j : list) {
                if (i.equals(j)) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * Method construct rectangle with given point which will be used for
     * upper-left point of rectangle, and also dimensions of rectangle.
     *
     * @param upperLeft the upper left.
     * @param width     the width.
     * @param height    the height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Method returns upper right corner point of rectangle.
     *
     * @return upper-right point of rectangle.
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY());
    }

    /**
     * Method returns upper right corner point of rectangle.
     *
     * @return down-left corner point of rectangle.
     */
    public Point getDownLeft() {
        return new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height);
    }

    /**
     * Method returns down right corner point of rectangle.
     *
     * @return down-right corner point of rectangle.
     */
    public Point getDownRight() {
        return new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY() + this.height);
    }

    /**
     * Method calculate and returns list contains intersection points of a
     * given line with rectangle. if no such points exist then return empty
     * list.
     *
     * @param line line which rectangel is or not intersect with.
     * @return list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        Point p2 = new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height);
        Point p3 = new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY());
        Point p4 = new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY() + this.height);
        Line l1 = new Line(this.upperLeft, p2);
        Line l2 = new Line(this.upperLeft, p3);
        Line l3 = new Line(p2, p4);
        Line l4 = new Line(p3, p4);
        List<Line> listline = Arrays.asList(l1, l2, l3, l4);
        for (Line i : listline) {
            if (i.intersectionWith(line) != null) {
                list.add(i.intersectionWith(line));
            }

        }

        List<Point> newList = updatedlist(list);
        return newList;


    }


    /**
     * Method returns width of rectangle.
     *
     * @return width of rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Method returns height of rectangle.
     *
     * @return height of rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Method returns upper-left corner point of rectangle.
     *
     * @return upper-left corner point of rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}