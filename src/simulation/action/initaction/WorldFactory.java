package simulation.action.initaction;

import simulation.entity.Entity;
import simulation.entity.animal.Creature;
import simulation.entity.animal.Herbivore;
import simulation.entity.animal.Predator;
import simulation.entity.staticobject.Food;
import simulation.entity.staticobject.Rock;
import simulation.entity.staticobject.Tree;
import simulation.map.Coordinates;
import simulation.map.WorldMap;

import java.util.Random;

import static simulation.map.WorldMap.CreatureList;

public class WorldFactory {
    private int herbivoreCount;
    private int rockCount;
    private int grassCount;
    private int treeCount;
    private final WorldMap map;

    public WorldFactory(WorldMap map){
        this.map = map;
    }
    private final Random random = new Random();

    public int getHerbivoreCount() {
        return herbivoreCount;
    }
    public int getMaxRockCount() {
        return rockCount;
    }
    public int getMaxGrassCount() {
        return grassCount;
    }
    public int getMaxTreeCount() {
        return treeCount;
    }

    public void initializeWorld(GameConfig config) {
        int total = config.totalCells();

        herbivoreCount = config.herbivoreCount();
        rockCount = (int) (total * config.rockPercent());
        grassCount = (int) (total * config.grassPercent());
        treeCount = (int) (total * config.treePercent());


        CreatureList.add(new Predator(config.speed(), config.attack()));

        for (int i = 0; i < getHerbivoreCount(); i++) {
            CreatureList.add(new Herbivore());
        }

        for (int i = 0; i < getMaxRockCount(); i++) {
            spawnEntity(new Rock());
        }

        for (int i = 0; i < getMaxGrassCount(); i++) {
            spawnEntity(new Food());
        }

        for (int i = 0; i < getMaxTreeCount(); i++) {
            spawnEntity(new Tree());
        }

        for (Creature creature : CreatureList) {
            spawnEntity(creature);
        }
    }
    public void spawnEntity(Entity entity) {
        Coordinates position = generateRandomPosition(map);

        entity.setCoordinates(position);
        map.addEntity(position, entity);
    }

    private Coordinates generateRandomPosition(WorldMap map) {

        while (true) {
            int vertical = random.nextInt(map.getMapVertical() + 1);
            int horizontal = random.nextInt(map.getMapHorizontal() + 1);

            if (map.getMap(new Coordinates(vertical, horizontal)) == null) {
                return new Coordinates(vertical, horizontal);
            }
        }
    }
}
