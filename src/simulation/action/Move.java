package simulation.action;

import simulation.entity.Entity;
import simulation.entity.animal.Creature;
import simulation.map.Coordinates;
import simulation.map.WorldMap;

public class Move {
    private final WorldMap map;

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
}
