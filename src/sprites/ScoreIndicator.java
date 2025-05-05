// 323013276 Ruth Ehrmann
package sprites;

import biuoop.DrawSurface;
import game.Game;

import java.awt.Color;
/**
 * The ScoreIndicator class is responsible for displaying the score on the game screen.
 * It implements the Sprite interface, so it can be drawn on the DrawSurface and notified of time passing.
 */
public class ScoreIndicator implements Sprite {
    private final Game game;
    /**
     * Constructs a ScoreIndicator with the given game.
     *
     * @param game the game instance which contains the score to be displayed
     */
    public ScoreIndicator(Game game) {
        this.game = game;
    }
    /**
     * Draws the score on the given DrawSurface.
     *
     * @param d the DrawSurface on which the score will be drawn
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(350, 20, "Score: " + this.game.getScore().getValue(), 15);
    }
    /**
     * Notifies the score indicator that time has passed.
     * Currently, this method does nothing, but it is required by the Sprite interface.
     */
    @Override
    public void timePassed() {
        // No action needed for timePassed
    }
}
