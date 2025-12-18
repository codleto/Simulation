package Simulation.Entity.Animal;


import Simulation.Map;
import Simulation.Action.TurnAction;
import Simulation.Entity.Entity;

public class Predator extends Creature {
    TurnAction turnAction = new TurnAction();
    public Predator(int speed, int attack) {
        super.setSkin("\uD83D\uDC3A");
        this.speed = speed;
        this.attack = attack;
    }

    @Override
    public boolean eat(Entity entity){
        return entity instanceof Herbivore;
    }

    public void makeMove() {
        int i = 1;
        while (speed >= i) {

            if (Map.noFoodFor(this)) { // еды больше нет
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

            Map.moveCreature(this.coordinates, turnAction.pathToFood.getFirst(), this);
            TurnAction.movesThisTurn.add(1);
            i++;
        }
    }
}
