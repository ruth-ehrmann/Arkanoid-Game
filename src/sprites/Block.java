// 323013276 Ruth Ehrmann
package sprites;
import biuoop.DrawSurface;
import collisionDetection.Collidable;
import game.Game;
import geometryPrimitive.Line;
import geometryPrimitive.Point;
import geometryPrimitive.Rectangle;
import hitListener.HitListener;
import hitNotifier.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The sprites.Block class represents a block in the game, which is both a sprite that can be drawn on the screen
 * and a collidable object that interacts with other objects in the game environment.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final List<HitListener> hitListeners;
    private final Rectangle rectangle;
    /**
     * Constructs a new sprites.Block with the given rectangle.
     *
     * @param rect the rectangle representing the block's shape and position
     */
    public Block(Rectangle rect) {
        this.rectangle = rect;
        this.hitListeners = new ArrayList<>();
    }
    /**
     * Returns the collision rectangle representing this block.
     *
     * @return the collision rectangle of this block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * Adds a hitListener.HitListener to the block.
     *
     * @param hl the hitListener.HitListener to add
     */
    @Override
    public void addHitListener(HitListener hl) {
        // Add the given hitListener.HitListener to the list of listeners
        this.hitListeners.add(hl);
    }
    /**
     * Removes a hitListener.HitListener from the block.
     *
     * @param hl the hitListener.HitListener to remove
     */
    @Override
    public void removeHitListener(HitListener hl) {
        // Remove the given hitListener.HitListener from the list of listeners
        this.hitListeners.remove(hl);
    }
    /**
     * Notifies all registered HitListeners about a hit event.
     *
     * @param hitter the sprites.Ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * Updates the velocity after a collision with this block occurs.
     *
     * @param collisionPoint   the point of collision
     * @param currentVelocity  the current velocity of the colliding object
     * @return the new velocity after the collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.getY() > 600) { // it means this block it the death block
            this.notifyHit(hitter);
            return currentVelocity;
       }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        for (Point corner : this.rectangle.getCornersPoints()) {
            if (collisionPoint.equals(corner)) {
                currentVelocity.setDx(-currentVelocity.getDx());
                currentVelocity.setDy(-currentVelocity.getDy());
                return currentVelocity;
            }
        }
        for (Line line : this.rectangle.getHorizontalLines()) {
            if (collisionPoint.getY() == line.start().getY() && line.inRangeX(line, collisionPoint)) {
                currentVelocity.setDy(-currentVelocity.getDy());
                return currentVelocity;
            }
        }
        for (Line line : this.rectangle.getVerticalLines()) {
            if (collisionPoint.getX() == line.start().getX() && line.inRangeY(line, collisionPoint)) {
                currentVelocity.setDx(-currentVelocity.getDx());
                return currentVelocity;
            }
        }
        return currentVelocity;
    }
    /**
     * Draws the block on the given draw surface.
     *
     * @param surface the draw surface on which to draw the block
     */
    @Override
    public void drawOn(DrawSurface surface) {
        //System.out.println("rectangle");
        surface.setColor(this.rectangle.getRectangleColor());
        surface.fillRectangle(this.rectangle.getX(), this.rectangle.getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle(this.rectangle.getX(), this.rectangle.getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }
    /**
     * Updates the block's state for one frame.
     */
    @Override
    public void timePassed() { }
    /**
     * Adds this block to the given game, allowing it to interact with the game environment.
     *
     * @param game the game to which to add the block
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
    /**
     * Checks if the color of the given ball matches the color of this block.
     *
     * @param ball the sprites.Ball whose color is to be checked
     * @return true if the ball's color matches the block's color, false otherwise
     */
    public Boolean ballColorMatch(Ball ball) {
        if (ball == null) {
            System.out.println("The given ball is NULL");
            return false; // If the ball is null, return false as there is no color to match
        }
        // Compare the color of the ball with the color of the rectangle
        return this.rectangle.getRectangleColor().equals(ball.getColor());
    }
    /**
     * Removes this block from the given game. If the game is null, it prints a message.
     *
     * @param game the game.Game from which this block should be removed
     */
    public void removeFromGame(Game game) {
        if (game == null) {
            System.out.println("The given game is NULL");
        } else {
            // Remove this block as a sprite from the game
            game.removeSprite(this);
            // Remove this block as a collidable from the game
            game.removeCollidable(this);
        }
    }
}
