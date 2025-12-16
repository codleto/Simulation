package Simulation.entity.Animal;
import Simulation.Map;
import Simulation.action.TurnAction;
import Simulation.entity.Entity;
import Simulation.entity.StaticObject.Grass;

public class Herbivore extends Creature {
    TurnAction turnAction = new TurnAction();
    private int stepIndex = 0;

    public Herbivore() {
        super.setSkin("\uD83D\uDC11");
    }

    @Override
    public boolean eat(Entity entity){
        return entity instanceof Grass;
    }

    public void makeMove() {
        while (true) {
            if (Map.noFoodFor(this)) {
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

            Map.moveCreature(this.coordinates, turnAction.pathToFood.get(stepIndex), this);
            stepIndex++;
            TurnAction.movesThisTurn.add(1);
            break;
        }
    }
}
