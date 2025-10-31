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

}
