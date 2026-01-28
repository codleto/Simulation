package simulation.action;

import simulation.Simulation;
import simulation.entity.Entity;
import simulation.entity.animal.Creature;
import simulation.map.Coordinates;

import static simulation.map.WorldMap.*;
import static simulation.map.WorldMap.removeEntity;

public class Move {
    public static void moveCreature(Coordinates from , Coordinates to, Creature mover){
        Creature movingCreature = (Creature) getMap(from);
        Entity target = getMap(to);

        if (target != null && mover.eat(target)) {
            if (target instanceof Creature){
                Creature targetCreature = (Creature)target;
                targetCreature.setHp(targetCreature.getHp() - movingCreature.getAttack());
                if(targetCreature.getHp() > 0){
                    return;
                }
            }
            removeEntity(to);
            target.coordinates = null;
            mover.satisfiedItsHunger();
        }
        if (target != null && !mover.eat(target)) {
            return;
        }

        removeEntity(from);
        mover.coordinates = to;
        addEntity(to, movingCreature);
        Simulation.moveCount++;
    }
}
