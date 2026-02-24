package simulation.entity.animal;

import simulation.action.turnaction.FoodService;
import simulation.action.turnaction.MoveAction;
import simulation.algorithm.BFS;
import simulation.entity.Entity;
import simulation.map.WorldMap;

public class Predator extends Creature {
    private final int attack;

    public Predator(int speed, int attack) {
        super(speed);
        this.attack = attack;
        super.skin = "\uD83D\uDC3A";

    }

    public int getAttack(){
        return this.attack;
    }

    @Override
    public int getHp() {
        return this.hp;
    }

    @Override
    public void setHp(int hp){
        if(hp >= 0 && hp <= 100){
            this.hp = hp;
        }
    }

    @Override
    public boolean eat(Entity entity){
        return entity instanceof Herbivore;
    }

    public void makeMove(WorldMap map) {
        final BFS bfs = new BFS(map);
        final MoveAction moveAction = new MoveAction(map);
        final FoodService foodService = new FoodService(map);

        int i = 1;
        while (getSpeed() >= i) {

            if (foodService.noFoodFor(this)) {
                return;
            }

            bfs.searchForFood(this.getCoordinates(), Herbivore.class);

            if (bfs.getPathToFood().isEmpty()) {
                return;
            }


            moveAction.moveCreature(this.getCoordinates(), bfs.getPathToFood().getFirst(), this);
            i++;
        }
    }
}
