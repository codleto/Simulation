package simulation.action;

import simulation.entity.animal.Creature;

import static simulation.map.WorldMap.herbivoreArrayList;
import static simulation.map.WorldMap.predatorArrayList;

public class TuAc {
    public static void nextTurn(){
        for(Creature herbivore : herbivoreArrayList){ //todo: через  wildcard нужно исправить эти списки
            herbivore.makeMove();
        }
        predatorArrayList.getFirst().makeMove();
    }
}
