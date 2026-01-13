package Simulation.Entity.Animal;
import Simulation.Entity.Entity;

public abstract class Creature extends Entity {
    protected int hp = 100;
    protected int speed = 1;
    protected int attack = 1;

    public abstract int getHp();
    public abstract void setHp(int hp);

    public abstract void setSpeed(int speed);

    public abstract int getAttack();
    public abstract void setAttack(int attack);

    public abstract void makeMove();
    public abstract boolean eat(Entity entity);

    public void satisfiedItsHunger(){
        this.hp = 100;
    }
}
