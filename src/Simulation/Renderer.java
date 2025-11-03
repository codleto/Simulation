package Simulation;

import java.util.HashMap;

public class Renderer {

    public static void renderer() {
        for (int gorizontal = 4; gorizontal >= 0; gorizontal--) {
            System.out.println();
            for (int vertical = 4; vertical >= 0; vertical--){
                if(Map.getMap(vertical, gorizontal) == null){
                    System.out.print("\u001b[42m   ");
                } else {
                    System.out.print("\u001b[42m " + Map.getSkins(vertical, gorizontal));
                }System.out.print("\u001B[0m");

            }
        }

    }
}
