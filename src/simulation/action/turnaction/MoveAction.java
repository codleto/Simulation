package simulation.action.turnaction;

import simulation.entity.Entity;
import simulation.entity.animal.Creature;
import simulation.map.Coordinates;
import simulation.map.WorldMap;


public class MoveAction {
    private final WorldMap map;
    private int moveCount = 0;

    public MoveAction(WorldMap map){
        this.map = map;
    }
    public void moveCreature(Coordinates from , Coordinates to, Creature mover){
        Creature movingCreature = (Creature) map.getMap(from);
        Entity target = map.getMap(to);

        if (target != null && mover.eat(target)) {
            if (target instanceof Creature targetCreature){
                targetCreature.setHp(movingCreature.getAttack());
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
        for(Creature creature : map.CreatureList){
            creature.makeMove(map);
            moveCount ++;
        }
    }

    public int getMoveCount(){
        return moveCount;
    }
}
