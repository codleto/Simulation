package Simulation.action;

import Simulation.Coordinates;
import Simulation.Map;
import Simulation.entity.Animal.Creature;

import Simulation.entity.StaticObject.Grass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static Simulation.Map.*;

public class TurnAction {
    private Coordinates startCell;
    public ArrayList<Coordinates> visitedCells = new ArrayList<>();
    public HashMap<Coordinates, Coordinates> cameFrom = new HashMap<>();
    public ArrayList<Coordinates> pathToFood = new ArrayList<>();
    public static ArrayList<Integer> movesThisTurn = new ArrayList<>();
    public ArrayList<Integer> foodIndicators = new ArrayList<>();

    public void clear() {
        visitedCells.clear();
        cameFrom.clear();
        pathToFood.clear();
        startCell = null;
        foodIndicators.clear();
    }

    public boolean isCellNotVisited(Coordinates coordinates) {
        return !visitedCells.contains(coordinates);
    }

    public void searchForFood(Coordinates coordinates, Creature creature) {
        startCell = coordinates;
        visitedCells.add(coordinates);

        int index = 0;
        boolean foundFood = false;
        while (!foundFood) {
            if (index >= visitedCells.size()) {
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
                if (!isInsideMap(child)) {
                    if (Map.getMap(child) == null) {
                        if (isCellNotVisited(child)) {
                            visitedCells.add(child);
                            cameFrom.put(child, parentCell);
                        }
                    } else if (creature.eat(Map.getEntity(child))) {
                        cameFrom.put(child, parentCell);
                        findPathToFood(child);
                        foundFood = true;
                        break;

                    } else if(Map.getEntity(child) instanceof Grass ) {
                        foodIndicators.add(1);
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
                childCell = cameFrom.get(childCell);
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

    public static void nextTurn(){
        for(Creature herbivore : herbivoreArrayList){
            herbivore.makeMove();
        }
        predatorArrayList.getFirst().makeMove();
    }
}