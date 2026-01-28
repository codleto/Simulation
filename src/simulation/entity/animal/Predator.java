package simulation.entity.animal;

import simulation.algorithm.BFS;
import simulation.entity.Entity;

import static simulation.action.Food.noFoodFor;
import static simulation.action.Move.moveCreature;

public class Predator extends Creature {
    private final int attack;

    public Predator(int speed, int attack) {
        super(speed);
        this.attack = attack;
        super.skin = "\uD83D\uDC3A";

    }

    public int getAttack(){
        return this.attack;
    }

    @Override
    public int getHp() {
        return this.hp;
    }

    @Override
    public void setHp(int hp){
        if(hp >= 0 && hp <= 100){
            this.hp = hp;
        }
    }

    @Override
    public boolean eat(Entity entity){
        return entity instanceof Herbivore;
    }

    public void makeMove() {// todo: код поиска должен быть одинаковый и находиться в creature
        final BFS bfs = new BFS();
        int i = 1;
        while (getSpeed() >= i) {

            if (noFoodFor(this)) { // еды больше нет
                return;
            }

            bfs.searchForFood(this.coordinates, Herbivore.class); // если только начали искать еду первый раз

            if (bfs.getPathToFood().isEmpty()) { // если все пусто
                return;
            }


            moveCreature(this.coordinates, bfs.getPathToFood().getFirst(), this);
            BFS.movesThisTurn.add(1);
            i++;
        }
    }
}
