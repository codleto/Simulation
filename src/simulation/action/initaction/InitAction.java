package simulation.action.initaction;

import simulation.map.WorldMap;

public class InitAction {

    public GameConfig readConfig(WorldMap map) {
        int mapVertical = map.getMapVertical();
        int mapHorizontal = map.getMapHorizontal();
        int wolfAttack = 1;
        int wolfSpeed = 2;
        int sheepPercent = 1;
        double grassPercent = 0.2;
        double rockPercent = 0.2;
        double treePercent = 0.2;

        return new GameConfig(mapVertical, mapHorizontal, wolfAttack, wolfSpeed, sheepPercent, grassPercent,
                rockPercent, treePercent);
    }
}