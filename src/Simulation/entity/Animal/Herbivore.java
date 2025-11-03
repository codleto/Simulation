package Simulation.entity.Animal;
import Simulation.Map;
import Simulation.Search;

public class Herbivore extends Creature {
    private int stepIndex = 0;

    public Herbivore(int id) {
        this.id = id;
        this.skin = "\uD83D\uDC11";
    }
    Search search = new Search();

    public void makeMove() {
        while (true) {
            if (stepIndex == 0) {
                search.runSearch(this.coordinates);
            }

            if (stepIndex >= search.pathToFood.size()) {
                search.clear();
                stepIndex = 0;
                continue;
            }
            Map.chooch(this.coordinates, search.pathToFood.get(stepIndex));
            this.coordinates = search.pathToFood.get(stepIndex);
            stepIndex++;
            break;
        }
    }
}
