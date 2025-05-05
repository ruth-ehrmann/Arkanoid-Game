// 323013276 Ruth Ehrmann
package hitListener;

import game.Counter;
import game.Game;
import sprites.Ball;
import sprites.Block;

/**
 * The hitListener.BallRemover class is in charge of removing balls from the game, and updating the counter of
 * remaining balls.
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;
    /**
     * Constructor.
     *
     * @param game the game instance from which balls should be removed
     * @param remainingBalls the counter of remaining balls in the game
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    /**
     * This method is called whenever the beingHit object is hit.
     * Balls that hit the bottom block should be removed from the game.
     * Also, it updates the count of remaining balls.
     *
     * @param beingHit the block that was hit
     * @param hitter the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
