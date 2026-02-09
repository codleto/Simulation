package simulation.action.turnaction;

import simulation.entity.Entity;
import simulation.entity.animal.Creature;
import simulation.entity.animal.Herbivore;
import simulation.map.WorldMap;

public class Grass {
    private final WorldMap map;

    public Grass(WorldMap map){
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
            if(entry != creature && creature.eat(entry)){
                return false;
            }
        }
        return true;
    }
}

