/**
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
 */

package Geometry;
import GameEnvironment.Sprite;
import GameEnvironment.CollisionInfo;
import GameEnvironment.GameEnvironment;
import GameEnvironment.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Ball class.
 */
public class Ball implements Sprite {

    private GameEnvironment ge;

    /**
     * center point of ball.
     */
    private Point center;
    /**
     * The Radius of ball.
     */
    private int radius;
    /**
     * Color of ball.
     */
    private Color color;
    /**
     * speed of ball.
     */
    private Velocity speed;


    /**
     * constructor instances of balls. default speed set to 0.
     *
     * @param center center point of ball.
     * @param r      radius of ball.
     * @param color  color of ball.
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.speed = new Velocity(0, 0);
        this.ge = new GameEnvironment();
    }

    /**
     * constructor instances of balls. default speed set to 0.
     *
     * @param x     x coordinate center of ball
     * @param y     y coordinate center of ball
     * @param r     radius of ball.
     * @param color color of ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.speed = new Velocity(0, 0);
        this.ge = new GameEnvironment();
    }

    /**
     * Gets game.
     *
     * @return the game
     */
    public GameEnvironment getGame() {
        return this.ge;
    }

    /**
     * returns x coordinate of center.
     *
     * @return (int) center x coordinate of ball.
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * returns y coordinate of center.
     *
     * @return (int) center y coordinate of ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * returns radius of ball.
     *
     * @return (int) The radius of ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * returns color of ball.
     *
     * @return (Color) color of ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Method draw ball in a given surface.
     *
     * @param surface surface for drawing on.
     */

    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(),
                this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(),
                this.radius);

    }

    /**
     * Method sets speed of ball.
     *
     * @param v The wanted speed.
     */
    public void setVelocity(Velocity v) {
        this.speed = v;
    }

    /**
     * Method sets speed of ball.
     *
     * @param dx speed in axis x.
     * @param dy speed in axis y.
     */
    public void setVelocity(double dx, double dy) {

        this.speed.setDx(dx);
        this.speed.setDy(dy);
    }

    /**
     * returns speed of ball.
     *
     * @return (Velocity) the speed of ball.
     */
    public Velocity getVelocity() {
        return this.speed;
    }

    /**
     * Method checks if ball should change direction movement in case of
     * hitting the surface corners.
     *
     * @param pstart position of up-left corner of frame
     * @param pend   position of down-right corner of frame.
     * @return (int) returns one number from the set {0,1,2,3,4}.
     * 0 - ball didn't hit corner.
     * 1 - ball hit up-left corner
     * 2 - ball hit up-right corner.
     * 3 - ball hit down-left corner.
     * 4 - ball hit down-right corner.
     */
    public int checkDirection(Point pstart, Point pend) {
        if ((this.getX() - this.radius) <= pstart.getX() && (this.getY() - this.radius <= pstart.getY())) {
            return 1;
        }
        if ((this.getX() + this.radius) >= pend.getX() && (this.getY() - this.radius <= pstart.getY())) {
            return 2;
        }
        if ((this.getX() - this.radius) <= pstart.getX() && (this.getY() + this.radius >= pend.getY())) {
            return 3;
        }
        if ((this.getX() + this.radius) >= pend.getX() && (this.getY() + this.radius >= pend.getY())) {
            return 4;
        }
        return 0;
    }


    /**
     * Method updates speed according to wanted direction.
     *
     * @param speed     current speed of ball.
     * @param direction wanted direction of ball.
     * @return (Velocity) returns new speed of ball according to given direction.
     */
    public Velocity replaceVelocity(Velocity speed, int direction) {
        //when hitting up-left corner.
        if (direction == 1) {
            if (speed.getDx() <= 0 && speed.getDy() <= 0) {
                speed.setDx(-speed.getDx());
                speed.setDy(-speed.getDy());
                return speed;
            }
            if (speed.getDx() <= 0 && speed.getDy() >= 0) {
                speed.setDx(-speed.getDx());
                return speed;
            }
            if (speed.getDx() >= 0 && speed.getDy() <= 0) {
                speed.setDy(-speed.getDy());
                return speed;
            }
            if (speed.getDx() >= 0 && speed.getDy() >= 0) {
                return speed;
            }
        }
        //when hitting up-right corner.
        if (direction == 2) {
            if (speed.getDx() <= 0 && speed.getDy() <= 0) {
                speed.setDy(-speed.getDy());
                return speed;
            }
            if (speed.getDx() <= 0 && speed.getDy() >= 0) {

                return speed;
            }
            if (speed.getDx() >= 0 && speed.getDy() <= 0) {
                speed.setDy(-speed.getDy());
                speed.setDx(-speed.getDx());
                return speed;
            }
            if (speed.getDx() >= 0 && speed.getDy() >= 0) {
                speed.setDx(-speed.getDx());
                return speed;
            }
        }
        //when hitting down-left corner.
        if (direction == 3) {
            if (speed.getDx() <= 0 && speed.getDy() <= 0) {
                speed.setDx(-speed.getDx());
                return speed;
            }
            if (speed.getDx() <= 0 && speed.getDy() >= 0) {
                speed.setDx(-speed.getDx());
                speed.setDy(-speed.getDy());
                return speed;
            }
            if (speed.getDx() >= 0 && speed.getDy() <= 0) {
                return speed;
            }
            if (speed.getDx() >= 0 && speed.getDy() >= 0) {
                speed.setDy(-speed.getDy());
                return speed;
            }
        }
        //when hitting down-right corner.
        if (direction == 4) {
            if (speed.getDx() <= 0 && speed.getDy() <= 0) {
                return speed;
            }
            if (speed.getDx() <= 0 && speed.getDy() >= 0) {
                speed.setDy(-speed.getDy());
                return speed;
            }
            if (speed.getDx() >= 0 && speed.getDy() <= 0) {
                speed.setDx(-speed.getDx());
                return speed;
            }
            if (speed.getDx() >= 0 && speed.getDy() >= 0) {
                speed.setDy(-speed.getDy());
                speed.setDx(-speed.getDx());
                return speed;
            }
        }
        return speed;
    }

    /**
     * Method checks cases of hitting walls of certain frame and returns
     * ball's next position respectively.
     *
     * @param pstart position of up-left corner of frame.
     * @param pend   position of down-right corner of frame.
     * @return (Point) returns new position of ball after implementing one step.
     */
    public Point checkStep(Point pstart, Point pend) {
        //in case of hitting left wall of frame.
        if (this.center.getX() - this.radius == pstart.getX()) {
            if (this.getVelocity().getDx() < 0) {
                this.setVelocity(-this.getVelocity().getDx(),
                        this.getVelocity().getDy());
            }
            return this.getVelocity().applyToPoint(this.center);
        }
        //in case of hitting right wall of frame.
        if (this.center.getX() + this.radius == pend.getX()) {
            if (this.getVelocity().getDx() > 0) {
                this.setVelocity(-this.getVelocity().getDx(),
                        this.getVelocity().getDy());
            }
            return this.getVelocity().applyToPoint(this.center);
        }
        //in case of hitting border on the top of the frame.
        if (this.center.getY() - this.radius == pstart.getY()) {
            if (this.getVelocity().getDy() < 0) {
                this.setVelocity(this.getVelocity().getDx(),
                        -this.getVelocity().getDy());
            }
            return this.getVelocity().applyToPoint(this.center);

        }
        //in case of hitting border on the bottom of the frame.
        if (this.center.getY() + this.radius == pend.getY()) {
            if (this.getVelocity().getDy() > 0) {
                this.setVelocity(this.getVelocity().getDx(),
                        -this.getVelocity().getDy());
            }
            return this.getVelocity().applyToPoint(this.center);
        }
        //for checking if next position of ball is beyond frame's borders.
        Point tmp = this.getVelocity().applyToPoint(this.center);
        //when next position is beyond right border, ball touch right border.
        if (tmp.getX() + this.radius > pend.getX()) {
            return new Point(pend.getX() - this.radius,
                    this.getY() + this.getVelocity().getDy());
        }
        //when next position is beyond left border, ball touch left border.
        if (tmp.getX() - this.radius < pstart.getX()) {
            return new Point(this.radius + pstart.getX(),
                    this.getY() + this.getVelocity().getDy());
        }
        //when next position is beyond upper border, ball touch upper border.
        if (tmp.getY() + this.radius > pend.getY()) {
            return new Point(this.getX() + this.getVelocity().getDx(),
                    pend.getY() - this.radius);
        }
        //when next position is beyond bottom border, ball touch bottom border.
        if (tmp.getY() - this.radius < pstart.getY()) {
            return new Point(this.getX() + this.getVelocity().getDx(),
                    this.radius + pstart.getY());
        }
        return tmp;
    }


    /**
     * Method updates center point of ball according to its speed in
     * surface of 200x200.
     */
    public void moveOneStep() {
        //In cases of hitting corners.
        if (this.checkDirection(new Point(0, 0), new Point(200, 200)) != 0) {
            this.setVelocity(this.replaceVelocity(this.getVelocity(),
                    this.checkDirection(new Point(0, 0), new Point(200, 200))));
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        this.center = checkStep(new Point(0, 0), new Point(200, 200));
    }

    /**
     * Method updates center point of ball according to its speed in
     * a given rectangle frame.
     *
     * @param startp point of up-left corner of the frame.
     * @param endp   point of down-right corner of the frame.
     */
    public void moveOneStep(Point startp, Point endp) {
        //In cases of hitting corners.
        if (this.checkDirection(startp, endp) != 0) {
            this.setVelocity(this.replaceVelocity(this.getVelocity(),
                    this.checkDirection(startp, endp)));
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        this.center = checkStep(startp, endp);
    }

    /**
     * Method checks if ball colliding with Collidable object and updates its
     * center point respectively.
     */
    public void moveOneStepC() {
        Point tmp = new Point(this.getX() + this.speed.getDx(),
                this.getY() + this.speed.getDy());
        Line l = new Line(this.center, tmp);
        CollisionInfo info = this.ge.getClosestCollision(l);
        //if no collision then move one step to (x+dx,y+dy).
        if (info == null) {
            this.center = tmp;
            return;
        }
        //In case of hitting left side of block.
        if (info.collisionPoint().getX() == info.collisionObject().getCollisionRectangle().getUpperLeft().getX()) {
            this.center =
                    new Point(info.collisionObject().getCollisionRectangle().getUpperLeft().getX() - this.radius,
                            l.findY(info.collisionObject().getCollisionRectangle().getUpperLeft().getX()
                                    - this.radius));
            this.setVelocity(info.collisionObject().hit(this,
                    info.collisionPoint(),
                    this.speed));
            return;
        }
        //In case of hitting right side of block.
        if (info.collisionPoint().getX() == info.collisionObject().getCollisionRectangle().getUpperRight().getX()) {
            this.center =
                    new Point(info.collisionObject().getCollisionRectangle().getUpperRight().getX() + this.radius,
                            l.findY(info.collisionObject().getCollisionRectangle().getUpperRight().getX()
                                    + this.radius));
            this.setVelocity(info.collisionObject().hit(this,
                    info.collisionPoint(),
                    this.speed));
            return;
        }
        //In case of hitting upper side of block.
        if (info.collisionPoint().getY() == info.collisionObject().getCollisionRectangle().getUpperLeft().getY()) {
            this.center =
                    new Point(l.findX(info.collisionObject().getCollisionRectangle().getUpperLeft().getY()
                            - this.radius),
                            info.collisionObject().getCollisionRectangle().getUpperLeft().getY() - this.radius);
            this.setVelocity(info.collisionObject().hit(this,
                    info.collisionPoint(),
                    this.speed));
            return;
        }
        //In case of hitting bottom side of block.
        if (info.collisionPoint().getY()
                == info.collisionObject().getCollisionRectangle().getDownLeft().getY()) {
            this.center =
                    new Point(l.findX(info.collisionObject().getCollisionRectangle().getDownLeft().getY()
                            + this.radius),
                            info.collisionObject().getCollisionRectangle().getDownLeft().getY() + this.radius);
            this.setVelocity(info.collisionObject().hit(this,
                    info.collisionPoint(),
                    this.speed));
        }
    }

    /**
     * Method tells ball that time has passed and activates moveOneStepC().
     *
     */
    public void timePassed() {
        this.moveOneStepC();
    }

    /**
     * Addto game.
     *
     * @param game the game
     */
    public void addtoGame(GameLevel game) {
        this.setVelocity(-3, -3);
        this.setGameEnvironment(game.getEnvironment());
        game.addSprite(this);
        game.addnumBalls(1);
    }

    /**
     * Method removes ball from the game.
     *
     * @param game the game for removing balls from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removenumBalls(1);
    }

    /**
     * Set game environment.
     *
     * @param ge the ge
     */
    public void setGameEnvironment(GameEnvironment ge) {
        this.ge = ge;
    }

}
