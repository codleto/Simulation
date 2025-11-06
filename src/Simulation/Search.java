package Simulation;

import Simulation.entity.Animal.Creature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static Simulation.Map.hasFoodFor;
import static Simulation.Map.granicPole;

public class Search {
    private Coordinates start;
    public ArrayList<Coordinates> visitedCells = new ArrayList<>();
    public HashMap<Coordinates, Coordinates> parentMap = new HashMap<>();
    public ArrayList<Coordinates> pathToFood = new ArrayList<>();
    public static ArrayList<Integer> breakk = new ArrayList<>();

    public void clear() {
        visitedCells.clear();
        parentMap.clear();
        pathToFood.clear();
        start = null;
    }

    public boolean isCellNotVisited(Coordinates coordinates) {
        return !visitedCells.contains(coordinates);
    }


    public void runSearch(Coordinates coordinates, Creature creature) {
        start = coordinates;
        visitedCells.add(coordinates);
        int index = 0;
        while (true) {

            if (index >= visitedCells.size()) { // если индекс равен или больше размера посещенных клеток
                break;
            }
            Coordinates parentCell = visitedCells.get(index);
            Coordinates childCell = new Coordinates(parentCell.vertical, parentCell.horizontal + 1);
            if (!granicPole(childCell)) {
                if (Map.getMap(childCell.vertical, childCell.horizontal) == null) { // если клетка пустая
                    if (isCellNotVisited(childCell)) { // если клетку не посещали
                        visitedCells.add(childCell); // начинаем запись в очередь
                        parentMap.put(childCell, parentCell);
                    }
                } else if (creature.eat(Map.getEntity(childCell))) { // если клетка еда
                    parentMap.put(childCell, parentCell);
                    findPathToFood(childCell);
                    break;
                } else if(!creature.eat(Map.getEntity(childCell))){ // если клетка не еда
                    break;
                }
            }

            childCell = new Coordinates(parentCell.vertical + 1, parentCell.horizontal);
            if (!granicPole(childCell)) {
                if (Map.getMap(childCell.vertical, childCell.horizontal) == null) {
                    if (isCellNotVisited(childCell)) {
                        visitedCells.add(childCell);
                        parentMap.put(childCell, parentCell);
                    }
                } else if (creature.eat(Map.getEntity(childCell))) {
                    parentMap.put(childCell, parentCell);
                    findPathToFood(childCell);
                    break;
                } else if(!creature.eat(Map.getEntity(childCell))) {
                    break;
                }
            }

            childCell = new Coordinates(parentCell.vertical, parentCell.horizontal - 1);
            if (!granicPole(childCell)) {
                if (Map.getMap(childCell.vertical, childCell.horizontal) == null) {
                    if (isCellNotVisited(childCell)) {
                        visitedCells.add(childCell);
                        parentMap.put(childCell, parentCell);
                    }
                } else if (creature.eat(Map.getEntity(childCell))) {
                    parentMap.put(childCell, parentCell);
                    findPathToFood(childCell);
                    break;
                } else if(!creature.eat(Map.getEntity(childCell))){
                    break;
                }
            }

            childCell = new Coordinates(parentCell.vertical - 1, parentCell.horizontal);
            if (!granicPole(childCell)) {
                if (Map.getMap(childCell.vertical, childCell.horizontal) == null) {
                    if (isCellNotVisited(childCell)) {
                        visitedCells.add(childCell);
                        parentMap.put(childCell, parentCell);
                    }
                } else if (creature.eat(Map.getEntity(childCell))) {
                    parentMap.put(childCell, parentCell);
                    findPathToFood(childCell);
                    break;
                } else if(!creature.eat(Map.getEntity(childCell))){
                    break;
                }
            }
            index++;
        }
    }

    public void findPathToFood(Coordinates coordinates) {
        pathToFood.clear();
        Coordinates childCell = coordinates;
        while(true) {
            if (childCell != null && !start.equals(childCell)) {
                pathToFood.add(childCell);
                childCell = parentMap.get(childCell);
            }
            if(start.equals(childCell)){
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
// теперь первый элемент — следующий шаг