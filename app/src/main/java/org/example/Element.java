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
    private static float MAX_MASS=Float.MAX_VALUE;
   // protected Point2D screen_position;
    private boolean debug;
    protected Rectangle2D rectangle;
    protected float mass;
    private Color color;
    protected Image img;


    protected String name;
    public Element() {

        this.rectangle = Rectangle2D.EMPTY;
    }

    public Element(double x, double y, double width, double height,float mass) {
        this.rectangle = new Rectangle2D(x, y, width, height);
       // this.screen_position= new Point2D(0,0);
        this.mass=mass;
    }
    public Element(double x, double y, double width, double height) {
        this.rectangle = new Rectangle2D(x, y, width, height);
       // this.screen_position= new Point2D(0,0);
        this.mass=MAX_MASS;
    }
    public void setMass(float mass) {
        this.mass=mass;
    }
    public float getMass() {
        return mass;
    }

    public void setPosition(double x, double y) {
        this.rectangle = new Rectangle2D(x, y, this.rectangle.getWidth(), this.rectangle.getHeight());

    }
    public Point2D getPosition(){
        return new Point2D(this.rectangle.getMinX(), this.rectangle.getMinY());
    }


    @Override
    public void setDebug(boolean value) {
        this.debug = value;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public void debug(GraphicsContext gc, Point2D cameraPosition) {
        //gc.setStroke(Color.RED);
        gc.setFill(this.color);

        gc.fillOval((this.getRectangle().getMinX() -cameraPosition.getX()) * Game.SCALE - 5,
                (this.getRectangle().getMinY()- cameraPosition.getY()) * Game.SCALE- 5,
                10, 10);
        gc.fillOval(((this.getCenterX()-cameraPosition.getX()) * Game.SCALE) - 5,
                (this.getCenterY()-cameraPosition.getY()) * Game.SCALE - 5,
                10, 10);
        gc.strokeText(" X:" + (int) (this.getRectangle().getMinX())
                        + " Y:" + (int) (this.getRectangle().getMinY()),
                (this.getRectangle().getMinX() -cameraPosition.getX()) * Game.SCALE ,
                (this.getRectangle().getMinY() -cameraPosition.getY()) * Game.SCALE - 5 );

       // gc.setFill(this.color);
       // gc.fillRect((this.getRectangle().getMinX() - this.screen_position.getX()) * Game.SCALE, this.getRectangle().getMinY() * Game.SCALE, this.getRectangle().getWidth() * Game.SCALE, this.getRectangle().getHeight() * Game.SCALE);

    }

    protected Color getColor() {
        return this.color;
    }

    @Override
    public void paint(GraphicsContext gc) {
        //sin desplazamiento
        this.paint(gc, new Point2D(0,0));
    }
    @Override
    public void paint(GraphicsContext gc, Point2D cameraposition) {
        gc.setStroke(this.getColor());

        gc.strokeRect((this.getRectangle().getMinX() -cameraposition.getX()) * Game.SCALE,
                (this.getRectangle().getMinY()-cameraposition.getY()) * Game.SCALE,
                this.getRectangle().getWidth() * Game.SCALE,
                this.getRectangle().getHeight() * Game.SCALE);
      //  if (this.isDebug()) {

            this.debug(gc,cameraposition);
     //   }
    }

}