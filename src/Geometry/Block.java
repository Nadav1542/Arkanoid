/**
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
 */
package Geometry;

import java.awt.Color;

import GameEnvironment.HitListener;
import GameEnvironment.HitNotifier;
import GameEnvironment.Sprite;
import biuoop.DrawSurface;

import java.util.List;
import java.util.ArrayList;

/**
 * Class Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    /**
     * Rectangle form of block.
     */
    private Rectangle rectangle;
    /**
     * Color of block.
     */
    private Color color;

    /**
     * Lists of listeners.
     */
    private List<HitListener> hitListeners;

    /**
     * Method construct block instance with given upper-left corner point of
     * block, width,height and color.
     *
     * @param upperLeft upper-left corner point
     * @param width     width of rectangle
     * @param height    height of rectangle
     * @param color     color of block
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
    }

    /**
     * Method returns hit listeners.
     *
     * @return the hit listeners
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }

    /**
     * Method sets hit listeners.
     *
     * @param hitListeners list of listeners for setting.
     */
    public void setHitListeners(List<HitListener> hitListeners) {
        this.hitListeners = hitListeners;
    }


    @Override
    public void addHitListener(HitListener hl) {
        if (this.hitListeners == null) {
            this.hitListeners = new ArrayList<>();
        }
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Method tells block what to do when time passed. No use in this stage
     * of project.
     */
    public void timePassed() {
        return;
    }

    /**
     * Method draw block on give surface.
     *
     * @param surface the surface for drawing on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * Method returns color of block.
     *
     * @return the color of block
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Method construct instance of block with a given rectangle and color.
     *
     * @param rectangle rectangle form of block.
     * @param color     color of block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * Method return form of the collidable paddle.
     *
     * @return (Rectangle) rectangle form of block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }


    /**
     * Method calls listeners and activates relevant method..
     *
     * @param hitter ball which activates the listener.
     */
    private void notifyHit(Ball hitter) {
        if (this.hitListeners != null) {
            // Make a copy of the hitListeners before iterating over them.
            List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
            // Notify all listeners about a hit event:
            for (HitListener hl : listeners) {
                hl.hitEvent(this, hitter);
            }
        }
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        //in case of hitting corners.
        if (collisionPoint.equals(this.rectangle.getUpperRight())
                || collisionPoint.equals(this.rectangle.getUpperLeft())
                || collisionPoint.equals(this.rectangle.getDownLeft())
                || collisionPoint.equals(this.rectangle.getDownRight())) {
            currentVelocity.setDx(-currentVelocity.getDx());
            currentVelocity.setDy(-currentVelocity.getDy());
            this.notifyHit(hitter);
            return currentVelocity;
        }
        //in case of hitting left and right bounds of block.
        if (collisionPoint.getX() == this.rectangle.getUpperLeft().getX()
                || collisionPoint.getX() == this.rectangle.getUpperRight().getX()) {
            currentVelocity.setDx(-currentVelocity.getDx());
            this.notifyHit(hitter);
            return currentVelocity;
        }
        //in case of hitting up and down bounds of block.
        if (collisionPoint.getY() == this.rectangle.getUpperLeft().getY()
                || collisionPoint.getY() == this.rectangle.getDownRight().getY()) {
            currentVelocity.setDy(-currentVelocity.getDy());
            this.notifyHit(hitter);
            return currentVelocity;
        }
        return currentVelocity;
    }
}

