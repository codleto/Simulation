package simulation.action.turnaction;

import simulation.entity.Entity;
import simulation.entity.animal.Creature;
import simulation.map.Coordinates;
import simulation.map.WorldMap;

import static simulation.map.WorldMap.CreatureList;

public class Move {
    private final WorldMap map;
    private int moveCount = 0;

    public Move(WorldMap map){
        this.map = map;
    }
    public void moveCreature(Coordinates from , Coordinates to, Creature mover){
        Creature movingCreature = (Creature) map.getMap(from);
        Entity target = map.getMap(to);

        if (target != null && mover.eat(target)) {
            if (target instanceof Creature){
                Creature targetCreature = (Creature)target;
                targetCreature.setHp(targetCreature.getHp() - movingCreature.getAttack());
                if(targetCreature.getHp() > 0){
                    return;
                }
            }
            map.removeEntity(to);
            target.setCoordinates(null);
            mover.satisfiedItsHunger();
        }
        if (target != null && !mover.eat(target)) {
            return;
        }

        map.removeEntity(from);
        mover.setCoordinates(to);
        map.addEntity(to, movingCreature);
    }

    public void makeMoveCreatures(WorldMap map){
        for(Creature creature : CreatureList){
            creature.makeMove(map);
            moveCount ++;
        }
    }

    public int getMoveCount(){
        return moveCount;
    }
}
