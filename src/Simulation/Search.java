package Simulation;

import Simulation.entity.Animal.Creature;
import Simulation.entity.StaticObject.Grass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static Simulation.Map.granicPole;

public class Search {
    private Coordinates startCell;
    public ArrayList<Coordinates> visitedCells = new ArrayList<>();
    public HashMap<Coordinates, Coordinates> pathToStartCell = new HashMap<>();
    public ArrayList<Coordinates> pathToFood = new ArrayList<>();
    public static ArrayList<Integer> breakk = new ArrayList<>();
    public ArrayList<Integer> grass = new ArrayList<>();

    public void clear() {
        visitedCells.clear();
        pathToStartCell.clear();
        pathToFood.clear();
        startCell = null;
        grass.clear();
    }

    public boolean isCellNotVisited(Coordinates coordinates) {
        return !visitedCells.contains(coordinates);
    }

    public void runSearch(Coordinates coordinates, Creature creature) {
        startCell = coordinates;
        visitedCells.add(coordinates);

        int index = 0;
        boolean go = true;
        while (go) {
            if (index >= visitedCells.size()) { // если индекс равен или больше размера посещенных клеток
                break;
            }

            Coordinates parentCell = visitedCells.get(index);
            Coordinates[] childCell = {
                    new Coordinates(parentCell.vertical, parentCell.horizontal + 1),
                    new Coordinates(parentCell.vertical + 1, parentCell.horizontal),
                    new Coordinates(parentCell.vertical, parentCell.horizontal - 1),
                    new Coordinates(parentCell.vertical - 1, parentCell.horizontal)
            };

            for (Coordinates child : childCell) {
                if (!granicPole(child)) {
                    if (Map.getMap(child) == null) { // если клетка пустая
                        if (isCellNotVisited(child)) { // если клетку не посещали
                            visitedCells.add(child); // начинаем запись в очередь
                            pathToStartCell.put(child, parentCell);
                        }
                    } else if (creature.eat(Map.getEntity(child))) { // если клетка еда
                        pathToStartCell.put(child, parentCell);
                        findPathToFood(child);
                        go = false;
                        break;

                    } else if(Map.getEntity(child) instanceof Grass ) {
                        grass.add(1);
                    }
                }
            }
            index++;
        }
    }

    public void findPathToFood(Coordinates coordinates) {
        pathToFood.clear();
        Coordinates childCell = coordinates;

        while(true) {
            if (childCell != null && !startCell.equals(childCell)) {
                pathToFood.add(childCell);
                childCell = pathToStartCell.get(childCell);
            }

            if(startCell.equals(childCell)){
                break;
            }

            if (childCell == null) {
                pathToFood.clear();
                return;
            }
        }
        Collections.reverse(pathToFood);
    }
}