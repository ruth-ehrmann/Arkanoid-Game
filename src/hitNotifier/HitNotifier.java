// 323013276 Ruth Ehrmann
package hitNotifier;

import hitListener.HitListener;

/**
 * The hitNotifier.HitNotifier interface should be implemented by any object that can notify
 * listeners about hit events.
 */
public interface HitNotifier {
    /**
     * Adds a hitListener.HitListener to the list of listeners to be notified about hit events.
     *
     * @param hl the hitListener.HitListener to be added
     */
    void addHitListener(HitListener hl);
    /**
     * Removes a hitListener.HitListener from the list of listeners to no longer receive hit events.
     *
     * @param hl the hitListener.HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}
