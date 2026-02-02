package simulation.action.initaction;

public class GameConfig {

    private final int sheepCount;
    private final int wolfAttack;
    private final int wolfSpeed;

    public GameConfig(int sheepCount, int wolfAttack, int wolfSpeed){
        if(sheepCount > 0 && wolfAttack > 0 && wolfSpeed > 0){
            this.sheepCount = sheepCount;
            this.wolfAttack = wolfAttack;
            this.wolfSpeed = wolfSpeed;
        } else {
            this.sheepCount = 1;
            this.wolfAttack = 1;
            this.wolfSpeed = 1;
        }
    }

    public int getSheepCount(){
        return sheepCount;
    }

    public int getWolfAttack(){
        return wolfAttack;
    }

    public int getWolfSpeed(){
        return wolfSpeed;
    }
}
