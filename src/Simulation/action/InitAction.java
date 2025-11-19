package Simulation.action;

import Simulation.entity.Animal.Herbivore;
import Simulation.entity.Animal.Predator;
import Simulation.entity.StaticObject.Grass;
import Simulation.entity.StaticObject.Rock;
import Simulation.entity.StaticObject.Tree;

import java.util.Scanner;

import static Simulation.Map.*;

public class InitAction {
    static Scanner scanner = new Scanner(System.in);
    static int  sheepCount;
    static int wolfAttack;
    static int wolfSpeed;

    // размер карты
    public static void mapSize() {
        while (true) {
            System.out.println("""
                    Карта 4x4 - жми 1
                    Карта 5х5 = жми 2
                    Карта 10х10 - жми 3""");
            int index = scanner.nextInt(); // TODO: добавить проверку ввода пользователя
            if (index > 0 && index < 4) {
                if (index == 1) {
                    mapSize = 4;
                }
                if (index == 2) {
                    mapSize = 5;
                }
                if (index == 3) {
                    mapSize = 10;
                }
                break;
            } else {
                System.out.println("Введи от 1, 2 или 3");
            }
        }
    }

    // выбрать сколько будет овечек
    public static void setSheepCount() {
        int maxSheep = 0;
        switch (mapSize) {
            case 4:
                System.out.println("Выбери от 1 до 4 овечки");
                maxSheep = 4;
                break;
            case 5:
                System.out.println("Выбери от 1 до 7 овечек");
                maxSheep = 7;
                break;
            case 10:
                System.out.println("Выбери от 1 до 10 овечек");
                maxSheep = 10;
                break;
            default:
        }
        while (true) {
            int count = scanner.nextInt();
            if (count > 0 && count <= maxSheep) {
                sheepCount = count;
                break;
            } else {
                System.out.println("Добавь от 1 до" + maxSheep);
            }
        }
    }

    // расставить объекты и существ перед стартом
    public static void initializeWorld() {
        int maxRock = 0;
        int maxGrass = 0;
        int maxTree = 0;

        if (mapSize == 4) {
            maxRock = 4;
            maxGrass = 6;
            maxTree = 4;
        }
        if (mapSize == 5) {
            maxRock = 6;
            maxGrass = 8;
            maxTree = 6;
        }
        if (mapSize == 10) {
            maxRock = 11;
            maxGrass = 15;
            maxTree = 11;
        }
        predatorArrayList.add(new Predator(wolfSpeed, wolfAttack));
        setEntity(predatorArrayList.getFirst());


        for (int i = 0; i < sheepCount; i++) {
            herbivoreArrayList.add(new Herbivore());
            setEntity(herbivoreArrayList.get(i));

        }

        for (int i = 0; i < maxRock; i++) {
            setEntity(new Rock());
        }

        for (int i = 0; i < maxGrass; i++) {
            setEntity(new Grass());
        }

        for (int i = 0; i < maxTree; i++) {
            setEntity(new Tree());
        }
    }

    // сила атаки волка
    public static void wolfAttackPower() {
        while (true) {
            System.out.println("Введи силу атаки волка от 1 до 100");
            int power = scanner.nextInt();
            if (power > 0 && power <= 100) {
                wolfAttack = power;
                break;
            } else {
                System.out.println("Ты вышел из диапазона");
            }
        }
    }
        // скорость волка и овечки
    public static void wolfSpeed(){
        while (true) {
            System.out.println("Введи скорость волка от 1 до 5");
            int speed = scanner.nextInt();
            if (speed > 0 && speed <= 5) {
                wolfSpeed = speed;
                break;
            } else {
                System.out.println("Ты вышел из диапазона");
            }
        }
    }
}
