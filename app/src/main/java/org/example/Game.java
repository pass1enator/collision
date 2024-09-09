package org.example;

import java.util.HashMap;
import javafx.geometry.Dimension2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;


public class Game extends Clock implements IKeyListener {

    public static final int SCALE = 2;
    public static Image imagenes = null;
    //private HashMap<String, IScene> scenes;
    //private IScene actual_scene;
    private Dimension2D original_size;
    //private Board board;
    private GraphicsContext ctx, bg_context;
    private ElementWithJump element;
    //private SrArthur hero;

    /**
     * constructor
     *
     * @param context
     * @param bg_context
     * @param original
     */
    public Game(GraphicsContext context, GraphicsContext bg_context,
                Dimension2D original) {
        super(30);
        this.ctx = context;
        this.bg_context = bg_context;
        this.original_size = original;
    this.element=new ElementWithJump(0,0.5f,0,0,0,20,10,10);
this.element.setDebug(true);
//this.element.activeVerticalGravity();
this.element.start();


    }





    @Override
    public void start() {
            super.start();
    }
    private void update(){
        this.element.update(null);
    }
    private  void procesInput(){

    }
    private void clear() {
        this.ctx.restore();
        this.ctx.clearRect(0, 0, this.original_size.getWidth() * Game.SCALE, this.original_size.getHeight() * Game.SCALE);
    }
    public void paint(){
        this.clear();
        //this.ctx.clearRect(0,0,this.original_size.getWidth(),this.original_size.getHeight());
        this.element.paint(this.ctx);
    }
    @Override
    protected void onEvent() {
        System.out.println("-----------");
        this.update();
        this.paint();

    }


    @Override
    public void onKeyPressed(KeyCode code) {
        switch (code){
            case LEFT -> this.element.moveLeft(0.5);
            case UP -> {
                if (this.element.canJump())
                    this.element.jump();
            }
            case RIGHT -> this.element.moveRight(0.5);
        }


    }

    @Override
    public void onKeyReleased(KeyCode code) {


    }

}