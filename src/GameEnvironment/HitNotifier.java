/**
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
 */
package GameEnvironment;

/**
 * The interface Hit notifier.
 */
public interface HitNotifier {
    /**
     * Method adding hit listener.
     *
     * @param hl listener for adding.
     */
    void addHitListener(HitListener hl);

    /**
     * Method removing hit listener.
     *
     * @param hl listener for removing.
     */
    void removeHitListener(HitListener hl);
}
