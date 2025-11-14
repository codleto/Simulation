package Simulation.entity.Animal;
import Simulation.entity.Entity;

public abstract class Creature extends Entity {
    int id;
    int hp = 100;
    int speed;
    int attack;

    public abstract void makeMove();
    public abstract boolean eat(Entity entity);

    public void satisfiedItsHunger(){ // нашли еду - восстанавливаем хп
        this.hp = 100;
    }
}
