package org.example;

public interface IState {

    public enum State {
        RUNNING,
        STOPPED,
        PREDESTROYED,
        DESTROYED,
        PAUSED
    }

    public void stop();

    public void start();

    public void pause();

    public State getState();

    public void setState(State s);
}