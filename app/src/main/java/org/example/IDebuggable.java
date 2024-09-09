package org.example;
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
    public void debug(GraphicsContext gc);

}