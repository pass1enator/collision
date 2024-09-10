package org.example;

public class ElementWithJump extends ElementWithGravity {

    private boolean jumping;

    public ElementWithJump(double gx, double gy, double vx, double vy, double x, double y, double width, double height,float mass) {
        super(gx, gy, vx, vy, x, y, width, height,mass);
        this.jumping = false;
    }

    public void jump() {

        this.jump(-5);

    }

    public void setNotJump() {
        this.jumping = false;

    }
    @Override
    public void setVy(double vy) {
        super.setVy(vy);

        if(Math.abs(vy)<=0.15f){
            this.vy=0;
            this.setNotJump();
        }
    }
    public void jump(double power) {
        if (!jumping) {
            this.jumping = true;
            this.vy = power;
            this.activeVerticalGravity();
            this.activeGravity();
        }
    }

    public boolean canJump() {
        return !this.jumping;
    }


}
