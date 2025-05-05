// 323013276 Ruth Ehrmann

package sprites;

import geometryPrimitive.Point;

/**
 * The sprites.Velocity class represents the change in position on the `x` and `y` axes.
 */
public class Velocity {
    // The change in position on the x-axis
    private double dx;
    // The change in position on the y-axis
    private double dy;

    /**
     * Constructor to initialize the velocity with the specified changes in `x` and `y` positions.
     *
     * @param dx the change in position on the x-axis
     * @param dy the change in position on the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns the change in position on the x-axis.
     *
     * @return the change in position on the x-axis
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Returns the change in position on the y-axis.
     *
     * @return the change in position on the y-axis
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * Sets the change in position on the x-axis.
     *
     * @param dx the new change in position on the x-axis
     */
    public void setDx(double dx) {
        this.dx = dx;
    }
    /**
     * Sets the change in position on the y-axis.
     *
     * @param dy the new change in position on the y-axis
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Creates a sprites.Velocity object from an angle and speed.
     *
     * @param angle the angle of movement in degrees
     * @param speed the speed of movement
     * @return a sprites.Velocity object with the calculated `dx` and `dy` values
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Applies the velocity to a given point, returning a new point with the updated position.
     *
     * @param p the point to which the velocity will be applied
     * @return a new geometryPrimitive.Point object with the updated position
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}
