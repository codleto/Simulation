package Simulation.Entity.Animal;
import Simulation.World;
import Simulation.action.TurnAction;
import Simulation.Entity.Entity;
import Simulation.Entity.StaticObject.Eat;

public class Herbivore extends Creature {
    TurnAction turnAction = new TurnAction();
    private int stepIndex = 0;

    public Herbivore() {
        super.setSkin("\uD83D\uDC11");
    }

    @Override
    public boolean eat(Entity entity){
        return entity instanceof Eat;
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

    public void makeMove() {
        while (true) {
            if (World.noFoodFor(this)) {
                break;
            }

            if(this.coordinates == null){
                break;
            }

            if (stepIndex == 0) {
                turnAction.searchForFood(this.coordinates, this);
            }

            if (stepIndex >= turnAction.pathToFood.size() && stepIndex > 0) {
                turnAction.clear();
                stepIndex = 0;
                continue;
            }

            if (turnAction.pathToFood.isEmpty() && turnAction.foodIndicators.isEmpty()) {// если все пусто
                return;
            }

            if(turnAction.pathToFood.isEmpty()){
                break;
            }

            World.moveCreature(this.coordinates, turnAction.pathToFood.get(stepIndex), this);
            stepIndex++;
            TurnAction.movesThisTurn.add(1);
            break;
        }
    }
}
