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

    public void clear(){
    visitedCells.clear();
    parentMap.clear();
    pathToFood.clear();
}

    public boolean isCellNotVisited(Coordinates coordinates) {
        return !visitedCells.contains(coordinates);
    }



    public void runSearch(Coordinates coordinates, Creature creature) {
        start = coordinates;
        visitedCells.add(coordinates);
        int index = 0;
        while(true){
            if(hasFoodFor(creature)){
                break;
            }
            if(index >= visitedCells.size()){
                break;
            }
            Coordinates parentCell = visitedCells.get(index)
                    ;
            Coordinates childCell = new Coordinates(parentCell.vertical, parentCell.horizontal +1);
            if(!granicPole(childCell)) {
                if (Map.getMap(childCell.vertical, childCell.horizontal) == null) {
                    if (isCellNotVisited(childCell)) {
                        visitedCells.add(childCell);
                        parentMap.put(childCell, parentCell);
                    }
                } else if (creature.eat(childCell)) {
                    parentMap.put(childCell, parentCell);
                    findPathToFood(childCell);
                    break;
                }
            }

            childCell = new Coordinates(parentCell.vertical + 1, parentCell.horizontal);
            if(!granicPole(childCell)) {
                if (Map.getMap(childCell.vertical, childCell.horizontal) == null) {
                    if (isCellNotVisited(childCell)) {
                        visitedCells.add(childCell);
                        parentMap.put(childCell, parentCell);
                    }
                } else if (creature.eat(childCell)) {
                    parentMap.put(childCell, parentCell);
                    findPathToFood(childCell);
                    break;

                }
            }

            childCell = new Coordinates(parentCell.vertical, parentCell.horizontal -1);
            if(!granicPole(childCell)) {
                if (Map.getMap(childCell.vertical, childCell.horizontal) == null) {
                    if (isCellNotVisited(childCell)) {
                        visitedCells.add(childCell);
                        parentMap.put(childCell, parentCell);
                    }
                } else if (creature.eat(childCell)) {
                    parentMap.put(childCell, parentCell);
                    findPathToFood(childCell);
                    break;

                }
            }

            childCell = new Coordinates(parentCell.vertical -1, parentCell.horizontal);
            if(!granicPole(childCell)) {
                if (Map.getMap(childCell.vertical, childCell.horizontal) == null) {
                    if (isCellNotVisited(childCell)) {
                        visitedCells.add(childCell);
                        parentMap.put(childCell, parentCell);
                    }
                } else if (creature.eat(childCell)) {
                    parentMap.put(childCell, parentCell);
                    findPathToFood(childCell);
                    break;
                }
            }
            index++;
        }
    }

    public  void findPathToFood(Coordinates coordinates){
        pathToFood.add(coordinates);
        Coordinates childCell = coordinates;
        while(true) {
            if (!start.equals(childCell)) {
                Coordinates parentCell = parentMap.get(childCell);
                pathToFood.add(parentCell);
                childCell = parentCell;
            }else{
                Collections.reverse(pathToFood);
                pathToFood.remove(0);
                break;
            }
        }
    }
}

