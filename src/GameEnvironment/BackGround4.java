package GameEnvironment;

import biuoop.DrawSurface;

import java.awt.Color;


/**
 * The BackGround4 Class.
 */
public class BackGround4 implements Sprite {


    private static final int START_RADIUS = 10;


    /**
     * Generate random color color.
     *
     * @return the color
     */
    public static Color generateRandomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);

        return new Color(red, green, blue);
    }


    @Override
    public void drawOn(DrawSurface d) {
        // Draw the circles
        int centerX = 420;
        int centerY = 120;
        int radius = START_RADIUS;
        for (int i = 0; i < 400; i++) {
            d.setColor(generateRandomColor());
            d.drawCircle(centerX, centerY, radius);
            radius += (int) (Math.random() * 50);
        }
    }


    @Override
    public void timePassed() {

    }
}
