package Simulation;

import Simulation.entity.Animal.Creature;
import Simulation.entity.StaticObject.Grass;

import java.util.ArrayList;
import java.util.HashMap;
import static Simulation.Map.granicPole;

public class Search {
    public static ArrayList<Coordinates> visitedCells = new ArrayList<>();
    public static HashMap<Coordinates, Coordinates> parentMap = new HashMap<>();
    public static ArrayList<Coordinates> pathToFood = new ArrayList<>();

    public static boolean isCellVisited(Coordinates coordinates) {
        return !visitedCells.contains(coordinates);
    }

    public static boolean hasGrass(Map map, Coordinates coordinates){
        return map.getEntity(coordinates) instanceof Grass;
    }

    public static void runSearch(Map map, Creature creature) {
        Coordinates startPosition = creature.coordinates;

        visitedCells.add(startPosition);
        int index = 0;
        while(true){
            Coordinates c = visitedCells.get(index);
            Coordinates s = new Coordinates(c.horizontal, c.vertical +1);
            if(!granicPole(s)) {
                if (map.getMap(s.horizontal, s.vertical) == null) {
                    if (isCellVisited(s)) {
                        visitedCells.add(s);
                        parentMap.put(s, c);
                    }
                } else if (hasGrass(map, s)) {
                    parentMap.put(s, c);
                    break;

                }
            }

            s = new Coordinates(c.horizontal + 1, c.vertical);
            if(!granicPole(s)) {
                if (map.getMap(s.horizontal, s.vertical) == null) {
                    if (isCellVisited(s)) {
                        visitedCells.add(s);
                        parentMap.put(s, c);
                    }
                } else if (hasGrass(map, s)) {
                    parentMap.put(s, c);
                    break;

                }
            }

            s = new Coordinates(c.horizontal, c.vertical -1);
            if(!granicPole(s)) {
                if (map.getMap(s.horizontal, s.vertical) == null) {
                    if (isCellVisited(s)) {
                        visitedCells.add(s);
                        parentMap.put(s, c);
                    }
                } else if (hasGrass(map, s)) {
                    parentMap.put(s, c);
                    break;

                }
            }

            s = new Coordinates(c.horizontal -1, c.vertical);
            if(!granicPole(s)) {
                if (map.getMap(s.horizontal, s.vertical) == null) {
                    if (isCellVisited(s)) {
                        visitedCells.add(s);
                        parentMap.put(s, c);
                    }
                } else if (hasGrass(map, s)) {
                    parentMap.put(s, c);
                    //findPathToFood(s);
                    break;

                }
            }

            index++;
        }



    }
}
