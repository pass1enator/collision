package org.example;

import java.util.ArrayList;
import java.util.Optional;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;


/**
 * @author Administrador
 */
public abstract class ElementMovable extends ElementDynamic implements IMovable {

    protected double vx;
    protected double vy;
    //coeficiente de restitucion en caso de colision
    protected double coefResX;


    protected double coefResY;
    private static int MAX_SPEED = 4;

    public ElementMovable() {
        super();
        this.state = State.STOPPED;//RUNNING;
    }

    public ElementMovable(double vx, double vy, double x, double y, double width, double height, float mass) {
        super(x, y, width, height, mass);
        this.state = State.STOPPED;//RUNNING;
        this.vx = vx;
        this.vy = vy;
        this.coefResX = 0;
        this.coefResY = 0;
    }

    /**
     * no registra el movimiento
     *
     * @param x
     * @param y
     */
    @Override
    public void move(double x, double y) {
        if (this.state == State.RUNNING) {
            this.updatelast(x, y);
            Rectangle2D r = new Rectangle2D(this.getRectangle().getMinX() + x,
                    this.getRectangle().getMinY() + y,
                    this.getRectangle().getWidth(),
                    this.getRectangle().getHeight());
            this.rectangle = r;
        }
    }

    public void moveTo(double x, double y) {
        if (this.state == State.RUNNING) {
            this.updatelast(x, y);
            Rectangle2D r = new Rectangle2D(x,
                    y,
                    this.getRectangle().getWidth(),
                    this.getRectangle().getHeight());
            this.rectangle = r;
        }
    }

    @Override
    public void move() {
        this.move(getVx(), getVy());
    }

    private void friction() {
        var friction = 0.025f * this.mass;
        if (this.vx > 0) {
            this.vx -= friction;//0.02f;
        } else if (this.vx < 0) {
            this.vx += friction;//0.01f;
        }

        if (Math.abs(vx) <= 0.2f)
            this.vx = 0;

    }

    public void paint(GraphicsContext gc) {

        super.paint(gc);
        //vectores velocidad en x,y
        if (this.isDebug()) {
            gc.beginPath();
            gc.setStroke(Color.GREEN);
            gc.setLineCap(StrokeLineCap.ROUND);
            gc.moveTo(this.getCenterX() * Game.SCALE, this.getCenterY() * Game.SCALE);
            gc.setLineWidth(2.0);
            gc.lineTo((this.getCenterX() + vx * 10) * Game.SCALE, this.getCenterY() * Game.SCALE);
            gc.stroke();
            gc.setStroke(Color.BLUE);
            gc.moveTo(this.getCenterX() * Game.SCALE, this.getCenterY() * Game.SCALE);
            gc.setLineWidth(1.0);
            gc.lineTo((this.getCenterX()) * Game.SCALE, (this.getCenterY() + vy * 10) * Game.SCALE);
            gc.stroke();
            gc.setStroke(Color.RED);
            gc.moveTo(this.getCenterX() * Game.SCALE, this.getCenterY() * Game.SCALE);
            gc.setLineWidth(1.0);
            gc.lineTo((this.getCenterX() + vx * 10) * Game.SCALE, (this.getCenterY() + vy * 10) * Game.SCALE);
            gc.stroke();
            gc.closePath();
        }
    }
    public void paint(GraphicsContext gc, Point2D cameraPosition) {

        super.paint(gc, cameraPosition);
        //vectores velocidad en x,y
        if (this.isDebug()) {
            gc.beginPath();
            gc.setStroke(Color.GREEN);
            gc.setLineCap(StrokeLineCap.ROUND);
            gc.moveTo((this.getCenterX()-cameraPosition.getX()) * Game.SCALE, (this.getCenterY()-cameraPosition.getY()) * Game.SCALE);
            gc.setLineWidth(2.0);
            gc.lineTo((this.getCenterX() + vx * 10 -cameraPosition.getX()) * Game.SCALE, (this.getCenterY() -cameraPosition.getY())* Game.SCALE);
            gc.stroke();
            gc.setStroke(Color.BLUE);
            gc.moveTo((this.getCenterX()-cameraPosition.getX()) * Game.SCALE , (this.getCenterY()-cameraPosition.getY()) * Game.SCALE);
            gc.setLineWidth(1.0);
            gc.lineTo((this.getCenterX()-cameraPosition.getX()) * Game.SCALE, (this.getCenterY() + vy * 10-cameraPosition.getY()) * Game.SCALE);
            gc.stroke();
            gc.setStroke(Color.RED);
            gc.moveTo((this.getCenterX()-cameraPosition.getX()) * Game.SCALE, (this.getCenterY()-cameraPosition.getY()) * Game.SCALE);
            gc.setLineWidth(1.0);
            gc.lineTo((this.getCenterX() + vx * 10-cameraPosition.getX()) * Game.SCALE, (this.getCenterY() + vy * 10-cameraPosition.getY()) * Game.SCALE);
            gc.stroke();
            gc.closePath();
        }
    }
    @Override
    public void update(ArrayList<Element> elements) {//}, Level l) {
        if (this.getState() == State.RUNNING) {
            this.friction();
            this.move(getVx(), getVy());
            var l = elements.stream().map(
                            this::collision)
                    .filter(Optional::isPresent).toList();

            if (!l.isEmpty()) {

                l.forEach(c -> {
                    this.move(c.get().getSeparator().getX(), (c.get().getSeparator().getY()));
                    if (Math.abs(c.get().getSeparator().getX()) > 0) {
                        //solo se frena si esta subiendo
                        //if (this.getVy() >= 0)
                            setVx(-this.getVx() * this.getCoefResX());
                       // else
                        //    setVx(0);
                    }
                    if (Math.abs(c.get().getSeparator().getY()) > 0)

                        setVy(-this.getVy() * this.getCoefResY());


                });


            }
        }

    }

    @Override
    public void moveLeft() {
        if (this.getState() == State.RUNNING) {

            this.move(-getVx(), 0);

        }
    }

    @Override
    public void moveLeft(double inc) {
        if (this.getState() == State.RUNNING) {
            this.vx = this.getVx() - inc;
            //this.move(-inc, 0);
            if (this.vx < -MAX_SPEED)
                this.vx = -MAX_SPEED;
        }
    }

    @Override
    public void moveRigth() {
        if (this.getState() == State.RUNNING) {

            this.move(getVx(), 0);

        }
    }

    @Override
    public void moveRight(double inc) {
        if (this.getState() == State.RUNNING) {
            this.vx = this.getVx() + inc;
            if (this.vx > MAX_SPEED)
                this.vx = MAX_SPEED;
            //this.move(inc, 0);

        }
    }

    @Override
    public void moveUp() {
        if (this.getState() == State.RUNNING) {

            this.move(0, -getVy());

        }
    }

    @Override
    public void moveUp(double inc) {
        if (this.getState() == State.RUNNING) {

            this.move(0, -inc);

        }
    }

    @Override
    public void moveDown() {
        if (this.getState() == State.RUNNING) {
            this.move(0, getVy());


        }
    }

    @Override
    public void moveDown(double inc) {
        if (this.getState() == State.RUNNING) {
            this.move(0, inc);

        }
    }

    public double getCoefResY() {
        return coefResY;
    }

    public void setCoefResY(double coefResY) {
        this.coefResY = coefResY;
    }

    public double getCoefResX() {
        return coefResX;
    }

    public void setCoefResX(double coefResX) {
        this.coefResX = coefResX;
    }

    public void changeVerticalDirection() {
        this.setVy(this.getVy() * -1);
    }

    public void changeHorizontalDirection() {
        this.setVx(this.getVx() * -1);
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

}