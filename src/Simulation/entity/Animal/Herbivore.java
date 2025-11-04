package Simulation.entity.Animal;
import Simulation.Coordinates;
import Simulation.Map;
import Simulation.Search;
import Simulation.entity.StaticObject.Grass;

public class Herbivore extends Creature {
    private int stepIndex = 0;

    public Herbivore(int id) {
        this.id = id;
        super.setSkin("\uD83D\uDC11");
    }
    Search search = new Search();

    public boolean eat(Coordinates coordinates){
        return Map.getEntity(coordinates) instanceof Grass;
    }

    public void makeMove() {
        while(true) {
            if (stepIndex == 0) {
                search.runSearch(this.coordinates, this);
            }

            if (stepIndex >= search.pathToFood.size()) {
                search.clear();
                stepIndex = 0;
                return;
            }
            Map.chooch(this.coordinates, search.pathToFood.get(stepIndex), this);
            this.coordinates = search.pathToFood.get(stepIndex);
            stepIndex++;
            break;
        }
    }
}
