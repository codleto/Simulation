package Simulation;

import Simulation.entity.Entity;

import java.util.HashMap;
import java.util.Random;

public class Map {

    Random r = new Random();
    public HashMap<Coordinates, Entity> map = new HashMap<>();

    public void setEntity(Entity entity) {
        Coordinates c = genPosition();
        map.put(c, entity);
    }

    public Entity getMap(int vertical, int horizontal) {
        return map.get(new Coordinates(vertical, horizontal));
    }

    public Coordinates genPosition() {
        while (true) {
            int vertical = r.nextInt(5);
            int horizontal = r.nextInt(5);

            if (map.get(new Coordinates(vertical, horizontal)) == null) {
                return new Coordinates(vertical, horizontal);
            }
        }
    }

    public String getSkins(int vertical, int horizontal) {
        Entity skin = map.get(new Coordinates(vertical, horizontal));
        return skin.getSkin();
    }

    public void clear(){
        map.clear();
    }
}

