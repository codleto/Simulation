package Simulation;

import Simulation.action.TurnAction;

import static Simulation.Map.mapEat;
import static Simulation.Renderer.renderer;
import static Simulation.action.TurnAction.nextTurn;


import Simulation.action.TurnAction;
import Simulation.entity.Animal.Herbivore;
import Simulation.entity.Animal.Predator;
import Simulation.entity.StaticObject.Grass;
import Simulation.entity.StaticObject.Rock;
import Simulation.entity.StaticObject.Tree;

import java.util.Scanner;

import static Simulation.Map.*;
import static Simulation.Renderer.renderer;
import static Simulation.action.InitAction.*;
import static Simulation.action.TurnAction.nextTurn;

public class Simulation {
    public static int moveCount = 0;
    public static void mainn() {
        Scanner scanner = new Scanner(System.in);
        Console concolee = new Console();
        renderer();
        mapSize();
        setSheepCount();
        wolfAttackPower();
        wolfSpeed();
        initializeWorld();
        concolee.start();

        while (true) {
            int x = scanner.nextInt();
            if (x == 1) {
                concolee.pauseSimulation();
                System.out.println("Продолжить нажми - 2 Для выхода нажми - 3");
                continue;
            }
            if (x == 2) {
                concolee.continueSimulation();
                continue;
            }

            if (x == 3) {
                concolee.exitSimulation();
                break;
            }
        }

    }

}

class Console extends Thread {
    public volatile boolean runneble = true;
    public volatile boolean pause = false;

    public void run() {
        renderer();
        System.out.println("1 = пауза" + " 3 = выход");
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (runneble) {
            if(!pause) {
                if (mapEat()) {
                    break;
                }
                nextTurn();
                renderer();
                System.out.println("1 = пауза" + " 3 = выход"); // todo: Подумать как сделать 1пауза и
                                                                //  2 выход а когда пауза ( 1 продолить 2 выход)
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (!TurnAction.breakk.isEmpty()) {
                    TurnAction.breakk.clear();
                    continue;
                }
                break;

            }
        }
    }

    public void exitSimulation(){
        runneble = false;
    }

    public void pauseSimulation(){
        pause = true;
    }

    public void continueSimulation(){
        pause = false;
    }
}

