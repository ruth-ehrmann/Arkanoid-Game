// 323013276 Ruth Ehrmann
package hitListener;

import game.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * The hitListener.ScoreTrackingListener class implements the hitListener.HitListener interface
 * to track and update the score whenever a hit event occurs.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;
    /**
     * Constructs a hitListener.ScoreTrackingListener with a given score counter.
     *
     * @param scoreCounter the game.Counter object to track the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * Updates the current score by increasing it whenever a hit event occurs.
     * In this implementation, the score increases by 5 points for each hit event.
     *
     * @param beingHit the sprites.Block object that was hit
     * @param hitter   the sprites.Ball object that hit the sprites.Block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
