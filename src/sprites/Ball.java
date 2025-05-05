// 323013276 Ruth Ehrmann
package sprites;

import biuoop.DrawSurface;
import collisionDetection.CollisionInfo;
import collisionDetection.GameEnvironment;
import game.Game;
import geometryPrimitive.Line;
import geometryPrimitive.Point;

import java.awt.Color;
/**
 * The sprites.Ball class represents a ball with a center point, radius, color, velocity,
 * and the lines representing its horizontal and vertical boundaries.
 */
public class Ball implements Sprite {
    private Point center;             // Center point of the ball
    private final int r;              // Radius of the ball
    private Color color;        // Color of the ball
    private Velocity v;               // sprites.Velocity of the ball
    private GameEnvironment gameEnvironment;
    /**
     * Constructor to initialize the sprites.Ball object.
     *
     * @param center The center point of the ball
     * @param r      The radius of the ball
     * @param color  The color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * @return The x-coordinate of the ball's center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return The y-coordinate of the ball's center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return The radius of the ball
     */
    public int getSize() {
        return this.r;
    }
    /**
     * @return The color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * Sets the color of this block.
     *
     * @param color the new color to be set for this block
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Sets the velocity of the ball.
     *
     * @param v The new velocity
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }
    /**
     * Sets the center of the ball.
     *
     * @param x The new x-coordinate of the center
     * @param y The new y-coordinate of the center
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }
    /**
     * Sets the game environment associated with the ball.
     *
     * @param gameEnvironment The new game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }
    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface The DrawSurface to draw the ball on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), r);
    }
    /**
     * Moves the ball one step according to its velocity, and handles collisions with the borders.
     */
    public void moveOneStep() {
        // Determine the trajectory of the ball
        Line trajectory = new Line(this.center, new Point(this.center.getX() + this.v.getDx(),
                this.center.getY() + this.v.getDy()));
        // Find the closest collision
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo != null) {
            // Adjust position just before collision
            Point potentialCollision = collisionInfo.collisionPoint();
            this.center = new Point(potentialCollision.getX() - (this.v.getDx()), potentialCollision.getY()
                    - (this.v.getDy()));
            // Handle the collision
            this.v = collisionInfo.collisionObject().hit(this, potentialCollision, this.v);
        }
        //Recalculate the trajectory after the first collision adjustment. We check twice to prevent a situation where
        // the ball enters one of the blocks.
        trajectory = new Line(this.center, new Point(this.center.getX() + this.v.getDx(),
                this.center.getY() + this.v.getDy()));
       // Check again for the closest collision on the new trajectory
        collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo != null) {
           // Adjust position just before the second collision
            Point potentialCollision = collisionInfo.collisionPoint();
            this.center = new Point(potentialCollision.getX() - (this.v.getDx()), potentialCollision.getY()
                    - (this.v.getDy()));
           // Handle the second collision
            this.v = collisionInfo.collisionObject().hit(this, potentialCollision, this.v);
        } else {
            // If no collision, move the ball normally
            this.center = this.v.applyToPoint(this.center);
        }
    }
    /**
     * Notifies the ball that a unit of time has passed.
     * Moves the ball one step accordingly.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }
    /**
     * Adds the ball to the game by adding it to the game's sprites collection.
     *
     * @param game The game to which the ball will be added
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
    /**
     * Removes this block from the given game.
     *
     * @param game the game from which this block should be removed
     */
    public void removeFromGame(Game game) {
        if (game == null) {
            System.out.println("The given game is NULL");
        } else {
            game.removeSprite(this);
        }
    }
}