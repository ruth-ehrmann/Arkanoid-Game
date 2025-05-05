// 323013276 Ruth Ehrmann
package geometryPrimitive;

import java.util.List;
/**
 * The geometryPrimitive.Line class represents a line defined by two points.
 */
public class Line {
    // Constants representing different types of line intersections
    private static final int POINT = 1;
    private static final int VERTICAL = 2;
    private static final int HORIZONTAL = 3;
    private static final int LINE = 4;
    private static final int PP = 5;
    private static final int PV = 6;
    private static final int PH = 7;
    private static final int PL = 8;
    private static final int VV = 9;
    private static final int VH = 10;
    private static final int VL = 11;
    private static final int HH = 12;
    private static final int HL = 13;
    private static final int LL = 14;

    // Points defining the line
    private final Point p1;
    private final Point p2;
    /**
     * Constructs a geometryPrimitive.Line with the specified start and end points.
     *
     * @param start the start point of the line
     * @param end the end point of the line
     */
    public Line(Point start, Point end) {
        this.p1 = start;
        this.p2 = end;
    }
    /**
     * Constructs a geometryPrimitive.Line with the specified coordinates.
     *
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
    }
    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return Math.sqrt(Math.pow(this.p1.getX() - this.p2.getX(), 2) + Math.pow(this.p1.getY() - this.p2.getY(), 2));
    }
    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        return new Point((this.p1.getX() + this.p2.getX()) / 2, (this.p1.getY() + this.p2.getY()) / 2);
    }
    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.p1;
    }
    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.p2;
    }
    /**
     * Returns the slope of the line.
     *
     * @param other the line for which the slope is calculated
     * @return the slope of the line
     */
    public double getSlope(Line other) {
        return (other.p2.getY() - other.p1.getY()) / (other.p2.getX() - other.p1.getX());
    }
    /**
     * Returns the y-intercept of the line.
     *
     * @param other the line for which the y-intercept is calculated
     * @return the y-intercept of the line
     */
    public double getIntersectY(Line other) {
        return other.p1.getY() - getSlope(other) * other.p1.getX();
    }
    /**
     * Determines the type of the line.
     *
     * @param other the line whose type is determined
     * @return the type of the line
     */
    public int lineType(Line other) {
        if ((other.p1.getX() == (other.p2.getX())) && (other.p1.getY() == other.p2.getY())) {
            return POINT;
        }
        if (other.p1.getX() == other.p2.getX()) {
            return VERTICAL;
        }
        if (other.p1.getY() == other.p2.getY()) {
            return HORIZONTAL;
        } else {
            return LINE;
        }
    }
    /**
     * Checks if a point's y-coordinate is in the range of the line's y-coordinates.
     *
     * @param other the line to check against
     * @param p the point to check
     * @return true if the point's y-coordinate is in range, false otherwise
     */
    public boolean inRangeY(Line other, Point p) {
        if (other.p1.getY() < other.p2.getY()) {
            return ((p.getY() >= other.p1.getY()) && (p.getY() <= other.p2.getY()));
        } else {
            return ((p.getY() <= other.p1.getY()) && (p.getY() >= other.p2.getY()));
        }
    }
    /**
     * Checks if a point's x-coordinate is in the range of the line's x-coordinates.
     *
     * @param other the line to check against
     * @param p the point to check
     * @return true if the point's x-coordinate is in range, false otherwise
     */
    public boolean inRangeX(Line other, Point p) {
        if (other.p1.getX() < other.p2.getX()) {
            return ((p.getX() >= other.p1.getX()) && (p.getX() <= other.p2.getX()));
        } else {
                return ((p.getX() <= other.p1.getX()) && (p.getX() >= other.p2.getX()));
            }
    }
    /**
     * Helper method to determine the intersection type between two lines.
     *
     * @param other1 the first line
     * @param other2 the second line
     * @return an integer representing the intersection type
     */
    public int isIntersectionHelper(Line other1, Line other2) {
        if (other2 == null) {
            return 0;
        }
        if (lineType(other1) == POINT) {
            if (lineType(other2) == POINT) {
                if ((other1.p1.getX() == other2.p1.getX()) && (other1.p1.getY() == other2.p1.getY())) {
                    return PP;
                }
            } else if (lineType(other2) == VERTICAL) {
                if ((other1.p1.getX() == other2.p1.getX()) && (inRangeY(other2, other1.p1))) {
                    return PV;
                }
            } else if (lineType(other2) == HORIZONTAL) {
                if ((other1.p1.getY() == other2.p1.getY()) && (inRangeX(other2, other1.p1))) {
                    return PH;
                }
            } else if (lineType(other2) == LINE) {
                if (((getSlope(other2) * other1.p1.getX()) + getIntersectY(other2)) == (other1.p1.getY())) {
                    return PL;
                }
            }
        }
        if (lineType(other1) == VERTICAL) {
            if (lineType(other2) == VERTICAL) {
                if ((other1.p1.getX() == other2.p1.getX()) && ((inRangeY(other2, other1.p1))
                        || (inRangeY(other2, other1.p2)))) {
                    return VV;
                }
            } else if (lineType(other2) == HORIZONTAL) {
                if ((inRangeX(other2, other1.p1)) && (inRangeY(other1, other2.p1))) {
                    return VH;
                }
            } else if (lineType(other2) == LINE) {
                Point p = new Point(other1.p1.getX(), ((getSlope(other2) * other1.p1.getX()) + getIntersectY(other2)));
                if ((inRangeY(other2, p)) && (inRangeY(other1, p))) {
                    return VL;
                }
            }
        }
        if (lineType(other1) == HORIZONTAL) {
            if (lineType(other2) == HORIZONTAL) {
                if ((other1.p1.getY() == other2.p1.getY()) && ((inRangeX(other2, other1.p1))
                        || (inRangeX(other2, other1.p2)))) {
                    return HH;
                }
            } else if (lineType(other2) == LINE) {
                Point p = new Point(((other1.p1.getY() - getIntersectY(other2)) / getSlope(other2)), other1.p1.getY());
                    if ((inRangeX(other2, p)) && (inRangeX(other1, p))) {
                        return HL;
                    }
                }
            }
        if (lineType(other1) == LINE) {
                if (lineType(other2) == LINE) {
                    if ((getSlope(other1)) == (getSlope(other2))) {
                        if ((getIntersectY(other1)) == (getIntersectY(other2))) {
                            if ((inRangeX(other2, other1.p1)) || (inRangeX(other2, other1.p2))) {
                                return LL;
                            }
                        }
                    } else {
                        double pX = ((getIntersectY(other2) - getIntersectY(other1))
                                / (getSlope(other1) - getSlope(other2)));
                        double pY = (getSlope(other1) * pX) + getIntersectY(other1);
                        if ((inRangeX(other1, new Point(pX, pY))) && (inRangeX(other2, new Point(pX, pY)))) {
                            return LL;
                        }
                    }
                }
            }
        return 0;
    }
    /**
     * Checks if the intersection result is valid.
     *
     * @param result the intersection result
     * @return true if the intersection is valid, false otherwise
     */
    public boolean isValidIntersection(int result) {
        return result == PP || result == PV || result == PH || result == PL || result == VV || result == VH
                || result == VL || result == HH || result == HL || result == LL;
    }
    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other the other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        int result1 = isIntersectionHelper(this, other);
        int result2 = isIntersectionHelper(other, this);
        return isValidIntersection(result1) || isValidIntersection(result2);
    }
    /**
     * Returns true if the two lines intersect with this line, false otherwise.
     *
     * @param other1 the first other line
     * @param other2 the second other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        int result1 = isIntersectionHelper(this, other1);
        int result2 = isIntersectionHelper(other1, this);
        int result3 = isIntersectionHelper(this, other2);
        int result4 = isIntersectionHelper(other2, this);

        return ((isValidIntersection(result1) || isValidIntersection(result2))
                && (isValidIntersection(result3) || isValidIntersection(result4)));
    }
    /**
     * Helper method to find the intersection point between two lines.
     *
     * @param other1 the first line
     * @param other2 the second line
     * @return the intersection point or null if no intersection
     */
    public Point intersectionWithHelper(Line other1, Line other2) {
        if (isIntersectionHelper(other1, other2) == PP
                || isIntersectionHelper(other1, other2) == PV
                || isIntersectionHelper(other1, other2) == PH
                || isIntersectionHelper(other1, other2) == PL) {
            return new Point(other1.p1.getX(), other1.p1.getY());
        }
        if (isIntersectionHelper(other1, other2) == VV) {
            if ((other1.p2.equals(other2.p1) && !inRangeY(other1, other2.p2) && !inRangeY(other2, other1.p1))
                    || (other1.p2.equals(other2.p2) && !inRangeY(other1, other2.p1) && !inRangeY(other2, other1.p1))) {
                return new Point(other1.p2.getX(), other1.p2.getY());
            } else if ((other1.p1.equals(other2.p1) && !inRangeY(other1, other2.p2) && !inRangeY(other2, other1.p2))
                    || (other1.p1.equals(other2.p2) && !inRangeY(other1, other2.p1) && !inRangeY(other2, other1.p2))) {
                return new Point(other1.p1.getX(), other1.p1.getY());
            }
        }
        if (isIntersectionHelper(other1, other2) == VH) {
            return new Point(other1.p1.getX(), other2.p1.getY());
        }
        if (isIntersectionHelper(other1, other2) == VL) {
            return new Point(other1.p1.getX(), getSlope(other2) * other1.p1.getX() + getIntersectY(other2));
        }
        if (isIntersectionHelper(other1, other2) == HH) {
            if ((other1.p2.equals(other2.p1) && !inRangeX(other1, other2.p2) && !inRangeX(other2, other1.p1))
                    || (other1.p2.equals(other2.p2) && !inRangeX(other1, other2.p1) && !inRangeX(other2, other1.p1))) {
                return new Point(other1.p2.getX(), other1.p2.getY());
            } else if ((other1.p1.equals(other2.p1) && !inRangeX(other1, other2.p2) && !inRangeX(other2, other1.p2))
                    || (other1.p1.equals(other2.p2) && !inRangeX(other1, other2.p1) && !inRangeX(other2, other1.p2))) {
                return new Point(other1.p1.getX(), other1.p1.getY());
            }
        }
        if (isIntersectionHelper(other1, other2) == HL) {
            return new Point((other1.p1.getY() - getIntersectY(other2)) / getSlope(other2), other1.p1.getY());
        }
        if (isIntersectionHelper(other1, other2) == LL) {
            if (getSlope(other1) == getSlope(other2)) {
                if ((other1.p2.equals(other2.p1) && !inRangeX(other1, other2.p2) && !inRangeX(other2, other1.p1))
                        || (other1.p2.equals(other2.p2) && !inRangeX(other1, other2.p1)
                        && !inRangeX(other2, other1.p1))) {
                    return new Point(other1.p2.getX(), other1.p2.getY());
                }
                if ((other1.p1.equals(other2.p1) && !inRangeX(other1, other2.p2) && !inRangeX(other2, other1.p2))
                        || (other1.p1.equals(other2.p2) && !inRangeX(other1, other2.p1)
                        && !inRangeX(other2, other1.p2))) {
                    return new Point(other1.p1.getX(), other1.p1.getY());
                }
            }
            if (getSlope(other1) != getSlope(other2)) {
                double pX = (getIntersectY(other2) - getIntersectY(other1)) / (getSlope(other1) - getSlope(other2));
                double pY = getSlope(other1) * pX + getIntersectY(other1);
                return new Point(pX, pY);
            }
        }
        return null;
    }
    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other the other line
     * @return the intersection point if the lines intersect, and null otherwise
     */
    public Point intersectionWith(Line other) {
        if (intersectionWithHelper(this, other) == null) {
            return intersectionWithHelper(other, this);
        } else {
            return intersectionWithHelper(this, other);
        }
    }
    /**
     * Checks if this line is equal to another line.
     *
     * @param other the other line
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.p1.equals(other.p1) && this.p2.equals(other.p2))
                || (this.p1.equals(other.p2) && this.p2.equals(other.p1));
    }
    /**
     * Finds the closest intersection point of this line with a rectangle.
     *
     * @param rect the rectangle to check for intersection
     * @return the closest intersection point, or null if there are no intersection points
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);

        if (intersectionPoints == null || intersectionPoints.isEmpty()) {
            return null;
        }
        Point closestPoint = intersectionPoints.get(0);
        double minDistance = this.start().distance(closestPoint);
        for (Point intersectionPoint : intersectionPoints) {
            double currentDistance = this.start().distance(intersectionPoint);
            if (currentDistance < minDistance) {
                closestPoint = intersectionPoint;
                minDistance = currentDistance;
            }
        }
        return closestPoint;
    }
}



