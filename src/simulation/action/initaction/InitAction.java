package simulation.action.initaction;

import simulation.map.WorldMap;

public class InitAction {

    public GameConfig readConfig(WorldMap map) {
        int mapX = map.getMapX();
        int mapY = map.getMapY();
        int wolfAttack = 100;
        int wolfSpeed = 10;
        int sheepPercent = 1;
        double grassPercent = 0.2;
        double rockPercent = 0.2;
        double treePercent = 0.2;

        return new GameConfig(mapX, mapY, wolfAttack, wolfSpeed, sheepPercent, grassPercent,
                rockPercent, treePercent);
    }
}