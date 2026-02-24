package simulation.entity.animal;
import simulation.action.turnaction.FoodService;
import simulation.action.turnaction.MoveAction;
import simulation.algorithm.BFS;
import simulation.entity.Entity;
import simulation.entity.staticobject.Grass;
import simulation.map.WorldMap;

public class Herbivore extends Creature {
    private int stepIndex = 0;

    public Herbivore() {
        super(1);
        super.skin = "\uD83D\uDC11";
    }

    @Override
    public boolean eat(Entity entity){
        return entity instanceof Grass;
    }

    @Override
    public int getAttack() {
        return 0;
    }

    @Override
    public int getHp() {
        return this.hp;
    }

    @Override
    public void setHp(int damage){
        if(damage > this.hp){
            this.hp = 0;
        } else {
            this.hp -= damage;
        }
    }

    public void makeMove(WorldMap map) {
        final BFS bfs = new BFS(map);
        final MoveAction moveAction = new MoveAction(map);
        final FoodService foodService = new FoodService(map);

       while (true) {
            if (foodService.noFoodFor(this)) {
                break;
            }

            if(this.getCoordinates() == null){
                break;
            }

            if (stepIndex == 0) {
                bfs.searchForFood(this.getCoordinates(), Grass.class);
            }

            if (stepIndex >= bfs.getPathToFood().size() && stepIndex > 0) {
                stepIndex = 0;
                continue;
            }

            if (bfs.getPathToFood().isEmpty()) {
                break;
            }


            moveAction.moveCreature(this.getCoordinates(), bfs.getPathToFood().get(stepIndex), this);
            stepIndex++;
            break;
        }
    }
}
