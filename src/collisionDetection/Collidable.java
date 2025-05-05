// 323013276 Ruth Ehrmann
package collisionDetection;
import geometryPrimitive.Point;
import geometryPrimitive.Rectangle;
import sprites.Ball;
import sprites.Velocity;

/**
 * The collisionDetection.Collidable interface represents objects that can be collided with in a game.
 * It provides methods to get the collision shape and to handle the collision event.
 */
public interface Collidable {
    /**
     * Returns the "collision shape" of the object.
     *
     * @return the geometryPrimitive.Rectangle representing the collision shape of the object
     */
    Rectangle getCollisionRectangle();
    /**
     * Notifies the object that it has been collided with at a specific point with a given velocity.
     * The method calculates and returns the new velocity expected after the hit, based on the force
     * the object inflicted on the colliding object.
     *
     * @param hitter the ball that hit the object
     * @param collisionPoint the point at which the collision occurred
     * @param currentVelocity the current velocity of the colliding object
     * @return the new sprites.Velocity expected after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
