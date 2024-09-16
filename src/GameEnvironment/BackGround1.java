package GameEnvironment;

import biuoop.DrawSurface;


import java.awt.Color;

/**
 * The Background1 class.
 */
public class BackGround1 implements Sprite {


    /**
     * The constant WINDOW_WIDTH.
     */
    public static final int WINDOW_WIDTH = 800;
    /**
     * The constant WINDOW_HEIGHT.
     */
    public static final int WINDOW_HEIGHT = 600;


    /**
     * Method draws sky.
     *
     * @param surface the surface
     */
        public static void drawSky(DrawSurface surface) {
            Color skyColor1 = new Color(135, 206, 250);
            Color skyColor2 = new Color(30, 144, 255);
            surface.setColor(skyColor1);
            surface.fillRectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

            for (int y = 0; y < WINDOW_HEIGHT / 2; y++) {
                int r = skyColor1.getRed() + (skyColor2.getRed() - skyColor1.getRed()) * y / (WINDOW_HEIGHT / 2);
                int g = skyColor1.getGreen() + (skyColor2.getGreen() - skyColor1.getGreen()) * y / (WINDOW_HEIGHT / 2);
                int b = skyColor1.getBlue() + (skyColor2.getBlue() - skyColor1.getBlue()) * y / (WINDOW_HEIGHT / 2);
                surface.setColor(new Color(r, g, b));
                surface.drawLine(0, y, WINDOW_WIDTH, y);
            }
        }

    /**
     * Method draws clouds.
     *
     * @param surface the surface
     */
        public static void drawClouds(DrawSurface surface) {
            Color cloudColor = new Color(240, 240, 240);
            int cloudWidth = 150;
            int cloudHeight = 60;

            for (int i = 0; i < 5; i++) {
                int x = (i + 1) * WINDOW_WIDTH / 6;
                int y = WINDOW_HEIGHT / 4;
                surface.setColor(cloudColor);
                surface.fillOval(x, y, cloudWidth, cloudHeight);
            }
        }


        @Override
        public void drawOn(DrawSurface d) {
            // Draw the sky background
            drawSky(d);

            // Draw the clouds
            drawClouds(d);


        }

        @Override
        public void timePassed() {
            return;
        }

    }





