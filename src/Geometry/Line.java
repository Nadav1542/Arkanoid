/**
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
 */
package Geometry;

/**
 * Line class.
 */
public class Line {
    /**
     * Start point of line.
     */
    private Point start;
    /**
     * End point of line.
     */
    private Point end;

    private static final int VERTICAL = 1;
    private static final int HORIZONTAL = 2;








    /**
     * constructor of line.
     *
     * @param start starting point of line
     * @param end   ending point of line
     */
    public Line(Point start, Point end) {
        /*if (start.equals(end)) {
            throw new RuntimeException("line can not be defined by two equal "
                    + "points");
        }

         */
        this.start = start;
        this.end = end;
    }

    /**
     * constructor of line.
     *
     * @param x1 x coordinate of starting point
     * @param y1 y coordinate of starting point
     * @param x2 x coordinate of ending point
     * @param y2 y coordinate of ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        /*if (x1 == x2 && y1 == y2) {
            throw new RuntimeException("line can not be defined by two equal "
                    + "points");
        }

         */
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Method calculate length of line.
     *
     * @return (double) the distance between starting and ending point.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Method calculate middle point of a given line.
     *
     * @return (Point) the middle point of a given line.
     */
    public Point middle() {
        double xm = (this.start.getX() + this.end.getX()) / 2;
        double ym = (this.start.getY() + this.end.getY()) / 2;
        return new Point(xm, ym);
    }

    /**
     * Method returns the start point of line.
     *
     * @return (Point) starting point of line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Method returns end point ot line.
     *
     * @return (Point) ending point of line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Method check if y coordinate in range of y coordinate of starting and
     * ending point of line.
     *
     * @param y y coordinate of point
     * @return (@ code true) if coordinate is in range (@code false) otherwise.
     */
    public boolean yRange(double y) {

        return y >= Math.min(this.start().getY(), this.end().getY())
                && y <= Math.max(this.start().getY(), this.end().getY());
    }

    /**
     * Method finding x coordinate of point by line and y coordinate.
     *
     * @param y the y coordinate of wanted point
     * @return (double) x coordinate of point
     */
    public double findX(double y) {
        double m = this.end.getIncline(this.start);
        return (y - this.start.getY()) / m + this.start.getX();
    }


    /**
     * Method check if x coordinate in range of x coordinate of starting and
     * ending point.
     *
     * @param x the x coordinate of point
     * @return (@ code true) if coordinate is in range (@code false) otherwise.
     */
    public boolean xRange(double x) {
        return x >= Math.min(this.start().getX(), this.end().getX())
                && x <= Math.max(this.start().getX(), this.end().getX());
    }

    /**
     * Method finding y coordinate of point by line and x coordinate.
     *
     * @param x the x coordinate of wanted point
     * @return (double) y coordinate of point
     */
    public double findY(double x) {
        double m = this.end.getIncline(this.start);
        double y = m * x + (this.start.getY() - m * this.start.getX());
        return y;
    }

    /**
     * Method check if line is intersecting with other line.
     *
     * @param other other line.
     * @return (@ code true) if two lines have one intersecting point,
     * (@code false) if they're not intersecting or have infinite intersection points.
     */
    public boolean isIntersecting(Line other) {
        Line l1 = new Line(this.start, this.end);
        if (l1.equals(other) || other.equals(l1)) {
            return true;
        }
        //when both lines are not vertical or horizontal
        if ((!l1.specialCase()) && (!other.specialCase())) {
            double m1 = l1.getIncline();
            double m2 = other.getIncline();
            if (m1 != m2) {
                double y2 = other.start().getY();
                double y1 = this.end.getY();
                double x2 = other.start().getX();
                double x1 = this.end.getX();
                double ix = (m1 * x1 - m2 * x2 + y2 - y1) / (m1 - m2);
                double iy = m1 * (ix - x1) + y1;
                Point inP = new Point(ix, iy);
                if (intersectedge(other.start(), other.end(), this.start,
                        this.end) != null) {
                    return true;
                }
                //check if intersection point on both lines.
                return (l1.pointOn(inP) && other.pointOn(inP));
            }
            //if inclines are equals.
            return l1.pointOn(other.start()) || l1.pointOn(other.end());
        }
        //if only first line is vertical or horizontal.
        if ((!l1.specialCase()) && (other.specialCase())) {
            if (other.vertOrhori() == VERTICAL) {
                if (l1.xRange(other.start().getX())) {
                    return (other.yRange(l1.findY(other.start().getX())));
                }
            }
            if (other.vertOrhori() == HORIZONTAL) {
                if (l1.yRange(other.start().getY())) {
                    return (other.xRange(l1.findX(other.start().getY())));
                }
            }
        }
        //if second line is vertical or horizontal.
        if ((l1.specialCase()) && (!other.specialCase())) {
            if (l1.vertOrhori() == VERTICAL) {
                if (other.xRange(l1.start().getX())) {
                    return (l1.yRange(other.findY(l1.start().getX())));
                }
            }
            if (l1.vertOrhori() == HORIZONTAL) {
                if (other.yRange(l1.start().getY())) {
                    return (l1.xRange(other.findX(l1.start().getY())));
                }
            }
        }
        //if both lines are verticals or horizontals.
        if ((l1.specialCase()) && (other.specialCase())) {
            //if both are verticals.
            if (l1.vertOrhori() == VERTICAL && other.vertOrhori() == VERTICAL) {
                if (isEqual(l1.start().getX(), other.start().getX())) {
                    return Math.min(l1.start().getY(), l1.end().getY())
                            <= Math.max(other.start().getY(), other.end().getY())
                            || Math.min(other.start().getY(), other.end().getY())
                            >= Math.max(l1.start().getY(), l1.end().getY());
                }
                return false;
            }
            //if both are horizontals.
            if (l1.vertOrhori() == HORIZONTAL && other.vertOrhori() == HORIZONTAL) {
                if (isEqual(l1.start().getY(), other.start().getY())) {
                    return Math.max(l1.start().getX(), l1.end().getX())
                            >= Math.min(other.start().getX(), other.end().getX())
                            && Math.max(other.start().getX(), other.end().getX())
                            >= Math.min(l1.start().getX(), l1.end().getX());
                }
                return false;
            }
            //if first is vertical and second is horizontal.
            if (l1.vertOrhori() == VERTICAL && other.vertOrhori() == HORIZONTAL) {
                if ((other.start().getY() <= Math.max(l1.start().getY(),
                        l1.end().getY())) && (other.start().getY() >= Math.min(l1.start().getY(),
                        l1.end().getY()))) {
                    return Math.min(other.start().getX(), other.end().getX()) <= l1.start().getX()
                            && Math.max(other.start().getX(), other.end().getX()) >= l1.start().getX();
                }
                return false;
            }
            //if first is horizontal and second is vertical.
            if (l1.vertOrhori() == HORIZONTAL && other.vertOrhori() == VERTICAL) {
                if (l1.start().getY() <= Math.max(other.start().getY(),
                        other.end().getY()) && l1.start().getY() >= Math.min(other.start().getY(),
                        other.end().getY())) {
                    return Math.min(l1.start().getX(), l1.end().getX()) <= other.start().getX()
                            && Math.max(l1.start().getX(), l1.end().getX()) >= other.start().getX();

                }
            }
        }
        return false;
    }

    /**
     * Method checks if line is vertical or horizontal.
     *
     * @return (@ code true) if line is vertical or horizontal. (@code false) if not.
     */
    public boolean specialCase() {
        return isEqual(this.start.getX(), this.end.getX()) || isEqual(this.start.getY(), this.end.getY());
    }

    /**
     * Method returns calculate if line is vertical or horizontal.
     *
     * @return (int) 1 for vertical and 2 for horizontal.
     */
    public int vertOrhori() {
        if (isEqual(this.start.getX(), this.end.getX())) {
            return VERTICAL;
        }
        if (isEqual(this.start.getY(), this.end.getY())) {
            return HORIZONTAL;
        }
        return 0;
    }


    /**
     * Method calculate incline of line.
     *
     * @return (double) incline of line.
     */
    public double getIncline() {
        return (this.start.getY() - this.end.getY()) / ((this.start.getX() - this.end.getX()));
    }

    /**
     * Method tells if difference between two numbers is small enough, so they can be equals.
     *
     * @param d1 first number
     * @param d2 second number
     * @return (@ code true) if two numbers considers as equals.
     * (@code false) if two numbers are not considers as equal.
     */
    public static boolean isEqual(double d1, double d2) {
        double epsilon = 1e-10;
        return Math.abs(d1 - d2) < epsilon;
    }


    /**
     * Method tells if 4 givens points are equals, which in our case mostly
     * represent the edges point of lines.
     *
     * @param p1 point one
     * @param p2 point two
     * @param p3 point three
     * @param p4 point four
     * @return (Point) equality point. if equality has not found, then null.
     */
    static Point intersectedge(Point p1, Point p2, Point p3, Point p4) {
        if (p1.equals(p2) || p1.equals(p3) || p1.equals(p4)) {
            return p1;
        }
        if (p2.equals(p3) || p2.equals(p4)) {
            return p2;
        }
        if (p3.equals(p4)) {
            return p3;
        }
        return null;
    }

    /**
     * Method return intersection point if it is the only one. if lines have
     * infinite or none intersections point, return null.
     *
     * @param other the line to calculate intersection with.
     * @return (Point) intersection point, if line doesn't intersect or have  infinite, return null.
     */
    public Point intersectionWith(Line other) {
        Line l1 = new Line(this.start, this.end);
        double m1 = l1.getIncline();
        double m2 = other.getIncline();
        if ((!l1.specialCase()) && (!other.specialCase())) {
            if (l1.isIntersecting(other)) {
                if (l1.equals(other)) {
                    return null;
                }
                if (m1 != m2) {
                    double y2 = other.start().getY();
                    double y1 = this.start.getY();
                    double x2 = other.start().getX();
                    double x1 = this.start.getX();
                    double ix = (m1 * x1 - m2 * x2 + y2 - y1) / (m1 - m2);
                    double iy = m1 * ix - m1 * x1 + y1;
                    return new Point(ix, iy);
                }
                return intersectedge(l1.start(), l1.end(), other.start(),
                        other.end());
            }
            return null;
        }
        if ((!l1.specialCase()) && (other.specialCase())) {
            if (other.vertOrhori() == VERTICAL) {
                if (l1.isIntersecting(other)) {
                    if (l1.equals(other)) {
                        return null;
                    }
                    return new Point(other.start().getX(),
                            l1.findY(other.start().getX()));
                }
                return null;
            }
            if (other.vertOrhori() == HORIZONTAL) {
                if (l1.isIntersecting(other)) {
                    if (l1.equals(other)) {
                        return null;
                    }
                    return new Point(
                            l1.findX(other.start().getY()),
                            other.start().getY());
                }
                return null;
            }
        }
        if ((l1.specialCase()) && (!other.specialCase())) {
            if (l1.vertOrhori() == VERTICAL) {
                if (l1.isIntersecting(other)) {
                    if (l1.equals(other)) {
                        return null;
                    }
                    return new Point(l1.start().getX(),
                            other.findY(l1.start().getX()));
                }
                return null;
            }
            if (l1.vertOrhori() == HORIZONTAL) {
                if (l1.isIntersecting(other)) {
                    if (l1.equals(other)) {
                        return null;
                    }
                    return new Point(
                            other.findX(l1.start().getY()),
                            l1.start().getY());
                }
                return null;
            }
        }
        if ((l1.specialCase()) && (other.specialCase())) {
            if (l1.isIntersecting(other)) {
                if (l1.equals(other)) {
                    return null;
                }
                if (l1.vertOrhori() == VERTICAL && other.vertOrhori() == HORIZONTAL) {
                    return new Point(l1.start().getX(), other.start().getY());
                }
                if (l1.vertOrhori() == HORIZONTAL && other.vertOrhori() == VERTICAL) {
                    return new Point(other.start().getX(),
                            l1.start().getY());
                }
                if (l1.vertOrhori() == HORIZONTAL && other.vertOrhori() == HORIZONTAL) {
                    if (Math.max(l1.start().getX(), l1.end().getX())
                            == Math.min(other.start().getX(), other.end().getX())
                            || Math.max(other.start().getX(),
                            other.end().getX()) == Math.min(l1.start().getX(), l1.end().getX())) {
                        return (intersectedge(l1.start(), l1.end(), other.start(),
                                other.end()));
                    }
                    return null;
                }
                if (l1.vertOrhori() == VERTICAL && other.vertOrhori() == VERTICAL) {
                    if (Math.max(l1.start().getY(), l1.end().getY())
                            == Math.min(other.start().getY(), other.end().getY())
                            || Math.max(other.start().getY(),
                            other.end().getY()) == Math.min(l1.start().getY(), l1.end().getY())) {
                        return (intersectedge(l1.start(), l1.end(), other.start(),
                                other.end()));
                    }
                    return null;
                }

            }
            return null;
        }
        return null;
    }
    /**
     * Method return closest intersection point with given rectangle  to start
     * of the line.
     *
     *
     *
     * @param rect the rectangle to calculate intersection with.
     * @return (Point) intersection point, if line doesn't intersect, return
     * null.
     */

    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line l = new Line(this.start, this.end);
        if (rect.intersectionPoints(l) == null) {
            return null;
        }
        if (rect.intersectionPoints(l).size() == 1) {
            return rect.intersectionPoints(l).get(0);
        }
        if (this.start().distance(rect.intersectionPoints(l).get(0))
                <= this.start().distance(rect.intersectionPoints(l).get(1))) {
            return rect.intersectionPoints(l).get(0);
        }
        return rect.intersectionPoints(l).get(1);
    }

    /**
     * Method return true if point is located on the line, between starting
     * and ending points.
     *
     * @param p the checked point
     * @return (boolean) (@code true) if point in range of starting and ending point (@code false) otherwise.
     */
    public boolean pointOn(Point p) {
        double m1 = this.start.getIncline(this.end);
        double b = this.start.getY() - m1 * this.start.getX();

        double maxY = Math.max(this.start.getY(), this.end.getY());
        double minY = Math.min(this.start.getY(), this.end.getY());
        double maxX = Math.max(this.start.getX(), this.end.getX());
        double minX = Math.min(this.start.getX(), this.end.getX());
        if (p.getY() <= maxY && p.getY() >= minY) {
            return p.getX() <= maxX && p.getX() >= minX;
        }
        return false;
    }

    /**
     * Method return true if two lines are equals.
     *
     * @param other line for comparing equality.
     * @return (boolean) (@code true) if lines are equal, (@code false) otherwise.
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start()) && this.end.equals(other.end())) {
            return true;
        }
        return this.start.equals(other.end()) && this.end.equals(other.start());
    }
}