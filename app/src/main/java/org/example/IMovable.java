package org.example;

public interface IMovable {

    public enum LimitCollision {
        UP, DOWN, LEFT, RIGTH, NONE
    }

    public void move(double x, double y);

    public void move();

    public void moveLeft();

    public void moveLeft(double inc);

    public void moveRigth();

    public void moveRight(double inc);

    public void moveUp();

    public void moveUp(double inc);

    public void moveDown();

    public void moveDown(double inc);

    public void stop();

    public void start();

    public void pause();
    //public void setLimits(Rectangle2D rectangle);
}