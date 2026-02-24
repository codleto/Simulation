package simulation.action.turnaction;

import simulation.entity.Entity;
import simulation.entity.animal.Creature;
import simulation.entity.animal.Herbivore;
import simulation.map.WorldMap;

public class FoodService {
    private final WorldMap map;

    public FoodService(WorldMap map){
        this.map = map;
    }

    public boolean isFoodAvailableFor() {
        for (Entity entry : map.getValue()) {
            if (entry instanceof Herbivore) {
                return false;
            }
        }
        return true;
    }

    public boolean noFoodFor(Creature creature){
        for(Entity entry : map.getValue()){
            if(creature.eat(entry)){
                return false;
            }
        }
        return true;
    }
}

