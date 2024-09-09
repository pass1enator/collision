package org.example;

import java.util.Optional;

public interface ICollidable {


    public Optional<Collision> collision(Element element);


}