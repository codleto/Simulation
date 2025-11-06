package Simulation.entity.Animal;

import Simulation.Coordinates;
import Simulation.entity.Entity;

public abstract class Creature extends Entity {
    int id;

    public abstract void makeMove();
    public abstract boolean eat(Entity entity);
    //energy


}
