package simulation.algorithm;

import simulation.entity.Entity;
import simulation.map.Coordinates;
import simulation.map.WorldMap;

import java.util.*;

import static simulation.map.WorldMap.*;

public class BFS {
    private Coordinates startCell;
    private final List<Coordinates> visitedCells = new ArrayList<>();
    private final Map<Coordinates, Coordinates> cameFrom = new HashMap<>();
    private final List<Coordinates> pathToFood = new ArrayList<>();
    public static List<Integer> movesThisTurn = new ArrayList<>();

    public List<Coordinates> getPathToFood(){
        return pathToFood;
    }

    private boolean isCellNotVisited(Coordinates coordinates) {
        return !visitedCells.contains(coordinates);
    }

    public void searchForFood(Coordinates coordinates, Class<? extends Entity> food) {
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
            Entity entityAtCell = (WorldMap.getEntity(child));
                if (!isInsideMap(child)) {
                    if (entityAtCell == null) {
                        if (isCellNotVisited(child)) {
                            visitedCells.add(child);
                            cameFrom.put(child, parentCell);
                        }
                    } else if (food.isInstance(entityAtCell)) {
                        cameFrom.put(child, parentCell);
                        findPathToFood(child);
                        foundFood = true;
                        break;

                    }
                }
            }
            index++;
        }
    }

    private void findPathToFood(Coordinates coordinates) {
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
}