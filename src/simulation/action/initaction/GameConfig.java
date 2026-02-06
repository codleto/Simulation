package simulation.action.initaction;

public record GameConfig(int mapSize, int herbivoreCount, int attack, int speed) {

    public GameConfig(int mapSize, int herbivoreCount, int attack, int speed) {
        if (herbivoreCount > 0 && attack > 0 && speed > 0) {
            this.mapSize = mapSize;
            this.herbivoreCount = herbivoreCount;
            this.attack = attack;
            this.speed = speed;
        } else {
            this.mapSize = 5;
            this.herbivoreCount = 1;
            this.attack = 1;
            this.speed = 1;
        }
    }
}
