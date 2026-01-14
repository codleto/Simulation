package Simulation;

import static Simulation.World.mapSize;

public class Renderer {

    public static void renderer() {
        for (int gorizontal = mapSize; gorizontal >= 0; gorizontal--) {
            System.out.println();
            for (int vertical = mapSize; vertical >= 0; vertical--){
                if(World.getMap(vertical, gorizontal) == null){
                    System.out.print("\u001b[42m   ");
                } else {
                    System.out.print("\u001b[42m " + World.getSkins(vertical, gorizontal));
                }System.out.print("\u001B[0m");

            }

        }
        System.out.println(" ");

    }
}
