package Simulation;

import java.util.HashMap;
import java.util.Random;

public class Map {
    Random r = new Random();
    public HashMap<Coordinates, Entity> map = new HashMap<>();



    public void setEntity(Entity entity) {
        genPosition(entity);
        map.put(entity.coordinates, entity);
    }

    public void setEntityTwo(Entity entity) {
        genPosition(entity);
        map.put(entity.coordinates, entity);
    }

    public Entity getMap(int vertical, int gorizontal) {
        return map.get(new Coordinates(vertical, gorizontal));
    }

    public void genPosition(Entity entity) {
        while (true) {
            int vertical = r.nextInt(5) + 1;
            int gorizantal = r.nextInt(5) + 1;

            if (map.get(new Coordinates(vertical, gorizantal)) == null) {
                entity.coordinates = new Coordinates(vertical, gorizantal);
                break;
            }
        }
    }

    public String getSkins(int vertical, int gorizontal) {
        Entity skin = map.get(new Coordinates(vertical, gorizontal));
        return skin.getSkin();
    }

    public void clear(){
        map.clear();
    }
}

