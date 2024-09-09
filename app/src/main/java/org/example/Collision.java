package org.example;

import javafx.geometry.Point2D;

public class Collision {

    private Element a;
    private Element b;
    //distancia entre los centros de los elementos
    private double distance;
    private Point2D separator;
    private boolean isover;

    public Collision() {
        super();
        this.isover = false;
    }

    public Element getA() {
        return a;
    }

    public void setA(Element a) {
        this.a = a;
    }

    public Element getB() {
        return b;
    }

    public void setB(Element b) {
        this.b = b;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Point2D getSeparator() {
        return separator;
    }

    public void setSeparator(Point2D separator) {
        this.separator = separator;
    }

    public boolean isIsover() {
        return isover;
    }

    public void setIsover(boolean isover) {
        this.isover = isover;
    }

    @Override
    public String toString() {
        return "Distance:" + this.getDistance() + " Separator:" + this.getSeparator();
    }
}