package Simulation.Entity.Animal;
import Simulation.Entity.Entity;

public abstract class Creature extends Entity {
    public int hp = 100;
    public int speed = 1;
    public int attack = 1;

    public abstract void makeMove();
    public abstract boolean eat(Entity entity);

    public void satisfiedItsHunger(){
        this.hp = 100;
    }
}
