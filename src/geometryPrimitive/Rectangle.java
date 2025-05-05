// 323013276 Ruth Ehrmann
package geometryPrimitive;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
/**
 * The geometryPrimitive.Rectangle class represents a rectangle with a specified position, width, height, and color.
 * It provides methods to draw the rectangle on a drawing surface and to find intersection points with a line.
 */
public class Rectangle {
    private Point upperLeft;
    private final double width;
    private final double height;
    private Color color;
    private Line[] horizontalLines;
    private Line[] verticalLines;
    private Point[] cornersPoints;

    /**
     * Constructs a new geometryPrimitive.Rectangle with the specified top-left corner, width, and height.
     * Initializes the corner points, horizontal lines, and vertical lines of the rectangle.
     *
     * @param topLeft the top-left corner of the rectangle
     * @param width   the width of the rectangle
     * @param height  the height of the rectangle
     */
    public Rectangle(Point topLeft, double width, double height) {
        this.upperLeft = topLeft;
        this.width = width;
        this.height = height;
        initializeCornerPoints();
        initializeHorizontalLines();
        initializeVerticalLines();
    }
    /**
     * Returns the x-coordinate of the top-left corner of the rectangle.
     *
     * @return the x-coordinate of the top-left corner
     */
    public int getX() {
        return (int) this.upperLeft.getX();
    }
    /**
     * Returns the y-coordinate of the top-left corner of the rectangle.
     *
     * @return the y-coordinate of the top-left corner
     */
    public int getY() {
        return (int) this.upperLeft.getY();
    }
    /**
     * Sets the color of the rectangle.
     *
     * @param color the color to set
     */
    public void setColorRectangle(Color color) {
        this.color = color;
    }
    /**
     * Returns the color of the rectangle.
     *
     * @return the color of the rectangle
     */
    public Color getRectangleColor() {
        return this.color;
    }
    /**
     * Sets the top-left corner of the rectangle.
     *
     * @param point the new top-left corner point
     */
    public void setUpperLeft(Point point) {
        this.upperLeft = point;
    }
    /**
     * Returns a list of intersection points between the given line and the sides of the rectangle.
     *
     * @param line the line to check for intersections
     * @return a list of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();
        // Check each side for intersection with the given line
        for (Line hside : this.horizontalLines) {
            Point intersection = line.intersectionWith(hside);
            if (intersection != null) {
                intersections.add(intersection);
            }
        }
        for (Line vside : this.verticalLines) {
            Point intersection = line.intersectionWith(vside);
            if (intersection != null) {
            intersections.add(intersection);
            }
        }
        for (Point corner : this.cornersPoints) { // Check for intersections at the corners
            Line pointLine = new Line(corner, corner);
            Point intersection = line.intersectionWith(pointLine);
            if (intersection != null) {
                intersections.add(intersection);
            }
        }
        return intersections;
    }
    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Returns the top-left corner point of the rectangle.
     *
     * @return the top-left corner point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * Returns the horizontal lines of the rectangle.
     *
     * @return an array of horizontal lines
     */
    public Line[] getHorizontalLines() {
        return horizontalLines;
    }
    /**
     * Returns the vertical lines of the rectangle.
     *
     * @return an array of vertical lines
     */
    public Line[] getVerticalLines() {
        return verticalLines;
    }
    /**
     * Returns the corner points of the rectangle.
     *
     * @return an array of corner points
     */
    public Point[] getCornersPoints() {
        return cornersPoints;
    }
    /**
     * Initializes the corner points of the rectangle.
     */
    public void initializeCornerPoints() {
        this.cornersPoints = new Point[4];
        this.cornersPoints[0] = this.upperLeft;
        this.cornersPoints[1] = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        this.cornersPoints[2] = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        this.cornersPoints[3] = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }
    /**
     * Initializes the horizontal lines of the rectangle.
     */
    public void initializeHorizontalLines() {
        this.horizontalLines = new Line[2];
        this.horizontalLines[0] = new Line(this.cornersPoints[0], this.cornersPoints[1]);
        this.horizontalLines[1] = new Line(this.cornersPoints[2], this.cornersPoints[3]);
    }
    /**
     * Initializes the vertical lines of the rectangle.
     */
    public void initializeVerticalLines() {
        this.verticalLines = new Line[2];
        this.verticalLines[0] = new Line(this.cornersPoints[0], this.cornersPoints[2]);
        this.verticalLines[1] = new Line(this.cornersPoints[1], this.cornersPoints[3]);
    }
    /**
     * Sets the corner points of the rectangle based on the current top-left corner, width, and height.
     */
    public void setCornerPoints() {
        this.cornersPoints[0] = this.upperLeft;
        this.cornersPoints[1] = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        this.cornersPoints[2] = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        this.cornersPoints[3] = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }
    /**
     * Sets the horizontal lines of the rectangle based on the current corner points.
     */
    public void setHorizontalLines() {
        this.horizontalLines[0] = new Line(this.cornersPoints[0], this.cornersPoints[1]);
        this.horizontalLines[1] = new Line(this.cornersPoints[2], this.cornersPoints[3]);
    }
    /**
     * Sets the vertical lines of the rectangle based on the current corner points.
     */
    public void setVerticalLines() {
        this.verticalLines[0] = new Line(this.cornersPoints[0], this.cornersPoints[2]);
        this.verticalLines[1] = new Line(this.cornersPoints[1], this.cornersPoints[3]);
    }
}

