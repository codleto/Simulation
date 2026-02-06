package simulation.entity.animal;

import simulation.action.Grass;
import simulation.action.Move;
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
        BFS bfs = new BFS(map);
        Move move = new Move(map);
        Grass grass = new Grass(map);

        int i = 1;
        while (getSpeed() >= i) {

            if (grass.noFoodFor(this)) { // еды больше нет
                return;
            }

            bfs.searchForFood(this.getCoordinates(), Herbivore.class); // если только начали искать еду первый раз

            if (bfs.getPathToFood().isEmpty()) { // если все пусто
                return;
            }


            move.moveCreature(this.getCoordinates(), bfs.getPathToFood().getFirst(), this);
            i++;
        }
    }
}
