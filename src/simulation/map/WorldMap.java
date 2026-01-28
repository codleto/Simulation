package simulation.map;

import simulation.entity.animal.Creature;
import simulation.entity.Entity;
import java.util.*;

public class WorldMap {

    private static final Map<Coordinates, Entity> map = new HashMap<>();
    public static int mapSize = 5;
    public static List<Creature> predatorArrayList = new ArrayList<>(); // todo: сделать один список
    public static List<Creature> herbivoreArrayList = new ArrayList<>();// todo: сделать один список

    public static void addEntity(Coordinates coordinates, Entity entity ){
        map.put(coordinates, entity);
    }

    public static void removeEntity(Coordinates coordinates){
        map.remove(coordinates);
    }

    public static Collection<Entity> getValue(){
        return map.values();
    }

    public static Entity getEntity(Coordinates coordinates){
        return map.get(coordinates);
    }

    public static Entity getEntity(int row, int column) {
        return map.get(new Coordinates(row, column));
    }


    public static boolean isInsideMap(Coordinates coordinates) {
        return (coordinates.row > mapSize || coordinates.row < 0 || coordinates.column > mapSize || coordinates.column < 0);
    }

    public static Entity getMap(int vertical, int horizontal) {
        return map.get(new Coordinates(vertical, horizontal));
    }

    public static Entity getMap(Coordinates coordinates) {
        return map.get(coordinates);
    }
}

