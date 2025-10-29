package Simulation;

import Simulation.entity.Animal.Creature;

import java.util.ArrayList;

public class Search {
    public static ArrayList<Coordinates> obshiy = new ArrayList<>();
    public static ArrayList<ArrayList<Coordinates>> putivod = new ArrayList<>();

    public static boolean estPrepatstvie(Map map) {
        return map != null;
    }

    public static boolean izuchKlet(Coordinates coordinates) {
        return !obshiy.contains(coordinates);
    }

    public static void alg(Map map, Creature creature) {
        Coordinates startPosition = map.getKey(creature);
        Coordinates position = map.getKey(creature);

        obshiy.add(startPosition);

        while(true) {
            if(izuchKlet(position)) {
                obshiy.add(position);
                // короче тут если такой координаты в списке нет значит добавлем ее в список и нужно придумать как
                // добавить эту координату в список и прошлую и что добавлять в putivod ну на листе логика вроде изложена

            }
        }
    }
}

