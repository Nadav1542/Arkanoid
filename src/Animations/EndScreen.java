package Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {


    private KeyboardSensor keyboard;
    private boolean stop;

    private boolean win;
    private int finalScore;

    /**
     * Instantiates a new End screen.
     *
     * @param keyboard   the keyboard
     * @param finalScore the final score
     * @param win        the win
     */
    public EndScreen(KeyboardSensor keyboard, int finalScore,
                     boolean win) {
        this.keyboard = keyboard;
        this.stop = false;
        this.finalScore = finalScore;
        this.win = win;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.win) {
            d.drawText(10, d.getHeight() / 2,
                    "Game Over. Your score is " + finalScore,
                    32);
        }
        if (this.win) {
            d.drawText(10, d.getHeight() / 2,
                    "You Win! Your score is " + finalScore,
                    32);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
