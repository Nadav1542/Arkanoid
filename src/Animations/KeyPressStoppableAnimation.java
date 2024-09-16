package Animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The KeyPressStoppableAnimation class. Adding behaviour of stopping running
 * of a certain animation by certain key press.
 */
public class KeyPressStoppableAnimation implements Animation {

    private Animation decorated;
    private KeyboardSensor sensor;
    private String key;
    private boolean isAlreadyPressed;
    private boolean close;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.decorated = animation;
        this.isAlreadyPressed = true;
        this.close = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!sensor.isPressed(key)) {
            isAlreadyPressed = false;
            decorated.doOneFrame(d);
        } else {
            if (!isAlreadyPressed) {
                this.close = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.close;
    }
}
