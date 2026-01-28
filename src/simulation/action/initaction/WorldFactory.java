package simulation.action.initaction;

import simulation.entity.animal.Herbivore;
import simulation.entity.animal.Predator;
import simulation.entity.staticobject.Food;
import simulation.entity.staticobject.Rock;
import simulation.entity.staticobject.Tree;

import static simulation.action.turnaction.Spawn.spawnEntity;
import static simulation.map.WorldMap.*;
import static simulation.map.WorldMap.herbivoreArrayList;
import static simulation.action.initaction.GameConfig.*;

public class WorldFactory {
    public static void initializeWorld() {
        int maxRockCount = 0;
        int maxGrassCount = 0;
        int maxTreeCount = 0;

        if (mapSize == 4) {
            maxRockCount = 4;
            maxGrassCount = 6;
            maxTreeCount = 4;
        }
        if (mapSize == 5) {
            maxRockCount = 6;
            maxGrassCount = 8;
            maxTreeCount = 6;
        }
        if (mapSize == 10) {
            maxRockCount = 11;
            maxGrassCount = 15;
            maxTreeCount = 11;
        }
        predatorArrayList.add(new Predator(wolfSpeed, wolfAttack));
        spawnEntity(predatorArrayList.getFirst());


        for (int i = 0; i < sheepCount; i++) {
            herbivoreArrayList.add(new Herbivore());
            spawnEntity(herbivoreArrayList.get(i));

        }

        for (int i = 0; i < maxRockCount; i++) {
            spawnEntity(new Rock());
        }

        for (int i = 0; i < maxGrassCount; i++) {
            spawnEntity(new Food());
        }

        for (int i = 0; i < maxTreeCount; i++) {
            spawnEntity(new Tree());
        }
    }

}
