package simulation.entity.animal;
import simulation.map.WorldMap;
import simulation.algorithm.BFS;
import simulation.entity.Entity;
import simulation.entity.staticobject.Food;

public class Herbivore extends Creature {
    private int stepIndex = 0;
    private int attack = 0;

    public Herbivore() {
        super(1);
        super.skin = "\uD83D\uDC11";
    }

    @Override
    public boolean eat(Entity entity){
        return entity instanceof Food;
    }

    @Override
    public int getHp() {
        return this.hp;
    }

    @Override
    public void setHp(int hp){
        if(hp >= 0 && hp <= 100){
            this.hp = hp;
        } else {
            this.hp = 0;
        }
    }

    @Override
    public int getAttack() {
        return this.attack;
    }

    public void makeMove() {
        BFS bfs = new BFS();
       while (true) {
            if (WorldMap.noFoodFor(this)) {
                break;
            }

            if(this.coordinates == null){
                break;
            }

            if (stepIndex == 0) {
                bfs.searchForFood(this.coordinates, Food.class);
            }

            if (stepIndex >= bfs.getPathToFood().size() && stepIndex > 0) {
                stepIndex = 0;
                continue;
            }

            if (bfs.getPathToFood().isEmpty()) {
                break;
            }

            WorldMap.moveCreature(this.coordinates, bfs.getPathToFood().get(stepIndex), this);
            stepIndex++;
            BFS.movesThisTurn.add(1);
            break;
        }
    }
}
