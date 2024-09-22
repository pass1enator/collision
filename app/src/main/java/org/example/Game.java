package org.example;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;


public class Game extends Clock implements IKeyListener {

    public static final int SCALE = 3;
    public static Image imagenes = null;
    //private HashMap<String, IScene> scenes;
    //private IScene actual_scene;
    private Dimension2D original_size;

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
    private Camera camera;
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
        this.camera= new Camera(new Point2D(0,0),original);
        this.camera.setDebug(true);
        this.elements = new ArrayList<>();
        this.element = new ElementWithJump(0, 0.2f, 0.0f, 0, 120, 20, 10, 10,1);
        this.element.setDebug(true);
        this.element.setColor(Color.BLUE);
        this.element.activeGravity();
        this.element.setCoefResX(0.5);
        this.element.setCoefResY(0.05);
        this.floor = new Element(0, this.original_size.getHeight() - 10, this.original_size.getWidth()/2, 10);
        this.floor.setColor(Color.RED);
        this.floor.setDebug(true);
        this.floor.setName("Suelo");
        this.elements.add(this.floor);

        this.floor = new Element(this.original_size.getWidth()/2+50, this.original_size.getHeight() - 10, this.original_size.getWidth()/2, 10);
        this.floor.setColor(Color.RED);
        this.floor.setDebug(true);
        this.floor.setName("Suelo2");
        this.elements.add(this.floor);
        this.floor = new Element(this.original_size.getWidth()+50, this.original_size.getHeight() - 10, this.original_size.getWidth()/2, 10);
        this.floor.setColor(Color.RED);
        this.floor.setDebug(true);
        this.floor.setName("Suelo2");
        this.elements.add(this.floor);
        this.p1= new Element(80, this.original_size.getHeight() - 70, 40, 20);
        this.p1.setColor(Color.GREEN);
        this.p1.setDebug(true);
        this.p1.setName("Plataforma 1");
        this.elements.add(this.p1);

        this.p2= new Element(250, this.original_size.getHeight() - 70, 40, 20);
        this.p2.setColor(Color.YELLOW);
        this.p2.setDebug(true);
        this.p2.setName("Plataforma 2");
        this.elements.add(this.p2);

        this.p3= new Element(310, this.original_size.getHeight() - 70, 40, 20);
        this.p3.setColor(Color.GRAY);
        this.p3.setDebug(true);
        this.p3.setName("Plataforma 3");
        this.elements.add(this.p3);

        this.p4= new Element(60, this.original_size.getHeight() - 135, 40, 20);
        this.p4.setColor(Color.GRAY);
        this.p4.setDebug(true);
        this.p4.setName("Plataforma 4");
        this.elements.add(this.p4);

        this.p5= new Element(120, this.original_size.getHeight() - 135, 40, 20);
        this.p5.setColor(Color.ORANGE);
        this.p5.setDebug(true);
        this.p5.setName("Plataforma 5");
        this.elements.add(this.p5);

        this.p6= new Element(230, this.original_size.getHeight() - 135, 40, 20);
        this.p6.setColor(Color.BLUE);
        this.p6.setDebug(true);
        this.p6.setName("Plataforma 6");
        this.elements.add(this.p6);


        this.p6= new Element(170, this.original_size.getHeight() - 180, 40, 20);
        this.p6.setColor(Color.BLUE);
        this.p6.setDebug(true);
        this.p6.setName("Plataforma 7");
        this.elements.add(this.p6);


        this.p6= new Element(230, this.original_size.getHeight() - 205, 40, 20);
        this.p6.setColor(Color.BLUE);
        this.p6.setDebug(true);
        this.p6.setName("Plataforma 8");
        this.elements.add(this.p6);

        this.p6= new Element(150, this.original_size.getHeight() - 240, 40, 20);
        this.p6.setColor(Color.BLUE);
        this.p6.setDebug(true);
        this.p6.setName("Plataforma 9");
        this.elements.add(this.p6);

         this.element.start();


    }


    @Override
    public void start() {
        super.start();
    }

    private void update() {
        this.element.update(this.elements);
        //se actualiza la camara
        this.camera.move(this.element);
    }

    private void procesInput() {
        if (this.left) {

            this.element.moveLeft(0.25);

        }
        if (this.right) {

            var init=this.element.getPosition().getX();

            this.element.moveRight(0.25);

        }
        if (this.down) {

            this.element.moveDown(0.5);
        }
        if (this.up) {

            this.element.moveUp(0.5);
        }
        if (this.space) {

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

        this.element.paint(this.ctx,this.camera.getPosition());
        this.elements.forEach( e-> e.paint(this.ctx,this.camera.getPosition()));
        this.camera.debug(this.ctx,null);
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
            this.camera.reset();
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