package org.example;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.geometry.Dimension2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;


public class Game extends Clock implements IKeyListener {

    public static final int SCALE = 2;
    public static Image imagenes = null;
    //private HashMap<String, IScene> scenes;
    //private IScene actual_scene;
    private Dimension2D original_size;
    //private Board board;
    private GraphicsContext ctx, bg_context;
    private ElementWithJump element;

    private Element floor;
    private Element wall;
    private Element p1,p2,p3, p4,p5,p6;
    private ArrayList<Element> elements;
    private boolean left=false;
    private boolean right=false;
    private boolean up=false;
    private boolean down=false;
    private boolean space=false;
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
        this.element = new ElementWithJump(0, 0.2f, 0.0f, 0, 0, 20, 10, 10,1);
        this.element.setDebug(true);
        this.element.setColor(Color.BLUE);
        this.element.activeGravity();
        this.floor = new Element(0, this.original_size.getHeight() - 10, this.original_size.getWidth(), 10);
        this.floor.setColor(Color.RED);
        this.floor.setDebug(true);
        this.floor.setName("Suelo");
        this.wall = new Element(200, this.original_size.getHeight() - 60, 20, 60);
        this.wall.setColor(Color.ORANGE);
        this.wall.setDebug(true);
        this.wall.setName("Muro");
        this.elements = new ArrayList<>();

        this.p1= new Element(80, this.original_size.getHeight() - 100, 40, 20);
        this.p1.setColor(Color.GREEN);
        this.p1.setDebug(true);
        this.p1.setName("Plataforma 1");
        this.elements.add(this.p1);

        this.p2= new Element(150, this.original_size.getHeight() - 100, 40, 20);
        this.p2.setColor(Color.YELLOW);
        this.p2.setDebug(true);
        this.p2.setName("Plataforma 2");
        this.elements.add(this.p2);

        this.p3= new Element(220, this.original_size.getHeight() - 100, 40, 20);
        this.p3.setColor(Color.GRAY);
        this.p3.setDebug(true);
        this.p3.setName("Plataforma 3");
        this.elements.add(this.p3);

        this.p4= new Element(90, this.original_size.getHeight() - 155, 40, 20);
        this.p4.setColor(Color.GRAY);
        this.p4.setDebug(true);
        this.p4.setName("Plataforma 4");
        this.elements.add(this.p4);

        this.p5= new Element(160, this.original_size.getHeight() - 155, 40, 20);
        this.p5.setColor(Color.ORANGE);
        this.p5.setDebug(true);
        this.p5.setName("Plataforma 5");
        this.elements.add(this.p5);

        this.p6= new Element(210, this.original_size.getHeight() - 155, 40, 20);
        this.p6.setColor(Color.BLUE);
        this.p6.setDebug(true);
        this.p6.setName("Plataforma 6");
        this.elements.add(this.p6);


        this.elements.add(this.floor);
        this.elements.add(this.wall);


        this.element.start();


    }


    @Override
    public void start() {
        super.start();
    }

    private void update() {
        this.element.update(this.elements);

    }

    private void procesInput() {
        if (this.left) {
            //this.left=true;
            this.element.moveLeft(0.25);
        }
        if (this.right) {
           // this.right=true;
             this.element.moveRight(0.25);
        }
        if (this.down) {
            //this.up=true;
            this.element.moveDown(0.5);
        }
        if (this.up) {
            //this.up=true;
            this.element.moveUp(0.5);
        }
        if (this.space) {
            //this.up=true;
             this.element.jump(-6);
        }
    }

    private void clear() {
        this.ctx.restore();
        this.ctx.clearRect(0, 0, this.original_size.getWidth() * Game.SCALE, this.original_size.getHeight() * Game.SCALE);
    }

    public void paint() {
        this.clear();
        this.ctx.clearRect(0, 0, this.original_size.getWidth() * Game.SCALE, this.original_size.getHeight() * Game.SCALE);

        this.element.paint(this.ctx);
        this.elements.forEach( e-> e.paint(this.ctx));

        // this.floor.paint(this.ctx);
    }

    @Override
    protected void onEvent() {
        this.procesInput();
        this.update();
        this.paint();

    }


    @Override
    public void onKeyPressed(KeyCode code) {
        if (code == KeyCode.LEFT) {
            this.left=true;
           //this.element.moveLeft(0.5);
        }
        if (code == KeyCode.RIGHT) {
            this.right=true;
           // this.element.moveRight(0.5);
        }
        if (code == KeyCode.UP) {
            this.up=true;
           // this.element.jump(-3);
        }
        if(code== KeyCode.DOWN)
            this.down=true;
        if(code == KeyCode.SPACE)
            this.space=true;
        if(code== KeyCode.R){
            this.element.setPosition(4,4);
            this.element.setVy(0);
            this.element.setVx(0);
            this.element.setState(IState.State.RUNNING);
            //this.element.setMass(this.element.getMass()+0.25f);
        }
       /* switch (code){
            case LEFT -> this.element.moveLeft(0.5);
            case UP -> {
                if (this.element.canJump())
                    this.element.jump(-3);
            }
            case RIGHT -> this.element.moveRight(0.5);
        }*/


    }

    @Override
    public void onKeyReleased(KeyCode code) {
        if (code == KeyCode.LEFT) {
            this.left=false;
            //this.element.moveLeft(0.5);
        }
        if (code == KeyCode.RIGHT) {
            this.right=false;
            // this.element.moveRight(0.5);
        }
        if (code == KeyCode.UP) {
            this.up=false;
            // this.element.jump(-3);
        }
        if(code== KeyCode.DOWN)
            this.down=false;
        if(code == KeyCode.SPACE)
            this.space=false;

    }

}