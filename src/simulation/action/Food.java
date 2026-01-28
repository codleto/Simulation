package simulation.action;

import simulation.entity.Entity;
import simulation.entity.animal.Creature;
import simulation.entity.animal.Herbivore;

import static simulation.map.WorldMap.getValue;

public class Food { //todo: проверить на S

    public static boolean isFoodAvailableFor() {
        for (Entity entry : getValue()) {
            if (entry instanceof Herbivore) {
                return false;
            }
        }
        return true;
    }

    public static boolean noFoodFor(Creature creature){
        for(Entity entry : getValue()){
            if(entry != creature && creature.eat(entry)){
                return false;
            }
        }
        return true;
    }
}

