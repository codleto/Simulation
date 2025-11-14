package Simulation.entity.Animal;
import Simulation.Map;
import Simulation.Search;
import Simulation.entity.Entity;
import Simulation.entity.StaticObject.Grass;

public class Herbivore extends Creature {
    private int stepIndex = 0;

    public Herbivore(int speed) {
        super.setSkin("\uD83D\uDC11");
        this.speed = speed;
    }
    Search search = new Search();


    @Override
    public boolean eat(Entity entity){
        return entity instanceof Grass;
    }

    public void makeMove() {
        while (true) {
            if (!Map.hasFoodFor(this)) {
                break;
            }

            if(this.coordinates == null){
                break;
            }

            if (stepIndex == 0) {
                search.runSearch(this.coordinates, this);
            }

            if (stepIndex >= search.pathToFood.size() && stepIndex > 0) {
                search.clear();
                stepIndex = 0;
                continue;
            }

            if(search.pathToFood.isEmpty()){
                break;
            }

            Map.step(this.coordinates, search.pathToFood.get(stepIndex), this);
            stepIndex++;
            break;
        }
    }
}
