package org.example;
import javafx.geometry.Rectangle2D;
public class Motion {

    private Rectangle2D r;
    private double ix, iy;

    public Motion(Rectangle2D rectangle, double ix, double iy) {
        this.r = rectangle;
        this.ix = ix;
        this.iy = iy;
    }

    public Rectangle2D getMotioninx() {
        return new Rectangle2D(r.getMinX() + ix, r.getMinY(), this.r.getWidth(), this.r.getHeight());
    }

    public Rectangle2D getMotioniny() {
        return new Rectangle2D(r.getMinX(), r.getMinY() + iy, this.r.getWidth(), this.r.getHeight());
    }

    public double getMotionX() {
        return this.ix;
    }

    public double getMotionY() {
        return this.iy;
    }
}