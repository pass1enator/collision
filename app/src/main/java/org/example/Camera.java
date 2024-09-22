package org.example;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * Clase camara para el juego de forma que se actulice a medida que el personaje se va moviendo
 */
public class Camera implements IDebuggable{
    private Point2D position;
    private Dimension2D area;
    private float borderHorizontal=200;
   // private float borderHorizontalRight=10;
    private float borderVertical=10;
    private boolean debug;

    public Camera(Point2D position, Dimension2D area) {
            this.position=position;
            this.area=area;
           // this.borderHorizontalRight= (float) (this.area.getWidth()-this.borderHorizontalLeft);
    }
    public void reset(){
        this.position= new Point2D(0,0);
    }
    //se necesita saber el incremento
    public void move(Element actor){
        //if-else ->solo puede estar en uno, por ahorrar un poco
        if(this.position.getX()+300<actor.getPosition().getX()+actor.getWidth()){
            var incx=(actor.getPosition().getX()+actor.getWidth()-300);//+actor.getWidth())-(this.position.getX()+200);
            this.position=new Point2D(incx,this.position.getY());

        }else if(this.position.getX()+84>actor.getPosition().getX()){
            var incx=(actor.getPosition().getX()-84);//+actor.getWidth())-(this.position.getX()+200);
            this.position=new Point2D(incx,this.position.getY());

        }
        //if-else ->solo puede estar en uno, por ahorrar un poco
        if(this.position.getY()+180<actor.getPosition().getY()){//+this.area.getHeight()>this.area.getHeight()){//+224<actor.getPosition().getY() && this.position.getY()+224<this.area.getHeight()){
           // var incy=(actor.getPosition().getX()+224);//+actor.getWidth())-(this.position.getX()+200);
            if(actor.getPosition().getY()-180>0)
                this.position=new Point2D(this.position.getX(),0);
            else
                this.position=new Point2D(this.position.getX(),actor.getPosition().getY()-200);



        }else
            if(this.position.getY()+50>actor.getPosition().getY()-actor.getHeight()){
            var incy=(actor.getPosition().getY()-actor.getHeight()-50);//+actor.getWidth())-(this.position.getX()+200);
            this.position=new Point2D(this.position.getX(),incy);

        }

        //this.position=this.position.add(actor);
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Dimension2D getArea() {
        return area;
    }

    public void setArea(Dimension2D area) {
        this.area = area;
    }

    public float getBorderHorizontal() {
        return borderHorizontal;
    }

    public void setBorderHorizontal(float borderHorizontal) {
        this.borderHorizontal = borderHorizontal;
    }

    public float getBorderVertical() {
        return borderVertical;
    }

    public void setBorderVertical(float borderVertical) {
        this.borderVertical = borderVertical;
    }

    private void debug(GraphicsContext gc) {
        //gc.setStroke(Color.RED);



        gc.strokeText(" X:" + (int) (this.position.getX())
                        + " Y:" + (int) (this.position.getY())
                        +   " BR:"+ (int)(this.borderHorizontal)
                        + " WS: W:"+((int)this.area.getWidth())+"H:"+((int)this.area.getHeight()),
                5,
                10);


    }

    @Override
    public void setDebug(boolean value) {
        this.debug=value;
    }

    @Override
    public boolean isDebug() {
        return this.debug;
    }

    @Override
    public void debug(GraphicsContext gc, Point2D cameraPosition) {
            if(isDebug())
                this.debug(gc);
    }
}
