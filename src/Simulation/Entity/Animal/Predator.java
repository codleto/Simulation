package Simulation.Entity.Animal;

import Simulation.World;
import Simulation.action.TurnAction;
import Simulation.Entity.Entity;

public class Predator extends Creature {
    TurnAction turnAction = new TurnAction();

    public Predator(int speed, int attack) {
        super.setSkin("\uD83D\uDC3A");
        setAttack(attack);
        setSpeed(speed);
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
    public void setSpeed(int speed) {
        if(speed >= 0){
            this.speed = speed;
        }
    }

    @Override
    public int getAttack() {
        return this.attack;
    }

    @Override
    public void setAttack(int attack) {
        if (attack >= 0) {
            this.attack = attack;
        }
    }

    @Override
    public boolean eat(Entity entity){
        return entity instanceof Herbivore;
    }

    public void makeMove() {
        int i = 1;
        while (speed >= i) {

            if (World.noFoodFor(this)) { // еды больше нет
                turnAction.clear();
                return;
            }

            turnAction.clear();
            turnAction.searchForFood(this.coordinates, this); // если только начали искать еду первый раз

            if (turnAction.pathToFood.isEmpty() && turnAction.foodIndicators.isEmpty()) { // если все пусто
                return;
            }

            if (turnAction.pathToFood.isEmpty()) {// если путь пустой
                return;
            }

            World.moveCreature(this.coordinates, turnAction.pathToFood.getFirst(), this);
            TurnAction.movesThisTurn.add(1);
            i++;
        }
    }
}
