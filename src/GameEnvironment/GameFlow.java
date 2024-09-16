package GameEnvironment;

import Animations.Animation;
import Animations.AnimationRunner;
import Animations.EndScreen;
import Animations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {

    /**
     * The Ar.
     */
    private AnimationRunner ar;
    /**
     * The Keyboard sensor.
     */
   private KeyboardSensor keyboardSensor;

    /**
     * Gets keyboard sensor.
     *
     * @return the keyboard sensor
     */
    public KeyboardSensor getKeyboardSensor() {
        return keyboardSensor;
    }

    /**
     * Sets keyboard sensor.
     *
     * @param keyboardSensor the keyboard sensor
     */
    public void setKeyboardSensor(KeyboardSensor keyboardSensor) {
        this.keyboardSensor = keyboardSensor;
    }

    /**
     * Gets ar.
     *
     * @return the ar
     */
    public AnimationRunner getAr() {
        return ar;
    }

    /**
     * Sets ar.
     *
     * @param ar the ar
     */
    public void setAr(AnimationRunner ar) {
        this.ar = ar;
    }

    /**
     * Method construct instantiates of a new Game flow.
     *
     * @param ar Animation runner for running the game.
     * @param ks KeyboardSensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {

        this.ar = ar;
        this.keyboardSensor = ks;
    }


    /**
     * Method run levels.
     *
     * @param levels list of levels for running.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean win = true;
        ScoreTrackingListener sc = new ScoreTrackingListener(new Counter(0));
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor,
                    this.ar, sc);

            level.initialize();

            level.setRunning(true);

            while (!level.shouldStop()) {
                level.run();
            }

            if (level.getCounterBalls().getValue() == 0) {
                win = false;
                break;
            }
            sc.setCurrentScore(new Counter(sc.getCurrentScore().getValue()+100));
        }
        Animation endScreen = new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                new EndScreen(this.keyboardSensor,
                        sc.getCurrentScore().getValue(), win));
        this.ar.run(endScreen);
        this.ar.getGui().close();
    }
}


