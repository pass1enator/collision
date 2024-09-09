package org.example;

import javafx.scene.input.KeyCode;
public interface IKeyListener {
    public void onKeyPressed(KeyCode code);
    public void onKeyReleased(KeyCode code);
}
