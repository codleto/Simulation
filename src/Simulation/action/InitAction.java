package Simulation.action;

import Simulation.Entity.Animal.Herbivore;
import Simulation.Entity.Animal.Predator;
import Simulation.Entity.StaticObject.Food;
import Simulation.Entity.StaticObject.Rock;
import Simulation.Entity.StaticObject.Tree;

import java.util.Scanner;

import static Simulation.World.*;

public class InitAction {
    private static final int SMALL = 1;
    private static final int MEDIUM = 2;
    private static final int LARGE = 3;

    private static Scanner scanner = new Scanner(System.in);
    private static int sheepCount;
    private static int wolfAttack;
    private static int wolfSpeed;

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

    public static void initializeWorld() {
        int maxRockCount = 0;
        int maxGrassCount = 0;
        int maxTreeCount = 0;

        if (mapSize == 4) {
            maxRockCount = 4;
            maxGrassCount = 6;
            maxTreeCount = 4;
        }
        if (mapSize == 5) {
            maxRockCount = 6;
            maxGrassCount = 8;
            maxTreeCount = 6;
        }
        if (mapSize == 10) {
            maxRockCount = 11;
            maxGrassCount = 15;
            maxTreeCount = 11;
        }
        predatorArrayList.add(new Predator(wolfSpeed, wolfAttack));
        spawnEntity(predatorArrayList.getFirst());


        for (int i = 0; i < sheepCount; i++) {
            herbivoreArrayList.add(new Herbivore());
            spawnEntity(herbivoreArrayList.get(i));

        }

        for (int i = 0; i < maxRockCount; i++) {
            spawnEntity(new Rock());
        }

        for (int i = 0; i < maxGrassCount; i++) {
            spawnEntity(new Food());
        }

        for (int i = 0; i < maxTreeCount; i++) {
            spawnEntity(new Tree());
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
            int wolfSpeed = readNumber();
            if (wolfSpeed > 0 && wolfSpeed <= 5) {
                InitAction.wolfSpeed = wolfSpeed;
                break;
            } else {
                System.out.println("Ты вышел из диапазона. Пожалуйста введи скорость от 1 до 5 шагов");
            }
        }
    }

    public static int readNumber(){
        while (true){
            String input = scanner.nextLine().trim();
            if(input.matches("\\d+")){
                return Integer.parseInt(input);
            }
            System.out.println("Введи число без символов и букв");
        }
    }
}
