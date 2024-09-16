/**
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
 */
import Animations.AnimationRunner;
import GameEnvironment.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Ass6Game class.
 */
public class Ass6Game {


    /**
     *
     * Main Method initialize game and run animation.
     *
     * @param args no arguments for this method.
     */
    public static void main(String[] args) {
        List<LevelInformation> levelInformationList = new ArrayList<>();
        if(args.length==0) {

            levelInformationList.add(new LevelOne());
            levelInformationList.add(new LevelTwo());
            levelInformationList.add(new LevelThree());
            AnimationRunner ar = new AnimationRunner(60);
            GameFlow gameFlow = new GameFlow(ar, ar.getGui().getKeyboardSensor());
            gameFlow.runLevels(levelInformationList);
        }
        else {
            for (int i = 0; i < args.length; i++) {
                try {
                    int z = Integer.parseInt(args[i]);
                    if (z >= 1 && z <= 3) {
                        if (z == 1) {
                            levelInformationList.add(new LevelOne());
                        }
                        if (z == 2) {
                            levelInformationList.add(new LevelTwo());
                        }
                        if (z == 3) {
                            levelInformationList.add(new LevelThree());
                        }
                    }
                }
                catch (NumberFormatException e){
                    continue;
                }
            }
            AnimationRunner ar = new AnimationRunner(60);
            GameFlow gameFlow = new GameFlow(ar, ar.getGui().getKeyboardSensor());
            gameFlow.runLevels(levelInformationList);
        }





    }
}
