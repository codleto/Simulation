package simulation.map;

import simulation.entity.animal.Creature;
import simulation.entity.Entity;
import java.util.*;

public class WorldMap {

    private final Map<Coordinates, Entity> map = new HashMap<>();
    private final int mapX;
    private final int mapY;
    public final List<Creature> CreatureList = new ArrayList<>();

    public WorldMap(int x, int y){
        this.mapX = x;
        this.mapY = y;
    }

    public int getMapX() {
        return mapX;
    }

    public int getMapY() {
        return mapY;
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        map.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates) {
        map.remove(coordinates);
    }

    public Collection<Entity> getValue() {
        return map.values();
    }

    public Entity getEntity(Coordinates coordinates) {
        return map.get(coordinates);
    }

    public Entity getEntity(int x, int y) {
        return map.get(new Coordinates(x, y));
    }


    public boolean isInsideMap(Coordinates coordinates) {
        return (coordinates.getX() > mapY || coordinates.getX() < 0 || coordinates.getY() > mapX || coordinates.getY() < 0);
    }

    public Entity getMap(int x, int y) {
        return map.get(new Coordinates(x, y));
    }

    public Entity getMap(Coordinates coordinates) {
        return map.get(coordinates);
    }
}

