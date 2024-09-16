/**
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
 */
package Geometry;

import GameEnvironment.Sprite;
import biuoop.DrawSurface;
import GameEnvironment.GameLevel;
import biuoop.KeyboardSensor;

/**
 * Paddle class. Controlled by user in the game.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;

    private Block paddleBlock;

    private int speed;


    /**
     * Gets speed.
     *
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Method move the paddle to left 15 units. method activated when left
     * key pressed.
     */
    public void moveLeft() {
        if (this.getCollisionRectangle().getUpperLeft().getX() <= 5) {
            return;
        }
        Point upperleft =
                new Point(this.getCollisionRectangle().getUpperLeft().getX() - this.getSpeed(),
                        this.getCollisionRectangle().getUpperLeft().getY());
        Block newBlock = new Block(upperleft,
                this.getCollisionRectangle().getWidth(),
                this.getCollisionRectangle().getHeight(),
                this.paddleBlock.getColor());
        this.paddleBlock = newBlock;
    }

    /**
     * Method move the paddle to right 15 units. method activated when right
     * key pressed.
     */
    public void moveRight() {
        if (this.getCollisionRectangle().getUpperRight().getX() >= 790) {
            return;
        }
        Point upperleft =
                new Point(this.getCollisionRectangle().getUpperLeft().getX() + this.getSpeed(),
                        this.getCollisionRectangle().getUpperLeft().getY());
        Block newBlock = new Block(upperleft,
                this.getCollisionRectangle().getWidth(),
                this.getCollisionRectangle().getHeight(),
                this.paddleBlock.getColor());
        this.paddleBlock = newBlock;
    }


    /**
     * Method creates paddle with given block and keyboardsensor.
     *
     * @param keyboardSensor the keyboard sensor
     * @param paddleBlock    block which determines size of paddle.
     * @param speed          speed of paddle.
     */
    public Paddle(KeyboardSensor keyboardSensor, Block paddleBlock, int speed) {
        this.keyboard = keyboardSensor;
        this.paddleBlock = paddleBlock;
        this.speed = speed;
    }

    /**
     * Method activates move left and right for paddle when user press left
     * and right key respectively.
     */
    public void timePassed() {
        if (keyboard.isPressed(this.keyboard.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
            this.moveRight();
        }

    }

    /**
     * Method draw paddle on give surface.
     *
     * @param d the surface for drawing on.
     */

    public void drawOn(DrawSurface d) {
        d.setColor(this.paddleBlock.getColor());
        d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());

    }

    /**
     * Method return form of the collidable paddle.
     *
     * @return (Rectangle) rectangle form of paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleBlock.getCollisionRectangle();
    }

    /**
     * Method return velocity after hitting paddle.
     *
     * @param currentVelocity velocity of hitting.
     * @param collisionPoint  point of collision on Collidable.
     * @param hitter          hitting ball.
     * @return (Velocity) velocity of object after hitting the paddle.
     */

    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double sizeofregion = this.getCollisionRectangle().getWidth() / 5;
        //calculating value of speed in direction
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                + Math.pow(currentVelocity.getDy(), 2));
        double collisonX = collisionPoint.getX();
        double collisonY = collisionPoint.getY();
        if (collisionPoint == null) {
            return currentVelocity;
        }
        //hitting region 1 in paddle (y coordinate is 570)
        if (collisonX < (this.getCollisionRectangle().getUpperLeft().getX()
                + sizeofregion) && collisonY == 570) {
            double angle =
                    Math.atan(currentVelocity.getDx() / currentVelocity.getDy());
            Velocity v = Velocity.fromAngleAndSpeed(300 + angle, speed);
            return v;
        }
        //hitting region 2 in paddle (y coordinate is 570)
        if (collisonX >= (this.getCollisionRectangle().getUpperLeft().getX() + sizeofregion)
                && collisonX < (this.getCollisionRectangle().getUpperLeft().getX()
                + 2 * sizeofregion) && collisonY == 570) {
            double angle =
                    Math.atan(currentVelocity.getDx() / currentVelocity.getDy());
            Velocity v = Velocity.fromAngleAndSpeed(330 + angle, speed);
            return v;
        }
        //hitting region 3 in paddle (y coordinate is 570)
        if (collisonX >= (this.getCollisionRectangle().getUpperLeft().getX() + 2 * sizeofregion)
                && collisonX < (this.getCollisionRectangle().getUpperLeft().getX() + 3 * sizeofregion)
                && collisonY == 570) {

            Velocity v = new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy());
            //Velocity v = Velocity.fromAngleAndSpeed(0,speed);
            return v;
        }
        //hitting region 4 in paddle (y coordinate is 570)
        if (collisonX >= (this.getCollisionRectangle().getUpperLeft().getX() + 3 * sizeofregion)
                && collisonX < this.getCollisionRectangle().getUpperLeft().getX() + 4 * sizeofregion
                && collisonY == 570) {
            double angle =
                    Math.atan(currentVelocity.getDx() / currentVelocity.getDy());
            Velocity v = Velocity.fromAngleAndSpeed(30 + angle, speed);
            return v;
        }
        //hitting region 5 in paddle (y coordinate is 570)
        if (collisonX >= (this.getCollisionRectangle().getUpperLeft().getX() + 4 * sizeofregion)
                && collisonX <= this.getCollisionRectangle().getUpperLeft().getX() + 5 * sizeofregion
                && collisonY == 570) {
            double angle =
                    Math.atan(currentVelocity.getDx() / currentVelocity.getDy());
            Velocity v = Velocity.fromAngleAndSpeed(60 + angle, speed);
            return v;
        }
        if (collisionPoint.getY() == 585) {
            currentVelocity.setDy(-Math.abs(currentVelocity.getDy()));
            return currentVelocity;
        }
        //if no hitting in upper side of block, then behave like Colidable.
        return ((Collidable) this.paddleBlock).hit(hitter, collisionPoint,
                currentVelocity);
    }

    /**
     * Method adding paddle to a given game.
     *
     * @param g the game which paddle added to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);

    }
}