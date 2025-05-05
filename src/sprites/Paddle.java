// 323013276 Ruth Ehrmann
package sprites;

import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisionDetection.Collidable;
import game.Game;
import geometryPrimitive.Point;
import geometryPrimitive.Rectangle;

/**
 * The sprites.Paddle class represents a paddle in the game, which is a rectangle that can be moved left or right
 * based on user input. It implements the sprites.Sprite and collisionDetection.Collidable interfaces.
 */
public class Paddle  implements Sprite, Collidable {
    private final Rectangle rectangle;
    private final biuoop.KeyboardSensor keyboard;
    private final double speed;
    /**
     * Constructs a sprites.Paddle with the specified rectangle, keyboard sensor, and speed.
     *
     * @param rect the geometryPrimitive.Rectangle representing the paddle
     * @param keyboard the KeyboardSensor to detect user input
     * @param speed the speed at which the paddle moves
     */
    public Paddle(Rectangle rect, biuoop.KeyboardSensor keyboard, double speed) {
        this.rectangle = rect;
        this.keyboard = keyboard;
        this.speed = speed;
    }
    /**
     * Moves the paddle to the left. If the paddle reaches the left edge, it wraps around to the right edge.
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() == 25) { // + this.speed <= 25) { //CROSS FROM THE LEFT BORDER
            this.rectangle.setUpperLeft(new Point(800 - this.rectangle.getWidth() - 25, this.rectangle.getY()));
        } else {
            this.rectangle.setUpperLeft(new Point(this.rectangle.getX() - speed, this.rectangle.getY()));
        }
        this.rectangle.setCornerPoints();
        this.rectangle.setHorizontalLines();
        this.rectangle.setVerticalLines();
    }
    /**
     * Moves the paddle to the right. If the paddle reaches the right edge, it wraps around to the left edge.
     */
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() == 775) {
            this.rectangle.setUpperLeft(new Point(25, this.rectangle.getY()));
        } else {
            this.rectangle.setUpperLeft(new Point(this.rectangle.getX() + speed, this.rectangle.getY()));
        }
        this.rectangle.setCornerPoints();
        this.rectangle.setHorizontalLines();
        this.rectangle.setVerticalLines();
    }
    /**
     * Notifies the paddle that time has passed. This method checks for user input to move the paddle.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param surface the DrawSurface on which to draw the paddle
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.rectangle.getRectangleColor());
        surface.fillRectangle(this.rectangle.getX(), this.rectangle.getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle(this.rectangle.getX(), this.rectangle.getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }
    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the geometryPrimitive.Rectangle representing the collision shape of the paddle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * Notifies the paddle that it has been hit at the specified collision point with a given velocity.
     * Returns the new velocity after the hit based on the region of the paddle that was hit.
     *
     * @param collisionPoint the point at which the collision occurred
     * @param currentVelocity the current velocity of the object hitting the paddle
     * @return the new sprites.Velocity after the hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double angle;
        double widthRegion = this.rectangle.getWidth() / 5;
        if ((collisionPoint.getX() >= rectangle.getUpperLeft().getX()) && (collisionPoint.getX()
                < rectangle.getUpperLeft().getX() + widthRegion)) {
            angle = 210;
        } else if ((collisionPoint.getX() >= rectangle.getUpperLeft().getX() + widthRegion)
                && (collisionPoint.getX() < rectangle.getUpperLeft().getX() + (2 * widthRegion))) {
            angle = 240;
        } else if ((collisionPoint.getX() >= rectangle.getUpperLeft().getX() + (2 * widthRegion))
            && (collisionPoint.getX() < rectangle.getUpperLeft().getX() + (3 * widthRegion))) {
            angle = 270;
        } else if ((collisionPoint.getX() >= rectangle.getUpperLeft().getX() + (3 * widthRegion))
            && (collisionPoint.getX() < rectangle.getUpperLeft().getX() + (4 * widthRegion))) {
            angle = 300;
        } else if ((collisionPoint.getX() >= rectangle.getUpperLeft().getX() + (4 * widthRegion))
                && (collisionPoint.getX() <= rectangle.getUpperLeft().getX() + (5 * widthRegion))) {
            angle = 330;
        } else {
            return currentVelocity;
        }
        return Velocity.fromAngleAndSpeed(angle, 5);
    }
    /**
     * Adds this paddle to the game as both a sprite and a collidable object.
     *
     * @param game the game.Game to which this paddle will be added
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}

