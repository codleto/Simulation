package simulation.action.turnaction;

import simulation.entity.Entity;
import simulation.map.Coordinates;
import simulation.map.WorldMap;

import java.util.Random;


public class Spawn {
    private final WorldMap map;
    private final Random random = new Random();

    public Spawn(WorldMap map){
        this.map = map;
    }

    public void spawnEntity(Entity entity) {
        Coordinates position = generateRandomPosition(map);

        entity.setCoordinates(position);
        map.addEntity(position, entity);
    }

    private Coordinates generateRandomPosition(WorldMap map) {

        while (true) {
            int vertical = random.nextInt(map.getMapSize() + 1);
            int horizontal = random.nextInt(map.getMapSize() + 1);

            if (map.getMap(new Coordinates(vertical, horizontal)) == null) {
                return new Coordinates(vertical, horizontal);
            }
        }
    }
}
