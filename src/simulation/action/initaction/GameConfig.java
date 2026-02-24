package simulation.action.initaction;

public record GameConfig(int mapX, int mapY, int attack, int speed, int herbivoreCount,
                         double grassPercent, double rockPercent, double treePercent) {

    public GameConfig(int mapX, int mapY, int attack, int speed, int herbivoreCount,
                      double grassPercent, double rockPercent, double treePercent) {

        double sum = ((double)herbivoreCount / 100) + grassPercent + rockPercent + treePercent;
        if(sum > 0.9){
            throw new IllegalArgumentException("Сущности не могут занимать больше 90% карты");
        }

        if(mapX <= 0 || mapY <= 0){
             throw new IllegalArgumentException("Размер карты должен быть  больше 0");
        }

        if (herbivoreCount > 0 && attack > 0 && speed > 0 && grassPercent > 0 && rockPercent > 0 && treePercent > 0) {
            this.mapX = mapX;
            this.mapY = mapY;
            this.attack = attack;
            this.speed = speed;
            this.herbivoreCount = herbivoreCount;
            this.grassPercent = grassPercent;
            this.rockPercent = rockPercent;
            this.treePercent = treePercent;
        } else {
            this.mapX = mapX;
            this.mapY = mapY;
            this.attack = 1;
            this.speed = 1;
            this.herbivoreCount = 1;
            this.grassPercent = 0.01;
            this.rockPercent = 0.01;
            this.treePercent = 0.01;
        }
    }

    public int totalCells(){
        return mapX * mapY;
    }
}
