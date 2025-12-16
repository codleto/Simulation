package Simulation;

import static Simulation.Map.mapSize;

public class Renderer {

    public static void renderer() {
        for (int gorizontal = mapSize; gorizontal >= 0; gorizontal--) {
            System.out.println();
            for (int vertical = mapSize; vertical >= 0; vertical--){
                if(Map.getMap(vertical, gorizontal) == null){
                    System.out.print("\u001b[42m   ");
                } else {
                    System.out.print("\u001b[42m " + Map.getSkins(vertical, gorizontal));
                }System.out.print("\u001B[0m");

            }

        }
        System.out.println(" ");

    }
}
