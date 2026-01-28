package simulation.action.turnaction;

import simulation.entity.Entity;
import simulation.map.Coordinates;

import static simulation.action.RandomPosition.generateRandomPosition;
import static simulation.map.WorldMap.addEntity;

public class Spawn {

    public static void spawnEntity(Entity entity) {
        Coordinates position = generateRandomPosition();

        entity.coordinates = position; //todo: нужно инкапсулировать
        addEntity(position, entity);
    }
}
