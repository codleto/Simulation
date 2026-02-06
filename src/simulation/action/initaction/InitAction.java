package simulation.action.initaction;

public class InitAction {
    private static final int SMALL = 4;
    private static final int MEDIUM = 5;
    private static final int LARGE = 10;
    private int mapSize;

    Input input = new Input();

   public GameConfig readConfig() {
       int mapSize = chooseMapSize();
       int sheepCount = configureSheepCount();
       int wolfAttack = setWolfAttackPower();
       int wolfSpeed = setWolfSpeed();

       return new GameConfig(mapSize, sheepCount, wolfAttack, wolfSpeed);
   }

    public int chooseMapSize() {
        while (true) {

            System.out.println(" Карта 4x4 - жми " + 1);
            System.out.println("Карта 5х5 = жми " + 2);
            System.out.println("Карта 10х10 - жми " + 3);

            int userChoice = input.readNumber();
            if (userChoice > 0 && userChoice < 4) {
                if (userChoice == 1) {
                    mapSize = SMALL;
                    return SMALL;
                }
                else if (userChoice == 2) {
                    mapSize = MEDIUM;
                    return MEDIUM;
                }
                else{
                    mapSize = LARGE;
                    return LARGE;
                }
            } else {
                System.out.println("Введи 1, 2 или 3");
            }
        }
    }

    private int configureSheepCount() {
        int maxSheepCount = 0;
        switch (mapSize) {
            case SMALL:
                System.out.println("Выбери от 1 до 4 овечек");
                maxSheepCount = 4;
                break;
            case MEDIUM:
                System.out.println("Выбери от 1 до 7 овечек");
                maxSheepCount = 7;
                break;
            case LARGE:
                System.out.println("Выбери от 1 до 10 овечек");
                maxSheepCount = 10;
                break;
            default:
        }
        while (true) {
            int userSheepCount = input.readNumber();
            if (userSheepCount > 0 && userSheepCount <= maxSheepCount) {
                    return userSheepCount;
            } else {
                System.out.println("Добавь от 1 до" + maxSheepCount);
            }
        }
    }

    private int setWolfAttackPower() {
        while (true) {
            System.out.println("Введи силу атаки волка от 1 до 100");
            int attackPower = input.readNumber();
            if (attackPower > 0 && attackPower <= 100) {
                return attackPower;
            } else {
                System.out.println("Ты вышел из диапазона. Пожалуйста введи атаку от 1 до 100");
            }
        }
    }

    private int setWolfSpeed(){
        while (true) {
            System.out.println("Введи скорость волка от 1 до 5 шагов");
            int userWolfSpeed = input.readNumber();
            if (userWolfSpeed > 0 && userWolfSpeed <= 5) {
                return userWolfSpeed;
            } else {
                System.out.println("Ты вышел из диапазона. Пожалуйста введи скорость от 1 до 5 шагов");
            }
        }
    }


}
