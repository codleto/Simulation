package Simulation;

import Simulation.Animal.Creature;
import Simulation.Animal.Herbivore;
import Simulation.Animal.Predator;

import java.util.HashMap;

public class Map {
    HashMap<Cordinates, Entity> map = new HashMap<>();


    public void setEntity(Cordinates cordinates, Entity entity) {
        map.put(cordinates, entity);
    }

    public void change(Entity entity, int id){

    }
    public Entity getMap(File file, int a) {
        return map.get(new Cordinates(file, a));
    }
}

