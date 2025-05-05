// 323013276 Ruth Ehrmann
package geometryPrimitive;
/**
 * The geometryPrimitive.Point class represents a point in a 2D space.
 * Each point has an x and y coordinate.
 */
public class Point {
    private final double x;
    private final double y;
    private static final double THRESHOLD = 0.0001; // Define a constant threshold value
    /**
     * Constructs a new geometryPrimitive.Point with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Returns the distance from this point to another point.
     *
     * @param other the other point to which the distance is calculated
     * @return the distance to the other point
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.getX() - other.getX(), 2) + Math.pow(this.getY() - other.getY(), 2));
    }
    /**
     * Checks if this point is equal to another point.
     * Two points are considered equal if their x and y coordinates are the same.
     *
     * @param other the other point to compare to
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        double dx = Math.abs(this.getX() - other.getX());
        double dy = Math.abs(this.getY() - other.getY());
        return dx < THRESHOLD && dy < THRESHOLD;
    }
    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }
    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }
}
