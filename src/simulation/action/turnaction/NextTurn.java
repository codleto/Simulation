package simulation.action.turnaction;

import simulation.entity.animal.Creature;

import static simulation.map.WorldMap.herbivoreArrayList;
import static simulation.map.WorldMap.predatorArrayList;

public class NextTurn {
    public static void nextTurn(){
        for(Creature herbivore : herbivoreArrayList){ //todo: через  wildcard нужно исправить эти списки
            herbivore.makeMove();
        }
        predatorArrayList.getFirst().makeMove();
    }
}
