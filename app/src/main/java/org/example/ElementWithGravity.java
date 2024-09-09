package org.example;

import java.util.ArrayList;


/**
 *
 * @author Administrador
 */
public abstract class ElementWithGravity extends ElementMovable implements IGravity {

    private double gx;
    private double gy;
    private boolean activegravityx;
    private boolean activegravityy;

    public ElementWithGravity() {
        super();
    }

    public ElementWithGravity(double gx, double gy) {
        super();
        this.gx = gx;
        this.gy = gy;
    }

    public ElementWithGravity(double gx, double gy, double vx, double vy, double x, double y, double width, double height) {
        super(vx, vy, x, y, width, height);
        this.gx = gx;
        this.gy = gy;
    }

    @Override
    public boolean isActive() {
        return this.activegravityx || this.activegravityy;
    }

    @Override
    public void activeGravity() {
        this.activegravityy = true;
        this.activegravityx = true;
    }

    @Override
    public void activeHorizontalGravity() {
        this.activegravityx = true;
    }

    @Override
    public void activeVerticalGravity() {
        this.activegravityy = true;
    }

    @Override
    public void unactiveGravity() {
        this.activegravityx = false;
        this.activegravityy = false;
    }

    @Override
    public void unactiveHorizontalGravity() {
        this.activegravityx = false;
    }

    @Override
    public void unactiveVerticalGravity() {
        this.activegravityy = false;
    }

    @Override
    public void setHorizontalGravity(double gravity) {
        this.gx = gravity;
    }

    @Override
    public void setVerticalGravity(double gravity) {
        this.gy = gravity;
    }

    @Override
    public double getHorizontalGravity() {
        return this.gx;
    }

    @Override
    public double getVerticalGravity() {
        return this.gy;
    }

    @Override
    public boolean isActiveHorizontalGravity() {
        return this.activegravityx;
    }

    @Override
    public boolean isActiveVerticalGravity() {
        return this.activegravityy;
    }

    @Override
    public void update(ArrayList<Element> elements){//}, Level l) {
        if (this.getState() == State.RUNNING) {
            if (this.isActiveHorizontalGravity()) {
                this.vx = this.vx + this.gx;
            }
            if (this.isActiveVerticalGravity()) {
                this.vy = this.vy + this.gy;
            }
            super.update(elements); //,l);
        } else {
            System.out.println("parado");
        }

    }

    @Override
    public void move() {
        this.vx += vx;
        this.vy += vy;
        this.move(vx, vy);
    }

}