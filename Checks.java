import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.Random;

/**
 * The type Checks.
 */
public class Checks {

    /**
     * Draw random line 1.
     */
    public void drawRandomLine1() {
        Line[] arr = new Line[23];
        arr[0] = new Line(50, 240, 50, 280);
        arr[1] = new Line(50, 280, 200, 280);
        arr[2] = new Line(50, 240, 200, 280);
        arr[3] = new Line(200, 200, 200, 230);
        arr[4] = new Line(200, 230, 200, 250);
        arr[5] = new Line(200, 170, 200, 200);
        arr[6] = new Line(200, 250, 200, 280);
        arr[7] = new Line(50, 130, 178, 164);
        arr[8] = new Line(178, 164, 200, 170);
        arr[9] = new Line(230, 150, 230, 250);
        arr[10] = new Line(50, 130, 300, 100);
        arr[11] = new Line(20, 30, 178, 164);
        arr[12] = new Line(250, 270, 250, 50);
        arr[13] = new Line(0, 207, 400, 207);
        arr[14] = new Line(250, 50, 300, 50);
        arr[15] = new Line(300, 50, 340, 50);
        arr[16] = new Line(340, 50, 380, 50);
        arr[17] = new Line(125, 45, 178, 84);
        arr[18] = new Line(178, 84, 200, 90);
        arr[19] = new Line(260, 110, 310, 150);
        arr[20] = new Line(310, 150, 360, 190);
        arr[21] = new Line(260, 290, 310, 250);
        arr[22] = new Line(310, 250, 360, 210);

        GUI gui = new GUI("title", 700, 700);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();


        DrawSurface d = gui.getDrawSurface();
        for (int i = 0; i < 23; i++) {
            drawLine(arr[i], d);
            //middle point.
            drawMiddlePoint(arr[i], d);
        }
        for (int i = 0; i < 23; i++) {
            for (int j = i + 1; j < 23; j++) {
                //interaction point.
                drawIntersectionPoint(arr[i], arr[j], d);
            }
        }
        gui.show(d);

    }


    /**
     * Draw line.
     *
     * @param line the line
     * @param d    the d
     */
    public void drawLine(Line line, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) line.start().getX(), (int) line.start().getY(),
                (int) line.end().getX(), (int) line.end().getY());

    }


    /**
     * Draw middle point.
     *
     * @param line the line
     * @param d    the d
     */
    public void drawMiddlePoint(Line line, DrawSurface d) {
        Point middle = line.middle();
        d.setColor(Color.BLUE);
        d.fillCircle((int) middle.getX(), (int) middle.getY(), 3);
    }

    /**
     * Draw intersection point.
     *
     * @param l1 the l 1
     * @param l2 the l 2
     * @param d  the d
     */
    public void drawIntersectionPoint(Line l1, Line l2, DrawSurface d) {
        // check if there is an intersection point.
        Point intersection = l1.intersectionWith(l2);
        // draw if there is intersection point.
        if (intersection != null) {
            d.setColor(Color.RED);
            d.fillCircle((int) intersection.getX(),
                    (int) intersection.getY(), 3);

        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Random rand = new Random();
        int r = Integer.parseInt(args[0]);
        Checks example = new Checks();
        GUI gui = new GUI("title", 700, 700);
        DrawSurface surface = gui.getDrawSurface();

        //double x1 =
        //        rand.nextDouble((200 - r) - (100 + r)) + (100 + r);
        //double y1 =
          //      rand.nextDouble((200 - r) - (100 + r)) + (100 + r);
        Ball b1 = new Ball(170,170,r,Color.green);
        if (r >= 50) {
            b1.setVelocity(1, 1); //default speed for big balls.
        }
        b1.setVelocity(50 / r, 50 / r);
        //example.drawRandomLine1();
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        while(true){
            b1.moveOneStep(new Point(100,100),new Point(200,200));
            surface = gui.getDrawSurface();
            surface.setColor(Color.gray);
            surface.fillRectangle(100, 100, 100, 100);
            b1.drawOn(surface);
            gui.show(surface);
            sleeper.sleepFor(50);
        }
    }
}

/*import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

public class Checks {
    static private void drawAnimation() {
        GUI gui = new GUI("title",200,200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        java.util.Random rand = new java.util.Random();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            Ball ball = new Ball(rand.nextInt(200), rand.nextInt(200), 30, java.awt.Color.BLACK);
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title",200,200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
    static private void drawChecks() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Circles Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        Line[] larray = new Line[10];
        for (int i = 0; i < 10; ++i) {
            int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
            int x2 = rand.nextInt(400) + 1; // get integer in 5,10,15,20
            int y2 = rand.nextInt(300) + 1; // get integer in 5,10,15,20
            Line l = new Line(x1, y1, x2, y2);
            larray[i] = l;
            Point pm = new Point(l.middle().getX(), l.middle().getY());
            d.setColor(Color.BLACK);
            d.drawLine(x1, y1, x2, y2);
            d.setColor(Color.BLUE);
            d.fillCircle((int) pm.getX(), (int) pm.getY(), 3);
        }
        d.setColor(Color.GREEN);
        Line l1 = new Line(0,0,0,4);
        Line l2 = new Line(3,5,-1,0);
        d.drawLine(2,2,3,3);
        d.drawLine (1,1,5,5);
        boolean pi = l1.isIntersecting(l2);
        System.out.println(l1.intersectionWith(l2).getY());
        System.out.println(pi);


        gui.show(d);
    }

    public static void main(String[] args) {
        /*Checks example = new Checks();
        example.drawChecks();
        */
        //Point center = new Point(250,250);
        //drawAnimation(center,20,20);
        //drawAnimation();
 //       Line l1 = new Line(151.94528, 65.76442,54.76838, 16.41678);
   //     Line l2 = new Line(102.04805, 77.17004,119.96,49.52);
     //   Point intersectL1L2 = l1.intersectionWith(l2);
       // System.out.println(intersectL1L2.x);
        //System.out.println(intersectL1L2.y);
        //System.out.println(l1.middle().x);
        //System.out.println(l1.middle().y);
        //Velocity v1 = new Velocity(3.5, 6.7);
        //Point p3 = new Point(-3.5, -7.8);
        //System.out.println(v1.applyToPoint(p3).x);
        //System.out.println(v1.applyToPoint(p3).y);
        //System.out.println(v1.applyToPoint(p3).equals(new Point(0.0,-1.1)));

    //}
//}
//*/