package Simulation.Animal;

import Simulation.Cordinates;
import Simulation.Entity;
import Simulation.Vertical;

public abstract class Creature extends Entity {
    int id;
    public Creature move;
    //energy
    public Cordinates position(Creature creature){
       return creature.cordinates;
    }

    public void setPosititon(Cordinates cordinates, Creature creature, Vertical x, int y){
    }

}
