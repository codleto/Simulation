package Simulation.entity.Animal;
import Simulation.Coordinates;
import Simulation.Map;
import Simulation.Search;
import Simulation.entity.Entity;
import Simulation.entity.StaticObject.Grass;

public class Herbivore extends Creature {
    private int stepIndex = 0;

    public Herbivore(int id) {
        this.id = id;
        super.setSkin("\uD83D\uDC11");
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



            if (stepIndex == 0) {
                search.runSearch(this.coordinates, this);
            }

            if (stepIndex >= search.pathToFood.size()) {
                search.clear();
                stepIndex = 0;
                continue;
            }

            if(search.pathToFood.isEmpty()){
                Search.breakk.add(1);
                break;
            }

            Map.step(this.coordinates, search.pathToFood.get(stepIndex), this);
            this.coordinates = search.pathToFood.get(stepIndex);
            stepIndex++;
            break;
        }
    }
}
