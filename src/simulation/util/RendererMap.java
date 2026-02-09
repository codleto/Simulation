package simulation.util;

import simulation.map.WorldMap;

public class RendererMap {

    public void renderer(WorldMap map) {
        for (int horizontal = map.getMapHorizontal(); horizontal >= 0; horizontal--) {
            System.out.println();
            for (int vertical = map.getMapVertical(); vertical >= 0; vertical--){
                if(map.getMap(vertical, horizontal) == null){
                    System.out.print("\u001b[42m   ");
                } else {
                    System.out.print("\u001b[42m " + map.getEntity(vertical, horizontal).getSkin());
                }System.out.print("\u001B[0m");

            }

        }
        System.out.println(" ");

    }
}
