package Simulation;

public class Renderer {

    public static void renderer(Map map) {
        for (int rank = 10; rank >= 1; rank--) {
            System.out.println();
            for (File file : File.values()) {
                if(map.getMap(file, rank) == null){
                    System.out.print("\u001b[42m   ");
                } else {
                    System.out.print("\u001b[41m \uD83D\uDC3A");
                }System.out.print("\u001B[0m");


            }
        }

    }
}
