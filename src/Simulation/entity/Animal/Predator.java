package Simulation.entity.Animal;

import Simulation.Coordinates;
import Simulation.Map;
import Simulation.Search;
import Simulation.entity.Entity;

public class Predator extends Creature {
    public Predator(int id) {
        super.setSkin("\uD83D\uDC3A");
        this.id = id;

    }

    Search search = new Search();

    @Override
    public boolean eat(Entity entity){
        return entity instanceof Herbivore;
    }

    public void makeMove() {

        if (!Map.hasFoodFor(this)) { // еды больше нет
            search.clear();
            return;
        }

        search.clear();
        search.runSearch(this.coordinates, this); // если только начали искать еду первый раз

        if(search.pathToFood.isEmpty()){
            Search.breakk.add(1);
            return;
        }


        Map.step(this.coordinates, search.pathToFood.get(0), this);
        this.coordinates = search.pathToFood.get(0);
    }
}
