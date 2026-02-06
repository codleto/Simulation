package simulation.action.initaction;

import simulation.action.turnaction.Spawn;
import simulation.entity.animal.Herbivore;
import simulation.entity.animal.Predator;
import simulation.entity.staticobject.Food;
import simulation.entity.staticobject.Rock;
import simulation.entity.staticobject.Tree;
import static simulation.map.WorldMap.CreatureList;

public class WorldFactory {
    private int maxRockCount;
    private int maxGrassCount;
    private int maxTreeCount;
    private final Spawn spawn;

    public WorldFactory(Spawn spawn){
        this.spawn = spawn;
    }

    public int getMaxRockCount() {
        return maxRockCount;
    }
    public int getMaxGrassCount() {
        return maxGrassCount;
    }
    public int getMaxTreeCount() {
        return maxTreeCount;
    }

    public void initializeWorld(GameConfig config) {

        if (config.mapSize() == 4) {
            maxRockCount = 4;
            maxGrassCount = 6;
            maxTreeCount = 4;
        }
        if (config.mapSize() == 5) {
            maxRockCount = 6;
            maxGrassCount = 8;
            maxTreeCount = 6;
        }
        if (config.mapSize() == 10) {
            maxRockCount = 11;
            maxGrassCount = 15;
            maxTreeCount = 11;
        }

        CreatureList.add(new Predator(config.speed(), config.attack()));
        spawn.spawnEntity(new Predator(config.speed(), config.attack()));


        for (int i = 0; i < config.herbivoreCount(); i++) {
            CreatureList.add(new Herbivore());
            spawn.spawnEntity(new Herbivore());

        }

        for (int i = 0; i < getMaxRockCount(); i++) {
            spawn.spawnEntity(new Rock());
        }

        for (int i = 0; i < getMaxGrassCount(); i++) {
            spawn.spawnEntity(new Food());
        }

        for (int i = 0; i < getMaxTreeCount(); i++) {
            spawn.spawnEntity(new Tree());
        }
    }
}
