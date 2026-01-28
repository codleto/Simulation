package simulation.action;

import simulation.map.Coordinates;

import java.util.Random;
import static simulation.map.WorldMap.*;

import static simulation.map.WorldMap.mapSize;

public class RandomPosition {
    private static final Random random = new Random();

    public static Coordinates generateRandomPosition() {
        while (true) {
            int vertical = random.nextInt(mapSize + 1);
            int horizontal = random.nextInt(mapSize + 1);

            if (getMap(new Coordinates(vertical, horizontal)) == null) {
                return new Coordinates(vertical, horizontal);
            }
        }
    }
}
