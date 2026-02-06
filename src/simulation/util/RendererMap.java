package simulation.util;

import simulation.map.WorldMap;

public class RendererMap {

    public void renderer(WorldMap map) {
        for (int gorizontal = map.getMapSize(); gorizontal >= 0; gorizontal--) {
            System.out.println();
            for (int vertical = map.getMapSize(); vertical >= 0; vertical--){
                if(map.getMap(vertical, gorizontal) == null){
                    System.out.print("\u001b[42m   ");
                } else {
                    System.out.print("\u001b[42m " + map.getEntity(vertical, gorizontal).getSkin());
                }System.out.print("\u001B[0m");

            }

        }
        System.out.println(" ");

    }
}
