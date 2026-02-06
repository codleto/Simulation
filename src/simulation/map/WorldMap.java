package simulation.map;

import simulation.entity.animal.Creature;
import simulation.entity.Entity;
import java.util.*;

public class WorldMap {

    private final Map<Coordinates, Entity> map = new HashMap<>();
    private int mapSize = 5;
    public static List<Creature> CreatureList = new ArrayList<>();

    public void setMapSize(int mapSize) {
        if(mapSize >= 5){
            this.mapSize = mapSize;
        }
    }

    public int getMapSize(){
        return mapSize;
    }

    public void addEntity(Coordinates coordinates, Entity entity ){
        map.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates){
        map.remove(coordinates);
    }

    public Collection<Entity> getValue(){
        return map.values();
    }

    public Entity getEntity(Coordinates coordinates){
        return map.get(coordinates);
    }

    public Entity getEntity(int row, int column) {
        return map.get(new Coordinates(row, column));
    }


    public boolean isInsideMap(Coordinates coordinates) {
        return (coordinates.row > mapSize || coordinates.row < 0 || coordinates.column > mapSize || coordinates.column < 0);
    }

    public Entity getMap(int vertical, int horizontal) {
        return map.get(new Coordinates(vertical, horizontal));
    }

    public Entity getMap(Coordinates coordinates) {
        return map.get(coordinates);
    }
}

