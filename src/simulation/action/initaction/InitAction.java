package simulation.action.initaction;

import static simulation.action.ConsoleInput.readNumber;
import static simulation.map.WorldMap.*;
import static simulation.action.initaction.GameConfig.*;

public class InitAction {
    private static final int SMALL = 1;
    private static final int MEDIUM = 2;
    private static final int LARGE = 3;

    public static void chooseMapSize() {
        while (true) {
            System.out.println(" Карта 4x4 - жми " + SMALL);
            System.out.println("Карта 5х5 = жми " + MEDIUM);
            System.out.println("Карта 10х10 - жми " + LARGE);

            int userChoice = readNumber();
            if (userChoice > 0 && userChoice < 4) {
                if (userChoice == SMALL) {
                    mapSize = 4;
                }
                if (userChoice == MEDIUM) {
                    mapSize = 5;
                }
                if (userChoice == LARGE) {
                    mapSize = 10;
                }
                break;
            } else {
                System.out.println("Введи 1, 2 или 3");
            }
        }
    }

    public static void configureSheepCount() {
        int maxSheepCount = 0;
        switch (mapSize) {
            case 4:
                System.out.println("Выбери от 1 до 4 овечек");
                maxSheepCount = 4;
                break;
            case 5:
                System.out.println("Выбери от 1 до 7 овечек");
                maxSheepCount = 7;
                break;
            case 10:
                System.out.println("Выбери от 1 до 10 овечек");
                maxSheepCount = 10;
                break;
            default:
        }
        while (true) {
            int userSheepCount = readNumber();
            if (userSheepCount > 0 && userSheepCount <= maxSheepCount) {
                sheepCount = userSheepCount;
                break;
            } else {
                System.out.println("Добавь от 1 до" + maxSheepCount);
            }
        }
    }

    public static void setWolfAttackPower() {
        while (true) {
            System.out.println("Введи силу атаки волка от 1 до 100");
            int attackPower = readNumber();
            if (attackPower > 0 && attackPower <= 100) {
                wolfAttack = attackPower;
                break;
            } else {
                System.out.println("Ты вышел из диапазона. Пожалуйста введи атаку от 1 до 100");
            }
        }
    }

    public static void setWolfSpeed(){
        while (true) {
            System.out.println("Введи скорость волка от 1 до 5 шагов");
            int userWolfSpeed = readNumber();
            if (userWolfSpeed > 0 && userWolfSpeed <= 5) {
                wolfSpeed = userWolfSpeed;
                break;
            } else {
                System.out.println("Ты вышел из диапазона. Пожалуйста введи скорость от 1 до 5 шагов");
            }
        }
    }
}
