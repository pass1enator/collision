package org.example;

import java.util.ArrayList;
import javafx.geometry.Rectangle2D;


/**
 *
 * @author Administrador
 */
public abstract class ElementMovable extends ElementDynamic implements IMovable {

    protected double vx;
    protected double vy;

    public ElementMovable() {
        super();
        this.state = State.STOPPED;//RUNNING;
    }

    public ElementMovable(double vx, double vy, double x, double y, double width, double height) {
        super(x, y, width, height);
        this.state = State.STOPPED;//RUNNING;
        this.vx = vx;
        this.vy = vy;
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

    @Override
    public void move() {
        this.move(getVx(), getVy());
    }

    @Override
    public void update(ArrayList<Element> elements){//}, Level l) {
        if (this.getState() == State.RUNNING) {
            this.move(getVx(), getVy());
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

            this.move(-inc, 0);

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

            this.move(inc, 0);

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

            this.move(0,-inc);

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