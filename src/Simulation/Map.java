package Simulation;

import Simulation.entity.Animal.Creature;

import Simulation.entity.Animal.Herbivore;
import Simulation.entity.Entity;
import Simulation.entity.StaticObject.Grass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Map {

    static Random random = new Random();

    private static HashMap<Coordinates, Entity> map = new HashMap<>();
    public static int mapSize = 5;
    public static ArrayList<Creature> predatorArrayList = new ArrayList<>();
    public static ArrayList<Creature> herbivoreArrayList = new ArrayList<>();

    public static void setEntity(Entity entity) {
        Coordinates c = genPosition();

        entity.coordinates = c;
        addEntity(c, entity);
    }

    public static void addEntity(Coordinates coordinates, Entity entity ){
        map.put(coordinates, entity);
    }

    public static void removeEntity(Coordinates coordinates){
        map.remove(coordinates);
    }

    public static Coordinates genPosition() {
        while (true) {
            int vertical = random.nextInt(mapSize + 1);
            int horizontal = random.nextInt(mapSize + 1);

            if (map.get(new Coordinates(vertical, horizontal)) == null) {
                return new Coordinates(vertical, horizontal);
            }
        }
    }

    public static boolean mapEat() {
        boolean a = false;
        boolean b = false;
        for (Entity entry : map.values()) {
            if (entry instanceof Grass) {
                a = true;
            }
            if (entry instanceof Herbivore) {
                b = true;
            }
        }
        return !b;
    }

    public static Entity getEntity(Coordinates coordinates){
        return map.get(coordinates);
    }

    public static boolean hasFoodFor(Creature creature){
        for(Entity entry : map.values()){
            if(entry != creature && creature.eat(entry)){
                return true;
            }
        }
        return false;
    }

    public static void step(Coordinates oldCoordinates , Coordinates newCoordinates, Creature creature){
        Creature animal = (Creature) map.get(oldCoordinates);
        Entity target = map.get(newCoordinates);

            if (target != null && creature.eat(target)) {// если новая клетка занята и она еда то удаляем новую клетку и старую
                if (target instanceof Creature){
                    Creature t = (Creature)target;
                    t.hp -= animal.attack;
                    if(t.hp > 0){
                        return;
                    }
                }
                    removeEntity(newCoordinates);
                    target.coordinates = null;
                    creature.satisfiedItsHunger();
            }
            if (target != null && !creature.eat(target)) { // если нова клетка занята и она не еда то сваливаем на след ход
                return;
            }

            removeEntity(oldCoordinates);
            creature.coordinates = newCoordinates;
            addEntity(newCoordinates, animal);
            Simulation.moveCount++;
    }


    public static boolean granicPole(Coordinates coordinates) {
        return (coordinates.vertical > mapSize || coordinates.vertical < 0 || coordinates.horizontal > mapSize || coordinates.horizontal < 0);
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

    public void clear(){
        map.clear();
    }
}

