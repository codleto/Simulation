package Simulation;

import Simulation.entity.Animal.Creature;

import Simulation.entity.Entity;
import java.util.HashMap;
import java.util.Random;

public class Map {

    static Random random = new Random();

    public static HashMap<Coordinates, Entity> map = new HashMap<>();

    public static void setEntity(Entity entity) {
        Coordinates c = genPosition();
        entity.coordinates = c;
        map.put(c, entity);
    }

    public static Coordinates genPosition() {
        while (true) {
            int vertical = random.nextInt(5);
            int horizontal = random.nextInt(5);

            if (map.get(new Coordinates(vertical, horizontal)) == null) {
                return new Coordinates(vertical, horizontal);
            }
        }
    }

    public static Entity getEntity(Coordinates coordinates){
        return map.get(coordinates);
    }

    public static boolean hasFoodFor(Creature creature){
        for(java.util.Map.Entry<Coordinates, Entity> entry : map.entrySet()){
            Coordinates eating = entry.getKey();
            if(creature.eat(eating)){
                return true;
            }
        }
        return false;
    }

    public static void step(Coordinates oldCoordinates , Coordinates newCoordinates, Creature creature){
        Entity animal = map.get(oldCoordinates);
        Entity target = map.get(newCoordinates);

        map.remove(oldCoordinates);

        if( target != null && creature.eat(newCoordinates)){
            map.remove(newCoordinates);
        }
        creature.coordinates = newCoordinates;
        map.put(newCoordinates, animal);
    }


    public static boolean granicPole(Coordinates coordinates) {
        return (coordinates.vertical > 4 || coordinates.vertical < 0 || coordinates.horizontal > 4 || coordinates.horizontal < 0);
    }

    public static Entity getMap(int vertical, int horizontal) {
        return map.get(new Coordinates(vertical, horizontal));
    }

    public static String getSkins(int vertical, int horizontal) {
        Entity skin = map.get(new Coordinates(vertical, horizontal));
        return skin.getSkin();
    }

    public void clear(){
        map.clear();
    }
}

