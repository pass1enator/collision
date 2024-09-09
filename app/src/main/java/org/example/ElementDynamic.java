package org.example;
import java.util.ArrayList;
import java.util.Optional;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
public abstract class ElementDynamic extends Element implements ICollidable, IState {

    protected State state;
    protected Motion lastmotion;
    protected boolean overGround;

    public ElementDynamic() {
        super();
        this.state = State.STOPPED;//RUNNING;
        this.overGround = false;
    }

    public ElementDynamic(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.state = State.STOPPED;//RUNNING;

        this.overGround = false;
    }

    protected Optional<Collision> isOver(Element e) {
        Collision c = null;
        Optional<Collision> oc = null;
        //si esta justo sobre el elemento
        if (Math.abs(this.getRectangle().getMaxY() - e.getRectangle().getMinY()) < 1) {
            if (this.getRectangle().getMinX() < e.getRectangle().getMaxX()
                    && this.getRectangle().getMaxX() > e.getRectangle().getMinX()) {
                c = new Collision();
                c.setA(this);
                c.setB(e);
                c.setDistance(this.getCenter().distance(e.getCenter()));
                c.setSeparator(new Point2D(0, 0));
                c.setIsover(true);
                oc = Optional.of(c);

                this.setOverGround(true);
                return oc;
            }

        }
        return Optional.empty();
    }

    protected Optional<Collision> collisionWithElement(Element element) {
        Collision c = null;
        double dx = 0;
        double dy = 0;
        Rectangle2D rx, ry;
        //se produce una colision
        if (element != null && this.rectangle.intersects(element.getRectangle())) {
            c = new Collision();
            c.setA(this);
            c.setB(element);
            c.setDistance(this.getCenter().distance(element.getCenter()));
            if (this.lastmotion != null) {
                if (this.lastmotion != null) {
                    rx = this.lastmotion.getMotioninx();
                    if (rx.intersects(element.getRectangle())) {
                        //se encuentra a la izquierda para restaurar negativo
                        if (this.getCenterX() < element.getCenterX()) {
                            dx = Math.abs(element.getCenterX() - this.getCenterX()) - (this.getWidth() / 2 + element.getWidth() / 2);

                        } else {
                            dx = Math.abs((this.getCenterX() - element.getCenterX()) - (this.getWidth() / 2 + element.getWidth() / 2));

                        }
                    } else {
                        ry = this.lastmotion.getMotioniny();
                        if (ry.intersects(element.getRectangle())) {
                            //se encuentra en la parte superior para restaurar negativo
                            if (this.getCenterY() < element.getCenterY()) {
                                dy = Math.abs(element.getCenterY() - this.getCenterY()) - (this.getHeight() / 2 + element.getHeight() / 2);

                            } else {
                                dy = Math.abs((this.getCenterY() - element.getCenterY()) - (this.getHeight() / 2 + element.getHeight() / 2));

                            }
                        }
                    }

                }
                //esta es la separación del objeto para que no se produzca colision
                c.setSeparator(new Point2D(dx, dy));
                //System.out.println("Se produce una colision que no es borde con" + c.getSeparator());

            }
        }
        return c == null ? Optional.empty() : Optional.of(c);

    }

    @Override
    public Optional<Collision> collision(Element element) {
        Optional<Collision> oc = this.isOver(element);
        //no esta sobre, toca mirar si se produce colisión
        if (oc == null || oc.isEmpty()
                || (oc.get().getSeparator().getX() != 0 || oc.get().getSeparator().getY() != 0)) {
            oc = this.collisionWithElement(element);

            this.setOverGround(false);
        } else {
            this.setOverGround(true);
        }
        return oc;

    }

    @Override
    public void stop() {
        this.setState(State.STOPPED);
    }

    @Override
    public void start() {
        this.setState(State.RUNNING);
    }

    @Override
    public void pause() {
        this.setState(State.PAUSED);
    }

    public void restartLastX() {
        this.updatelast(this.lastmotion.getMotionX(), 0);
        Rectangle2D r = new Rectangle2D(
                this.getRectangle().getMinX() + this.lastmotion.getMotionX(),
                this.getRectangle().getMinY(),
                this.getRectangle().getWidth(),
                this.getRectangle().getHeight());
        this.rectangle = r;
    }

    public void restartLastY() {
        this.updatelast(0, this.lastmotion.getMotionY());

        Rectangle2D r = new Rectangle2D(this.getRectangle().getMinX(),
                this.getRectangle().getMinY() + this.lastmotion.getMotionY() * (-1),
                this.getRectangle().getWidth(),
                this.getRectangle().getHeight());
        this.rectangle = r;
    }

    public void restart() {
        this.updatelast(this.lastmotion.getMotionX(), this.lastmotion.getMotionY());

        Rectangle2D r = new Rectangle2D(this.getRectangle().getMinX() + this.lastmotion.getMotionX(),
                this.getRectangle().getMinY() + this.lastmotion.getMotionY(),
                this.getRectangle().getWidth(),
                this.getRectangle().getHeight());
        this.rectangle = r;
    }

    protected void updatelast(double ix, double iy) {
        this.lastmotion = new Motion(this.getRectangle(), ix, iy);

    }

    public boolean isOverGround() {
        return overGround;
    }

    protected void setOverGround(boolean overGround) {
        this.overGround = overGround;
    }

    public CollisionDirection borderCollision(Rectangle2D border) {
        CollisionDirection collision = CollisionDirection.NONE;
        if (this.rectangle.getMinX() <= border.getMinX()) {
            collision = CollisionDirection.LEFT;
        } else if (this.rectangle.getMaxX() >= border.getMaxX()) {
            collision = CollisionDirection.RIGTH;
        } else if (this.rectangle.getMinY() <= border.getMinX()) {
            collision = CollisionDirection.TOP;
        } else if (this.rectangle.getMaxY() >= border.getMaxY()) {
            collision = CollisionDirection.DOWN;
        }

        return collision;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    //se encarga de rebotar y no pasarse de los márgenes
    public abstract void update(ArrayList<Element> elements);//, Level l);
}