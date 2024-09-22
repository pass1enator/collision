package org.example;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
public interface IDebuggable {


    public void setDebug(boolean value);

    /**
     * @return boolean
     */
    public boolean isDebug();

    /**
     * @param gc
     */
    public void debug(GraphicsContext gc, Point2D cameraPosition);

}