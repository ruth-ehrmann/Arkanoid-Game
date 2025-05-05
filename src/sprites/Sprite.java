// 323013276 Ruth Ehrmann
package sprites;

import biuoop.DrawSurface;
/**
 * The sprites.Sprite interface represents a game object that can be drawn on the screen
 * and updated over time.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the sprite
     */
    void drawOn(DrawSurface d);
    /**
     * Notifies the sprite that time has passed, allowing it to update its state.
     */
    void timePassed();
}
