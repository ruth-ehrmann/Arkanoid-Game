// 323013276 Ruth Ehrmann
package hitListener;

import game.Counter;
import game.Game;
import sprites.Ball;
import sprites.Block;

/**
 * The hitListener.BlockRemover class is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */

public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;
    /**
     * Constructs a hitListener.BlockRemover.
     *
     * @param game the game instance from which blocks should be removed
     * @param remainingBlocks a counter for the remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }
    /**
     * This method is called whenever the beingHit object is hit.
     * Blocks that are hit should be removed from the game.
     * Also removes this listener from the block that is being removed from the game.
     *
     * @param beingHit the block that is being hit
     * @param hitter the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Set the color of the hitter ball to the color of the block being hit
        hitter.setColor(beingHit.getCollisionRectangle().getRectangleColor());
        // Remove this listener from the block being hit
        beingHit.removeHitListener(this);
        // Remove the block from the game
        beingHit.removeFromGame(this.game);
        // Decrease the count of remaining blocks by 1
        this.remainingBlocks.decrease(1);
    }
}
