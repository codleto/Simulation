package Simulation.action;

import Simulation.Coordinates;
import Simulation.World;
import Simulation.Entity.Animal.Creature;

import Simulation.Entity.StaticObject.Food;

import java.util.*;

import static Simulation.World.*;

public class TurnAction {
    private Coordinates startCell;
    public List<Coordinates> visitedCells = new ArrayList<>();
    public Map<Coordinates, Coordinates> cameFrom = new HashMap<>();
    public List<Coordinates> pathToFood = new ArrayList<>();
    public static List<Integer> movesThisTurn = new ArrayList<>();
    public List<Integer> foodIndicators = new ArrayList<>();

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
            if (parentCell == null){
                return;
            }
            Coordinates[] childCell = {
                    new Coordinates(parentCell.row, parentCell.column + 1),
                    new Coordinates(parentCell.row + 1, parentCell.column),
                    new Coordinates(parentCell.row, parentCell.column - 1),
                    new Coordinates(parentCell.row - 1, parentCell.column)
            };

            for (Coordinates child : childCell) {
                if (!isInsideMap(child)) {
                    if (World.getMap(child) == null) {
                        if (isCellNotVisited(child)) {
                            visitedCells.add(child);
                            cameFrom.put(child, parentCell);
                        }
                    } else if (creature.eat(World.getEntity(child))) { //
                        cameFrom.put(child, parentCell);
                        findPathToFood(child);
                        foundFood = true;
                        break;

                    } else if(World.getEntity(child) instanceof Food) {
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