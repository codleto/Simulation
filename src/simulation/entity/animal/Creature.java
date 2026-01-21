package simulation.entity.animal;
import simulation.entity.Entity;

public abstract class Creature extends Entity {
    private static final int MAX_HP = 100;

    protected int hp = MAX_HP;
    private final int speed;

    protected Creature(int speed){
        this.speed = speed;
    }
    public int getSpeed(){
        return this.speed;
    }

    public abstract int getAttack();

    public abstract int getHp();

    public abstract void setHp(int hp);

    public abstract void makeMove();
    public abstract boolean eat(Entity entity);

    public void satisfiedItsHunger(){
        this.hp = MAX_HP;
    }
}
