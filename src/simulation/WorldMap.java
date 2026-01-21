package simulation;

import simulation.entity.animal.Creature;

import simulation.entity.animal.Herbivore;
import simulation.entity.Entity;

import java.util.*;

public class WorldMap {
    static Random random = new Random();

    private static final Map<Coordinates, Entity> map = new HashMap<>();
    public static int mapSize = 5;
    public static List<Creature> predatorArrayList = new ArrayList<>();
    public static List<Creature> herbivoreArrayList = new ArrayList<>();

    public static void spawnEntity(Entity entity) {
        Coordinates position = generateRandomPosition();

        entity.coordinates = position;
        addEntity(position, entity);
    }
    public static void addEntity(Coordinates coordinates, Entity entity ){
        map.put(coordinates, entity);
    }
    public static void removeEntity(Coordinates coordinates){
        map.remove(coordinates);
    }
    public static Coordinates generateRandomPosition() {
        while (true) {
            int vertical = random.nextInt(mapSize + 1);
            int horizontal = random.nextInt(mapSize + 1);

            if (map.get(new Coordinates(vertical, horizontal)) == null) {
                return new Coordinates(vertical, horizontal);
            }
        }
    }
    public static boolean isFoodAvailableFor() {
        for (Entity entry : map.values()) {
            if (entry instanceof Herbivore) {
                return false;
            }
        }
        return true;
    }
    public static Entity getEntity(Coordinates coordinates){
        return map.get(coordinates);
    }
    public static boolean noFoodFor(Creature creature){
        for(Entity entry : map.values()){
            if(entry != creature && creature.eat(entry)){
                return false;
            }
        }
        return true;
    }
    public static void moveCreature(Coordinates from , Coordinates to, Creature mover){
        Creature movingCreature = (Creature) map.get(from);
        Entity target = map.get(to);

            if (target != null && mover.eat(target)) {
                if (target instanceof Creature){
                    Creature targetCreature = (Creature)target;
                    targetCreature.setHp(targetCreature.getHp() - movingCreature.getAttack());
                    if(targetCreature.getHp() > 0){
                        return;
                    }
                }
                    removeEntity(to);
                    target.coordinates = null;
                    mover.satisfiedItsHunger();
            }
            if (target != null && !mover.eat(target)) {
                return;
            }

            removeEntity(from);
            mover.coordinates = to;
            addEntity(to, movingCreature);
            Simulation.moveCount++;
    }
    public static boolean isInsideMap(Coordinates coordinates) {
        return (coordinates.row > mapSize || coordinates.row < 0 || coordinates.column > mapSize || coordinates.column < 0);
    }
    public static Entity getMap(Coordinates coordinates){
        return map.get(coordinates);
    }
    public static Entity getMap(int vertical, int horizontal) {
        return map.get(new Coordinates(vertical, horizontal));
    }
    public static String getSkins(int vertical, int horizontal) {
        Entity skin = map.get(new Coordinates(vertical, horizontal));
        return skin.getSkin();
    }
}

