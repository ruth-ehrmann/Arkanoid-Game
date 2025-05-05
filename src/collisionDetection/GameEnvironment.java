// 323013276 Ruth Ehrmann
package collisionDetection;
import geometryPrimitive.Line;
import geometryPrimitive.Point;

import java.util.ArrayList;
import java.util.List;
/**
 * The collisionDetection.GameEnvironment class represents the environment in which the game objects (collidables)
 * exist. It keeps track of all the collidable objects and calculates the closest collision for a given trajectory.
 */
public class GameEnvironment {
    private final List<Collidable> collidables;
    /**
     * Constructs a new collisionDetection.GameEnvironment with an empty list of collidables.
     */
    public GameEnvironment() {
        this.collidables =  new ArrayList<>();
    }
    /**
     * Adds the given collidable to the environment.
     *
     * @param c the collisionDetection.Collidable to add
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**
     * Removes a collidable object from the collection of collidables.
     *
     * @param c the collisionDetection.Collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        // Removes the specified collisionDetection.Collidable object from the collection of collidables.
        this.collidables.remove(c);
    }
    /**
     * Returns the list of all collidable objects in the environment.
     *
     * @return a list of collisionDetection.Collidable objects
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }
    /**
     * Calculates and returns information about the closest collision that will occur along the given trajectory.
     * If no collision is detected, returns null.
     *
     * @param trajectory the geometryPrimitive.Line representing the trajectory of the moving object
     * @return a collisionDetection.CollisionInfo object containing the details of the closest collision, or null if no
     * collision occurs
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double minDistance = Double.MAX_VALUE;
        Point comparisionPoint;
        Point collisionPoint = null;
        Collidable collisionObject = null;
        // Iterate over all collidables to find the closest collision point
        for (Collidable collidable : this.collidables) {
            comparisionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (comparisionPoint == null) {
                continue;
            }
            double distance = comparisionPoint.distance(trajectory.start());
            if (distance < minDistance) {
                minDistance = distance;
                collisionPoint = comparisionPoint;
                collisionObject = collidable;
            }
        }
        // If no collision is detected, return null
        if (collisionPoint == null || collisionObject == null) {
            return null;
        }
        // Return the information about the closest collision
        return new CollisionInfo(collisionPoint, collisionObject);
    }
}
