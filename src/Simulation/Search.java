package Simulation;

import Simulation.entity.Animal.Creature;
import java.util.ArrayList;

public class Search {
    public static ArrayList<Coordinates> obshiy = new ArrayList<>();
    public static ArrayList<ArrayList<Coordinates>> putivod = new ArrayList<>();
    public static ArrayList<Coordinates> inPutevod = new ArrayList<>();



    public static boolean eda(Coordinates coordinates){
        return coordinates == null;
    }


    public static boolean izuchKlet(Coordinates coordinates) {
        return !obshiy.contains(coordinates);
    }

    /*
  1. понять от куда начинаем
  - Методу нужно получить ключ , для этого ему нужно знать значение(то есть сущность)
  - ключ будет стартовой позицией сущности
   */

    public static void alg(Map map, Creature creature) {
        Coordinates startPosition = map.getKey(creature);
        Coordinates position;

        obshiy.add(startPosition);

        int i = 0;// счетчик нужен для счета внутри массивов и поиска индекса
        int j = 0; // счетчик для putevod
        while(true) {
            Coordinates c = obshiy.get(i);
            position = new Coordinates(c.vertical, c.horizontal + 1);
            if (eda(position)) {
                if (izuchKlet(position)) {
                    obshiy.add(position);
                    inPutevod.add(startPosition);
                    inPutevod.add(position);
                    putivod.add(i, inPutevod);
                    j++;
                }
            } else {
                j--;
                putivod.get(j);
            }

            position = new Coordinates(c.vertical + 1, c.horizontal);
            if (eda(position)) {
                if (izuchKlet(position)) {
                    obshiy.add(position);
                    inPutevod.add(startPosition);
                    inPutevod.add(position);
                    putivod.add(i, inPutevod);
                    j++;
                }
            } else {
                j--;
                putivod.get(j);
            }

            position = new Coordinates(c.vertical - 1, c.horizontal);
            if (eda(position)) {
                if (izuchKlet(position)) {
                    obshiy.add(position);
                    inPutevod.add(startPosition);
                    inPutevod.add(position);
                    putivod.add(i, inPutevod);
                    j++;
                }
            } else {
                j--;
                putivod.get(j);
            }

            position = new Coordinates(c.vertical, c.horizontal - 1);
            if (eda(position)) {
                if (izuchKlet(position)) {
                    obshiy.add(position);
                    inPutevod.add(startPosition);
                    inPutevod.add(position);
                    putivod.add(i, inPutevod);
                    j++;
                }
            } else {
                j--;
                putivod.get(j);
                break;
            }

            i++;
        }
    }
}

