/**
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
 */
package GameEnvironment;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * The SpriteCollection class.
 */
public class SpriteCollection {

    /**
     * list of objects which implements Sprite interface.
     */
    private List<Sprite> listS = new ArrayList<>();

    /**
     * Method adding object which implements Sprite interface to the collection.
     *
     * @param s object which implements Sprite interface.
     */
    public void addSprite(Sprite s) {
        this.listS.add(s);
    }

    /**
     * Method removes given Sprite from collection.
     *
     * @param s Sprite for removing.
     */
    public void removeSprite(Sprite s) {
        this.listS.remove(s);
    }

    /**
     * Method notify all sprites in collection that time has passed.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < listS.size(); i++) {
            listS.get(i).timePassed();
        }
    }
    /**
     * Method return all sprites in the collection.
     *
     * @return list of sprites
     */
    public List<Sprite> getListS() {
        return this.listS;
    }

    /**
     * Method drawing all sprites in a given surface.
     *
     * @param d the surface for drawing on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite i : this.listS) {
            i.drawOn(d);
        }
    }
}
