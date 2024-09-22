package org.example;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Administrador
 */
public interface IDrawable {

    public void paint(GraphicsContext gc);
    public void paint(GraphicsContext gc, Point2D cameraPosition);
}
