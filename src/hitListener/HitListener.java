// 323013276 Ruth Ehrmann
package hitListener;

import sprites.Ball;
import sprites.Block;

/**
 * The hitListener.HitListener interface should be implemented by any class that wants to listen
 * for hit events in the game.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit by a ball.
     *
     * @param beingHit the sprites.Block object that is being hit
     * @param hitter   the sprites.Ball object that hits the block
     */
    void hitEvent(Block beingHit, Ball hitter);

}
