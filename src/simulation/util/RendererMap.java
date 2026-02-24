package simulation.util;

import simulation.map.WorldMap;

public class RendererMap {

    public void renderer(WorldMap map) {
        for (int y = map.getMapY(); y >= 0; y--) {
            System.out.println();
            for (int x = map.getMapX(); x >= 0; x--){
                if(map.getMap(x, y) == null){
                    System.out.print("\u001b[42m   ");
                } else {
                    System.out.print("\u001b[42m " + map.getEntity(x, y).getSkin());
                }System.out.print("\u001B[0m");

            }

        }
        System.out.println(" ");

    }
}
