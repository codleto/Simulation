package simulation.action.turnaction;

import simulation.entity.animal.Creature;
import simulation.map.WorldMap;
import static simulation.map.WorldMap.CreatureList;

public class NextTurn {
    public void nextTurn(WorldMap map){
        for(Creature creature : CreatureList){
            creature.makeMove(map);
        }
    }
}
