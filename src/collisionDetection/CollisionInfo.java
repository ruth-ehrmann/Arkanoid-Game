// 323013276 Ruth Ehrmann
package collisionDetection;

import geometryPrimitive.Point;

/**
 * The collisionDetection.CollisionInfo class represents information about a collision between two objects.
 */

public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;
    /**
     * Constructs a new collisionDetection.CollisionInfo object with the given collision point and collidable object.
     *
     * @param collisionPoint   the point at which the collision occurs
     * @param collisionObject  the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
