package org.example;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


/**
 *
 * @author Administrador
 */
public class Element implements IDebuggable, IDrawable {

    //private javafx.geometry.Dimension2D d;
    private Point2D screen_position;
    private boolean debug;
    protected Rectangle2D rectangle;
    private Color color;
    protected Image img;

    public Element() {

        this.rectangle = Rectangle2D.EMPTY;
    }

    public Element(double x, double y, double width, double height) {
        this.rectangle = new Rectangle2D(x, y, width, height);
        this.screen_position= new Point2D(0,0);
    }

    /*   public javafx.geometry.Dimension2D getDimension() {
        return d;
    }

    public void setDimension(javafx.geometry.Dimension2D d) {
        this.d = d;
    }*/
    public void setPosition(double x, double y) {
        this.rectangle = new Rectangle2D(x, y, this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    @Override
    public void setDebug(boolean value) {
        this.debug = value;
    }

    @Override
    public boolean isDebug() {

        return this.debug;
    }

    public double getWidth() {
        return this.getRectangle().getWidth();
    }

    public double getHeight() {
        return this.getRectangle().getHeight();
    }

    public Point2D getCenter() {
        return new Point2D(this.getCenterX(), this.getCenterY());
    }

    public double getCenterX() {
        return this.getRectangle().getMinX() + this.getRectangle().getWidth() / 2;
    }

    public double getCenterY() {
        return this.getRectangle().getMinY() + this.getRectangle().getHeight() / 2;

    }

    public Point2D getScreenPosition() {
        return this.screen_position;
    }

    public void setScreenPosition(Point2D position) {
        this.screen_position = position;
    }

    public double getDistance(Element e) {
        return this.getCenter().distance(e.getCenter());
    }

    public Color getStokecolor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Rectangle2D getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle2D rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void debug(GraphicsContext gc) {
        //gc.setStroke(Color.RED);
        gc.setFill(this.color);

        gc.fillOval((this.getRectangle().getMinX() - this.screen_position.getX()) * Game.SCALE - 5,
                (this.getRectangle().getMinY()) * Game.SCALE - 5,
                10, 10);

        gc.strokeText(" X:" + (int) (this.getRectangle().getMinX())
                        + " Y:" + (int) (this.getRectangle().getMinY()),
                (this.getRectangle().getMinX() - this.screen_position.getX()) * Game.SCALE,
                (this.getRectangle().getMinY()) * Game.SCALE - 5);

        gc.setFill(this.color);
        gc.fillRect((this.getRectangle().getMinX() - this.screen_position.getX()) * Game.SCALE, this.getRectangle().getMinY() * Game.SCALE, this.getRectangle().getWidth() * Game.SCALE, this.getRectangle().getHeight() * Game.SCALE);

    }

    protected Color getColor() {
        return this.color;
    }

    @Override
    public void paint(GraphicsContext gc) {
       // gc.setStroke(this.getColor());

        //gc.strokeRect((this.getRectangle().getMinX() - this.screen_position.getX()) * Game.SCALE, this.getRectangle().getMinY() * Game.SCALE, this.getRectangle().getWidth() * Game.SCALE, this.getRectangle().getHeight() * Game.SCALE);
        if (this.isDebug()) {

            this.debug(gc);
        }
    }

}