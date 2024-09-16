package GameEnvironment;
import biuoop.DrawSurface;
import Geometry.Line;
import Geometry.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Background3 class.
 */
public class BackGround3 implements Sprite {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int NUM_LINES = 200;
    private static final int LINE_LENGTH = 100;



    @Override
    public void drawOn(DrawSurface d) {
        List<Line> lines = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < NUM_LINES; i++) {
            int x1 = random.nextInt(SCREEN_WIDTH);
            int y1 = random.nextInt(SCREEN_HEIGHT);
            int x2 = x1 + LINE_LENGTH;
            int y2 = y1 + LINE_LENGTH;

            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);

            Line line = new Line(start, end);
            lines.add(line);
        }
        // Draw the lines
        for (Line line : lines) {
            int x1 = (int) line.start().getX();
            int y1 = (int) line.start().getY();
            int x2 = (int) line.end().getX();
            int y2 = (int) line.end().getY();

            d.setColor(generateRandomColor());
            d.drawLine(x1, y1, x2, y2);
        }

    }

    @Override
    public void timePassed() {

    }

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
}
