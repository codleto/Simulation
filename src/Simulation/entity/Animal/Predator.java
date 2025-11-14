package Simulation.entity.Animal;


import Simulation.Map;
import Simulation.Search;
import Simulation.entity.Entity;

public class Predator extends Creature {
    public Predator(int speed, int attack) {
        super.setSkin("\uD83D\uDC3A");
        this.speed = speed;
        this.attack = attack;
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

        if(search.pathToFood.isEmpty() && search.grass.isEmpty()){ // если все пусто
            Search.breakk.add(1);
            return;
        }

        if(search.pathToFood.isEmpty()){ // если путь пустой
            return;
        }

        Map.step(this.coordinates, search.pathToFood.getFirst(), this);
    }
}
